package leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.src.main.home.HomeViewPagerFragment
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.MyPageViewPagerFragment

class MyShopViewPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MyPageViewPagerFragment(R.drawable.myshop_viewpager_1)
            else -> MyPageViewPagerFragment(R.drawable.myshop_viewpager_2)
        }
    }
}