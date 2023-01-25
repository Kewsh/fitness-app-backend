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
import com.fitness.app.databinding.AthleteRecipeItemBinding
import com.fitness.app.model.Recipe
import com.fitness.app.util.FoodDiffUtilCallback
import com.fitness.app.views.fragments.AthleteDietRecipeDescriptionFragment

class RecipeAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
): ListAdapter<Recipe, RecipeAdapter.FoodViewHolder>(FoodDiffUtilCallback()) {

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteRecipeItemBinding = AthleteRecipeItemBinding.bind(itemView)
        val image: ImageView = binding.recipeImage
        val title: TextView = binding.recipeTitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_recipe_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { recipe ->
            holder.apply {
                image.background = BitmapDrawable(context.resources, recipe.image)
                title.text = recipe.title

                binding.recipe.setOnClickListener {
                    val athleteFoodDescriptionFragment = AthleteDietRecipeDescriptionFragment()
                    val bundle = Bundle()
                    bundle.putString("recipeId",recipe.id.toString())
                    athleteFoodDescriptionFragment.arguments = bundle
                    val fragmentManager = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    fragmentManager.replace(R.id.athleteHomeMainParentFragment, athleteFoodDescriptionFragment)
                    fragmentManager.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    fragmentManager.addToBackStack(null)
                    fragmentManager.commit()
                }

            }

        }

    }
}