package leeyuri.mobile.bunjang_mok_aos.src.login

import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.src.login.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val view: LoginView) {

    fun tryPostCertiNum(postCertiNumRequest: PostCertiNumRequest){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postCertiNum(postCertiNumRequest).enqueue(object : Callback<CertiResponse>{
            override fun onResponse(call: Call<CertiResponse>, response: Response<CertiResponse>) {
                view.onPostCertiNumSuccess(response.body() as CertiResponse)
            }

            override fun onFailure(call: Call<CertiResponse>, t: Throwable) {
                view.onPostCertiNumFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostSignUp(postSignUpRequest: PostSignUpRequest){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postSignUp(postSignUpRequest).enqueue(object : Callback<SignUpResponse>{
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                view.onPostSignUpSuccess(response.body() as SignUpResponse)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                view.onPostSignUpFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryLogin(postLoginRequest: PostLoginRequest){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postLogin(postLoginRequest).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                view.onPostLoginSuccess(response.body() as LoginResponse)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                view.onPostLoginFailure(t.message ?: "통신 오류")
            }
        })
    }
}
