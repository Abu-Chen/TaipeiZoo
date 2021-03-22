package com.abu.taipeizoo.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.abu.taipeizoo.R
import com.abu.taipeizoo.databinding.FragmentArealistBinding
import com.abu.taipeizoo.extension.TAG
import com.abu.taipeizoo.model.Area
import com.abu.taipeizoo.viewmodel.AreaListViewModel

class AreaListFragment : Fragment() {

    private lateinit var viewModel: AreaListViewModel
    private lateinit var viewBinding: FragmentArealistBinding
    private lateinit var adapter: AreaListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_arealist, container, false)
        viewModel = ViewModelProvider(this).get(AreaListViewModel::class.java)
        viewModel.getAreaList().observe(viewLifecycleOwner, { updateList(it) })
        adapter = AreaListAdapter(listener)
        viewBinding.rvListArea.adapter = adapter
        viewBinding.rvListArea.layoutManager = LinearLayoutManager(activity)
        return viewBinding.root
    }

    private val listener = object : OnAreaClickListener {
        override fun onItemClick(area: Area) {
            findNavController().navigate(AreaListFragmentDirections.toAreaFragment(area))
        }
    }

    private fun updateList(areas: ArrayList<Area>?) {
        areas?.let {
            for (area in areas) {
                Log.d(TAG, "Area:${area.name}")
            }
            adapter.areas = areas
        } ?: run {

        }
    }

    override fun onStart() {
        Log.d("ABu", "onStart()")
        super.onStart()
        viewModel.syncAreaList()
    }

    override fun onResume() {
        Log.d("ABu", "onResume()")
        super.onResume()
        //viewModel.syncAreaList()
    }
}