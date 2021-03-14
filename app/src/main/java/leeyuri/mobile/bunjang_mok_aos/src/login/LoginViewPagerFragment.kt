package leeyuri.mobile.bunjang_mok_aos.src.login

import android.os.Bundle
import android.view.View
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.config.BaseFragment
import leeyuri.mobile.bunjang_mok_aos.databinding.FragmentLoginViewPagerBinding

class LoginViewPagerFragment(private val image: Int) : BaseFragment<FragmentLoginViewPagerBinding>(FragmentLoginViewPagerBinding::bind, R.layout.fragment_login_view_pager){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginImageviewViewpager.setImageResource(image)
    }
}