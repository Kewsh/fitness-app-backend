package com.fitness.app.views.fragments

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.*
import com.fitness.app.databinding.FragmentAthleteHomeBinding
import com.fitness.app.model.Measurement
import com.fitness.app.model.api.request.event.DiscoverEventsRequest
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.views.activities.AthleteHomeActivity


class AthleteHomeFragment : Fragment(R.layout.fragment_athlete_home) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteHomeBinding
    lateinit var workoutAdapter: WorkoutAdapter
    lateinit var foodAdapter: FoodAdapter
    lateinit var recipeAdapter: RecipeAdapter
    lateinit var yourEventsAdapter: YourEventsAdapter
    lateinit var checkoutEventsAdapter: CheckoutEventsAdapter

    var userId: Int = -1
    var firstName: String = ""
    var dietId: Int = -1
    var programId: Int = -1
    var measurements:ArrayList<Measurement> = ArrayList<Measurement>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAthleteHomeBinding.bind(view)
//        val navigator = Navigation.findNavController(view)
        val activity = activity as AthleteHomeActivity

        val intent: Intent = activity.intent

        userId = intent.getIntExtra("userId", -1)
        firstName = intent.getStringExtra("firstName").toString()
        dietId = intent.getIntExtra("dietId", -1)
        programId = intent.getIntExtra("programId", -1)

        Log.e("firstName", firstName)
        Log.e("userId", userId.toString())
        Log.e("dietId", dietId.toString())
        Log.e("programId", programId.toString())
        val args = intent.getBundleExtra("bundle")
        measurements = args?.getSerializable("measurements") as ArrayList<Measurement>
        Log.e("measurements", measurements.toString())

        binding.userFirstName.text = firstName

        setUpUserProfilePic()
        setUpWorkouts()
        setUpFoods()
        setUpRecipes()
        setUpYourEvents()
        setUpCheckoutEvents()
        setUserTargets()

    }

    private fun setUpUserProfilePic() {
        viewModel.getUserProfilePicture(userId = userId.toString(), context = requireContext()){profilePicture->
            binding.profilePic.setImageBitmap(profilePicture)
        }
    }

    private fun setUserTargets() {
        for (measurement in measurements) {
            when (measurement.type) {
                "WEIGHT" -> {
                    measurement.progressPercentage?.let {
                        binding.targetWeightProgress.progress = it
                    }
                    binding.targetWeightKG.text = measurement.target.toString() + " kg"
                    Log.e("meas",measurement.progressPercentage.toString())
                    if(measurement.progressPercentage==100) binding.targetWeightIsDoneImage.visibility = View.VISIBLE
                    else binding.targetWeightIsDoneImage.visibility = View.INVISIBLE
                }
                "BICEP" -> {
                    measurement.progressPercentage?.let {
                        binding.targetBicepProgress.progress = it
                    }
                    binding.targetBicepSize.text = measurement.target.toString()+" cm"
                    if(measurement.progressPercentage==100) binding.targetBicepIsDoneImage.visibility = View.VISIBLE
                    else binding.targetBicepIsDoneImage.visibility = View.INVISIBLE

                }
                "WAIST" -> {
                    measurement.progressPercentage?.let {
                        binding.targetWaistProgress.progress = it
                    }
                    binding.targetWaistWidth.text = measurement.target.toString()+" cm"
                    if(measurement.progressPercentage==100) binding.targetWaistIsDoneImage.visibility = View.VISIBLE
                    else binding.targetWaistIsDoneImage.visibility = View.INVISIBLE

                }
            }
        }
    }

    private fun setUpWorkouts() {
        viewModel.getAllProgramWorkoutItems(
            programId = programId.toString(),
            context = requireContext()
        ) { workouts ->
            binding.workoutsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                setHasFixedSize(true)
                workoutAdapter =
                    WorkoutAdapter(viewLifecycleOwner, context)
                adapter = workoutAdapter

            }
            workoutAdapter.submitList(workouts)
        }
    }

    private fun setUpFoods() {
        viewModel.getAllDietFoodItems(
            dietId = dietId.toString(),
            context = requireContext()
        ) { foodRecipes ->
            binding.foodsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                setHasFixedSize(true)
                foodAdapter =
                    FoodAdapter(viewLifecycleOwner, context)
                adapter = foodAdapter
            }
            foodAdapter.submitList(foodRecipes)
        }
    }

    private fun setUpRecipes() {
        viewModel.getAllDietRecipeItems(
            dietId = dietId.toString(),
            context = requireContext()
        ) { dietRecipes ->
            binding.recipesRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                setHasFixedSize(true)
                recipeAdapter = RecipeAdapter(viewLifecycleOwner, context)
                adapter = recipeAdapter

            }
            recipeAdapter.submitList(dietRecipes)
        }
    }

    private fun setUpYourEvents() {
        viewModel.getAllUserEventsItems(
            userId = userId.toString(),
            context = requireContext()
        ) { userEvents ->
            binding.yourEventsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                setHasFixedSize(true)
                yourEventsAdapter = YourEventsAdapter(viewLifecycleOwner, context)
                adapter = yourEventsAdapter

            }
            yourEventsAdapter.submitList(userEvents)
        }
    }

    private fun setUpCheckoutEvents() {
        val discoverEventRequest = DiscoverEventsRequest(userId)

        viewModel.getAllCheckoutEventsItems(
            discoverEventRequest,
            requireContext()
        ) { checkoutEvents ->
            binding.checkoutEventsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                setHasFixedSize(true)
                checkoutEventsAdapter = CheckoutEventsAdapter(viewLifecycleOwner, context)
                adapter = checkoutEventsAdapter

            }
            checkoutEventsAdapter.submitList(checkoutEvents)
        }
    }
}