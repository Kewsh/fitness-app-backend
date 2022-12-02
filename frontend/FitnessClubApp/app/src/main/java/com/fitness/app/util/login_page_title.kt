package com.fitness.app.util

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.core.text.set
import com.fitness.app.R

fun constructLoginPageTitle(v: View) {
    val textView = v.findViewById(R.id.loginPageTitle) as TextView
    val word: Spannable = SpannableString("Use your credentials to log in to you account\n")

    word.setSpan(
        ForegroundColorSpan(Color.BLACK),
        0,
        word.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    textView.text = word

    val word2: Spannable = SpannableString("By continuing, you agree to our")

    word2.setSpan(
        ForegroundColorSpan(Color.BLACK),
        0,
        word2.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    textView.append(word2)

    val word3: Spannable = SpannableString(" Terms & Conditions ")
    word3.setSpan(
        ForegroundColorSpan(v.resources.getColor(R.color.light_blue)),
        0,
        word3.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    word3.setSpan(StyleSpan(Typeface.BOLD),0,word3.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    textView.append(word3)

    val word4: Spannable = SpannableString("and")

    word4.setSpan(
        ForegroundColorSpan(Color.BLACK),
        0,
        word4.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    textView.append(word4)


    val word5: Spannable = SpannableString(" Privacy Policy")
    word5.setSpan(
        ForegroundColorSpan(v.resources.getColor(R.color.light_blue)),
        0,
        word5.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    word5.setSpan(StyleSpan(Typeface.BOLD),0,word5.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    textView.append(word5)
}