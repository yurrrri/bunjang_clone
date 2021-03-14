package leeyuri.mobile.bunjang_mok_aos.src.main.addproduct

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.widget.addTextChangedListener
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityAddProductInsertTagBinding

class AddProductInsertTagActivity : BaseActivity<ActivityAddProductInsertTagBinding>(
    ActivityAddProductInsertTagBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.addimageBtnToaddproduct2.setOnClickListener {
            val tags = binding.addproductEdtTag.text.toString()
            val strArr = tags.split(" ")

            val intent = Intent()
            intent.putExtra("taglist", strArr as ArrayList<String>)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}