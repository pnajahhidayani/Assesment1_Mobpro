package org.d3if3029.assesment1.model

private fun hitung(uts: Float, uas: Float, tugas: Float, hadir: Float): Hasil{

    val nilai = (uts.toDouble() * 0.2) + (uas.toDouble() * 0.35) + (tugas.toDouble() * 0.25) + (hadir.toDouble() * 0.2)
    val kategori: KategoriNilai = if (nilai >= 80.0 && nilai <= 100.0) {
        KategoriNilai.A
    }else if (nilai >= 70.0 && nilai < 80.0){
        KategoriNilai.B
    }else if (nilai >= 60.0 && nilai <70.0){
        KategoriNilai.C
    }else if (nilai >= 50.0 && nilai < 60.0){
        KategoriNilai.D
    }else {
        KategoriNilai.E
    }
    return Hasil(nilai, kategori)
}