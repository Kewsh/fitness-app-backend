package com.fitness.app.views.fragments

import android.content.DialogInterface
import com.fitness.app.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.fitness.app.api.service.athlete.AthleteService
import com.fitness.app.databinding.FragmentLoginBinding
import com.fitness.app.model.api.request.athlete.AthleteLogInRequest
import com.fitness.app.util.constructLoginPageTitle
import com.google.android.material.dialog.MaterialAlertDialogBuilder


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

        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()
            if(email!="" && pass!="") {
                val athlete =
                    AthleteLogInRequest(email,pass)
                athleteLogin(athlete)
            }
            else{
                MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
                    .setTitle("Failed!")
                    .setMessage("Fields should not be empty!")
                    .setPositiveButton("OK",
                        DialogInterface.OnClickListener { dialogInterface, i ->  })
                    .show()
            }
        }

    }

    private fun athleteLogin(athlete: AthleteLogInRequest) {
        val apiService = AthleteService(requireContext())

        apiService.logInAthlete(athlete) {

        }
    }

}