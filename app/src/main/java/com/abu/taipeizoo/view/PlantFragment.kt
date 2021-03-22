package com.abu.taipeizoo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.abu.taipeizoo.MainApplication
import com.abu.taipeizoo.R
import com.abu.taipeizoo.databinding.FragmentPlantBinding
import com.abu.taipeizoo.model.Plant
import com.bumptech.glide.Glide

class PlantFragment : Fragment() {
    private lateinit var viewBinding: FragmentPlantBinding
    private lateinit var plant: Plant

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_plant, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        plant = arguments?.let { PlantFragmentArgs.fromBundle(it).plant } ?: return
        (requireActivity() as ZooActivity).supportActionBar?.title = plant.nameCh
        Glide.with(MainApplication.getContext())
            .load(plant.picUrl)
            .into(viewBinding.ivPic)

        viewBinding.tvNameCh.text = plant.nameCh
        viewBinding.tvNameEn.text = plant.nameEn
        viewBinding.tvAlsoKnown.text = "別名\n${plant.alsoKnown}"
        viewBinding.tvBrief.text = "簡介\n${plant.brief}"
        viewBinding.tvFeature.text = "辨識方式\n${plant.feature}"
        viewBinding.tvFunction.text = "功能性\n${plant.function}"
        viewBinding.tvUpdate.text = "最後更新：${plant.update}"

    }
}