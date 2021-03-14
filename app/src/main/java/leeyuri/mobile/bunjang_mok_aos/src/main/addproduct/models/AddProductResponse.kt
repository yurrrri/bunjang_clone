package leeyuri.mobile.bunjang_mok_aos.src.main.addproduct.models

import com.google.gson.annotations.SerializedName
import leeyuri.mobile.bunjang_mok_aos.config.BaseResponse

// 본인인증 문자 발송 response
data class AddProductResponse(
        @SerializedName("userIdx") val verifycode: Int
) : BaseResponse()
