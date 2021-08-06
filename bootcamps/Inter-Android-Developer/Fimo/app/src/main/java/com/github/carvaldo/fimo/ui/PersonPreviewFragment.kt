package com.github.carvaldo.fimo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.github.carvaldo.fimo.databinding.FragmentPreviewPersonBinding
import com.github.carvaldo.fimo.databinding.ListItemCastMovieBinding
import com.github.carvaldo.fimo.datasource.remote.response.CastMoviesItem
import com.github.carvaldo.fimo.datasource.remote.response.PersonData
import com.github.carvaldo.fimo.viewModel.PersonViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private val TAG = PersonPreviewFragment::class.simpleName

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class PersonPreviewFragment : Fragment() {
    private lateinit var binding: FragmentPreviewPersonBinding
    @Inject lateinit var adapter: Adapter
    private val viewModel: PersonViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentPreviewPersonBinding.inflate(inflater, container, false)
        loadData()
        return binding.root
    }

    private fun loadData() {
        viewModel.find(PersonPreviewFragmentArgs.fromBundle(requireArguments()).apiId)
            .observe(requireActivity()) {
                if (!it.errorMessage.isNullOrBlank()) {
                    TODO("Feedback")
                } else if (it.data != null){
                    bindView(it.data)
                }
            }
    }

    private fun bindView(person: PersonData) {
        binding.descriptionText.text = person.summary
        binding.roleText.text = person.role
        binding.nameText.text = person.name
        bindRecyclerView(person.castMovies)
        binding.castListView.adapter = adapter
        binding.mainImage.uri = person.image?.toUri()
    }

    private fun bindRecyclerView(items: List<CastMoviesItem>?) {
        binding.castListView.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        adapter.items = items
        adapter.onItemClickListener = {
            findNavController().navigate(PersonPreviewFragmentDirections.actionPersonPreviewFragmentToSecondFragment(it.id!!))
        }
    }

    class Adapter @Inject constructor(): RecyclerView.Adapter<Adapter.ViewHolder>() {
        var items: List<CastMoviesItem>? = null
            set(value) {
                field = value
                notifyDataSetChanged()
            }
        var onItemClickListener: ((castMovie: CastMoviesItem) -> Unit) ? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(ListItemCastMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.toBind(items!![holder.adapterPosition])
        }

        override fun getItemCount() = items?.size ?: 0

        inner class ViewHolder(val binding: ListItemCastMovieBinding)
            : RecyclerView.ViewHolder(binding.root) {
            fun toBind(item: CastMoviesItem) {
                binding.descriptionText.text = item.description
                binding.titleText.text = item.title
                binding.root.setOnClickListener { onItemClickListener?.invoke(item) }
            }
        }

    }
}