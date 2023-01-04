package com.fitness.app.adapters

import android.content.Context
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
import com.fitness.app.databinding.AthleteTodayWorkoutItemBinding
import com.fitness.app.model.Workout
import com.fitness.app.util.WorkoutDiffUtilCallback
import com.fitness.app.views.fragments.AthleteProgramDescriptionFragment
import com.fitness.app.views.fragments.AthleteWorkoutDescriptionFragment

class WorkoutAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
) : ListAdapter<Workout, WorkoutAdapter.WorkoutViewHolder>(WorkoutDiffUtilCallback()) {

    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteTodayWorkoutItemBinding = AthleteTodayWorkoutItemBinding.bind(itemView)
        val image: ImageView = binding.workoutImage
        val title: TextView = binding.workoutTitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        return WorkoutViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_today_workout_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { workout ->
            holder.apply {
                image.setBackgroundResource(workout.image)
                title.text = workout.title

                binding.workout.setOnClickListener {
                    val athleteWorkoutDescriptionFragment = AthleteWorkoutDescriptionFragment()
                    val fragmentManager = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    fragmentManager.replace(R.id.athleteHomeMainParentFragment, athleteWorkoutDescriptionFragment)
                    fragmentManager.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    fragmentManager.addToBackStack(null)
                    fragmentManager.commit()
                }

            }
        }

    }
}