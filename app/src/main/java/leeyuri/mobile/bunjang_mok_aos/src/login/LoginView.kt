package leeyuri.mobile.bunjang_mok_aos.src.login

import leeyuri.mobile.bunjang_mok_aos.src.login.models.CertiResponse
import leeyuri.mobile.bunjang_mok_aos.src.login.models.LoginResponse
import leeyuri.mobile.bunjang_mok_aos.src.login.models.SignUpResponse

interface LoginView {

    fun onPostCertiNumSuccess(response: CertiResponse)

    fun onPostCertiNumFailure(message: String)

    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)

    fun onPostLoginSuccess(response: LoginResponse)

    fun onPostLoginFailure(message: String)
}