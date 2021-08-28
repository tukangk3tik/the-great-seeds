package app.ourapps.apps_rebuild.ui.seedp.lbk.panen

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.ourapps.apps_rebuild.databinding.FragmentSeedLbkPanenTambahBinding

class SeedLbkPanenTambahFragment : Fragment() {

    private var _binding: FragmentSeedLbkPanenTambahBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeedLbkPanenTambahBinding.inflate(layoutInflater, container, false)
        val root = binding.root

        // Inflate the layout for this fragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contoh = "12321113"
        binding.edtNokawinan.inputType = InputType.TYPE_NULL
        binding.edtNokawinan.setTextIsSelectable(false)
        binding.edtNokawinan.setText(contoh)
    }

}