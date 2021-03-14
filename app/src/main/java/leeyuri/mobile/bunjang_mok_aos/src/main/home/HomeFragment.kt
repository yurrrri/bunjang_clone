package leeyuri.mobile.bunjang_mok_aos.src.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.recyclerview.widget.GridLayoutManager
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.config.BaseFragment
import leeyuri.mobile.bunjang_mok_aos.databinding.FragmentHomeBinding
import leeyuri.mobile.bunjang_mok_aos.src.main.home.models.AddHeartResponse
import leeyuri.mobile.bunjang_mok_aos.src.main.home.models.HomeViewPagerAdapter
import leeyuri.mobile.bunjang_mok_aos.src.main.home.models.TodayRecommendAdapter
import leeyuri.mobile.bunjang_mok_aos.src.main.home.models.TodayRecommendResponse
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home), HomeView {

    private val userIdx = ApplicationClass.sf.getInt("userIdx", 0)
    lateinit var recommendAdapter: TodayRecommendAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//  툴바
        setHasOptionsMenu(true)
        binding.homeToolbar.inflateMenu(R.menu.home_toolbar_items)

//       뷰페이저
        var currentPage = 0
        val pageradpater = HomeViewPagerAdapter(this)
        val viewpager = binding.homeViewpager

        viewpager.adapter = pageradpater

        val handler = Handler(Looper.myLooper()!!)
        val autoSlideRunnable = Runnable {
            if (currentPage == 3) {
                currentPage = 0
            }
            viewpager.setCurrentItem(currentPage++,
                false)
        }

        val timer = Timer() // This will create a new Thread

        timer.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(autoSlideRunnable)
            }
        }, 500, 3000)
//        카테고리 접속


//       오늘의 추천상품
        showLoadingDialog(requireContext())
        HomeService(this).tryGetTodayRecommendProductList(userIdx)

//       리프레시 레이아웃
        binding.homeRefreshlayout.setOnRefreshListener {
            // 새로고침 코드를 작성
            showLoadingDialog(requireContext())
            HomeService(this).tryGetTodayRecommendProductList(userIdx)
            //recommendAdapter.notifyDataSetChanged()
            // 새로고침 완료시,
            // 새로고침 아이콘이 사라질 수 있게 isRefreshing = false
            binding.homeRefreshlayout.isRefreshing = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_toolbar_items, menu)
    }

    override fun onGetTodayRecommendSuccess(response: TodayRecommendResponse) {
        dismissLoadingDialog()
        recommendAdapter = TodayRecommendAdapter(requireContext(), response.result){
            showLoadingDialog(requireContext())
            HomeService(this).postHeart(it.productIdx)
        }
        binding.todayrecommendrecycler.adapter = recommendAdapter
        binding.todayrecommendrecycler.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onGetTodayRecommendFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onPostHeartSuccess(response: AddHeartResponse) {
        dismissLoadingDialog()

        if (response.heartMessage=="찜 추가") {
            showCustomToast("찜 목록에 추가했어요!")
        }
        else {

            showCustomToast("찜 해제가 완료되었습니다")
        }
    }

    override fun onPostHeartFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}