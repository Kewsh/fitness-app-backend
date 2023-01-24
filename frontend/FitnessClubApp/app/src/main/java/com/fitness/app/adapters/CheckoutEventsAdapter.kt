package com.fitness.app.adapters

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.util.Log
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
import com.fitness.app.databinding.AthleteCheckoutEventsItemBinding
import com.fitness.app.model.CheckoutEvent
import com.fitness.app.model.Event
import com.fitness.app.util.CheckoutEventDiffUtilCallback
import com.fitness.app.views.fragments.AthleteEventDescriptionFragment

class CheckoutEventsAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
) : ListAdapter<Event, CheckoutEventsAdapter.CheckoutEventsViewHolder>(CheckoutEventDiffUtilCallback()) {

    inner class CheckoutEventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteCheckoutEventsItemBinding = AthleteCheckoutEventsItemBinding.bind(itemView)
        val image: ImageView = binding.checkoutEventImage
        val title: TextView = binding.checkoutEventTitle
        val subtitle: TextView = binding.checkoutEventSubtitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutEventsViewHolder {
        return CheckoutEventsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_checkout_events_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CheckoutEventsViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { checkoutEvent ->
            holder.apply {
                image.background = BitmapDrawable(context.resources, checkoutEvent.image)
                title.text = checkoutEvent.title
                subtitle.text = checkoutEvent.club.name

                binding.event.setOnClickListener {
                    val athleteEventDescriptionFragment = AthleteEventDescriptionFragment()
                    val fragmentManager = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    fragmentManager.replace(R.id.athleteHomeMainParentFragment, athleteEventDescriptionFragment)
                    fragmentManager.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    fragmentManager.addToBackStack(null)
                    fragmentManager.commit()
                }
            }

        }

    }
}