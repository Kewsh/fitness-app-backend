package com.fitness.app.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.fitness.app.R
import com.fitness.app.databinding.ActivityGetStartedBinding
import com.fitness.app.repository.AthleteHomeRepository
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.viewmodel.AthleteHomeViewModelFactory

class GetStartedActivity : AppCompatActivity() {
    lateinit var binding: ActivityGetStartedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_get_started)
    }
}