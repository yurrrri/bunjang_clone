package leeyuri.mobile.bunjang_mok_aos.util

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.src.login.LoginInsertNameActivity

class LoginBottomSheet() : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.login_bottomsheetdialog_anotherlogin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.findViewById<ImageButton>(R.id.login_btn_exit)?.setOnClickListener {
            dismiss()
        }
        view?.findViewById<ImageButton>(R.id.login_btn_certi)?.setOnClickListener {
            startActivity(Intent(activity, LoginInsertNameActivity::class.java))
        }
    }
}