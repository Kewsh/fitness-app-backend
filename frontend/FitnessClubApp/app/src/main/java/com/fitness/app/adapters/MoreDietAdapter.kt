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
import com.fitness.app.databinding.AthleteDietMoreItemBinding
import com.fitness.app.model.MoreDiet
import com.fitness.app.util.MoreDietDiffUtilCallback
import com.fitness.app.views.fragments.AthleteDietDescriptionFragment

class MoreDietAdapter (
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context,
) : ListAdapter<MoreDiet, MoreDietAdapter.MoreDietViewHolder>(
    MoreDietDiffUtilCallback()
) {

    inner class MoreDietViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteDietMoreItemBinding = AthleteDietMoreItemBinding.bind(itemView)
        val image: ImageView = binding.dietImage
        val title: TextView = binding.dietTitle
        var subTitle: TextView = binding.dietSubtitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreDietViewHolder {
        return MoreDietViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_diet_more_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoreDietViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { diet ->
            holder.apply {
                image.setBackgroundResource(R.drawable.temp_diet_more_image)
                title.text = diet.title
                subTitle.text = diet.subTitle

                binding.diet.setOnClickListener {
                    val athleteDietDescriptionFragment = AthleteDietDescriptionFragment()
                    val fragmentManager = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    fragmentManager.replace(R.id.athleteHomeMainParentFragment, athleteDietDescriptionFragment)
                    fragmentManager.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    fragmentManager.addToBackStack(null)
                    fragmentManager.commit()
                }


            }

        }

    }
}