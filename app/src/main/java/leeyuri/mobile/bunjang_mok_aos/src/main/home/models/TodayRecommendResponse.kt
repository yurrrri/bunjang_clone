package leeyuri.mobile.bunjang_mok_aos.src.main.home.models

import com.google.gson.annotations.SerializedName
import leeyuri.mobile.bunjang_mok_aos.config.BaseResponse

// 오늘의 추천상품 response
data class TodayRecommendResponse(
        @SerializedName("result") val result: List<ResultTodayRecommend>
) : BaseResponse()