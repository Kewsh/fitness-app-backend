package com.fitness.app.adapters

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.AthleteYourEventsItemBinding
import com.fitness.app.model.Event
import com.fitness.app.util.YourEventDiffUtilCallback
import com.fitness.app.views.fragments.AthleteEventDescriptionFragment

class YourEventsAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
) : ListAdapter<Event, YourEventsAdapter.YourEventsViewHolder>(YourEventDiffUtilCallback()) {

    inner class YourEventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteYourEventsItemBinding = AthleteYourEventsItemBinding.bind(itemView)
        val image: ImageView = binding.yourEventImage
        val title: TextView = binding.yourEventTitle
        val subtitle: TextView = binding.yourEventSubtitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourEventsViewHolder {
        return YourEventsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_your_events_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: YourEventsViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { event ->
            holder.apply {
                image.background = BitmapDrawable(context.resources, event.image)
                title.text = event.title
                subtitle.text = event.club.name

                binding.event.setOnClickListener {
                    val athleteEventDescriptionFragment = AthleteEventDescriptionFragment()
                    val bundle = Bundle()
                    bundle.putString("eventId",event.id.toString())
                    athleteEventDescriptionFragment.arguments = bundle
                    val fragmentManager = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    fragmentManager.replace(R.id.athleteHomeMainParentFragment, athleteEventDescriptionFragment)
                    fragmentManager.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    fragmentManager.addToBackStack(null)
                    fragmentManager.commit()
                }
            }

        }

    }
}