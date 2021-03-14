package leeyuri.mobile.bunjang_mok_aos.src.main.home

import leeyuri.mobile.bunjang_mok_aos.src.main.home.models.AddHeartResponse
import leeyuri.mobile.bunjang_mok_aos.src.main.home.models.TodayRecommendResponse

interface HomeView {
    fun onGetTodayRecommendSuccess(response: TodayRecommendResponse)

    fun onGetTodayRecommendFailure(message: String)

    fun onPostHeartSuccess(response: AddHeartResponse)

    fun onPostHeartFailure(message: String)
}