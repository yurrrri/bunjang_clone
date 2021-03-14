package leeyuri.mobile.bunjang_mok_aos.src.main.following

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.config.BaseFragment
import leeyuri.mobile.bunjang_mok_aos.databinding.FragmentFollowingBinding

class FollowingFragment: BaseFragment<FragmentFollowingBinding>(FragmentFollowingBinding::bind, R.layout.fragment_following){

    private val tabLayoutTextArray = arrayOf("내피드","팔로잉", "추천")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tablayout = binding.followingTab
        val viewpager2 = binding.followingViewpager
        val adapter = ViewPagerAdapter(this)

        binding.followingToolbar.inflateMenu(R.menu.following_toolbar_item)

        viewpager2.adapter = adapter
        TabLayoutMediator(tablayout,viewpager2){tab,position->
            tab.text = tabLayoutTextArray[position]
        }.attach()
    }

//    view pager adapter
    private inner class ViewPagerAdapter(fa: FollowingFragment): FragmentStateAdapter(fa){
        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> FollowingMyFeedFragment()
                1 -> FollwingStoreFragment()
                else -> FollowingRecommendFragment()
            }
        }
        override fun getItemCount():Int = 3
    }
}