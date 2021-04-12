package com.example.sample.app.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sample.app.MainActivity
import com.example.sample.app.R
import com.example.sample.app.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // data binding is used
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // data binding is used
        binding.apply {
            binding.viewModel = loginViewModel

            // Specify the current fragment as the lifecycle owner of the binding.
            // This is necessary so that the binding can observe updates.
            lifecycleOwner = this@LoginFragment
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.apply {
                title = getString(R.string.title_login)
            }
        }

        setUpObserver()
    }

    /**
     * check value change on viewModel live data
     */
    private fun setUpObserver() {
        loginViewModel.data.observe(viewLifecycleOwner, {
            (requireActivity() as LoginActivity).run {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment LoginFragment.
         */
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}