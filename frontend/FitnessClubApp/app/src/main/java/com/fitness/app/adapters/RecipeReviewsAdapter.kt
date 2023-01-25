package com.fitness.app.adapters

import android.content.Context
import android.graphics.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RecipeReviewItemBinding
import com.fitness.app.model.RecipeReview
import com.fitness.app.util.RecipeReviewDiffUtilCallback


class RecipeReviewsAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
) : ListAdapter<RecipeReview, RecipeReviewsAdapter.RecipeReviewViewHolder>(
    RecipeReviewDiffUtilCallback()
) {

    inner class RecipeReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: RecipeReviewItemBinding = RecipeReviewItemBinding.bind(itemView)
        val image: ImageView = binding.recipeReviewImage
        val userFirstName: TextView = binding.recipeReviewUserFirstName
        val rating: TextView = binding.recipeReviewRating
        val description: TextView = binding.recipeReviewText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeReviewViewHolder {
        return RecipeReviewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_review_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecipeReviewViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { recipeReview ->
            holder.apply {
                image.setImageBitmap(getRoundedImage(recipeReview.image))
                userFirstName.text = recipeReview.comment.user.firstName
                rating.text = recipeReview.comment.rate.toString()
                description.text = recipeReview.comment.text
            }

        }

    }

    private fun getRoundedImage(image:Bitmap) : Bitmap{
        val imageRounded = Bitmap.createBitmap(
            image.width,
            image.height,
            image.config
        )
        val canvas = Canvas(imageRounded)
        val mpaint = Paint()
        mpaint.isAntiAlias = true
        mpaint.shader = BitmapShader(
            image,
            Shader.TileMode.CLAMP,
            Shader.TileMode.CLAMP
        )
        canvas.drawRoundRect(
            RectF(0f, 0f, image.width.toFloat(),
                image.height.toFloat()
            ),
            100f,
            100f,
            mpaint
        ) // Round Image Corner 100 100 100 100

        return imageRounded
    }
}