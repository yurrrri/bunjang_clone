package leeyuri.mobile.bunjang_mok_aos.src.main.mypage

import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageService(val view: MyPageView) {

    fun tryGetProfile(userIdx: Int) {

        val myPageRetrofitInterface =
            ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        myPageRetrofitInterface.getProfile(userIdx).enqueue(object : Callback<ProfileGetResponse> {
            override fun onResponse(
                call: Call<ProfileGetResponse>,
                response: Response<ProfileGetResponse>
            ) {
                view.onGetProfileSuccess(response.body() as ProfileGetResponse)
            }

            override fun onFailure(call: Call<ProfileGetResponse>, t: Throwable) {
                view.onGetProfileFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPatchProfile(userIdx:Int, postPatchProfileRequest: PostPatchProfileRequest) {

        val myPageRetrofitInterface = ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        myPageRetrofitInterface.patchProfile(userIdx, postPatchProfileRequest).enqueue(object : Callback<ProfilePatchResponse>{
            override fun onResponse(
                call: Call<ProfilePatchResponse>,
                response: Response<ProfilePatchResponse>
            ) {
                view.onPatchProfileSuccess(response.body() as ProfilePatchResponse)
            }

            override fun onFailure(call: Call<ProfilePatchResponse>, t: Throwable) {
                view.onPatchProfileFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetMyShop(userIdx: Int) {
        val myPageRetrofitInterface =
            ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        myPageRetrofitInterface.getMyShopInfo(userIdx).enqueue(object : Callback<MyShopGetResponse> {
            override fun onResponse(
                call: Call<MyShopGetResponse>,
                response: Response<MyShopGetResponse>
            ) {
                view.onGetMyShopSuccess(response.body() as MyShopGetResponse)
            }

            override fun onFailure(call: Call<MyShopGetResponse>, t: Throwable) {
                view.onGetMyShopFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetMyShopSelling(userIdx: Int, status:String){
        val myPageRetrofitInterface =
            ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        myPageRetrofitInterface.getMyShopSelling(userIdx, status).enqueue(object : Callback<MyShopSellingGetResponse> {
            override fun onResponse(
                call: Call<MyShopSellingGetResponse>,
                response: Response<MyShopSellingGetResponse>
            ) {
                view.onGetMyShopSellingSuccess(response.body() as MyShopSellingGetResponse)
            }

            override fun onFailure(call: Call<MyShopSellingGetResponse>, t: Throwable) {
                view.onGetMyShopSellingFailure(t.message ?: "통신 오류")
            }
        })
    }
}