package leeyuri.mobile.bunjang_mok_aos.src.main.home.models

import com.google.gson.annotations.SerializedName
import leeyuri.mobile.bunjang_mok_aos.config.BaseResponse

// 찜 response
data class AddHeartResponse(

        @SerializedName("userIdx") val userIdx: Int,
        @SerializedName("heartMessage") val heartMessage:String,
        @SerializedName("heartCnt") val heartCnt:Int
) : BaseResponse()