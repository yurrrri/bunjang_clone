package leeyuri.mobile.bunjang_mok_aos.src.main.mypage

import android.os.Bundle
import android.view.MenuItem
import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityMyPageSettingGenderBinding
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models.*
import kotlin.properties.Delegates

class MyPageSettingGenderActivity : BaseActivity<ActivityMyPageSettingGenderBinding>(
    ActivityMyPageSettingGenderBinding::inflate), MyPageView{

    private var genderChar by Delegates.notNull<Char>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //        툴바 설정
        setSupportActionBar(binding.mypageToolbarSetting3)
        supportActionBar?.title = null
        binding.button4.setOnClickListener { finish() }

        if (intent.getStringExtra("gender")=="남자"){
            binding.mypageChbMan.isChecked = true
            binding.mypageChbWoman.isChecked = false
            genderChar = 'M'
        }
        else{
            binding.mypageChbWoman.isChecked = true
            binding.mypageChbMan.isChecked = false
            genderChar = 'W'
        }

        binding.button4.setOnClickListener {
            if (binding.mypageChbMan.isChecked) {
                genderChar = 'M'
            } else if (binding.mypageChbWoman.isChecked)
                genderChar = 'W'

            showLoadingDialog(this)

            var patchRequest = PostPatchProfileRequest(
                gender = genderChar,
                birth = null,
                phoneNumber = null,
                isPhoneRelease = null,
                email = null
            )
            MyPageService(this).tryPatchProfile(ApplicationClass.sf.getInt("userIdx", 0), patchRequest)
        }
    }

    override fun onGetProfileSuccess(response: ProfileGetResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetProfileFailure(message: String) {
        TODO("Not yet implemented")
    }

//    성공하면 activity 종료
    override fun onPatchProfileSuccess(response: ProfilePatchResponse) {
        dismissLoadingDialog()
        finish()
    }

    override fun onPatchProfileFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onGetMyShopSuccess(response: MyShopGetResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetMyShopFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetMyShopSellingSuccess(response: MyShopSellingGetResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetMyShopSellingFailure(message: String) {
        TODO("Not yet implemented")
    }
}