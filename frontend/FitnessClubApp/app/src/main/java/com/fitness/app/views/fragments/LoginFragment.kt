package com.fitness.app.views.fragments

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.fitness.app.R
import com.fitness.app.api.service.AthleteService
import com.fitness.app.databinding.FragmentLoginBinding
import com.fitness.app.model.api.request.athlete.AthleteLogInRequest
import com.fitness.app.util.constructLoginPageTitle
import com.fitness.app.views.activities.AthleteHomeActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.json.JSONObject
import java.io.Serializable


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
        apiService.logInAthlete(athlete) {athlete->
            if(athlete!=null){
                if(athlete.code==200){
                    Toast.makeText(context,"Login Successfully",Toast.LENGTH_SHORT).show();
                    val intent = Intent(activity, AthleteHomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    val firstName = athlete.data.firstName
                    val userId = athlete.data.id
                    val dietId = athlete.data.dietId
                    val programId = athlete.data.programId
                    val clubId = athlete.data.clubId
                    val measurements = athlete.data.measurements
                    val args = Bundle()
                    intent.putExtra("firstName",firstName)
                    intent.putExtra("userId",userId)
                    intent.putExtra("dietId",dietId)
                    intent.putExtra("programId",programId)
                    intent.putExtra("clubId",clubId)
                    args.putSerializable("measurements",measurements as Serializable)
                    intent.putExtra("bundle",args)
                    requireContext().startActivity(intent)
                }
            }
        }
    }

}