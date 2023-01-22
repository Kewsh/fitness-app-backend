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
import com.fitness.app.databinding.AthleteDietPlanItemBinding
import com.fitness.app.model.DietPlan
import com.fitness.app.util.DietPlanDiffUtilCallback

class DietPlansAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context,
) : ListAdapter<DietPlan, DietPlansAdapter.DietPlanViewHolder>(DietPlanDiffUtilCallback()) {
    lateinit var foodAdapter: FoodAdapter

    inner class DietPlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteDietPlanItemBinding = AthleteDietPlanItemBinding.bind(itemView)
        val dietPlanIsCheckedImage: ImageView = binding.dietPlanIsCheckedImage
        val title: TextView = binding.dietPlanTitle
        var foodsRecyclerView: RecyclerView = binding.foodsRecyclerView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietPlanViewHolder {
        return DietPlanViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_diet_plan_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DietPlanViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { dietPlan ->
            holder.apply {
                dietPlanIsCheckedImage.setBackgroundResource(R.drawable.ic_checked)
                title.text = dietPlan.title
                foodsRecyclerView.apply {
                    layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                    setHasFixedSize(true)
                    foodAdapter = FoodAdapter(lifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                    adapter = foodAdapter
//                    postponeEnterTransition(300, TimeUnit.MILLISECONDS)
//                    viewTreeObserver.addOnPreDrawListener {
//                        startPostponedEnterTransition()
//                        true
//                    }
                }
                foodAdapter.submitList(dietPlan.foods)

            }

        }

    }
}