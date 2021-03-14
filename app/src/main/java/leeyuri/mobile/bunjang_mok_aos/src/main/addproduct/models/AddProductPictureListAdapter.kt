package leeyuri.mobile.bunjang_mok_aos.src.main.addproduct.models

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

class AddProductPictureListAdapter(val context: Context, val uriList:ArrayList<String>, private val tvListCount: TextView):
    RecyclerView.Adapter<AddProductPictureListAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val imgPicture = itemView.findViewById<ImageView>(R.id.addproduct_img_picture)
        private val btnX = itemView.findViewById<Button>(R.id.addproduct_btn_x)

        fun bind(uri: String, context: Context, tvListCount:TextView) {
//            이미지
            Glide.with(context)
                .load(uri)
                .into(imgPicture)

//           이미지 삭제
            btnX.setOnClickListener {
                var count = uriList.size
                this.imgPicture.visibility = View.GONE
                this.btnX.visibility = View.GONE
                uriList.remove(uri)

                count-=1
                tvListCount.text = count.toString()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddProductPictureListAdapter.ItemViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.addproduct_picture_items, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: AddProductPictureListAdapter.ItemViewHolder,
        position: Int
    ) {
        holder.bind(uriList[position], context, tvListCount)
    }

    override fun getItemCount(): Int {
        return uriList.size
    }
}
