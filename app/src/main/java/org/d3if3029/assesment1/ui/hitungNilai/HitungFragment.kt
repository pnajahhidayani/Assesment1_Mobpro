package org.d3if3029.assesment1.ui.hitungNilai

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if3029.assesment1.R
import org.d3if3029.assesment1.SuggestionFragment
import org.d3if3029.assesment1.databinding.FragmentHitungBinding
import org.d3if3029.assesment1.databinding.FragmentSuggestionBinding
import org.d3if3029.assesment1.model.Hasil
import org.d3if3029.assesment1.model.KategoriNilai


class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        ViewModelProvider(this)[HitungViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    fun nilai() {
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
        viewModel.hitung(
            uts.toFloat(),
            uas.toFloat(),
            tugas.toFloat(),
            hadir.toFloat()
        )
        //val nilai = (uts.toDouble() * 0.2) + (uas.toDouble() * 0.35) + (tugas.toDouble() * 0.25) + (hadir.toDouble() * 0.2)
        //ini gak kepake karna udah manggil viewModel
        //binding.nilaiTextView.text = getString(R.string.nilai, nilai)
    }

    private fun showResult(result: Hasil?){
        if (result == null) return
        binding.nilaiTextView.text = getString(R.string.nilai, result.nilai)
        binding.kategoriTextView.text = getString(R.string.kategori, getKategoriLabel(result.kategori))
        binding.buttonGroup.visibility = View.VISIBLE
    }

    private fun getKategoriLabel(kategori: KategoriNilai):String{
        val stringRes = when(kategori){
            KategoriNilai.A->R.string.A
            KategoriNilai.B->R.string.B
            KategoriNilai.C->R.string.C
            KategoriNilai.D->R.string.D
            KategoriNilai.E->R.string.E
        }
        return getString(stringRes)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener { nilai() }
        binding.saranButton.setOnClickListener { viewModel.startNav() }
        binding.shareButton.setOnClickListener { shareData() }

        viewModel.getNilai().observe(requireActivity()) { showResult(it) }
        viewModel.getNav().observe(viewLifecycleOwner) {
            if (it == null) return@observe
            findNavController().navigate(
                HitungFragmentDirections
                    .actionHitungFragmentToSuggestionFragment(it)
            )
            viewModel.endNav()
        }
    }
    private fun shareData() {
        val message = getString(
            R.string.bagikan_template,
            binding.nilaiTextView.text
        )

        val msgKat = message + " " + binding.kategoriTextView.text

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, msgKat)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager
            ) != null
        ) {
            startActivity(shareIntent)
        }
    }
}