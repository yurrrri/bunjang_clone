package leeyuri.mobile.bunjang_mok_aos.src.main.home.models

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
import kotlin.properties.Delegates

class TodayRecommendAdapter(val context: Context, private val todayRecommendList: List<ResultTodayRecommend>, private val itemClick: (ResultTodayRecommend) -> Unit):
    RecyclerView.Adapter<TodayRecommendAdapter.ItemViewHolder>(){

    inner class ItemViewHolder(itemView: View, itemClick: (ResultTodayRecommend) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val imgView = itemView.findViewById<ImageView>(R.id.home_img_recommend)
        private val imgbtnHeart = itemView.findViewById<Button>(R.id.home_imgbtn_addheart)
        private val imgviewHeart = itemView.findViewById<ImageView>(R.id.home_img_heart)
        private val tvProductName = itemView.findViewById<TextView>(R.id.home_tv_title)
        private val tvPrice = itemView.findViewById<TextView>(R.id.home_tv_price)
        private val tvHeartCount = itemView.findViewById<TextView>(R.id.home_tv_heartcount)

        var isheartadded by Delegates.notNull<Boolean>()

        var isAddHeart by Delegates.notNull<Char>()

        fun bind(todayRecommend: ResultTodayRecommend, context: Context) {
            isAddHeart = todayRecommend.interestStatus
//            이미지
            Glide.with(context)
                .load(todayRecommend.imageUrl)
                .into(imgView)
//            상품 제목
            tvProductName.text = todayRecommend.productName
//            가격
            tvPrice.text = todayRecommend.price
//            하트수
            tvHeartCount.text = todayRecommend.heartCount.toString()
            var heartcount = todayRecommend.heartCount

//            관심이 하나도 없을때 - > 하트랑 갯수가 아얘 안보여야함
            if (todayRecommend.heartCount==0) {
                imgviewHeart.visibility = View.INVISIBLE
                tvHeartCount.visibility = View.INVISIBLE
            }

//            관심상태일때
            isheartadded = if (todayRecommend.interestStatus=='Y'){
                imgbtnHeart.setBackgroundResource(R.drawable.home_heart_added)
                true
            } else{
                imgbtnHeart.setBackgroundResource(R.drawable.home_add_heart)
                false
            }

//           관심 추가하기
            imgbtnHeart.setOnClickListener {
                isheartadded = if (isheartadded){
                    imgbtnHeart.setBackgroundResource(R.drawable.home_add_heart)
                    heartcount-=1
                    false
                } else{
                    imgbtnHeart.setBackgroundResource(R.drawable.home_heart_added)
                    heartcount+=1
                    true
                }
                if (heartcount==0){
                    imgviewHeart.visibility = View.INVISIBLE
                    tvHeartCount.visibility = View.INVISIBLE
                }
                else{
                    imgviewHeart.visibility = View.VISIBLE
                    tvHeartCount.visibility = View.VISIBLE
                    tvHeartCount.text = heartcount.toString()
                }

                itemClick(todayRecommend)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodayRecommendAdapter.ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_recommend_items, parent, false)
        return ItemViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: TodayRecommendAdapter.ItemViewHolder, position: Int) {
        holder.bind(todayRecommendList[position], context)
    }

    override fun getItemCount(): Int {
        return todayRecommendList.size
    }
}