package leeyuri.mobile.bunjang_mok_aos.src.main.home

import leeyuri.mobile.bunjang_mok_aos.src.main.home.models.AddHeartResponse
import leeyuri.mobile.bunjang_mok_aos.src.main.home.models.TodayRecommendResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeRetrofitInterface {
//   오늘의 추천상품
    @GET("/users/{userIdx}/products")
    fun getTodayRecommendProductList(@Path("userIdx") userIdx: Int) : Call<TodayRecommendResponse>

//   찜하기
    @POST("/products/{productIdx}/heart")
    fun postHeart(@Path("productIdx") productIdx: Int) : Call<AddHeartResponse>
}