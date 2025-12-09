package com.rose.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rose.instagram.R
import com.rose.instagram.common.base.DependencyInjector
import com.rose.instagram.common.util.TxtWatcher
import com.rose.instagram.databinding.FragmentRegisterNamePasswordBinding
import com.rose.instagram.databinding.FragmentRegisterWelcomeBinding
import com.rose.instagram.register.RegisterNameAndPassword
import com.rose.instagram.register.presenter.RegisterNameAndPasswordPresenter

class RegisterWelcomeFragment : Fragment(R.layout.fragment_register_welcome) {

    private var binding: FragmentRegisterWelcomeBinding? = null
    private var fragmentAttchListiner: FragmentAttachListiner? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterWelcomeBinding.bind(view)

        val name = arguments?.getString(KEY_NAME) ?: throw IllegalArgumentException("name not found")

        binding?.let {
            with(it) {
                registerTxtWelcome.text = getString(R.string.welcome_to_instagram, name)

                registerBtnNext.isEnabled = true
                registerBtnNext.setOnClickListener {
                    fragmentAttchListiner?.goToPhotoScreen()
                }
            }
            // Log.i("teste", email.toString())

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListiner) {
            fragmentAttchListiner = context
        }
    }

    companion object {
        const val KEY_NAME = "key_name"
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}