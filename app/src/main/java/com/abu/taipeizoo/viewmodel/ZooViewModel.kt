package com.abu.taipeizoo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abu.taipeizoo.model.Area
import com.abu.taipeizoo.model.Plant
import com.abu.taipeizoo.model.TaipeiDataRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ZooViewModel : ViewModel() {
    private val taipeiDataRepo = TaipeiDataRepo()
    private val areaList: MutableLiveData<ArrayList<Area>?> = MutableLiveData()
    private val plantList: MutableLiveData<ArrayList<Plant>?> = MutableLiveData()

    fun getAreaList(): MutableLiveData<ArrayList<Area>?> {
        return areaList
    }

    fun getPlantList(): MutableLiveData<ArrayList<Plant>?> {
        return plantList
    }

    fun syncAreaList() {
        viewModelScope.launch(Dispatchers.IO) {
            val areas = taipeiDataRepo.getAreaList()
            areaList.postValue(areas)
        }
    }

    fun syncPlantList(area: Area?) {
        area?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val plants = taipeiDataRepo.getPlantListByArea(area.name)
                plantList.postValue(plants)
            }
        } ?: plantList.postValue(null)
    }
}