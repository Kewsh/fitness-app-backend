package com.fitness.app.views.fragments

import android.graphics.Color
import com.fitness.app.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.fitness.app.databinding.FragmentLoginBinding
import com.fitness.app.util.constructLoginPageTitle
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.transition.MaterialContainerTransform


class LoginFragment : Fragment(R.layout.fragment_login) {
    lateinit var binding: FragmentLoginBinding
    lateinit var navigator: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        constructLoginPageTitle(view)

        binding = FragmentLoginBinding.bind(view)
        navigator = Navigation.findNavController(view)

        binding.forgotPasswordButton.setOnClickListener {
            navigator.navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }

        binding.clubSignUpButton.setOnClickListener{
            navigator.navigate(LoginFragmentDirections.actionLoginFragmentToClubSignUpFragment())
        }

        binding.athleteSignUpButton.setOnClickListener {
            navigator.navigate(LoginFragmentDirections.actionLoginFragmentToAthleteSignUpFragment())
        }


    }

}