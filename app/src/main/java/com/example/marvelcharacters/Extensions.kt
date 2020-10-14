package com.example.marvelcharacters

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.domain.Image

fun View.hideKeyboard(){
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken,0)
}

fun Image.getImage():String{
    return "${this.path}.${this.extension}"
}