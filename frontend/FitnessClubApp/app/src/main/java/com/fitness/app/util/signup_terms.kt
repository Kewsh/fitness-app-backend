package com.fitness.app.util

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import com.fitness.app.R

fun constructSignUpTerms(v: View) {
    val textView = v.findViewById(R.id.signUpTerms) as TextView
    val word: Spannable = SpannableString("By signing up, you agree to our")

    word.setSpan(
        ForegroundColorSpan(Color.BLACK),
        0,
        word.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    textView.text = word

    val word2: Spannable = SpannableString(" Terms & Conditions ")
    word2.setSpan(
        ForegroundColorSpan(v.resources.getColor(R.color.light_blue)),
        0,
        word2.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    word2.setSpan(StyleSpan(Typeface.BOLD),0,word2.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    textView.append(word2)

    val word3: Spannable = SpannableString("and")

    word3.setSpan(
        ForegroundColorSpan(Color.BLACK),
        0,
        word3.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    textView.append(word3)


    val word4: Spannable = SpannableString(" Privacy Policy")
    word4.setSpan(
        ForegroundColorSpan(v.resources.getColor(R.color.light_blue)),
        0,
        word4.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    word4.setSpan(StyleSpan(Typeface.BOLD),0,word4.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    textView.append(word4)
}