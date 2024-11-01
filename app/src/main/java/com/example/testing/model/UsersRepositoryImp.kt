package com.example.testing.model

import com.example.data.UsersDB
import com.example.data.toUsersEntity
import com.example.testing.network.CheckConnection
import com.example.testing.network.UsersApi
import com.example.testing.network.dto.Data
import com.example.testing.network.dto.UsersDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UsersRepositoryImp @Inject constructor(
    private val usersApi: UsersApi,
    private val dao: UsersDB
) : UsersRepository {
    override suspend fun getUsers(): Flow<CheckConnection<List<Data>>> {
        return flow {
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
            emit(CheckConnection.Success(data = users.data))
            dao.dao().insertUsers(users.data.map { it.toUsersEntity() })
            dao.dao().getUsers()

        }
    }
}