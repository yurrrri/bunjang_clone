package leeyuri.mobile.bunjang_mok_aos.src.main.following.models

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import leeyuri.mobile.bunjang_mok_aos.src.main.following.FollowingMyFeedFragment
import leeyuri.mobile.bunjang_mok_aos.src.main.following.FollowingRecommendFragment
import leeyuri.mobile.bunjang_mok_aos.src.main.following.FollwingStoreFragment

class FollowingAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FollowingMyFeedFragment()
            1 -> FollwingStoreFragment()
            else -> FollowingRecommendFragment()
        }
    }
    override fun getItemCount():Int = itemCount
}