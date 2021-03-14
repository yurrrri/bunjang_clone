package leeyuri.mobile.bunjang_mok_aos.src.login

import android.content.Intent
import android.os.Bundle
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityLoginInsertNameBinding

class LoginInsertNameActivity : BaseActivity<ActivityLoginInsertNameBinding>(ActivityLoginInsertNameBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.loginNameBtnNext.setOnClickListener {
            val intent = Intent(this, LoginInsertInfoActivity::class.java)
            intent.putExtra("user_name", binding.loginEdtName.text.toString())
            showLoadingDialog(this)
            dismissLoadingDialog()
            startActivity(intent)
            finish()
        }
    }
}