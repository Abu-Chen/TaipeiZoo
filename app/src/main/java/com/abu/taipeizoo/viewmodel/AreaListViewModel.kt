package com.abu.taipeizoo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abu.taipeizoo.model.Area
import com.abu.taipeizoo.model.TaipeiDataRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AreaListViewModel : ViewModel() {
    private val taipeiDataRepo = TaipeiDataRepo()
    private val areaList: MutableLiveData<ArrayList<Area>?> = MutableLiveData()

    fun getAreaList(): MutableLiveData<ArrayList<Area>?> {
        return areaList
    }

    fun syncAreaList() {
        viewModelScope.launch(Dispatchers.IO) {
            val areas = taipeiDataRepo.getZooAreaList()
            areaList.postValue(areas)
        }
    }
}