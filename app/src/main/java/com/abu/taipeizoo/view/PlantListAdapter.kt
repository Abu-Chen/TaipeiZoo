package com.abu.taipeizoo.view

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abu.taipeizoo.MainApplication
import com.abu.taipeizoo.R
import com.abu.taipeizoo.model.Plant
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

interface OnPlantClickListener {
    fun onItemClick(plant: Plant)
}

class PlantViewHolder(itemView: View, clickListener: OnPlantClickListener) :
    RecyclerView.ViewHolder(itemView) {
    private lateinit var plant: Plant
    private val ivPic = itemView.findViewById<ImageView>(R.id.iv_pic)
    private val tvNameCh = itemView.findViewById<TextView>(R.id.tv_name_ch)
    private val tvAlsoKnown = itemView.findViewById<TextView>(R.id.tv_also_known)

    init {
        itemView.setOnClickListener {
            clickListener.onItemClick(plant)
        }
    }

    fun bindPlant(plant: Plant) {
        this.plant = plant
        tvNameCh.text = plant.nameCh
        tvAlsoKnown.text = plant.brief
        if (plant.picUrl.isEmpty().not()) {
            Glide.with(MainApplication.getContext())
                .load(plant.picUrl)
                .centerCrop()
                .error(android.R.drawable.ic_menu_report_image)
                .into(ivPic)
        }
    }
}

class PlantListAdapter(private val clickListener: OnPlantClickListener) :
    RecyclerView.Adapter<PlantViewHolder>() {
    var plants: ArrayList<Plant> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bindPlant(plants[position])
    }

    override fun getItemCount(): Int {
        return plants.size
    }
}