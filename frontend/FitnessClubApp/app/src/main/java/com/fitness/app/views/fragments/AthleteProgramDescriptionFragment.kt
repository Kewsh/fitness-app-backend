package com.fitness.app.views.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.DayWorkoutAdapter
import com.fitness.app.adapters.DiscoverProgramsAdapter
import com.fitness.app.adapters.UserCommentAdapter
import com.fitness.app.databinding.FragmentAthleteProgramDescriptionBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.views.activities.AthleteHomeActivity
import java.util.concurrent.TimeUnit

class AthleteProgramDescriptionFragment : Fragment(R.layout.fragment_athlete_program_description) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteProgramDescriptionBinding
    lateinit var commentAdapter: UserCommentAdapter
    lateinit var dayWorkoutAdapter: DayWorkoutAdapter
    lateinit var programAdapter: DiscoverProgramsAdapter

    var programId: Int = -1
    var clubId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAthleteProgramDescriptionBinding.bind(view)

        val activity = activity as AthleteHomeActivity

        val intent = activity.intent
        programId = intent.getIntExtra("programId", -1)
        clubId = intent.getIntExtra("clubId", -1)

        setUpProgramData()
        setUpProgramComments()
        setUpDayWorkouts()
        setUpClubData()
        setUpClubPrograms()

        binding.backButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager.popBackStack()
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

    private fun setUpProgramData() {
        viewModel.getProgram(programId = programId.toString(),context = requireContext()){program->
            binding.programCoverPicture.background = BitmapDrawable(resources,program.image)
            binding.programTitle.text = program.title
            binding.programDescription.text = program.description
            binding.clubName.text = program.club.name
            binding.programCoachName.text = program.coachName
            binding.programRating.text = program.rating.rating
            binding.programNRates.text = program.rating.nRates.toString()
            binding.programDuration.text = program.duration.toString()
            binding.programPrice.text = program.price.toString()
            binding.programNAthletes.text = program.nAthletes.toString()
        }

    }

    private fun setUpClubData() {
        viewModel.getClubById(clubId = clubId.toString(), context = requireContext()){club->
            binding.clubProfilePicture.setImageBitmap(club.image)
            binding.clubName.text = club.name
            binding.clubDescription.text = club.description
            binding.clubNAthletes.text = club.nAthletes.toString()
            binding.clubRating.text = club.rating.rating
            binding.clubNRates.text = club.rating.nRates.toString()
            binding.clubSince.text = club.since
            binding.aboutClubName.text = club.name
            binding.moreClubName.text = club.name
        }
    }

    private fun setUpProgramComments(){
        viewModel.getProgramComments(programId = programId.toString(), context = requireContext()){programComments->
            binding.programCommentsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
                commentAdapter =
                    UserCommentAdapter(viewLifecycleOwner, context)
                adapter = commentAdapter

            }
            commentAdapter.submitList(programComments)
        }

    }

    private fun setUpClubPrograms(){
        viewModel.getClubPrograms(clubId = clubId.toString(), context = requireContext() ){programs->
            binding.programsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                setHasFixedSize(true)
                programAdapter =
                    DiscoverProgramsAdapter(viewLifecycleOwner, context)
                adapter = programAdapter

            }
            programAdapter.submitList(programs)
        }

    }
}