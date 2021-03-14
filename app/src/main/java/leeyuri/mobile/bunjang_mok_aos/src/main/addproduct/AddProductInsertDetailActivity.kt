package leeyuri.mobile.bunjang_mok_aos.src.main.addproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityAddProductBinding
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityAddProductInsertDetailBinding

class AddProductInsertDetailActivity : BaseActivity<ActivityAddProductInsertDetailBinding>(
    ActivityAddProductInsertDetailBinding::inflate){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.button8.setOnClickListener{
            val intent = Intent()
            intent.putExtra("detail", binding.addproductEdtDetail.text.toString())
            setResult(RESULT_OK, intent);
            finish()
        }

        binding.addimageBtnToaddproduct3.setOnClickListener {
            finish()
        }
    }

}