package com.fitness.app.views.fragments

import com.fitness.app.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fitness.app.util.constructLoginPageTitle
import com.google.android.material.textfield.TextInputEditText


class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_login, container, false)

        constructLoginPageTitle(v)

        return v

    }

}