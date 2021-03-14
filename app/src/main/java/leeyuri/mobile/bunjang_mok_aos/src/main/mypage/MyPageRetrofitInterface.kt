package leeyuri.mobile.bunjang_mok_aos.src.main.mypage

import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models.*
import retrofit2.Call
import retrofit2.http.*

interface MyPageRetrofitInterface {
//    마이샵 조회
    @GET("/users/{userIdx}/myshop")
    fun getMyShopInfo(@Path("userIdx") userIdx: Int) : Call<MyShopGetResponse>

//    마이샵 하단상태 정보 조회
    @GET("/users/{userIdx}/myshop/products")
    fun getMyShopSelling(@Path("userIdx") userIdx: Int, @Query("status") status:String) : Call<MyShopSellingGetResponse>

//   프로필 정보 조회
    @GET("/users/{userIdx}/profile")
    fun getProfile(@Path("userIdx") userIdx: Int) : Call<ProfileGetResponse>

//   프로필 수정
    @PATCH("/users/{userIdx}/profile")
    fun patchProfile(@Path("userIdx") userIdx: Int, @Body params: PostPatchProfileRequest) : Call<ProfilePatchResponse>
}

