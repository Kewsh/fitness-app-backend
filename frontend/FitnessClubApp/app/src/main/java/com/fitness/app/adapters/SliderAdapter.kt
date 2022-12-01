package com.fitness.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.fitness.app.R
import com.fitness.app.model.Slide

class SliderAdapter(
    private val context: Context
) : PagerAdapter() {
    private val slidesList: ArrayList<Slide> = ArrayList()
    private val slidesTitle:ArrayList<String> = ArrayList()
    private val slidesDescription:ArrayList<String> = ArrayList()
    private val slidesImage:ArrayList<Int> = ArrayList()

    init {
        initSlidesResources()
        for(i in 0 until 5){
            slidesList.add(
                Slide(
                    slidesTitle[i],
                    slidesDescription[i],
                    slidesImage[i]
                )
            )
        }
    }

    private fun initSlidesResources(){
        slidesTitle.add("CHOOSE YOUR PROGRAM")
        slidesTitle.add("PARTICIPATE IN EVENTS")
        slidesTitle.add("SET GOALS AND TARGETS")
        slidesTitle.add("PICK YOUR FAVORITE DIET")
        slidesTitle.add("BECOME THE BEST VERSION OF YOU")
        slidesDescription.add("Pick any of the thousands of fitness programs that fits you best! You can find hundreds of clubs offering various types of programs!")
        slidesDescription.add("Clubs can arrange events where all members can participate, sometimes to win a prize! We will notify you whenever there’s a new event waiting for you!")
        slidesDescription.add("You can set goals and targets to keep you motivated on your journey! You can see your progress and share it with others at your club!")
        slidesDescription.add("Our nutritionists have provided you with various healthy diets! Explore them and pick the one that best helps you reach your goals!")
        slidesDescription.add("Together, we’ll embark on a journey of self-improvement and progress, and we won’t stop until we’ve found the best version of you!")
        slidesImage.add(R.drawable.welcome_slider_img1)
        slidesImage.add(R.drawable.welcome_slider_img2)
        slidesImage.add(R.drawable.welcome_slider_img3)
        slidesImage.add(R.drawable.welcome_slider_img4)
        slidesImage.add(R.drawable.welcome_slider_img5)
    }

    override fun getCount(): Int {
        return slidesList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view: View = layoutInflater.inflate(R.layout.slider_item, container, false)

        val imageView: ImageView = view.findViewById(R.id.slideImage)
        val sliderHeadingTV: TextView = view.findViewById(R.id.slideTitle)
        val sliderDescTV: TextView = view.findViewById(R.id.slideDescription)

        val sliderData: Slide = slidesList[position]
        sliderHeadingTV.text = sliderData.slideTitle
        sliderDescTV.text = sliderData.slideDescription
        imageView.setImageResource(sliderData.slideImage)

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

}