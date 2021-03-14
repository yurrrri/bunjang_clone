package leeyuri.mobile.bunjang_mok_aos.src.login

import android.content.Intent
import android.os.Bundle
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityLoginRegisterCompleteBinding
import leeyuri.mobile.bunjang_mok_aos.src.main.MainActivity

class LoginRegisterComplete : BaseActivity<ActivityLoginRegisterCompleteBinding>(
    ActivityLoginRegisterCompleteBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.loginCompleteTvUserIdx.text = intent.getIntExtra("userIdx", 0).toString()
        binding.loginCompleteBtnOk.setOnClickListener {
            val toMainIntent = Intent(this, MainActivity::class.java)

            toMainIntent.putExtra("name", intent.getStringExtra("name"))
            toMainIntent.putExtra("userIdx",  intent.getIntExtra("userIdx", 0))
            toMainIntent.putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))

            startActivity(toMainIntent)
            finish()
        }
    }
}