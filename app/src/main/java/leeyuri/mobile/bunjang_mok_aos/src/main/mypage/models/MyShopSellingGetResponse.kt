package leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models

import com.google.gson.annotations.SerializedName
import leeyuri.mobile.bunjang_mok_aos.config.BaseResponse

data class MyShopSellingGetResponse(
    @SerializedName("result") val result: List<ResultGetMyShopSelling>
) : BaseResponse()