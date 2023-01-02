package com.fitness.app.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fitness.app.R
import com.fitness.app.databinding.ActivityAthleteHomeBinding
import com.fitness.app.repository.AthleteHomeRepository
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.viewmodel.AthleteHomeViewModelFactory
import com.fitness.app.views.fragments.AthleteDiscoverFragment
import com.fitness.app.views.fragments.AthleteFitnessFragment
import com.fitness.app.views.fragments.AthleteHomeFragment

class AthleteHomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAthleteHomeBinding
    lateinit var viewModel: AthleteHomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAthleteHomeBinding.inflate(layoutInflater)

        val homeFragment:AthleteHomeFragment = AthleteHomeFragment()
        val fitnessFragment:AthleteFitnessFragment = AthleteFitnessFragment()
        val discoverFragment:AthleteDiscoverFragment = AthleteDiscoverFragment()

        setCurrentFragment(homeFragment)

        try {
            setContentView(binding.root)
            val athleteHomeRepository = AthleteHomeRepository()
            val athleteHomeViewModelFactory = AthleteHomeViewModelFactory(athleteHomeRepository)
            viewModel = ViewModelProvider(this, athleteHomeViewModelFactory)[AthleteHomeViewModel::class.java]

        } catch (exp: Exception) {
            Log.d("Exception", exp.toString())
        }

        binding.athleteBottomNavigationBar.setOnNavigationItemSelectedListener {menuItem->
            when(menuItem.itemId){
                R.id.athleteHomeNav-> setCurrentFragment(homeFragment)
                R.id.athleteFitnessNav-> setCurrentFragment(fitnessFragment)
                R.id.athleteDiscoverNav-> setCurrentFragment(discoverFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.athleteHomeMainParentFragment,fragment)
            commit()
        }
}