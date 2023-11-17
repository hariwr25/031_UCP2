package com.example.exercise2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CobaViewModel {
    var nama: String by mutableStateOf("") //revised
        private set
    var nim: String by mutableStateOf("")
        private set
    var konsentrasi: String by mutableStateOf("")
        private set
    var judul: String by mutableStateOf("")
        private set

    fun insertData(nm: String, nmr: String, kns: String, jdl: String) {
        nama = nm;
        nim = nmr;
        konsentrasi = kns;
        judul = jdl;
    }
}