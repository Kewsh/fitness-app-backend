package com.fitness.app.views.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.*
import com.fitness.app.databinding.FragmentAthleteDiscoverBinding
import com.fitness.app.databinding.FragmentAthleteFitnessBinding
import com.fitness.app.model.api.request.diet.DiscoverDietsRequest
import com.fitness.app.model.api.request.event.DiscoverEventsRequest
import com.fitness.app.model.api.request.program.DiscoverProgramsRequest
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.views.activities.AthleteHomeActivity
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.util.concurrent.TimeUnit

class AthleteDiscoverFragment : Fragment(R.layout.fragment_athlete_discover) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteDiscoverBinding
    lateinit var discoverProgramAdapter: DiscoverProgramsAdapter
    lateinit var discoverEventAdapter: DiscoverEventsAdapter
    lateinit var discoverDietAdapter: DiscoverDietsAdapter

    var userId:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAthleteDiscoverBinding.bind(view)
        val activity = activity as AthleteHomeActivity

        val intent: Intent = activity.intent

        userId = intent.getIntExtra("userId",-1)
        Log.e("userId",userId.toString())
        setUpDiscoverPrograms()

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.e("position",binding.tabs.selectedTabPosition.toString())
                when(binding.tabs.selectedTabPosition){
                    0->setUpDiscoverPrograms()
                    1->setUpDiscoverEvents()
                    2->setUpDiscoverDiets()
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    private fun setUpDiscoverPrograms(){
        val discoverProgramsRequest = DiscoverProgramsRequest(userId)
        viewModel.getAllDiscoverProgramsItems(discoverProgramsRequest,requireContext()){discoverPrograms->
            binding.discoverProgramsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
                discoverProgramAdapter =
                    DiscoverProgramsAdapter(viewLifecycleOwner, context)
                adapter = discoverProgramAdapter

            }
            discoverProgramAdapter.submitList(discoverPrograms)
        }
    }

    private fun setUpDiscoverEvents(){
        val discoverEventsRequest = DiscoverEventsRequest(userId)
        viewModel.getAllDiscoverEventsItems(discoverEventsRequest,requireContext()){discoverEvents->
            binding.discoverProgramsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
                discoverEventAdapter =
                    DiscoverEventsAdapter(viewLifecycleOwner, context)
                adapter = discoverEventAdapter

            }
            discoverEventAdapter.submitList(discoverEvents)
        }
    }

    private fun setUpDiscoverDiets(){
        val discoverDietsRequest = DiscoverDietsRequest(userId)
        viewModel.getAllDiscoverDietsItems(discoverDietsRequest,requireContext()){discoverDiets->
            binding.discoverProgramsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
                discoverDietAdapter =
                    DiscoverDietsAdapter(viewLifecycleOwner, context)
                adapter = discoverDietAdapter

            }
            discoverDietAdapter.submitList(discoverDiets)
        }
    }
}