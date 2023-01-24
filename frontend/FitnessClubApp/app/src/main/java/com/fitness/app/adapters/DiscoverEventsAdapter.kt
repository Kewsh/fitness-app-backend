package com.fitness.app.adapters

import android.content.Context
import android.graphics.drawable.BitmapDrawable
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
import com.fitness.app.databinding.AthleteDiscoverEventItemBinding
import com.fitness.app.model.Event
import com.fitness.app.util.DiscoverEventDiffUtilCallback
import com.fitness.app.views.fragments.AthleteProgramDescriptionFragment

class DiscoverEventsAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context,
) : ListAdapter<Event, DiscoverEventsAdapter.DiscoverEventViewHolder>(
    DiscoverEventDiffUtilCallback()
) {

    inner class DiscoverEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteDiscoverEventItemBinding = AthleteDiscoverEventItemBinding.bind(itemView)
        val image: ImageView = binding.eventImage
        val title: TextView = binding.eventTitle
        var subTitle: TextView = binding.eventSubtitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverEventViewHolder {
        return DiscoverEventViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_discover_event_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DiscoverEventViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { discoverEvent ->
            holder.apply {
                image.background = BitmapDrawable(context.resources, discoverEvent.image)
                title.text = discoverEvent.title
                subTitle.text = discoverEvent.club.name

                binding.event.setOnClickListener {
                    val programDescriptionFragment = AthleteProgramDescriptionFragment()
                    val fragmentManager = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    fragmentManager.replace(R.id.athleteHomeMainParentFragment, programDescriptionFragment)
                    fragmentManager.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    fragmentManager.addToBackStack(null)
                    fragmentManager.commit()
                }


            }

        }

    }
}