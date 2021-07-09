package com.github.carvaldo.cartaovisitas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.carvaldo.cartaovisitas.R
import com.github.carvaldo.cartaovisitas.databinding.FragmentFirstBinding
import com.github.carvaldo.cartaovisitas.databinding.FragmentListBinding

/**
 * [Fragment] da lista de cartões.
 */
class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adicionarButton.setOnClickListener {
            findNavController().navigate(R.id.action_lista_para_adicao)
        }
    }
}