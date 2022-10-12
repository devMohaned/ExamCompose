package com.dev.exam.feature_auth.data.repo

import android.util.Log
import com.dev.exam.core.di.DataStoreManager
import com.dev.exam.core.util.Resource
import com.dev.exam.feature_auth.data.remote.AuthApi
import com.dev.exam.feature_auth.data.remote.dto.LoginResponseDTO
import com.dev.exam.feature_auth.data.remote.dto.RegisterResponseDTO
import com.dev.exam.feature_auth.domain.model.LoginRequest
import com.dev.exam.feature_auth.domain.model.LoginResponse
import com.dev.exam.feature_auth.domain.model.RegisterRequest
import com.dev.exam.feature_auth.domain.model.RegisterResponse
import com.dev.exam.feature_auth.domain.repo.AuthRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class AuthRepoImpl(private val authApi: AuthApi, private val dataStoreManager: DataStoreManager) : AuthRepo {
    override fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> = flow {
        emit(Resource.Loading())
        try{
            val loginResponse: LoginResponse = authApi.login(loginRequest).toLoginResponse()
            emit(Resource.Success(loginResponse))
            dataStoreManager.setToken(loginResponse.token)
        }catch (e: HttpException){
        emit(Resource.Error(message = "HTTP Exception, Something wrong has occurred", data = null))
            Log.d("Error Exception", "${e.message}")
        }catch (e: IOException){
            emit(Resource.Error(message = "IO Exception, Check your internet connection", data = null))
            Log.d("Error Exception", "${e.message}")
        }catch (e: Exception){
            emit(Resource.Error(message = "Exception ${e.message}, Couldn't Identify the error", data = null))
            Log.d("Error Exception", "${e.message}")
        }
    }

    override fun register(registerRequest: RegisterRequest): Flow<Resource<RegisterResponse>> = flow {
        emit(Resource.Loading())
        try{
            val registerResponse: RegisterResponse = authApi.register(registerRequest).toRegisterResponse()
            emit(Resource.Success(registerResponse))
            // TODO: Add Token Locally to Data Store
        }catch (e: HttpException){
            emit(Resource.Error(message = "HTTP Exception, Something wrong has occurred", data = null))
            Log.d("Error Exception", "${e.message}")
        }catch (e: IOException){
            emit(Resource.Error(message = "IO Exception, Check your internet connection", data = null))
            Log.d("Error Exception", "${e.message}")
        }catch (e: Exception){
            emit(Resource.Error(message = "Exception, Couldn't Identify the error", data = null))
            Log.d("Error Exception", "${e.message}")
        }
    }


}