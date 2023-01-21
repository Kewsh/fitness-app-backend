package com.fitness.app.views.fragments

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.fitness.app.R
import com.fitness.app.api.service.AthleteService
import com.fitness.app.databinding.FragmentAthleteSignUpBinding
import com.fitness.app.model.api.request.athlete.AthleteSignUpRequest
import com.fitness.app.util.constructSignUpTerms
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.views.activities.AthleteHomeActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class AthleteSignUpFragment : Fragment(R.layout.fragment_athlete_sign_up) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteSignUpBinding
    lateinit var navigator: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_athlete_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        constructSignUpTerms(view)

        binding = FragmentAthleteSignUpBinding.bind(view)
        navigator = Navigation.findNavController(view)


        binding.backButton.setOnClickListener{
            navigator.popBackStack()
        }

        binding.signUp.setOnClickListener {
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()
            val confirmPass = binding.confirmPassword.text.toString()
            if(firstName!=""&&lastName!=""&&email!="" && pass!="" && confirmPass!="") {
                if(confirmPass == pass) {
                    val athlete =
                        AthleteSignUpRequest(
                            firstName,lastName,email,pass
                        )
                    athleteSignUp(athlete)
                }
                else{
                    MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
                        .setTitle("Failed!")
                        .setMessage("ConfirmPassword and Password does not match!")
                        .setPositiveButton("OK",
                            DialogInterface.OnClickListener { dialogInterface, i ->  })
                        .show()
                }
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

    private fun athleteSignUp(athlete: AthleteSignUpRequest) {
        val apiService = AthleteService(requireContext())
        apiService.signUpAthlete(athlete) {athlete->
            if(athlete!=null){
                if(athlete.code==201){
                    Toast.makeText(context,"Signup Successfully", Toast.LENGTH_SHORT).show();
                    val intent = Intent(activity, AthleteHomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    requireContext().startActivity(intent)
                }
            }

        }
    }

}