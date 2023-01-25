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
import com.fitness.app.databinding.AthleteDiscoverDietItemBinding
import com.fitness.app.model.Diet
import com.fitness.app.util.DietDiffUtilCallback
import com.fitness.app.views.fragments.AthleteDietDescriptionFragment

class DiscoverDietsAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context,
    private val userId:Int
) : ListAdapter<Diet, DiscoverDietsAdapter.DiscoverDietViewHolder>(
    DietDiffUtilCallback()
) {

    inner class DiscoverDietViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteDiscoverDietItemBinding = AthleteDiscoverDietItemBinding.bind(itemView)
        val image: ImageView = binding.dietImage
        val title: TextView = binding.dietTitle
        var nutritionistFullName: TextView = binding.dietNutritionistFullName

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverDietViewHolder {
        return DiscoverDietViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_discover_diet_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DiscoverDietViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { diet ->
            holder.apply {
                image.background = BitmapDrawable(context.resources, diet.image)
                title.text = diet.title
                nutritionistFullName.text = diet.nutritionist.fullName

                binding.diet.setOnClickListener {
                    val athleteDietDescriptionFragment = AthleteDietDescriptionFragment()
                    val bundle = Bundle()
                    bundle.putString("dietId",diet.id.toString())
                    bundle.putInt("userId",userId)
                    athleteDietDescriptionFragment.arguments = bundle
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