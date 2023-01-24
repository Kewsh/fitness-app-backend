package com.fitness.app.views.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.UserCommentAdapter
import com.fitness.app.databinding.FragmentAthleteEventDescriptionBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.views.activities.AthleteHomeActivity
import java.util.concurrent.TimeUnit

class AthleteEventDescriptionFragment : Fragment(R.layout.fragment_athlete_event_description) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteEventDescriptionBinding
    lateinit var commentAdapter: UserCommentAdapter

    var eventId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAthleteEventDescriptionBinding.bind(view)

        val activity = activity as AthleteHomeActivity

        val bundle = this.arguments
        if (bundle != null) {
            eventId = bundle.get("eventId").toString()
        }

        Log.e("eventId",eventId)
        
        setUpEventData()
        setUpEventComments()

        binding.backButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager.popBackStack()
        }

    }

    private fun setUpEventData() {
        viewModel.getEvent(eventId = eventId,context = requireContext()){ event->
            binding.eventCoverPicture.background = BitmapDrawable(resources,event.image)
            binding.eventTitle.text = event.title
            binding.eventDescription.text = event.description
            binding.eventClubName.text = event.club.name
            binding.eventAttendees.text = event.nAttendees.toString()
            binding.eventMaxAttendees.text = event.maxAttendees.toString()
            binding.eventCost.text = event.price.toString()
            binding.eventStartDate.text = event.startDate
            binding.eventEndDate.text = event.endDate
        }

    }

    private fun setUpEventComments(){
        viewModel.getEventComments(eventId = eventId, context = requireContext()){ eventComments->
            binding.userCommentsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
                commentAdapter =
                    UserCommentAdapter(viewLifecycleOwner, context)
                adapter = commentAdapter

            }
            commentAdapter.submitList(eventComments)
        }

    }
}