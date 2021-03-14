package leeyuri.mobile.bunjang_mok_aos.src.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityMainBinding
import leeyuri.mobile.bunjang_mok_aos.src.main.addproduct.AddProductImageUploadActivity
import leeyuri.mobile.bunjang_mok_aos.src.main.following.FollowingFragment
import leeyuri.mobile.bunjang_mok_aos.src.main.home.HomeFragment
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.MyPageFragment
import leeyuri.mobile.bunjang_mok_aos.src.main.talk.TalkFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//      botom navigation과 fragment 연결
        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btmnavitem_home -> {
                        showLoadingDialog(this)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
                            .commitAllowingStateLoss()
                        dismissLoadingDialog()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btmnavitem_store -> {
                        showLoadingDialog(this)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, FollowingFragment())
                            .commitAllowingStateLoss()
                        dismissLoadingDialog()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btmnavitem_sell -> {
                        val intent = Intent(this, AddProductImageUploadActivity::class.java)
                        startActivity(intent)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btmnavitem_talk -> {
                        showLoadingDialog(this)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, TalkFragment())
                            .commitAllowingStateLoss()
                        dismissLoadingDialog()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btmnavitem_mypage -> {
                        showLoadingDialog(this)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, MyPageFragment())
                            .commitAllowingStateLoss()
                        dismissLoadingDialog()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })

    }
}