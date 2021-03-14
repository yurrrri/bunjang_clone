package leeyuri.mobile.bunjang_mok_aos.src.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityMyPageSettingBinding
import leeyuri.mobile.bunjang_mok_aos.src.login.LoginActivity
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models.MyPageSettingAdapter
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models.SettingTitle
import kotlin.system.exitProcess

class MyPageSettingActivity : BaseActivity<ActivityMyPageSettingBinding>(
    ActivityMyPageSettingBinding::inflate) {

    private val sf = ApplicationClass.sf.edit()

//    recyclerview setting
    private val usersettingtitleList = arrayListOf(
        SettingTitle("계정 설정"),
        SettingTitle("알림 설정"),
        SettingTitle("우리동네 지역 설정"),
        SettingTitle("배송지 설정"),
        SettingTitle("계좌 설정"),
        SettingTitle("차단 상점 관리")
    )

    private val serviceinfotitlelist = arrayListOf(
        SettingTitle("이용약관"),
        SettingTitle("개인정보 처리방침"),
        SettingTitle("위치기반서비스 이용약관"),
        SettingTitle("오픈소스 라이선스"),
        SettingTitle("버전정보")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //        툴바 설정
        setSupportActionBar(binding.mypageToolbarSetting)
        supportActionBar?.title = null
        binding.button2.setOnClickListener { finish() }

        binding.mypageSettingTvUserIdx.text = ApplicationClass.sf.getInt("userIdx", 0).toString()

        val usersettingadapter = MyPageSettingAdapter(this, usersettingtitleList){
            settingTitle ->
            if (settingTitle.tv_setting_title=="계정 설정"){
//                MyAccountSettingActivity -> 프로필 조회로 이동
                val toAccountSettingIntent = Intent(this, MyPageAccountSettingActivity::class.java)
                toAccountSettingIntent.putExtra("userIdx", intent.getIntExtra("userIdx", 0))
                startActivity(toAccountSettingIntent)
            }
            else if (settingTitle.tv_setting_title=="우리동네 지역 설정"){
                //   MyAccountSettingActivity -> 우리동네 지역 설정

            }
//        계정 설정 클릭 -> 사용자 프로필 조회
        }
        binding.mypageSettingUsersettingList.adapter = usersettingadapter
        binding.mypageSettingUsersettingList.setHasFixedSize(true)

//        서비스 정보
        val serviceinfoadapter = MyPageSettingAdapter(this, serviceinfotitlelist){

        }
        binding.mypageSettingServiceinfoList.adapter = serviceinfoadapter
        binding.mypageSettingServiceinfoList.setHasFixedSize(true)

//        로그아웃
        binding.loginAccountsettingLayoutWithdraw.setOnClickListener {
            sf.remove("userIdx")
            sf.remove(ApplicationClass.X_ACCESS_TOKEN)
            sf.commit()

            finishAffinity()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            exitProcess(0)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}