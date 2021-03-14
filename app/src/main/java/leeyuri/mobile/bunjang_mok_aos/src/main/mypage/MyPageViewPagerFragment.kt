package leeyuri.mobile.bunjang_mok_aos.src.main.mypage

import android.os.Bundle
import android.view.View
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.config.BaseFragment
import leeyuri.mobile.bunjang_mok_aos.databinding.FragmentMypageViewPagerBinding


class MyPageViewPagerFragment(private val image: Int) :
    BaseFragment<FragmentMypageViewPagerBinding>(FragmentMypageViewPagerBinding::bind, R.layout.fragment_mypage_view_pager){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mypageImgViewpager.setImageResource(image)
    }
}