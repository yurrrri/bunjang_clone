package leeyuri.mobile.bunjang_mok_aos.src.login.models

import com.google.gson.annotations.SerializedName
import leeyuri.mobile.bunjang_mok_aos.config.BaseResponse

// 본인인증 문자 발송 response
data class CertiResponse(
        @SerializedName("verifycode") val verifycode: Int,
        @SerializedName("member") val member:String
) : BaseResponse()
