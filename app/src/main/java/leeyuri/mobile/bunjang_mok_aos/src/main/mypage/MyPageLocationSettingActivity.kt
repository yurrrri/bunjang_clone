package leeyuri.mobile.bunjang_mok_aos.src.main.mypage

import android.os.Bundle
import android.view.MenuItem
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityMyPageLocationSettingBinding

class MyPageLocationSettingActivity : BaseActivity<ActivityMyPageLocationSettingBinding>(
    ActivityMyPageLocationSettingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //        액션바 설정
        supportActionBar?.title = "지역 설정"
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //뒤로가기 버튼

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.home -> {

            }
        }

        return super.onOptionsItemSelected(item)
    }
}
