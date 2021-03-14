package leeyuri.mobile.bunjang_mok_aos.src.main.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.src.main.home.models.ResultTodayRecommend
import leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models.ResultGetMyShopSelling
import org.w3c.dom.Text

class MyPageProductAdapter(val context: Context, private val productlist:java.util.ArrayList<ResultGetMyShopSelling>):
    RecyclerView.Adapter<MyPageProductAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val imgSelling = itemView.findViewById<ImageView>(R.id.mypage_selling_img)
        private val tvTitle = itemView.findViewById<TextView>(R.id.mypage_selling_title)
        private val tvPrice = itemView.findViewById<TextView>(R.id.mypage_selling_price)
        private val tvTime = itemView.findViewById<TextView>(R.id.mypage_tv_time)

        fun bind(resultGetMyShopSelling: ResultGetMyShopSelling, context: Context) {
//            이미지
            Glide.with(context)
                .load(resultGetMyShopSelling.imageUrl)
                .into(imgSelling)
//            제목
            tvTitle.text = resultGetMyShopSelling.productName
//            가격
            tvPrice.text = resultGetMyShopSelling.price
//            시간
            tvTime.text = resultGetMyShopSelling.createdAt

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyPageProductAdapter.ItemViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.mypage_selling_items, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyPageProductAdapter.ItemViewHolder,
        position: Int
    ) {
        holder.bind(productlist[position], context)
    }

    override fun getItemCount(): Int {
        return productlist.size
    }
}
