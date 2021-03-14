package leeyuri.mobile.bunjang_mok_aos.src.main.talk

import android.os.Bundle
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.config.BaseFragment
import leeyuri.mobile.bunjang_mok_aos.databinding.FragmentTalkBinding

class TalkFragment : BaseFragment<FragmentTalkBinding>(FragmentTalkBinding::bind,
    R.layout.fragment_talk){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        binding.talkToolbar.inflateMenu(R.menu.talk_toolbar_items)
    }
}