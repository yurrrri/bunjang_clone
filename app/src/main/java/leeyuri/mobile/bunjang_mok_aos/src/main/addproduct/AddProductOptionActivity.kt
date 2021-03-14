package leeyuri.mobile.bunjang_mok_aos.src.main.addproduct

import android.content.Intent
import android.os.Bundle
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityAddProductOptionBinding
import java.util.ArrayList
import kotlin.properties.Delegates

class AddProductOptionActivity : BaseActivity<ActivityAddProductOptionBinding>(
    ActivityAddProductOptionBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.addimageBtnToaddproduct4.setOnClickListener {
            finish()
        }

        binding.addproductBtnOption.setOnClickListener {
            var num = binding.addproductOptionNum.text.toString()
            var oldornew = ""
            var isexchange = ' '
            var option = ""
            var stat = ""
            var ex = ""

            option +=num
            option += "|"

            binding.addproductOptionNum.setText(intent.getIntExtra("num", 0).toString())
            if (intent.getStringExtra("stat")=="중고"){
                binding.addproductOptionOld.isChecked = true
            }
            else{
                binding.addproductOptionNew.isChecked = true
            }

            if (intent.getStringExtra("ex")=="교환불가"){
                binding.addproductOptionExchangeno.isChecked = true
            }
            else{
                binding.addproductOptionExyes.isChecked = true
            }


            if (binding.addproductOptionOld.isChecked){
                oldornew = "중고"
                stat = "중고"
            }
            else if (binding.addproductOptionNew.isChecked){
                oldornew = "새상품"
                stat = "새상품"
            }

            option +=oldornew
            if (binding.addproductOptionExchangeno.isChecked){
                isexchange ='Y'
                option +="|"
                option += "교환불가"
                ex= "교환불가"
            }
            else if (binding.addproductOptionExyes.isChecked){
                isexchange ='N'
                option +="|"
                option += "교환가능"
                ex = "교환가능"
            }

            val intent = Intent()
            intent.putExtra("num", num)
            intent.putExtra("status", oldornew)
            intent.putExtra("isexchange", isexchange)
            intent.putExtra("option", option)

            intent.putExtra("stat", stat)
            intent.putExtra("ex", ex)

            setResult(RESULT_OK, intent)
            finish()
        }
    }
}