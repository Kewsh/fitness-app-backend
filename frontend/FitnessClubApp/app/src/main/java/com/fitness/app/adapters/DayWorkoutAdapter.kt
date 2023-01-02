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
import com.fitness.app.model.DayWorkout
import com.fitness.app.util.DayWorkoutDiffUtilCallback

class DayWorkoutAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context,
) : ListAdapter<DayWorkout, DayWorkoutAdapter.DayWorkoutViewHolder>(DayWorkoutDiffUtilCallback()) {
    lateinit var workoutAdapter: WorkoutAdapter

    inner class DayWorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteDayWorkoutItemBinding = AthleteDayWorkoutItemBinding.bind(itemView)
        val dayWorkoutIsCheckedImage: ImageView = binding.dayWorkoutIsCheckedImage
        val title: TextView = binding.dayWorkoutTitle
        var workoutsRecyclerView:RecyclerView = binding.workoutsRecyclerView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayWorkoutViewHolder {
        return DayWorkoutViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_day_workout_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DayWorkoutViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { dayWorkout ->
            holder.apply {
                dayWorkoutIsCheckedImage.setBackgroundResource(R.drawable.ic_checked)
                title.text = dayWorkout.title
                workoutsRecyclerView.apply {
                    layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                    setHasFixedSize(true)
                    workoutAdapter = WorkoutAdapter(lifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                    adapter = workoutAdapter
//                    postponeEnterTransition(300, TimeUnit.MILLISECONDS)
//                    viewTreeObserver.addOnPreDrawListener {
//                        startPostponedEnterTransition()
//                        true
//                    }
                }
                workoutAdapter.submitList(dayWorkout.workouts)

            }

        }

    }
}