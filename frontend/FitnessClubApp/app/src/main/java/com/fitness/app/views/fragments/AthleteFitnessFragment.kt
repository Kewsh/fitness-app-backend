package com.fitness.app.views.fragments

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.DayWorkoutAdapter
import com.fitness.app.adapters.WorkoutAdapter
import com.fitness.app.databinding.FragmentAthleteFitnessBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.views.activities.AthleteHomeActivity

class AthleteFitnessFragment : Fragment(R.layout.fragment_athlete_fitness) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteFitnessBinding
    lateinit var workoutAdapter: WorkoutAdapter
    lateinit var dayWorkoutAdapter: DayWorkoutAdapter

    var userId: Int = -1
    var programId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAthleteFitnessBinding.bind(view)
        val activity = activity as AthleteHomeActivity

        val intent: Intent = activity.intent
        userId = intent.getIntExtra("userId", -1)
        programId = intent.getIntExtra("programId", -1)

        setUpWorkouts()
        setUpDayWorkouts()
        setUpCurrentProgram()
        setUpUserProfilePic()

        binding.programLayout.program.setOnClickListener {
            val programDescriptionFragment = AthleteProgramDescriptionFragment()
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            fragmentManager.replace(R.id.athleteHomeMainParentFragment, programDescriptionFragment)
            fragmentManager.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            fragmentManager.addToBackStack(null)
            fragmentManager.commit()
        }

    }

    private fun setUpUserProfilePic() {
        viewModel.getUserProfilePicture(userId = userId.toString(), context = requireContext()){profilePicture->
            binding.profilePic.setImageBitmap(profilePicture)
        }
    }

    private fun setUpCurrentProgram() {
        viewModel.getProgram(programId = programId.toString(), context = requireContext()){program->
            binding.programLayout.programImage.background = BitmapDrawable(resources,program.image)
            binding.programLayout.programTitle.text = program.title
            binding.programLayout.programClubName.text = program.clubName
            binding.programLayout.programDescription.text = program.description
        }
    }

    private fun setUpWorkouts(){
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

    private fun setUpDayWorkouts(){
        viewModel.getAllDayProgramWorkoutItems(programId = programId.toString(), context = requireContext()){ dayWorkouts->
            binding.dayWorkoutsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
                dayWorkoutAdapter =
                    DayWorkoutAdapter(viewLifecycleOwner, context)
                adapter = dayWorkoutAdapter
            }
            dayWorkoutAdapter.submitList(dayWorkouts)
        }
    }
}