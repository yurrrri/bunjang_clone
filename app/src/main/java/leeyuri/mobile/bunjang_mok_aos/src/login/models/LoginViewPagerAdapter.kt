package leeyuri.mobile.bunjang_mok_aos.src.login.models

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.src.login.LoginViewPagerFragment

class LoginViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> LoginViewPagerFragment(R.drawable.login_viewpager_first)
            1 -> LoginViewPagerFragment(R.drawable.login_viewpager_second)
            2 -> LoginViewPagerFragment(R.drawable.login_viewpager_third)
            else -> LoginViewPagerFragment(R.drawable.login_viewpager_fourth)
        }
    }
}