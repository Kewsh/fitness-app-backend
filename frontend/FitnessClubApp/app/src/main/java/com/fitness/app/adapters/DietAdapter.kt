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
import com.fitness.app.model.Diet
import com.fitness.app.util.DietDiffUtilCallback

class DietAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
) : ListAdapter<Diet, DietAdapter.DietViewHolder>(DietDiffUtilCallback()) {

    inner class DietViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteTodayDietItemBinding = AthleteTodayDietItemBinding.bind(itemView)
        val image: ImageView = binding.dietImage
        val title: TextView = binding.dietTitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {
        return DietViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_today_diet_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { diet ->
            holder.apply {
                image.setBackgroundResource(diet.image)
                title.text = diet.title
            }

        }

    }

}