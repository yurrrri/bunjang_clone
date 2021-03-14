package leeyuri.mobile.bunjang_mok_aos.src.main.mypage

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.config.BaseFragment
import leeyuri.mobile.bunjang_mok_aos.databinding.FragmentMyPageBinding
import leeyuri.mobile.bunjang_mok_aos.src.main.home.models.HomeViewPagerAdapter
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models.*
import java.util.*
import kotlin.collections.ArrayList

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind,
    R.layout.fragment_my_page), MyPageView {

    private val tabLayoutTextArray = arrayOf("판매중","예약중", "판매완료")

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showLoadingDialog(requireContext())
        MyPageService(this).tryGetMyShop(ApplicationClass.sf.getInt("userIdx", 0))
        setHasOptionsMenu(true)

        binding.toolbar.inflateMenu(R.menu.myshop_toolbar_items)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId){
                R.id.myshop_toolbar_setting-> {
                    val intent = Intent(activity, MyPageSettingActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        //       뷰페이저
        var currentPage = 0
        val pageradpater = MyShopViewPagerAdapter(this)
        val myshop_viewpager = binding.myshopViewpager

        myshop_viewpager.adapter = pageradpater

        val handler = Handler(Looper.myLooper()!!)
        val autoSlideRunnable = Runnable {
            if (currentPage == 2) {
                currentPage = 0
            }
            myshop_viewpager.setCurrentItem(currentPage++,
                false)
        }

        val timer = Timer() // This will create a new Thread

        timer.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(autoSlideRunnable)
            }
        }, 500, 3000)

//        탭 레이아웃
        val tablayout = binding.mypageTab
        val adapter = ViewPagerAdapter(this)
        val viewpager = binding.myshopTabViewpager

        viewpager.adapter = adapter
        TabLayoutMediator(tablayout,viewpager){tab,position->
            tab.text = tabLayoutTextArray[position]
        }.attach()
    }

    //    view pager adapter
    private inner class ViewPagerAdapter(fa: MyPageFragment): FragmentStateAdapter(fa){
        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> MyPageTabSellingFragment()
                1 -> MyPageTabReserveFragment()
                else -> MyPageTabSoldFragment()
            }
        }
        override fun getItemCount():Int = 3
    }

    override fun onGetProfileSuccess(response: ProfileGetResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetProfileFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPatchProfileSuccess(response: ProfilePatchResponse) {
        TODO("Not yet implemented")
    }

    override fun onPatchProfileFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetMyShopSuccess(response: MyShopGetResponse) {
        dismissLoadingDialog()
        binding.mypageStoreName.text = response.result[0].nickname
        Glide.with(this).load(response.result[0].profileImgLink).into(binding.mypageImgProfile)
        binding.ratingBar.rating = response.result[0].star.toFloat()
        binding.mypageTvJjim.text = response.result[0].heartCnt.toString()
        binding.mypageTvReview.text = response.result[0].reviewCnt.toString()
        binding.mypageTvFollower.text = response.result[0].followerCnt.toString()
        binding.mypageTvFollowing.text = response.result[0].followerCnt.toString()
    }

    override fun onGetMyShopFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onGetMyShopSellingSuccess(response: MyShopSellingGetResponse) {

    }

    override fun onGetMyShopSellingFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}