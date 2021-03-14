package leeyuri.mobile.bunjang_mok_aos.src.main.home

import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.src.main.home.models.AddHeartResponse
import leeyuri.mobile.bunjang_mok_aos.src.main.home.models.TodayRecommendResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val view: HomeView) {

//    오늘의 추천상품
    fun tryGetTodayRecommendProductList(userIdx:Int){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getTodayRecommendProductList(userIdx).enqueue(object : Callback<TodayRecommendResponse>{
            override fun onResponse(call: Call<TodayRecommendResponse>, response: Response<TodayRecommendResponse>) {
                view.onGetTodayRecommendSuccess(response.body() as TodayRecommendResponse)
            }

            override fun onFailure(call: Call<TodayRecommendResponse>, t: Throwable) {
                view.onGetTodayRecommendFailure(t.message ?: "통신 오류")
            }
        })
    }

//    찜하기
    fun postHeart(productIdx:Int){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.postHeart(productIdx).enqueue(object : Callback<AddHeartResponse>{
            override fun onResponse(call: Call<AddHeartResponse>, response: Response<AddHeartResponse>) {
                view.onPostHeartSuccess(response.body() as AddHeartResponse)
            }

            override fun onFailure(call: Call<AddHeartResponse>, t: Throwable) {
                view.onPostHeartFailure(t.message ?: "통신 오류")
            }
        })
    }
}
