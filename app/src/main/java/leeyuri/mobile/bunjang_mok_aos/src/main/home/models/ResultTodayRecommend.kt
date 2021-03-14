package leeyuri.mobile.bunjang_mok_aos.src.main.home.models

import com.google.gson.annotations.SerializedName

data class ResultTodayRecommend(
        @SerializedName("productIdx") val productIdx: Int,
        @SerializedName("imageUrl") val imageUrl: String,
        @SerializedName("productName") val productName: String,
        @SerializedName("price") val price: String,
        @SerializedName("heartCount") var heartCount: Int,
        @SerializedName("interestStatus") var interestStatus:Char
)