package com.example.sample.app.login.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mobile.design.lib.CustomizableGenericButton
import com.example.sample.app.R
import com.example.sample.app.databinding.FragmentSignupBinding
import com.example.sample.app.login.ui.LoginFragment
import com.example.sample.app.utils.isValidPassword
import com.example.sample.app.utils.removeSelfAndReplaceWithNextFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // data binding is used
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        // data binding is used
        binding.apply {
            binding.fragment = this@SignUpFragment
            binding.viewModel = signUpViewModel
            // Specify the current fragment as the lifecycle owner of the binding.
            // This is necessary so that the binding can observe updates.
            lifecycleOwner = this@SignUpFragment
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.apply {
                title = getString(R.string.sigup)
            }
        }

        setUpObserver()
    }

    /**
     * check value change on viewModel live data
     */
    private fun setUpObserver() {
        signUpViewModel.signedUpData.observe(viewLifecycleOwner, {
            requireActivity().run {
                removeSelfAndReplaceWithNextFragment(
                    containerID = this@SignUpFragment.id,
                    fragmentManager = supportFragmentManager,
                    fragmentToBeAdded = LoginFragment.newInstance(),
                    fragmentToBeRemoved = this@SignUpFragment,
                    arguments = null,
                    addToBackStack = false
                )
            }
        })
    }

    fun onTextChanged(fullName: String?, userName: String?, password: String?) {

        password?.let {
            if (password.isValidPassword()) {

                binding.passwordTil.error = null
                binding.signUpButton.apply {
                    buttonState =
                        if ((fullName?.isNotEmpty() == true && userName?.isNotEmpty() == true)) {
                            CustomizableGenericButton.BUTTON_STATE_ENABLED
                        } else {
                            CustomizableGenericButton.BUTTON_STATE_DISABLED
                        }
                }
            } else {
                binding.passwordTil.error = getString(R.string.invalid_password)
            }
        }
    }

    // click event only when button is enabled
    fun registerUser() {
        if (binding.signUpButton.buttonState == 2) {
            signUpViewModel.run {
                fullName?.let {
                    userName?.let {
                        password?.let {
                            registerUser()
                        }
                    }
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @return A new instance of fragment SignUp Fragment.
         */
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }
}