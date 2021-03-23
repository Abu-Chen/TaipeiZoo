package com.abu.taipeizoo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abu.taipeizoo.MainApplication
import com.abu.taipeizoo.R
import com.abu.taipeizoo.model.Area
import com.bumptech.glide.Glide

interface OnAreaClickListener {
    fun onItemClick(area: Area)
}

class AreaViewHolder(itemView: View, clickListener: OnAreaClickListener) :
    RecyclerView.ViewHolder(itemView) {
    private lateinit var area: Area
    private val ivPic = itemView.findViewById<ImageView>(R.id.iv_pic)
    private val tvName = itemView.findViewById<TextView>(R.id.tv_name)
    private val tvInfo = itemView.findViewById<TextView>(R.id.tv_info)
    private val tvMemo = itemView.findViewById<TextView>(R.id.tv_memo)

    init {
        itemView.setOnClickListener {
            clickListener.onItemClick(area)
        }
    }

    fun bindArea(area: Area) {
        this.area = area
        tvName.text = area.name
        tvInfo.text = area.info
        if (area.memo.isEmpty().not()) tvMemo.text = area.memo
        if (area.picUrl.isEmpty().not()) {
            Glide.with(MainApplication.getContext())
                .load(area.picUrl)
                .error(android.R.drawable.ic_menu_report_image)
                .centerCrop()
                .into(ivPic)
        }
    }
}

class AreaListAdapter(private val clickListener: OnAreaClickListener) :
    RecyclerView.Adapter<AreaViewHolder>() {
    var areas: ArrayList<Area> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        return AreaViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_area, parent, false),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        holder.bindArea(areas[position])
    }

    override fun getItemCount(): Int {
        return areas.size
    }
}