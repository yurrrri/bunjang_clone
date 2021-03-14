package leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models

import com.google.gson.annotations.SerializedName
import leeyuri.mobile.bunjang_mok_aos.config.BaseResponse

// 프로필 조회 response
data class ProfileGetResponse(
        @SerializedName("result") val resultGet: List<ResultGetProfile>
) : BaseResponse()
