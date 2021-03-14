package leeyuri.mobile.bunjang_mok_aos.src.main.mypage

import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models.MyShopGetResponse
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models.MyShopSellingGetResponse
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models.ProfileGetResponse
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models.ProfilePatchResponse

interface MyPageView {
    fun onGetProfileSuccess(response: ProfileGetResponse)

    fun onGetProfileFailure(message: String)

    fun onPatchProfileSuccess(response: ProfilePatchResponse)

    fun onPatchProfileFailure(message: String)

    fun onGetMyShopSuccess(response:MyShopGetResponse)

    fun onGetMyShopFailure(message: String)

    fun onGetMyShopSellingSuccess(response:MyShopSellingGetResponse)

    fun onGetMyShopSellingFailure(message: String)
}