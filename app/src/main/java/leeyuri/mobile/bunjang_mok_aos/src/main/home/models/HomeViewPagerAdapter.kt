package leeyuri.mobile.bunjang_mok_aos.src.main.home.models

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.src.main.home.HomeViewPagerFragment

class HomeViewPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeViewPagerFragment(R.drawable.home_viewpager_1)
            1 -> HomeViewPagerFragment(R.drawable.home_viewpager_2)
            else -> HomeViewPagerFragment(R.drawable.home_viewpager_3)
        }
    }
}