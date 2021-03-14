package leeyuri.mobile.bunjang_mok_aos.src.main.addproduct

import leeyuri.mobile.bunjang_mok_aos.src.main.addproduct.models.AddProductResponse

interface AddProductView {

    fun onPostAddProductSuccess(response: AddProductResponse)

    fun onPostAddProductFailure(message: String)
}