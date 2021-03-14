package leeyuri.mobile.bunjang_mok_aos.src.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.RequiresApi
import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivitySplashBinding
import leeyuri.mobile.bunjang_mok_aos.src.login.LoginActivity
import leeyuri.mobile.bunjang_mok_aos.src.main.MainActivity
import leeyuri.mobile.bunjang_mok_aos.util.LoginPermissionDialog

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sf = ApplicationClass.sf

//      앱 맨 처음 실행시 권한 허용 요청 대화상자
        if (!sf.getBoolean("permission_btn_ok", false)) {
            val dlg = LoginPermissionDialog(this)
//            1초정도 뒤에 대화상자 띄우기
            Handler(Looper.getMainLooper()).postDelayed({
                dlg.start()
            }, 1000)
        }
//        권한 허용 대화상자의 확인버튼을 누른상태면 이후에 계속 스플래시 화면으로 이동하도록
        else{
 //       스플래시 화면 1초정도 보여주고 넘어가기
//     만약 로그인을 한 상태면 -> 바로 메인으로 이동 (자동로그인!!!!!!!!!)
            if (sf.getString(ApplicationClass.X_ACCESS_TOKEN, null)!=null){
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }, 1000)
            }
            else {
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }, 1000)
            }
        }

//       상태바를 없애기위함
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }
        else {
            @Suppress("DEPRECATION")
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }
}