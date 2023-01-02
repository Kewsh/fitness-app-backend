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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.databinding.AthleteDayWorkoutItemBinding
import com.fitness.app.databinding.AthleteDiscoverProgramItemBinding
import com.fitness.app.model.DayWorkout
import com.fitness.app.model.DiscoverProgram
import com.fitness.app.util.DayWorkoutDiffUtilCallback
import com.fitness.app.util.DiscoverProgramDiffUtilCallback

class DiscoverProgramsAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context,
) : ListAdapter<DiscoverProgram, DiscoverProgramsAdapter.DiscoverProgramViewHolder>(DiscoverProgramDiffUtilCallback()) {

    inner class DiscoverProgramViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteDiscoverProgramItemBinding = AthleteDiscoverProgramItemBinding.bind(itemView)
        val image: ImageView = binding.programImage
        val title: TextView = binding.programTitle
        var subTitle: TextView = binding.programSubtitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverProgramViewHolder {
        return DiscoverProgramViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_discover_program_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DiscoverProgramViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { discoverProgram ->
            holder.apply {
                image.setBackgroundResource(R.drawable.temp_program_image)
                title.text = discoverProgram.title
                subTitle.text = discoverProgram.subTitle

            }

        }

    }
}