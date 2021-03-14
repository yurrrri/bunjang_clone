package leeyuri.mobile.bunjang_mok_aos.src.login.models

import com.google.gson.annotations.SerializedName

data class TokenInfoSign(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("phoneNumber") val phoneNumber: String
)