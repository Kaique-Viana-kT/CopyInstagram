package com.rose.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.rose.instagram.R
import com.rose.instagram.common.base.DependencyInjector
import com.rose.instagram.common.util.TxtWatcher
import com.rose.instagram.databinding.FragmentRegisterEmailBinding
import com.rose.instagram.register.RegisterEmail
import com.rose.instagram.register.presenter.RegisterEmailPresenter

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.View {

    private var binding: FragmentRegisterEmailBinding? = null
    private var fragmentAttachListiner: FragmentAttachListiner? = null

    override lateinit var presenter: RegisterEmail.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterEmailPresenter(this, repository)

        binding = FragmentRegisterEmailBinding.bind(view)

        binding?.let{
            with(it){
                loginTextRegister.setOnClickListener {
                    activity?.finish()
                }
                registerBtnNext.setOnClickListener {
                    presenter.create(
                        registerEditEmail.text.toString()
                    )
                }

                registerEditEmail.addTextChangedListener(watcher)
                registerEditEmail.addTextChangedListener(TxtWatcher{
                    displayEmailFailure(null)
                })
            }
        }
    }

    private val watcher = TxtWatcher {
       binding?.registerBtnNext?.isEnabled = binding?.registerEditEmail?.text.toString().isNotEmpty()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is FragmentAttachListiner){
            fragmentAttachListiner = context
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerBtnNext?.showProgress(enabled)
    }


    override fun displayEmailFailure(emailError: Int?) {
        binding?.registerEditEmailInput?.error = emailError?.let {getString(it)}
    }

    override fun onEmailFailure(message: String) {
        binding?.registerEditEmailInput?.error = message
    }

    override fun goToNameAndPasswordScreen(email: String) {
        fragmentAttachListiner?.goToNameAndPasswordScreen(email)
    }

    override fun onDestroy() {
        binding = null
        fragmentAttachListiner = null
        presenter.onDestroy()
        super.onDestroy()
    }

}