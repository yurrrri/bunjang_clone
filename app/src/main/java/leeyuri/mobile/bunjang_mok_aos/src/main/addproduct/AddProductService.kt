package leeyuri.mobile.bunjang_mok_aos.src.main.addproduct

import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.src.main.addproduct.models.AddProductResponse
import leeyuri.mobile.bunjang_mok_aos.src.main.addproduct.models.PostAddProductRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductService(val view: AddProductView) {

    fun tryPostAddProduct(userIdx:Int, postAddProductRequest: PostAddProductRequest){
        val addProductRetrofitInterface = ApplicationClass.sRetrofit.create(AddProductRetrofitInterface::class.java)
        addProductRetrofitInterface.postAddProduct(userIdx, postAddProductRequest).enqueue(object : Callback<AddProductResponse>{
            override fun onResponse(call: Call<AddProductResponse>, response: Response<AddProductResponse>) {
                view.onPostAddProductSuccess(response.body() as AddProductResponse)
            }

            override fun onFailure(call: Call<AddProductResponse>, t: Throwable) {
                view.onPostAddProductFailure(t.message ?: "통신 오류")
            }
        })
    }
}
