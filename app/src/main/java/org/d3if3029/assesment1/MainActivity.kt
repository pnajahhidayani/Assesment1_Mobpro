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
import org.d3if3029.assesment1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { nilai() }
    }

    private fun nilai(){
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
        val nilai = (tugas + uts + uas + hadir).toFloat()  / (4)

        binding.nilaiTextView.text = getString(R.string.nilai, nilai)
}
}