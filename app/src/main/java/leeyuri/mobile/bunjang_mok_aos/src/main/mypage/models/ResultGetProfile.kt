package leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models

import com.google.gson.annotations.SerializedName

data class ResultGetProfile(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("gender") val gender: String,
    @SerializedName("birth") val birth: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("isPhoneRelease") val isPhoneRelease: Char,
    @SerializedName("email") val email: String,
    @SerializedName("recommendCode") val recommendCode:String
)