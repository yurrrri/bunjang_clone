package leeyuri.mobile.bunjang_mok_aos.src.login

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityLoginBinding
import leeyuri.mobile.bunjang_mok_aos.src.login.models.LoginViewPagerAdapter
import leeyuri.mobile.bunjang_mok_aos.util.LoginBottomSheet
import java.util.*

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var currentPage = 0

//       상태바를 없애기위함
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }
        else {
            @Suppress("DEPRECATION")
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

//        이미지 슬라이드->viewpager & dot indicator
        val pageradpater = LoginViewPagerAdapter(this)
        val viewpager = binding.loginViewpager
        val indicator = binding.loginViewpagerIndicator

        indicator.setViewPager(viewpager)

//        val runnable = Runnable {
//            val numPages: Int = viewpager.adapter!!.itemCount
//            page = (page + 1) % numPages
//            pager.setCurrentItem(page)
//        }

        viewpager.adapter = pageradpater

        /*After setting the adapter use the timer */
        val handler = Handler(Looper.myLooper()!!)
        val autoSlideRunnable = Runnable {
            if (currentPage == 4) {
                currentPage = 0
            }
            viewpager.setCurrentItem(currentPage++,
                    true)
            indicator.createIndicators(4, currentPage)
        }

        val timer = Timer() // This will create a new Thread

        timer.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(autoSlideRunnable)
            }
        }, 500, 3000)

//       다른방법으로 로그인시 대화상자 -> Bottom Sheet Dialog
        val bottomsheetdialog = LoginBottomSheet()
        binding.loginBtnAnother.setOnClickListener{
            bottomsheetdialog.show(supportFragmentManager, bottomsheetdialog.tag)
        }
    }
}