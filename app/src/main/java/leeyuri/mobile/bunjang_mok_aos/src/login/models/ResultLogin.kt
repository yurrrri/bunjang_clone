package leeyuri.mobile.bunjang_mok_aos.src.login.models

import com.google.gson.annotations.SerializedName

data class ResultLogin(
        @SerializedName("jwt") val jwt: String,
        @SerializedName("tokenInfo") val tokenInfo: TokenInfoSign
)