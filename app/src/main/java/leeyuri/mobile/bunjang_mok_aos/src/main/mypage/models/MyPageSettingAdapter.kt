package leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import leeyuri.mobile.bunjang_mok_aos.R

class MyPageSettingAdapter(val context: Context, private val settingTitleList: ArrayList<SettingTitle>, val itemClick: (SettingTitle) -> Unit):
    RecyclerView.Adapter<MyPageSettingAdapter.ItemViewHolder>(){

    inner class ItemViewHolder(itemView: View, itemClick: (SettingTitle) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val tvSettingTitle = itemView.findViewById<TextView>(R.id.addproduct_category_tv_title)

        fun bind(settingTitle: SettingTitle, context: Context) {
            tvSettingTitle.text = settingTitle.tv_setting_title

            itemView.setOnClickListener { itemClick(settingTitle) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyPageSettingAdapter.ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mypage_setting_items, parent, false)
        return ItemViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: MyPageSettingAdapter.ItemViewHolder, position: Int) {
        holder.bind(settingTitleList[position], context)
    }

    override fun getItemCount(): Int {
        return settingTitleList.size
    }
}