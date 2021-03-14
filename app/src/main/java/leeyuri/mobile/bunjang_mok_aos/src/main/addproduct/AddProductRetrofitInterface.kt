package leeyuri.mobile.bunjang_mok_aos.src.main.addproduct

import leeyuri.mobile.bunjang_mok_aos.src.main.addproduct.models.AddProductResponse
import leeyuri.mobile.bunjang_mok_aos.src.main.addproduct.models.PostAddProductRequest
import retrofit2.Call
import retrofit2.http.*

interface AddProductRetrofitInterface {
//   상품등록
    @POST("/users/{userIdx}/products")
    fun postAddProduct(@Path("userIdx") userIdx: Int, @Body params: PostAddProductRequest) : Call<AddProductResponse>
}