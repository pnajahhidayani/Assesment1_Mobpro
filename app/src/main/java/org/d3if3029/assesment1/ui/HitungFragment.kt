package org.d3if3029.assesment1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.d3if3029.assesment1.R
import org.d3if3029.assesment1.databinding.ActivityMainBinding
import org.d3if3029.assesment1.databinding.FragmentHitungBinding


class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun nilai(){
        val tugas = binding.tugasInp.text.toString()
        if (TextUtils.isEmpty(tugas)) {
            Toast.makeText(context, R.string.tugas_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val uts = binding.utsInp.text.toString()
        if (TextUtils.isEmpty(uts)) {
            Toast.makeText(context, R.string.uts_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val uas = binding.uasInp.text.toString()
        if (TextUtils.isEmpty(uas)) {
            Toast.makeText(context, R.string.uas_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val hadir = binding.hadirInp.text.toString()
        if (TextUtils.isEmpty(hadir)) {
            Toast.makeText(context, R.string.hadir_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val nilai = (tugas + uts + uas + hadir).toFloat()  / (4)

        binding.nilaiTextView.text = getString(R.string.nilai, nilai)
}
}