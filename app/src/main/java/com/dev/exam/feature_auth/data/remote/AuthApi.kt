package com.dev.exam.feature_auth.data.remote

import com.dev.exam.feature_auth.data.remote.dto.LoginRequestDTO
import com.dev.exam.feature_auth.data.remote.dto.LoginResponseDTO
import com.dev.exam.feature_auth.data.remote.dto.RegisterRequestDTO
import com.dev.exam.feature_auth.data.remote.dto.RegisterResponseDTO
import com.dev.exam.feature_auth.domain.model.LoginRequest
import com.dev.exam.feature_auth.domain.model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {

    companion object {
/*
value: String = "{" +
            "\"email\": \"a@gmail.com\"," +
            "\"password\": \"password\"" +
            "}"
 */


        const val LOGIN_PATH: String = "api/Authenticate/login"
        const val REGISTER_PATH: String = "api/Authenticate/register"
        const val FORGET_PASSWORD_PATH: String = "register"
        const val VERIFY_CODE_PATH: String = "register"
        const val HOST:String = "mohamedafifi1993-001-site4.atempurl.com/"
        const val EMULATOR_LOCAL_HOST:String = "10.0.2.2"
        const val REAL_DEVICE_LOCAL_HOST:String = "192.168.1.102"
        const val PORT:String = "7129"
        const val BASE_URL: String = "http://$HOST/"
    }

    @POST(LOGIN_PATH)
    suspend fun login(@Body loginRequest: LoginRequest) : LoginResponseDTO

    @POST(REGISTER_PATH)
    suspend fun register(@Body registerRequest: RegisterRequest) : RegisterResponseDTO

    @POST(FORGET_PASSWORD_PATH)
    fun forgetPassword()

    @POST(VERIFY_CODE_PATH)
    fun verifyCode()

}