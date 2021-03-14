package leeyuri.mobile.bunjang_mok_aos.src.login.models

import com.google.gson.annotations.SerializedName

data class PostLoginRequest(
        @SerializedName("phoneNumber") val phoneNumber: String,
        @SerializedName("name") val name: String,
        @SerializedName("birth") val birth: String,
        @SerializedName("gender") val gender: Char,
        @SerializedName("telocodeName") val telocodeName:String,
        @SerializedName("isAgreement") val isAgreement:Char,
        @SerializedName("member") val member:String
)