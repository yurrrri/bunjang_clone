package leeyuri.mobile.bunjang_mok_aos.src.login

import leeyuri.mobile.bunjang_mok_aos.src.login.models.*
import retrofit2.Call
import retrofit2.http.*

interface LoginRetrofitInterface {
//   본인인증
    @POST("/auth")
    fun postCertiNum(@Body params: PostCertiNumRequest) : Call<CertiResponse>

//    회원가입
    @POST("/sign")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>

    //   로그인
    @POST("/sign")
    fun postLogin(@Body params: PostLoginRequest): Call<LoginResponse>
}
