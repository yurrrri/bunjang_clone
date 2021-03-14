package leeyuri.mobile.bunjang_mok_aos.src.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.config.BaseFragment
import leeyuri.mobile.bunjang_mok_aos.databinding.FragmentHomeViewPagerBinding
import leeyuri.mobile.bunjang_mok_aos.databinding.FragmentLoginViewPagerBinding


class HomeViewPagerFragment(private val image: Int) :
    BaseFragment<FragmentHomeViewPagerBinding>(FragmentHomeViewPagerBinding::bind, R.layout.fragment_home_view_pager){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeImgViewpager.setImageResource(image)
    }
}