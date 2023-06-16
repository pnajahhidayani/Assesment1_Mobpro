package org.d3if3029.assesment1.ui.hitungNilai

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3029.assesment1.model.Hasil
import org.d3if3029.assesment1.model.KategoriNilai
import org.d3if3029.assesment1.network.NilaiApi
import org.d3if3029.assesment1.network.UpdateWorker
import java.util.concurrent.TimeUnit

class HitungViewModel : ViewModel() {
    private val kategori = MutableLiveData<KategoriNilai>()
    private val status = MutableLiveData<NilaiApi.ApiStatus>()


    private val hasil = MutableLiveData<Hasil?>()

    //init {
    fun startNav() {
        navigasi.value = hasil.value?.kategori
    }

    fun endNav() {
        navigasi.value = null
    }

    fun getNav(): LiveData<KategoriNilai?> = navigasi
    private val navigasi = MutableLiveData<KategoriNilai?>()

    private val kategoriNilaiLiveData: MutableLiveData<KategoriNilai> = MutableLiveData()
    fun kategoriNilai(): LiveData<KategoriNilai> = kategoriNilaiLiveData
    fun getNilai(): LiveData<Hasil?> = hasil
    fun hitung(uts: Float, uas: Float, tugas: Float, hadir: Float) {
        val nilai =
            (uts.toDouble() * 0.2) + (uas.toDouble() * 0.35) +
                    (tugas.toDouble() * 0.25) +
                    (hadir.toDouble() * 0.2)
        val kategori: KategoriNilai = if (nilai >= 80.0 && nilai <= 100.0) {
            KategoriNilai.A
        } else if (nilai >= 70.0 && nilai < 80.0) {
            KategoriNilai.B
        } else if (nilai >= 60.0 && nilai < 70.0) {
            KategoriNilai.C
        } else if (nilai >= 50.0 && nilai < 60.0) {
            KategoriNilai.D
        } else {
            KategoriNilai.E
        }
        hasil.value = Hasil(nilai, kategori)
    }

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )

    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(NilaiApi.ApiStatus.LOADING)
            status.postValue(NilaiApi.ApiStatus.SUCCESS)
            status.postValue(NilaiApi.ApiStatus.FAILED)
            ////            try {
////                val result = HewanApi.service.getHewan()
////                Log.d("MainViewModel", "Success: $result")
////            } catch (e: Exception) {
////                Log.d("MainViewModel", "Failure: ${e.message}")
////            }
//        }
        }
    }
    fun getStatus(): LiveData<NilaiApi.ApiStatus> = status
}
