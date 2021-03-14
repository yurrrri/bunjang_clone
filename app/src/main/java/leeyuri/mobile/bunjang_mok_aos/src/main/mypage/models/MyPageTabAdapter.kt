package leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.MyPageTabReserveFragment
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.MyPageTabSellingFragment
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.MyPageTabSoldFragment

class MyPageTabAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> MyPageTabSellingFragment()
            1 -> MyPageTabReserveFragment()
            else -> MyPageTabSoldFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}