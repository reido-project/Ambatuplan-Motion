package com.remo.penjadwalan.penjadwalan

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JadwalViewModel : ViewModel(){
    private val _jadwals = MutableStateFlow<List<Jadwal>>(emptyList())
    val jadwals: StateFlow<List<Jadwal>> = _jadwals

    fun create(j: Jadwal){
        val jlist = _jadwals.value.toMutableList()
        jlist.add(j)

        _jadwals.value = jlist
    }

    fun delete(id: Int){
        val jlist = _jadwals.value.toMutableList()

        jlist.removeIf { it.id == id }

        _jadwals.value = jlist
    }
}