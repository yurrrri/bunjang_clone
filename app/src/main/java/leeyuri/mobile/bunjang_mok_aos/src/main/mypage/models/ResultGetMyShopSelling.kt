package leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models

import com.google.gson.annotations.SerializedName

data class ResultGetMyShopSelling(
    @SerializedName("shopIdx") val shopIdx: Int,
    @SerializedName("productIdx") val productIdx: Int,
    @SerializedName("productName") val productName: String,
    @SerializedName("price") val price: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("status") val status:String
)