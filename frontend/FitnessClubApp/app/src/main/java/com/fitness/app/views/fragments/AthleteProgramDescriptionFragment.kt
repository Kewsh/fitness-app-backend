package com.fitness.app.views.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.transaction
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.DayWorkoutAdapter
import com.fitness.app.adapters.DiscoverProgramsAdapter
import com.fitness.app.adapters.UserCommentAdapter
import com.fitness.app.databinding.FragmentAthleteHomeBinding
import com.fitness.app.databinding.FragmentAthleteProgramDescriptionBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.views.activities.AthleteHomeActivity
import com.google.android.material.textfield.TextInputLayout
import java.util.concurrent.TimeUnit

class AthleteProgramDescriptionFragment : Fragment(R.layout.fragment_athlete_program_description) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteProgramDescriptionBinding
    lateinit var userCommentAdapter: UserCommentAdapter
    lateinit var dayWorkoutAdapter: DayWorkoutAdapter
    lateinit var programAdapter: DiscoverProgramsAdapter

    var programId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAthleteProgramDescriptionBinding.bind(view)

        val activity = activity as AthleteHomeActivity

        val intent = activity.intent
        programId = intent.getIntExtra("programId", -1)

        setUpProgramData()
        setUpUserComments()
        setUpDayWorkouts()
        setUpPrograms()

        binding.backButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager.popBackStack()
        }

    }

    private fun setUpProgramData() {
        viewModel.getProgram(programId = programId.toString(),context = requireContext()){program->
            binding.coverPicture.background = BitmapDrawable(resources,program.image)
            binding.title.text = program.title
            binding.description.text = program.description
            binding.clubName.text = program.club.name
            binding.coachName.text = program.coachName
            binding.rating.text = program.rating.rating
            binding.nRates.text = program.rating.nRates.toString()
            binding.duration.text = program.duration.toString()
            binding.price.text = program.price.toString()
            binding.nAthletes.text = program.nAthletes.toString()

        }

    }

    private fun setUpUserComments(){
        binding.userCommentsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            userCommentAdapter =
                UserCommentAdapter(viewLifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = userCommentAdapter
            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        userCommentAdapter.submitList(viewModel.getAllUserCommentItems())
    }

    private fun setUpDayWorkouts(){
//        binding.dayWorkoutsRecyclerView.apply {
//            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
//            setHasFixedSize(true)
//            dayWorkoutAdapter =
//                DayWorkoutAdapter(viewLifecycleOwner, context)
////            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
//            adapter = dayWorkoutAdapter
//            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
//            viewTreeObserver.addOnPreDrawListener {
//                startPostponedEnterTransition()
//                true
//            }
//
//        }
//
//        dayWorkoutAdapter.submitList(viewModel.getAllDayWorkoutItems())
    }

    private fun setUpPrograms(){
        binding.programsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            setHasFixedSize(true)
            programAdapter =
                DiscoverProgramsAdapter(viewLifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = programAdapter
            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

//        programAdapter.submitList(viewModel.getAllDiscoverProgramsItems())
    }
}