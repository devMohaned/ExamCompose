package com.dev.exam.feature_auth.data.repo

import android.util.Log
import com.dev.exam.core.data.local.SharedPrefs
import com.dev.exam.core.di.DataStoreManager
import com.dev.exam.core.util.Resource
import com.dev.exam.feature_auth.data.remote.AuthApi
import com.dev.exam.feature_auth.domain.model.LoginRequest
import com.dev.exam.feature_auth.domain.model.LoginResponse
import com.dev.exam.feature_auth.domain.model.RegisterRequest
import com.dev.exam.feature_auth.domain.model.RegisterResponse
import com.dev.exam.feature_auth.domain.repo.AuthRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class AuthRepoImpl(private val authApi: AuthApi, private val sharedPrefs: SharedPrefs) :
    AuthRepo {
    override fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponse, LoginResponse>> =
        flow {
            emit(Resource.Loading())
            val result = authApi.login(loginRequest)
            result.onSuccess { loginResponseDTO ->
                val loginResponse = loginResponseDTO.toLoginResponse()
                emit(Resource.Success(loginResponse))
                sharedPrefs.saveToken(loginResponse.token)
            }.onFailure {
                emit(
                    Resource.Error(
                        data = null,
                        message = "That's an error ${it.localizedMessage} &&&" + it.message
                    )
                )
            }
        }

    override fun register(registerRequest: RegisterRequest): Flow<Resource<RegisterResponse, RegisterResponse>> =
        flow {
            emit(Resource.Loading())
            val result = authApi.register(registerRequest)
            result.onSuccess { registerResponseDTO ->
                val registerResponse = registerResponseDTO.toRegisterResponse()
                emit(Resource.Success(registerResponse))
                sharedPrefs.saveToken(registerResponse.token)
            }.onFailure {
                emit(
                    Resource.Error(
                        data = null,
                        message = "That's an error ${it.localizedMessage} &&&" + it.message
                    )
                )
            }
        }


}