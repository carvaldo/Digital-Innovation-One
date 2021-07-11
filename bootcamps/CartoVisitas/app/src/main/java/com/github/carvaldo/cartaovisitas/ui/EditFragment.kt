package com.github.carvaldo.cartaovisitas.ui

import android.app.Activity.MODE_PRIVATE
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.carvaldo.cartaovisitas.R
import com.github.carvaldo.cartaovisitas.databinding.FragmentEditBinding
import com.github.carvaldo.cartaovisitas.util.ProfileUtil
import com.github.carvaldo.cartaovisitas.viewmodel.CartaoViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

/**
 * [Fragment] para adicionar um cartão de visitas.
 */
class EditFragment : BaseFragment() {
    private lateinit var binding: FragmentEditBinding
    private lateinit var pictureReceiver: ActivityResultLauncher<Intent>
    private var photoFile: File? = null
    private val viewModel by viewModels<CartaoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processarArgumentos()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pictureReceiver = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data?.data != null) {
                configurarFotoCartao(result.data!!.data!!)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pictureReceiver.unregister()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_edit, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_excluir -> {
                viewModel.excluir(viewModel.cartao.value!!)
                Snackbar.make(binding.root, "Cartão excluído", Snackbar.LENGTH_LONG).show()
                findNavController().navigateUp()
                true
            }
            else -> false
        }
    }

    /**
     * Processar imagem e mostrar foto no cartão.
     *
     * @param data Uri da imagem a ser utilizada como foto do cartão.
     */
    private fun configurarFotoCartao(data: Uri) {
        val inputStream = requireActivity().contentResolver.openInputStream(data)
        lifecycleScope.launch(Dispatchers.Main) {
            photoFile = File(requireActivity().getDir("photo_profile", MODE_PRIVATE), System.currentTimeMillis().toString() + ".jpg")
            val imageWidth = binding.photoButton.measuredWidth
            val imageHeight = binding.photoButton.measuredHeight
            val thumbBitmap = ProfileUtil.gerarThumb(inputStream!!, photoFile!!, imageWidth, imageHeight, true)
            showThumb(thumbBitmap)
        }
    }


    /**
     * Mostrar foto do cartão.
     *
     * @param bitmap Recurso de imagem.
     * @return [RoundedBitmapDrawableFactory] Drawable gerado.
     */
    private fun showThumb(bitmap: Bitmap) {
        val drawable = RoundedBitmapDrawableFactory.create(resources, bitmap).apply {
            setAntiAlias(true)
            isCircular = true
            setTargetDensity(resources.displayMetrics)
        }
        binding.photoButton.setImageDrawable(drawable)
    }


    /**
     * Solicitar ao usuário uma imagem através de uma ação do sistema.
     *
     */
    private fun getPicture() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        pictureReceiver.launch(Intent.createChooser(intent, "Selecione uma imagem"))
    }

    override fun processarArgumentos() {
        val result = EditFragmentArgs.fromBundle(requireArguments()).cartao
        viewModel.cartao.postValue(result)
    }

    override fun observar() {
        viewModel.cartao.observe(this) { cartao ->
            binding.apply {
                nomeEdit.setText(cartao.nome)
                emailEdit.setText(cartao.email)
                empresaEdit.setText(cartao.empresa)
                telefoneEdit.setText(cartao.telefone)
                if (cartao.foto != null) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        val bitmap = withContext(Dispatchers.IO) {
                                BitmapFactory.decodeFile(cartao.foto!!)
                            }
                        showThumb(bitmap)
                    }
                }
            }
        }
    }

    override fun registrarListeners() {
        binding.confirmarButton.setOnClickListener {
            viewModel.salvar {
                this.id = viewModel.cartao.value?.id
                this.email = binding.emailEdit.text.toString()
                this.empresa = binding.empresaEdit.text.toString()
                this.nome = binding.nomeEdit.text.toString()
                this.telefone = binding.telefoneEdit.text.toString()
                if (photoFile != null) {
                    this.foto = photoFile?.absolutePath
                } else {
                    this.foto = viewModel.cartao.value?.foto
                }
            }
            Snackbar.make(binding.root, getString(R.string.cartao_salvo), Snackbar.LENGTH_LONG).show()
            findNavController().popBackStack()
        }
        binding.photoButton.setOnClickListener {
            getPicture()
        }
    }
}