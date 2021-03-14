package leeyuri.mobile.bunjang_mok_aos.src.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityLoginInsertCertiNumBinding
import leeyuri.mobile.bunjang_mok_aos.src.login.models.*
import leeyuri.mobile.bunjang_mok_aos.src.main.MainActivity
import leeyuri.mobile.bunjang_mok_aos.util.LoginNotCorrectedDialog

class LoginInsertCertiNumActivity : BaseActivity<ActivityLoginInsertCertiNumBinding>(ActivityLoginInsertCertiNumBinding::inflate), LoginView {

    private val editor: SharedPreferences.Editor = ApplicationClass.sf.edit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        - 를 붙여서 출력하기 위함.
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val formattedstring = phoneNumber?.substring(0, 3) + "-" + phoneNumber?.substring(3, 7) + "-" + phoneNumber?.substring(7, 11)

        binding.loginCertiTvPhone.text = formattedstring

//        인증번호 발송 API POST
        val postCertiNumRequest =
            PostCertiNumRequest(phoneNumber = phoneNumber!!)
        showLoadingDialog(this)
        LoginService(this).tryPostCertiNum(postCertiNumRequest)
    }

//    인증번호가 오는것이 성공했으면, 회원인지 비회원인지 매칭해서 다른 화면을 띄워줘야함
    override fun onPostCertiNumSuccess(response: CertiResponse) {
        dismissLoadingDialog()

        binding.loginComplete.setOnClickListener {
//           인증번호가 맞게 입력된 경우 -> 회원가입 혹은 로그인 진행
            if (binding.loginEdtCertinum.text.toString() == response.verifycode.toString()) {
//                회원일 경우
                if(response.member=="회원") {
                    showLoadingDialog(this)
                    //    로그인 post
                    val postLoginRequest = PostLoginRequest(
                            phoneNumber = intent.getStringExtra("phoneNumber")!!,
                            name = intent.getStringExtra("name")!!,
                            birth = intent.getStringExtra("birth")!!,
                            gender = intent.getCharExtra("gender", '1'),
                            telocodeName = intent.getStringExtra("telocodeName")!!,
                            isAgreement = intent.getCharExtra("isAgreement", 'Y'),
                            member = "회원"
                    )
                    LoginService(this).tryLogin(postLoginRequest)
                }
//                비회원일 경우 즉 회원가입할때
                else if (response.member=="비회원"){
                    showLoadingDialog(this)
                    //    회원가입 post
                    val postSignUpRequest = PostSignUpRequest(
                            phoneNumber = intent.getStringExtra("phoneNumber")!!,
                            name = intent.getStringExtra("name")!!,
                            birth = intent.getStringExtra("birth")!!,
                            gender = intent.getCharExtra("gender", '1'),
                            telocodeName = intent.getStringExtra("telocodeName")!!,
                            isAgreement = intent.getCharExtra("isAgreement", 'Y'),
                            member = "비회원"
                    )
                    LoginService(this).tryPostSignUp(postSignUpRequest)
                }
            } else {
//               잘못입력햇다는 대화상자 띄우기
                val dlg = LoginNotCorrectedDialog(this)
                dlg.start()
            }
        }
    }

    override fun onPostCertiNumFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

//    회원가입 성공 -> 회원가입화면으로
    override fun onPostSignUpSuccess(response: SignUpResponse) {

//    토큰값 넣기
        editor.putString(ApplicationClass.X_ACCESS_TOKEN, response.result.jwt)
        editor.apply()

//    userIdx 넣기
        editor.putInt("userIdx", response.result.tokenInfo.userIdx)
        editor.apply()

        dismissLoadingDialog()

    val tosignupintent = Intent(this, LoginRegisterComplete::class.java)
        tosignupintent.putExtra("name", intent.getStringExtra("name")!!)
        tosignupintent.putExtra("userIdx", response.result.tokenInfo.userIdx)
        tosignupintent.putExtra("phoneNumber", response.result.tokenInfo.phoneNumber)
        tosignupintent.putExtra("jwt", response.result.jwt)

        startActivity(tosignupintent)
        finish()
    }

    override fun onPostSignUpFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

//    로그인 성공 -> 메인화면으로
    override fun onPostLoginSuccess(response: LoginResponse) {

    //    토큰값 넣기
        editor.putString(ApplicationClass.X_ACCESS_TOKEN, response.result.jwt)
        editor.apply()

    //    userIdx 넣기
        editor.putInt("userIdx", response.result.tokenInfo.userIdx)
        editor.apply()

        dismissLoadingDialog()

        val tomainintent = Intent(this, MainActivity::class.java)
        tomainintent.putExtra("name", intent.getStringExtra("name")!!)
        tomainintent.putExtra("userIdx", response.result.tokenInfo.userIdx)
        tomainintent.putExtra("phoneNumber", response.result.tokenInfo.phoneNumber)
        tomainintent.putExtra("jwt", response.result.jwt)

        startActivity(tomainintent)
        finish()
    }

    override fun onPostLoginFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}