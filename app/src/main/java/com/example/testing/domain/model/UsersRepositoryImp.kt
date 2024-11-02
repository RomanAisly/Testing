package com.example.testing.domain.model

import com.example.testing.data.local.UsersDB
import com.example.testing.data.remote.CheckConnection
import com.example.testing.data.remote.UsersApi
import com.example.testing.data.toUsers
import com.example.testing.data.toUsersEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

import javax.inject.Inject

class UsersRepositoryImp @Inject constructor(
    private val usersApi: UsersApi,
    private val usersDb: UsersDB
) : UsersRepository {
    override suspend fun getUsers(
        page: Int,
        forceFetch: Boolean
    ): Flow<CheckConnection<List<Users>>> {
        return flow {
            emit(CheckConnection.Loading(true))
            val localUsers = usersDb.dao.getUsers()
            val shouldFetch = localUsers.isNotEmpty() && !forceFetch
            if (shouldFetch) {
                emit(CheckConnection.Success(data = localUsers.map { usersEntity ->
                    usersEntity.toUsers()
                }))
                emit(CheckConnection.Loading(false))
                return@flow
            }
            val users = try {
                usersApi.getUsers(1)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(CheckConnection.Error(message = "Network error: ${e.message}"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(CheckConnection.Error(message = "HTTP error: ${e.localizedMessage}"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(CheckConnection.Error(message = "Unexpected error: ${e.message}"))
                return@flow
            }
            val usersEntity = users.data.let {
                it.map { users ->
                    users.toUsersEntity()
                }
            }
            usersDb.dao.UpsertUsers(usersEntity)

            emit(CheckConnection.Success(data = usersEntity.map { it.toUsers() }))
            emit(CheckConnection.Loading(false))

        }
    }
}