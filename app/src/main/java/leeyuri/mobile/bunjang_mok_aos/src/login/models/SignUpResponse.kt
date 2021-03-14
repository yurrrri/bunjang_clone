package leeyuri.mobile.bunjang_mok_aos.src.login.models

import com.google.gson.annotations.SerializedName
import leeyuri.mobile.bunjang_mok_aos.config.BaseResponse

data class SignUpResponse(
        @SerializedName("result") val result: ResultSignUp
) : BaseResponse()