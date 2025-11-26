package com.rose.instagram.register.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.rose.instagram.R
import com.rose.instagram.common.view.CustomDialog
import com.rose.instagram.databinding.FragmentRegisterPhotoBinding

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {

    private var binding: FragmentRegisterPhotoBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

        val customDialog = CustomDialog(requireContext())
        customDialog.setTitle(R.string.add_photo)
        customDialog.addButton(R.string.photo, R.string.gallery){
            when(it.id){
                R.string.photo ->{
                    Log.i("teste", "foto")
                }
                R.string.gallery ->{
                    Log.i("teste", "galeria")
                }
            }
        }
        customDialog.show()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}