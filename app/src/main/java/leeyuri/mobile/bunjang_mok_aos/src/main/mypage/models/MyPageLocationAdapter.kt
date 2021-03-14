package leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import leeyuri.mobile.bunjang_mok_aos.R

class MyPageLocationAdapter(val context: Context, private val locationList: ArrayList<Location>, val itemClick: (Location) -> Unit):
    RecyclerView.Adapter<MyPageLocationAdapter.ItemViewHolder>(){

    inner class ItemViewHolder(itemView: View, itemClick: (Location) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val tvLocation = itemView.findViewById<TextView>(R.id.location_list_items_tv_location)
        private val btnCerti = itemView.findViewById<ImageButton>(R.id.location_list_item_btn_certi)

        fun bind(location: Location, context: Context) {
            tvLocation.text = location.address
            btnCerti.setImageResource(location.isAuth)

            itemView.setOnClickListener { itemClick(location) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyPageLocationAdapter.ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mypage_location_items, parent, false)
        return ItemViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: MyPageLocationAdapter.ItemViewHolder, position: Int) {
        holder.bind(locationList[position], context)
    }

    override fun getItemCount(): Int {
        return locationList.size
    }
}