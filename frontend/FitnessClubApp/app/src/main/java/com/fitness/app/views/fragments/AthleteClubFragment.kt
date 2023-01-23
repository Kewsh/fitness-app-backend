package com.fitness.app.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.DiscoverProgramsAdapter
import com.fitness.app.adapters.YourEventsAdapter
import com.fitness.app.databinding.FragmentAthleteClubBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.views.activities.AthleteHomeActivity
import java.util.concurrent.TimeUnit

class AthleteClubFragment : Fragment(R.layout.fragment_athlete_club) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteClubBinding
    lateinit var eventsAdapter: YourEventsAdapter
    lateinit var programAdapter: DiscoverProgramsAdapter
    var clubId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAthleteClubBinding.bind(view)
        val activity = activity as AthleteHomeActivity

        val intent: Intent = activity.intent

        clubId = intent.getIntExtra("clubId", -1)

        setUpClubPrograms()
        setUpClubEvents()


    }

    private fun setUpClubPrograms(){
        viewModel.getClubPrograms(clubId = clubId.toString(), context = requireContext()){clubPrograms->
            binding.programsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                setHasFixedSize(true)
                programAdapter =
                    DiscoverProgramsAdapter(viewLifecycleOwner, context)
                adapter = programAdapter
            }
            programAdapter.submitList(clubPrograms)
        }

    }

    private fun setUpClubEvents() {
        viewModel.getClubEvents(
            clubId = clubId.toString(),
            context = requireContext()
        ) { clubEvents ->
            binding.eventsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                setHasFixedSize(true)
                eventsAdapter = YourEventsAdapter(viewLifecycleOwner, context)
                adapter = eventsAdapter
            }
            eventsAdapter.submitList(clubEvents)
        }
    }

}