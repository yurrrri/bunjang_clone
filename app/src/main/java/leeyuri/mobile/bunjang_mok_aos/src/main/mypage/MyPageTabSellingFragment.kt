package leeyuri.mobile.bunjang_mok_aos.src.main.mypage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.config.BaseFragment
import leeyuri.mobile.bunjang_mok_aos.databinding.FragmentMypageTabSellingBinding
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models.*
import java.util.ArrayList

class MyPageTabSellingFragment : BaseFragment<FragmentMypageTabSellingBinding>(FragmentMypageTabSellingBinding::bind, R.layout.fragment_mypage_tab_selling),
MyPageView {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.myshopProductRecycler.visibility = View.GONE
        binding.imageView4.visibility = View.GONE

        showLoadingDialog(requireContext())
        MyPageService(this).tryGetMyShopSelling(ApplicationClass.sf.getInt("userIdx", 0), "판매중")
    }
    override fun onGetProfileSuccess(response: ProfileGetResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetProfileFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPatchProfileSuccess(response: ProfilePatchResponse) {
        TODO("Not yet implemented")
    }

    override fun onPatchProfileFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetMyShopSuccess(response: MyShopGetResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetMyShopFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetMyShopSellingSuccess(response: MyShopSellingGetResponse) {
        dismissLoadingDialog()

        binding.myshopProductRecycler.visibility = View.VISIBLE
        binding.imageView4.visibility = View.VISIBLE
        binding.imageView16.visibility = View.INVISIBLE

        val adapter = MyPageProductAdapter(requireContext(),
            response.result as ArrayList<ResultGetMyShopSelling>)
        binding.myshopProductRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.myshopProductRecycler.adapter = adapter
    }

    override fun onGetMyShopSellingFailure(message: String) {
        TODO("Not yet implemented")
    }

}