package com.fitness.app.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.fitness.app.R
import com.fitness.app.databinding.FragmentAthleteSignUpBinding
import com.fitness.app.util.constructSignUpTerms


class AthleteSignUpFragment : Fragment(R.layout.fragment_athlete_sign_up) {
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
    }

}