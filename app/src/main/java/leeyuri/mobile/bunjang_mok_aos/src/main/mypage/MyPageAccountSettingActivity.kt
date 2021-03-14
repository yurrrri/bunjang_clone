package leeyuri.mobile.bunjang_mok_aos.src.main.mypage

import android.content.Intent
import android.os.Bundle
import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityMyPageAccountSettingBinding
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models.*

class MyPageAccountSettingActivity : BaseActivity<ActivityMyPageAccountSettingBinding>(ActivityMyPageAccountSettingBinding::inflate), MyPageView{

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        //        툴바 설정
        setSupportActionBar(binding.mypageToolbarSetting2)
        supportActionBar?.title = null
        binding.button3.setOnClickListener { finish() }


//       생일 변경 -> 날짜 선택할 수 있는 대화상자 띄우기(DatePickerDialog)
//        binding.loginAccountsettingLayoutBirth.setOnClickListener {
//            val dlg = DatePickerDialog(this)
//            dlg.setOnDateSetListener(DatePickerDialog.OnDateSetListener {
//                    view, year, month, dayOfMonth ->
//                var birthday:String = year.toString()+"-"+
//            })
//            dlg.show()
//        }

//         연락처 변경

//        연락처 공개 변경
        binding.mypageSwitchIsPhoneRelease.setOnClickListener {
            showLoadingDialog(this)
            if (binding.mypageSwitchIsPhoneRelease.isChecked){
                val patchphoneNotReleaseRequest = PostPatchProfileRequest(
                    gender = null,
                    birth =null,
                    phoneNumber = null,
                    isPhoneRelease = 'N',
                    email = null
                )
                MyPageService(this).tryPatchProfile(ApplicationClass.sf.getInt("userIdx", 0), patchphoneNotReleaseRequest)
            }
            else{
                val patchphoneReleaseRequest = PostPatchProfileRequest(
                    gender = null,
                    birth =null,
                    phoneNumber = null,
                    isPhoneRelease = 'Y',
                    email = null
                )
                MyPageService(this).tryPatchProfile(ApplicationClass.sf.getInt("userIdx", 0), patchphoneReleaseRequest)
            }
        }
    }

//    프로필 수정후에 화면 갱신 -> onResume
    override fun onResume() {
        super.onResume()
        MyPageService(this).tryGetProfile(ApplicationClass.sf.getInt("userIdx", 0))

        // 성별 변경
    }

    override fun onGetProfileSuccess(response: ProfileGetResponse) {
        //  프로필 조회
        binding.loginAccountsettingTvGender.text = response.resultGet[0].gender
        binding.loginAccountsettingTvBirth.text = response.resultGet[0].birth
        binding.loginAccountsettingTvPhone.text = response.resultGet[0].phone
        binding.loginAccountsettingTvRecommendcode.text = response.resultGet[0].recommendCode

//       성별 변경
        binding.loginAccountsettingLayoutGender.setOnClickListener {
            val genderIntent = Intent(this, MyPageSettingGenderActivity::class.java)
            genderIntent.putExtra("gender", response.resultGet[0].gender)
            genderIntent.putExtra("userIdx", ApplicationClass.sf.getInt("userIdx", 0))
            startActivity(genderIntent)
        }
    }

    override fun onGetProfileFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onPatchProfileSuccess(response: ProfilePatchResponse) {
        dismissLoadingDialog()
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
