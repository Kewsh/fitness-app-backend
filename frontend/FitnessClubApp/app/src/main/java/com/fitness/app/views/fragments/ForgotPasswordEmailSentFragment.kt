package com.fitness.app.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.fitness.app.R
import com.fitness.app.databinding.FragmentClubSignUpBinding
import com.fitness.app.databinding.FragmentForgotPasswordEmailSentBinding
import com.fitness.app.databinding.FragmentLoginBinding
import com.fitness.app.util.constructDidReceiveEmail

class ForgotPasswordEmailSentFragment : Fragment(R.layout.fragment_forgot_password_email_sent) {
    lateinit var binding: FragmentForgotPasswordEmailSentBinding
    lateinit var navigator: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_forgot_password_email_sent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        constructDidReceiveEmail(view)

        binding = FragmentForgotPasswordEmailSentBinding.bind(view)
        navigator = Navigation.findNavController(view)


        binding.backButton.setOnClickListener {
            navigator.popBackStack()
        }
    }
}