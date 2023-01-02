package com.fitness.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.AthleteTodayDietItemBinding
import com.fitness.app.databinding.AthleteYourEventsItemBinding
import com.fitness.app.model.YourEvent
import com.fitness.app.util.YourEventDiffUtilCallback

class YourEventsAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
) : ListAdapter<YourEvent, YourEventsAdapter.YourEventsViewHolder>(YourEventDiffUtilCallback()) {

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
        getItem(position).let { yourEvent ->
            holder.apply {
                image.setBackgroundResource(yourEvent.image)
                title.text = yourEvent.title
                subtitle.text = yourEvent.subTitle
            }

        }

    }
}