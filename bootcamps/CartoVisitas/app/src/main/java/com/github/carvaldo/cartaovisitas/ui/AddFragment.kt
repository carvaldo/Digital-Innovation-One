package com.github.carvaldo.cartaovisitas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.carvaldo.cartaovisitas.R
import com.github.carvaldo.cartaovisitas.databinding.FragmentAddBinding
import com.github.carvaldo.cartaovisitas.viewmodel.MainViewModel

/**
 * [Fragment] para adicionar um cartão de visitas.
 */
class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val viewModel by lazy { MainViewModel.getViewModelFrom(requireActivity(), this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.confirmarButton.setOnClickListener {
            viewModel.salvar {
                this.email = binding.emailEdit.text.toString()
                this.empresa = binding.empresaEdit.text.toString()
                this.nome = binding.nomeEdit.text.toString()
                this.telefone = binding.telefoneEdit.text.toString()
            }
            findNavController().popBackStack()
        }
    }
}