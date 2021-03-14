package leeyuri.mobile.bunjang_mok_aos.src.login


import android.os.Bundle
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityLoginInsertInfoBinding
import leeyuri.mobile.bunjang_mok_aos.util.LoginAgreeBottomSheet

class LoginInsertInfoActivity : BaseActivity<ActivityLoginInsertInfoBinding> (
    ActivityLoginInsertInfoBinding::inflate) {

    //Activity  -> Fragment -> Acitivity
    private val bottomsheetdialog = LoginAgreeBottomSheet()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = Bundle()

        binding.loginEdtName2.setText(intent.getStringExtra("user_name"))

        binding.loginInfoBtnNext.setOnClickListener{
//      다음버튼 누르면 약관동의 대화상자 -> Bottom Sheet Dialog

//           대화상자 외의 다른 영역을 클릭 못하게
            bottomsheetdialog.isCancelable = false

            showLoadingDialog(this)
            dismissLoadingDialog()

            bundle.putString("phoneNumber",  binding.loginEdtPhone.text.toString())
            bundle.putString("name",binding.loginEdtName2.text.toString())
            bundle.putString("birth", binding.loginEdtReginumFirst.text.toString())
            bundle.putChar("gender", binding.loginEdtReginumSecond.text.toString()[0])
            bundle.putString("telocodeName", binding.loginEdtCarrier.text.toString())
            bundle.putChar("isAgreement", 'Y')

            bottomsheetdialog.arguments = bundle
            bottomsheetdialog.show(supportFragmentManager, bottomsheetdialog.tag)
        }
    }
}