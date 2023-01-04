package com.fitness.app.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.fitness.app.R
import com.fitness.app.databinding.ActivityAthleteHomeBinding
import com.fitness.app.repository.AthleteHomeRepository
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.viewmodel.AthleteHomeViewModelFactory
import com.fitness.app.views.fragments.*

class AthleteHomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAthleteHomeBinding
    lateinit var viewModel: AthleteHomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAthleteHomeBinding.inflate(layoutInflater)

        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        val homeFragment:AthleteHomeFragment = AthleteHomeFragment()
        val fitnessFragment:AthleteFitnessFragment = AthleteFitnessFragment()
        val discoverFragment:AthleteDiscoverFragment = AthleteDiscoverFragment()
        val dietFragment:AthleteDietFragment = AthleteDietFragment()
        val clubFragment:AthleteClubFragment = AthleteClubFragment()

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
                R.id.athleteDietNav-> setCurrentFragment(dietFragment)
                R.id.athleteClubNav-> setCurrentFragment(clubFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.athleteHomeMainParentFragment,fragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            commit()
        }
}