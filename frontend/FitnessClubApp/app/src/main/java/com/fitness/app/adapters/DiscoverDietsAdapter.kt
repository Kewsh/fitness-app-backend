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
import com.fitness.app.databinding.AthleteDiscoverDietItemBinding
import com.fitness.app.databinding.AthleteDiscoverProgramItemBinding
import com.fitness.app.model.DiscoverDiet
import com.fitness.app.util.DiscoverDietDiffUtilCallback
import com.fitness.app.views.fragments.AthleteProgramDescriptionFragment

class DiscoverDietsAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context,
) : ListAdapter<DiscoverDiet, DiscoverDietsAdapter.DiscoverDietViewHolder>(
    DiscoverDietDiffUtilCallback()
) {

    inner class DiscoverDietViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteDiscoverDietItemBinding = AthleteDiscoverDietItemBinding.bind(itemView)
        val image: ImageView = binding.dietImage
        val title: TextView = binding.dietTitle
        var subTitle: TextView = binding.dietSubtitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverDietViewHolder {
        return DiscoverDietViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_discover_diet_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DiscoverDietViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { discoverDiet ->
            holder.apply {
                image.background = BitmapDrawable(context.resources, discoverDiet.image)
                title.text = discoverDiet.title
                subTitle.text = discoverDiet.subTitle

                binding.diet.setOnClickListener {
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