package com.example.testing.model

import com.example.testing.network.CheckConnection
import com.example.testing.network.UsersApi
import com.example.testing.network.dto.UsersDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class UsersRepositoryImp(private val usersApi: UsersApi) : UsersRepository {
    override suspend fun getUsers(): Flow<CheckConnection<List<UsersDTO>>> {
        return flow {
            val users = try {
                usersApi.getUsers(2)
            } catch (e: IOException) {
                emit(CheckConnection.Error(message = "Network error: ${e.message}"))
                return@flow
            } catch (e: HttpException) {
                emit(CheckConnection.Error(message = "HTTP error: ${e.localizedMessage}"))
                return@flow
            } catch (e: Exception) {
                emit(CheckConnection.Error(message = "Unexpected error: ${e.message}"))
                return@flow
            }
            emit(CheckConnection.Success(users))

        }
    }
}