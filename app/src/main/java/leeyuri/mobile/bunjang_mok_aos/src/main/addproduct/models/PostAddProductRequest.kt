package leeyuri.mobile.bunjang_mok_aos.src.main.addproduct.models

import com.google.gson.annotations.SerializedName

data class PostAddProductRequest(
        @SerializedName("productName") val productName: String,
        @SerializedName("productInfo") val productInfo: String,
        @SerializedName("secondCategoryIdx") val secondCategoryIdx: Int,
        @SerializedName("price") val price: Int,
        @SerializedName("isPriceTalk") val isPriceTalk: Char,
        @SerializedName("isDeliveryfeePlus") val isDeliveryfeePlus: Char,
        @SerializedName("townIdx") val townIdx: Int?,
        @SerializedName("townName") val townName: String,
        @SerializedName("productStatus") val productStatus: String,
        @SerializedName("isAuthenticated") val isAuthenticated: Char,
        @SerializedName("isExchangeAvailable") val isExchangeAvailable: Char,
        @SerializedName("productCnt") val productCnt: Int,
        @SerializedName("tagInfo") val tagInfo: ArrayList<String>,
        @SerializedName("imageUrl") val imageUrl: ArrayList<String>,
)