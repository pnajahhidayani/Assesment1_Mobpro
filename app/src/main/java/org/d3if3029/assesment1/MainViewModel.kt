package org.d3if3029.assesment1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3029.assesment1.model.Hasil
import org.d3if3029.assesment1.model.KategoriNilai

class MainViewModel : ViewModel() {

    private val hasil = MutableLiveData<Hasil?>()
    val nilai:LiveData<Hasil?> get() = hasil

    private val kategoriNilaiLiveData: MutableLiveData<KategoriNilai> = MutableLiveData()
    val kategoriNilai: LiveData<KategoriNilai> get() = kategoriNilaiLiveData

    private fun hitung(uts: Float, uas: Float, tugas: Float, hadir: Float) {
        val nilai =
            (uts.toDouble() * 0.2) + (uas.toDouble() * 0.35) + (tugas.toDouble() * 0.25) + (hadir.toDouble() * 0.2)
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
}