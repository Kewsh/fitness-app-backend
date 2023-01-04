package com.fitness.app.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.UserCommentAdapter
import com.fitness.app.databinding.FragmentAthleteEventDescriptionBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel
import java.util.concurrent.TimeUnit

class AthleteEventDescriptionFragment : Fragment(R.layout.fragment_athlete_event_description) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteEventDescriptionBinding
    lateinit var userCommentAdapter: UserCommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAthleteEventDescriptionBinding.bind(view)

        setUpUserComments()

        binding.backButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager.popBackStack()
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
}