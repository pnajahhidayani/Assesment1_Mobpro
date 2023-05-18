package org.d3if3029.assesment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import org.d3if3029.assesment1.databinding.ActivityMainBinding
import org.d3if3029.assesment1.model.KategoriNilai



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
//        binding.button.setOnClickListener { nilai() }
    }

    private fun nilai() {
        val tugas = binding.tugasInp.text.toString()
        if (TextUtils.isEmpty(tugas)) {
            Toast.makeText(this, R.string.tugas_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val uts = binding.utsInp.text.toString()
        if (TextUtils.isEmpty(uts)) {
            Toast.makeText(this, R.string.uts_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val uas = binding.uasInp.text.toString()
        if (TextUtils.isEmpty(uas)) {
            Toast.makeText(this, R.string.uas_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val hadir = binding.hadirInp.text.toString()
        if (TextUtils.isEmpty(hadir)) {
            Toast.makeText(this, R.string.hadir_invalid, Toast.LENGTH_LONG).show()
            return
        }

    }
    private  fun getKategoriLabel(kategori: KategoriNilai): String{
        val stringRes = when (kategori) {
            KategoriNilai.A -> R.string.A
            KategoriNilai.B -> R.string.B
            KategoriNilai.C -> R.string.C
            KategoriNilai.D -> R.string.D
            KategoriNilai.E -> R.string.E
        }
        return getString(stringRes)
    }

}