package com.example.nonameapp.data.source.network

import com.example.nonameapp.request.ChangePasswordRequest
import com.example.nonameapp.request.CreatePostRequest
import com.example.nonameapp.request.ForgotPasswordRequest
import com.example.nonameapp.request.LoginRequest
import com.example.nonameapp.request.RegisterRequest
import com.example.nonameapp.request.RequestChangePassword
import com.example.nonameapp.request.UpdateProfileRequest
import com.example.nonameapp.response.ApiResponse
import com.example.nonameapp.response.CreatePostResponse
import com.example.nonameapp.response.LoginResponse
import com.example.nonameapp.response.QuestionsResponse
import com.example.nonameapp.response.QuestionsTResponse
import com.example.nonameapp.response.RegisterResponse
import com.example.nonameapp.response.SearchResponse
import com.example.nonameapp.response.SubjectResponse
import com.example.nonameapp.response.TestResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("/api/v1/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): ApiResponse<LoginResponse>

    @POST("/api/v1/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): ApiResponse<Any>

    @POST("/api/v1/auth/forgot-password")
    suspend fun getOtp(@Body request: ForgotPasswordRequest): ApiResponse<Any>

    @POST("/api/v1/auth/verify-otp")
    suspend fun verifyOtp(@Body request: ForgotPasswordRequest): ApiResponse<Any>

    @POST("/api/v1/auth/reset-password")
    suspend fun changePassword(@Body changePasswordRequest: RequestChangePassword): ApiResponse<Any>

    @GET("/api/v1/subjects/")
    suspend fun getListSubject(
        @Header("Authorization") accessToken: String
    ): ApiResponse<SubjectResponse>

    @GET("/api/v1/questions")
    suspend fun getQuestionsBySubjectIdTest(
        @Header("Authorization") accessToken: String,
        @Header("_id") subjectId: String
    ): ApiResponse<QuestionsResponse>

    @GET("/api/v1/tests/subject/{id}")
    suspend fun getTestBySubjectId(
        @Path("id") subjectId: String
    ): ApiResponse<TestResponse>

    @GET("/api/v1/tests/{id}")
    suspend fun getTestById(@Path("id") testId: String): ApiResponse<QuestionsTResponse>

    @GET("/api/v1/questions/subject/{id}")
    suspend fun getQuestionsBySubjectId(
        @Header("Authorization") accessToken: String,
        @Path("id") subjectId: String
    ): ApiResponse<QuestionsResponse>

    @GET("/api/v1/questions")
    suspend fun searchQuestions(
        @Header("Authorization") accessToken: String,
        @Query("search") query: String
    ): ApiResponse<SearchResponse>

    @PUT("/api/v1/auth/change-profile")
    suspend fun updateProfile(
        @Header("Authorization") accessToken: String,
        @Body updateProfileRequest: UpdateProfileRequest
    ): ApiResponse<RegisterResponse>

    @PUT("/api/v1/auth/change-password")
    suspend fun changePassword(
        @Header("Authorization") accessToken: String,
        @Body changePasswordRequest: ChangePasswordRequest
    ): ApiResponse<Any>

    @PUT("/api/v1/posts")
    suspend fun createPost(
        @Header("Authorization") accessToken: String,
        @Body createPostRequest: CreatePostRequest
    ): ApiResponse<CreatePostResponse>
}
