package org.d3if3029.assesment1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import org.d3if3029.assesment1.databinding.FragmentSuggestionBinding
import org.d3if3029.assesment1.model.KategoriNilai

class SuggestionFragment : Fragment() {

    private lateinit var binding: FragmentSuggestionBinding
    private val args: SuggestionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root

//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_suggestion, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSuggestionBinding.inflate(layoutInflater)
        binding.root
    }

    private fun getSaranBasedOnNilai(kategori: KategoriNilai) {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when (kategori) {
            KategoriNilai.A -> {
                actionBar?.title = getString(R.string.nilai_A)
                binding.textView.text = getString(R.string.saranA)
            }
            KategoriNilai.B -> {
                actionBar?.title = getString(R.string.nilai_B)
                binding.textView.text = getString(R.string.saranB)
            }
            KategoriNilai.C -> {
                actionBar?.title = getString(R.string.nilai_C)
                binding.textView.text = getString(R.string.saranC)
            }
            KategoriNilai.D -> {
                actionBar?.title = getString(R.string.nilai_D)
                binding.textView.text = getString(R.string.saranD)
            }
            KategoriNilai.E -> {
                actionBar?.title = getString(R.string.nilai_E)
                binding.textView.text = getString(R.string.saranE)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getSaranBasedOnNilai(KategoriNilai.A)
    }
}