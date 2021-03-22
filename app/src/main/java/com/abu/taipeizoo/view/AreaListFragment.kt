package com.abu.taipeizoo.view

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.abu.taipeizoo.R
import com.abu.taipeizoo.databinding.FragmentArealistBinding
import com.abu.taipeizoo.model.Area
import com.abu.taipeizoo.viewmodel.ZooViewModel

class AreaListFragment : Fragment() {
    private lateinit var viewModel: ZooViewModel
    private lateinit var viewBinding: FragmentArealistBinding
    private lateinit var adapter: AreaListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_arealist, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(ZooViewModel::class.java)
        viewModel.getAreaList().observe(viewLifecycleOwner, { updateList(it) })
        adapter = AreaListAdapter(object : OnAreaClickListener {
            override fun onItemClick(area: Area) {
                findNavController().navigate(AreaListFragmentDirections.toAreaFragment(area))
            }
        })
        viewBinding.rvListArea.adapter = adapter
        viewBinding.rvListArea.layoutManager = LinearLayoutManager(activity)
    }

    private fun updateList(areas: ArrayList<Area>?) {
        viewBinding.clProgress.visibility = View.GONE
        areas?.let {
            adapter.areas = areas
        } ?: run {
            AlertDialog.Builder(activity).setMessage("Oops! something went wrong.").show()
        }
    }

    override fun onStart() {
        super.onStart()
        viewBinding.clProgress.visibility = View.VISIBLE
        viewModel.syncAreaList()
    }
}