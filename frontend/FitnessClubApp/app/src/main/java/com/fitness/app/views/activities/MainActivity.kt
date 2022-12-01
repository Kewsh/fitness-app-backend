package com.fitness.app.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.fitness.app.R
import com.fitness.app.adapters.SliderAdapter
import com.fitness.app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var sliderAdapter: SliderAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextSlideButton.setOnClickListener {
            val currPos: Int = binding.slider.currentItem
            if ((currPos + 1) != binding.slider.adapter?.count) {
                binding.slider.currentItem = currPos + 1
            }
            if (currPos == 4) {
                val intent = Intent(this, GetStartedActivity::class.java)
                startActivity(intent)
            }
        }

        sliderAdapter = SliderAdapter(this)
        binding.slider.adapter = sliderAdapter
        binding.slider.addOnPageChangeListener(viewListener)

    }

    private var viewListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.firstDot.setImageResource(R.drawable.ic_slider_dot_active)
                        binding.secondDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.thirdDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.fourDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.fiveDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.nextSlideButton.setImageResource(R.drawable.ic_proceed_btn)

                    }
                    1 -> {
                        binding.firstDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.secondDot.setImageResource(R.drawable.ic_slider_dot_active)
                        binding.thirdDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.fourDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.fiveDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.nextSlideButton.setImageResource(R.drawable.ic_proceed_btn)
                    }
                    2 -> {
                        binding.firstDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.secondDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.thirdDot.setImageResource(R.drawable.ic_slider_dot_active)
                        binding.fourDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.fiveDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.nextSlideButton.setImageResource(R.drawable.ic_proceed_btn)
                    }
                    3 -> {
                        binding.firstDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.secondDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.thirdDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.fourDot.setImageResource(R.drawable.ic_slider_dot_active)
                        binding.fiveDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.nextSlideButton.setImageResource(R.drawable.ic_proceed_btn)
                    }
                    else -> {
                        binding.firstDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.secondDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.thirdDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.fourDot.setImageResource(R.drawable.ic_slider_dot_deactive)
                        binding.fiveDot.setImageResource(R.drawable.ic_slider_dot_active)
                        binding.nextSlideButton.setImageResource(R.drawable.ic_get_started_btn)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        }
}