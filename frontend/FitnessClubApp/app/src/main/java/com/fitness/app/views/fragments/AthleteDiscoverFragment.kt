package com.fitness.app.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.DiscoverProgramsAdapter
import com.fitness.app.adapters.WorkoutAdapter
import com.fitness.app.databinding.FragmentAthleteDiscoverBinding
import com.fitness.app.databinding.FragmentAthleteFitnessBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.views.activities.AthleteHomeActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.util.concurrent.TimeUnit

class AthleteDiscoverFragment : Fragment(R.layout.fragment_athlete_discover) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteDiscoverBinding
    lateinit var discoverProgramAdapter: DiscoverProgramsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAthleteDiscoverBinding.bind(view)
        val activity = activity as AthleteHomeActivity

        setUpDiscoverPrograms()

    }

    private fun setUpDiscoverPrograms(){
        binding.discoverProgramsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            discoverProgramAdapter =
                DiscoverProgramsAdapter(viewLifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = discoverProgramAdapter
            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        discoverProgramAdapter.submitList(viewModel.getAllDiscoverProgramsItems())
    }
}