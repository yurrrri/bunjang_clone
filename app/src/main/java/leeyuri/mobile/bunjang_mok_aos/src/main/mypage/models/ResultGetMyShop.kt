package leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models

import com.google.gson.annotations.SerializedName

data class ResultGetMyShop(
    @SerializedName("shopIdx") val shopIdx: Int,
    @SerializedName("profileImgLink") val profileImgLink: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("star") val star: Double,
    @SerializedName("heartCnt") val heartCnt: Int,
    @SerializedName("reviewCnt") val reviewCnt: Int,
    @SerializedName("followingCnt") val followingCnt:Int,
    @SerializedName("followerCnt") val followerCnt:Int
)