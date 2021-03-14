package leeyuri.mobile.bunjang_mok_aos.util

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.Window
import androidx.core.content.ContextCompat.startActivity
import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.databinding.DialogLoginPermissionBinding
import leeyuri.mobile.bunjang_mok_aos.src.login.LoginActivity

class LoginPermissionDialog(context : Context) {
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감
    val binding = DialogLoginPermissionBinding.inflate(LayoutInflater.from(context))
    private val thiscontext = context
    private val editor = ApplicationClass.sf.edit()

    fun start() {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

//      권한 요청 팝업창만 띄워줌
        binding.permissionBtnOk.setOnClickListener {
//           shared preferences에 저장해두고 확인버튼을 눌렀으면 다음부턴 이 대화상자가 안뜨게
//           sharedpreferences에 실제 쓰기를 하려면 apply를 꼭 할것
            editor.putBoolean("permission_btn_ok", true)
            editor.apply()
            //  dialog 종료
            dlg.dismiss()
//            로그인 화면으로 이동
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(thiscontext, Intent(thiscontext, LoginActivity::class.java), null)

            }, 1000)
        }

        dlg.show()
    }
}