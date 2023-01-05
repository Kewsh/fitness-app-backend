package com.fitness.app.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.UserCommentAdapter
import com.fitness.app.adapters.WhoTriedFoodAdapter
import com.fitness.app.databinding.FragmentAthleteEventDescriptionBinding
import com.fitness.app.databinding.FragmentAthleteFoodDescriptionBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel
import java.util.concurrent.TimeUnit

class AthleteFoodDescriptionFragment : Fragment(R.layout.fragment_athlete_food_description) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteFoodDescriptionBinding
    lateinit var whoTriedFoodAdapter: WhoTriedFoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAthleteFoodDescriptionBinding.bind(view)

        setUpWhoTriedFoods()

        binding.backButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager.popBackStack()
        }

    }

    private fun setUpWhoTriedFoods(){
        binding.userCommentsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            whoTriedFoodAdapter =
                WhoTriedFoodAdapter(viewLifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = whoTriedFoodAdapter
            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        whoTriedFoodAdapter.submitList(viewModel.getAllWhoTriedFoodItems())
    }
}