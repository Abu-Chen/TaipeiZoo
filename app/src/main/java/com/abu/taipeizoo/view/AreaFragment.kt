package com.abu.taipeizoo.view

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abu.taipeizoo.MainApplication
import com.abu.taipeizoo.R
import com.abu.taipeizoo.databinding.FragmentAreaBinding
import com.abu.taipeizoo.extension.TAG
import com.abu.taipeizoo.model.Area
import com.abu.taipeizoo.model.Plant
import com.abu.taipeizoo.viewmodel.ZooViewModel
import com.bumptech.glide.Glide

class AreaFragment : Fragment() {
    private lateinit var viewModel: ZooViewModel
    private lateinit var viewBinding: FragmentAreaBinding
    private lateinit var area: Area
    private lateinit var adapter: PlantListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_area, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(ZooViewModel::class.java)
        viewModel.getPlantList().observe(viewLifecycleOwner, { updateList(it) })

        area = arguments?.let { AreaFragmentArgs.fromBundle(it).area } ?: return

        (requireActivity() as ZooActivity).supportActionBar?.title = area.name
        Glide.with(MainApplication.getContext())
            .load(area.picUrl)
            .into(viewBinding.ivPic)
        viewBinding.tvInfo.text = area.info
        viewBinding.tvCategory.text = area.category
        viewBinding.tvLink.setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse(area.url)).let { activity?.startActivity(it) }
        }
        if (area.memo.isNotEmpty()) viewBinding.tvMemo.text = area.memo

        adapter = PlantListAdapter(object : OnPlantClickListener {
            override fun onItemClick(plant: Plant) {
                findNavController().navigate(AreaFragmentDirections.toPlantFragment(plant))
            }
        })
        viewBinding.rvListPlant.adapter = adapter
        viewBinding.rvListPlant.layoutManager = LinearLayoutManager(activity)
        viewBinding.rvListPlant.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d(TAG, "dy:$dy")
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewBinding.clProgress.visibility = View.VISIBLE
        viewModel.syncPlantList(area)
    }

    private fun updateList(plants: ArrayList<Plant>?) {
        viewBinding.clProgress.visibility = View.GONE
        plants?.let {
            adapter.plants = plants
        } ?: run {
            AlertDialog.Builder(activity).setMessage("Oops! something went wrong.").show()
        }
    }
}