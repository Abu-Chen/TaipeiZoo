package com.abu.taipeizoo.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.abu.taipeizoo.R
import com.abu.taipeizoo.databinding.FragmentAreaBinding
import com.abu.taipeizoo.databinding.FragmentArealistBinding
import com.abu.taipeizoo.extension.TAG
import com.abu.taipeizoo.model.Area
import com.abu.taipeizoo.viewmodel.AreaListViewModel

class AreaFragment: Fragment() {
    private lateinit var viewModel: AreaListViewModel
    private lateinit var viewBinding: FragmentAreaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_area, container, false)


        viewBinding.tvName.text = arguments?.let { AreaFragmentArgs.fromBundle(it).area?.name }

        return viewBinding.root
    }
}