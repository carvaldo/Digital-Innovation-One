package com.github.carvaldo.cartaovisitas.ui

import android.app.Activity.MODE_PRIVATE
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.carvaldo.cartaovisitas.R
import com.github.carvaldo.cartaovisitas.databinding.FragmentAddBinding
import com.github.carvaldo.cartaovisitas.util.ProfileUtil
import com.github.carvaldo.cartaovisitas.viewmodel.CartaoViewModel
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.lifecycleScope
import br.com.concrete.canarinho.watcher.MascaraNumericaTextWatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

/**
 * [Fragment] para adicionar um cartão de visitas.
 */
class AddFragment : BaseFragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var pictureReceiver: ActivityResultLauncher<Intent>
    private var photoFile: File? = null
    private val viewModel by viewModels<CartaoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        binding.telefoneEdit.addTextChangedListener(MascaraNumericaTextWatcher("(##) #####-####"))
        return binding.root
    }

    override fun registrarListeners() {
        binding.confirmarButton.setOnClickListener {
            viewModel.salvar {
                this.email = binding.emailEdit.text.toString()
                this.empresa = binding.empresaEdit.text.toString()
                this.nome = binding.nomeEdit.text.toString()
                this.telefone = binding.telefoneEdit.text.toString()
                this.foto = photoFile?.absolutePath
            }
            Snackbar.make(binding.root, getString(R.string.cartao_salvo), Snackbar.LENGTH_LONG).show()
            findNavController().navigateUp()
        }
        binding.photoButton.setOnClickListener {
            getPicture()
        }
    }

    override fun observar() {
    }

    override fun processarArgumentos() {
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
            binding.photoButton.setImageDrawable(makeDrawableThumb(thumbBitmap))
        }
    }


    /**
     * Gerar [RoundedBitmapDrawableFactory] para foto do cartão.
     *
     * @param bitmap Recurso de imagem.
     * @return [RoundedBitmapDrawableFactory] Drawable gerado.
     */
    private fun makeDrawableThumb(bitmap: Bitmap) = RoundedBitmapDrawableFactory.create(resources, bitmap).apply {
        setAntiAlias(true)
        isCircular = true
        setTargetDensity(resources.displayMetrics)
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
}