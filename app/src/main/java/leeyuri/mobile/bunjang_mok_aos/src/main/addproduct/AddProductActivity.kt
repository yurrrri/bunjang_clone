package leeyuri.mobile.bunjang_mok_aos.src.main.addproduct

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityAddProductBinding
import leeyuri.mobile.bunjang_mok_aos.src.main.addproduct.models.AddProductPictureListAdapter
import leeyuri.mobile.bunjang_mok_aos.src.main.addproduct.models.AddProductResponse
import leeyuri.mobile.bunjang_mok_aos.src.main.addproduct.models.PostAddProductRequest
import kotlin.properties.Delegates


class AddProductActivity: BaseActivity<ActivityAddProductBinding>(
    ActivityAddProductBinding::inflate), AddProductView {
//    파이어베이스 저장소
    private lateinit var mStorageRef: StorageReference

    private val tagrequest = 1000
    private val detailrequest = 2000
    private val optionrequest = 3000
    private val SET_LOCATION_REQUEST = 4000
    private lateinit var status:String
    private lateinit var taglist:ArrayList<String>
    private var num by Delegates.notNull<Int>()
    private var isexchange by Delegates.notNull<Char>()
    var count by Delegates.notNull<Int>()

    private val GET_MORE_PHOTO = 5000
    private lateinit var picturelist:ArrayList<String>
    private lateinit var tofirebaselist:ArrayList<String>
    private var urlList = arrayListOf<String>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mStorageRef = FirebaseStorage.getInstance().reference

        picturelist = if (intent.getStringArrayListExtra("picturelist") != null) {
            intent.getStringArrayListExtra("picturelist")!!
        } else
            arrayListOf()

        tofirebaselist = if (intent.getStringArrayListExtra("tofirebaselist") != null) {
            intent.getStringArrayListExtra("tofirebaselist")!!
        } else
            arrayListOf()


//       액션바 설정
        setSupportActionBar(binding.toolbar2)
        supportActionBar?.title = null
        binding.button5.setOnClickListener { finish() }

        count = picturelist.size
        binding.addproductTvPictureCount.text = count.toString()

        //       찍은 사진 전달
        val adapter = AddProductPictureListAdapter(this,
            picturelist,
            binding.addproductTvPictureCount
        )

        binding.addproductPicturelist.adapter = adapter
        adapter.notifyDataSetChanged()

//       다시 찍기 -> 여기서 리스트를 다시 전달해줘야함!!!!!
        binding.addproductImgbtnPicture.setOnClickListener {
            val intent = Intent(this, AddProductImageUploadActivity::class.java)
            intent.putStringArrayListExtra("picturelist", picturelist)
            intent.putStringArrayListExtra("tofirebaselist", tofirebaselist)
            startActivityForResult(intent, GET_MORE_PHOTO)
        }

//       연관태그 입력
        binding.addproductLayoutTag.setOnClickListener {
            val intent = Intent(this, AddProductInsertTagActivity::class.java)
            startActivityForResult(intent, tagrequest)
        }
//
////       상품설명 입력
        binding.addproductEdtInfo.isEnabled = false
        binding.addproductLayoutInfo.setOnClickListener {
            val intent = Intent(this, AddProductInsertDetailActivity::class.java)
            startActivityForResult(intent, detailrequest)
        }

        var is_delifee_checked = 'N'
        var is_nego_checked = 'N'
        // 배송비 포함 / 협의가능 여부
        binding.addproductLayoutIsdelifee.setOnClickListener {
            if (is_delifee_checked == 'N') {
                is_delifee_checked = 'Y'
                binding.addproductTvDelifee.setTextColor(resources.getColor(R.color.black))
                binding.addproductImgDelifee.setColorFilter(R.color.colorAccent)
            } else {
                is_delifee_checked = 'N'
                binding.addproductTvDelifee.setTextColor(resources.getColor(R.color.black))
                binding.addproductImgDelifee.setColorFilter(R.color.bunjang_dark_gray)
            }

        }

        binding.addproductLayoutIsnego.setOnClickListener {
            if (is_nego_checked == 'N') {
                is_nego_checked = 'Y'
                binding.addproductTvNego.setTextColor(resources.getColor(R.color.black))
                binding.addproductImgNego.setColorFilter(R.color.colorAccent)
            } else {
                is_nego_checked = 'N'
                binding.addproductTvNego.setTextColor(resources.getColor(R.color.black))
                binding.addproductImgNego.setColorFilter(R.color.bunjang_dark_gray)
            }

        }

//       지역 설정
//        binding.addproductLayoutLocation.setOnClickListener {
//            val intent = Intent(this, AddProductLocationActiivty::class.java)
//            startActivityForResult(intent, SET_LOCATION_REQUEST)
//        }

//       세부사항 설정
        binding.addproductLayoutOption.setOnClickListener {
            val intent = Intent(this, AddProductOptionActivity::class.java)
            intent.putExtra("num", binding.addproductTvCount.text.toString().toInt())
            intent.putExtra("stat", binding.addproductTvStatus.text.toString())
            intent.putExtra("ex", binding.addproductTvExchange.text.toString())

            startActivityForResult(intent, optionrequest)
        }
//

        //     파이어베이스 url 가져오기

        if (tofirebaselist.size != 0) {
            val firebaseStorage = FirebaseStorage.getInstance()
            firebaseStorage.reference.child("번개장터_클론").child(tofirebaselist[0]).downloadUrl
            binding.addproductBtnComplete.setOnClickListener {

                for (i in tofirebaselist) {
                    val task = firebaseStorage.reference.child("번개장터_클론").child(i).downloadUrl
                    task.addOnSuccessListener {
                        urlList.add(it.toString())
                    }
                    task.addOnFailureListener {
                        Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                    }
                }

                Log.d("hello", urlList.size.toString())

                status = "중고"
                isexchange = 'N'
                num = 1

                val postAddProductRequest = PostAddProductRequest(
                    productName = binding.addproductEdtName.text.toString(),
                    productInfo = binding.addproductEdtInfo.text.toString(),
                    secondCategoryIdx = 2,
                    price = binding.addproductEdtPrice.text.toString().toInt(),
                    isPriceTalk = is_nego_checked,
                    isDeliveryfeePlus = is_delifee_checked,
                    townIdx = null,
                    townName = binding.addproductTvLocation.text.toString(),
                    productStatus = status,
                    isAuthenticated = 'Y',
                    isExchangeAvailable = isexchange,
                    productCnt = num,
                    tagInfo = taglist,
                    imageUrl = urlList
                )

                AddProductService(this).tryPostAddProduct(ApplicationClass.sf.getInt("userIdx", 0),
                    postAddProductRequest)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//
//        사진 추가로 얻어오기
        if(requestCode == GET_MORE_PHOTO && resultCode == RESULT_OK){
//           상품등록 페이지로 넘어감
            val intent = Intent(this, AddProductActivity::class.java)
            intent.putStringArrayListExtra("picturelist", picturelist)
            intent.putStringArrayListExtra("tofirebaselist", tofirebaselist)
            startActivity(intent)
        }

        else if (requestCode==tagrequest && resultCode == RESULT_OK){
            var string = ""
            taglist = data?.getStringArrayListExtra("taglist") as ArrayList<String>

            for (i in taglist){
                string += "#"
                string+=i
            }
            Log.d("hello", string)
            binding.addproductEdtTag.text = string
        }
        else if (requestCode==detailrequest && resultCode == RESULT_OK){

            binding.addproductEdtInfo.text = data?.getStringExtra("detail")
        }

        else if (requestCode==optionrequest && resultCode == RESULT_OK){
            status = data?.getStringExtra("oldornew")!!
            isexchange = data.getCharExtra("isexchange", ' ')
            num = Integer.parseInt(data.getStringExtra("num")!!)

            binding.addproductTvCount.text = num.toString()
            binding.addproductTvStatus.text = intent.getStringExtra("stat")
            binding.addproductTvExchange.text = intent.getStringExtra("ex")
        }

//        else if (requestCode==SET_LOCATION_REQUEST && resultCode == RESULT_OK){
//            binding.addproductTvLocation.text = data?.getStringExtra("location")
//        }
    }

    override fun onPostAddProductSuccess(response: AddProductResponse) {
        showCustomToast("상품 등록완료")
    }

    override fun onPostAddProductFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}