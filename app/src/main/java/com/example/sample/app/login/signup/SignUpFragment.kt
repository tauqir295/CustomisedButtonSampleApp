package com.example.sample.app.login.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sample.app.R
import com.example.sample.app.databinding.FragmentSignupBinding
import com.example.sample.app.login.ui.LoginFragment
import com.example.sample.app.utils.replaceWithNextFragment
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
        signUpViewModel.data.observe(viewLifecycleOwner, {
            requireActivity().run {
                replaceWithNextFragment(
                    this@SignUpFragment.id,
                    supportFragmentManager,
                    LoginFragment.newInstance(),
                    null,
                        addToBackStack = false
                )
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @return A new instance of fragment SignupFragment.
         */
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }
}