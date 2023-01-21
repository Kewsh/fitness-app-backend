package com.fitness.app.model

import android.graphics.Bitmap

data class CheckoutEvent(
    val image: Bitmap,
    var title:String,
    val subTitle:String,
)
