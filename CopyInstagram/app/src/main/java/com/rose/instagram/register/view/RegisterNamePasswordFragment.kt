package com.rose.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rose.instagram.R
import com.rose.instagram.common.base.DependencyInjector
import com.rose.instagram.common.util.TxtWatcher
import com.rose.instagram.databinding.FragmentRegisterEmailBinding
import com.rose.instagram.databinding.FragmentRegisterNamePasswordBinding
import com.rose.instagram.register.RegisterEmail
import com.rose.instagram.register.RegisterNameAndPassword
import com.rose.instagram.register.presenter.RegisterEmailPresenter
import com.rose.instagram.register.presenter.RegisterNameAndPasswordPresenter
import kotlinx.coroutines.Job

class RegisterNamePasswordFragment : Fragment(R.layout.fragment_register_name_password),
    RegisterNameAndPassword.View {

    private var binding: FragmentRegisterNamePasswordBinding? = null
    private var fragmentAttchListiner: FragmentAttachListiner? = null

    override lateinit var presenter: RegisterNameAndPassword.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterNamePasswordBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterNameAndPasswordPresenter(this, repository)

        val email = arguments?.getString(KEY_EMAIL) ?: throw IllegalArgumentException("email not found")

        binding?.let {
            with(it){

                loginTextRegister.setOnClickListener {
                    activity?.finish()
                }

                registerNameBtnNext.setOnClickListener {
                    presenter.create(
                        email,
                        registerEditName.text.toString(),
                        registerEditPassword.text.toString(),
                        registerEditConfirm.text.toString()
                    )
                }

                registerEditName.addTextChangedListener(watcher)
                registerEditPassword.addTextChangedListener(watcher)
                registerEditConfirm.addTextChangedListener(watcher)

                registerEditName.addTextChangedListener(TxtWatcher{
                    displayNameFailure(null)
                })

                registerEditPassword.addTextChangedListener(TxtWatcher{
                    displaPasswordFailure(null)
                })

                registerEditConfirm.addTextChangedListener(TxtWatcher{
                    displaPasswordFailure(null)
                })
            }
        }
       // Log.i("teste", email.toString())

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListiner){
            fragmentAttchListiner = context
        }
    }

    private val watcher = TxtWatcher {
        binding?.registerNameBtnNext?.isEnabled =
            binding?.registerEditName?.text.toString().isNotEmpty()
                    && binding?.registerEditPassword?.text.toString().isNotEmpty()
                    && binding?.registerEditConfirm?.text.toString().isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerNameBtnNext?.showProgress(enabled)
    }

    override fun displayNameFailure(nameError: Int?) {
        binding?.registerEditNameInput?.error = nameError?.let { getString(it) }
    }

    override fun displaPasswordFailure(passError: Int?) {
        binding?.registerEditPasswordInput?.error = passError?.let { getString(it) }
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onCreateSucess(name: String) {
        fragmentAttchListiner?.goToWelcomeScreen(name)
    }

    companion object {
        const val KEY_EMAIL = "key_email"
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }


}
