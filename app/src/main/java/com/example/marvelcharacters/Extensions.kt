package com.example.marvelcharacters


import com.example.domain.Image


fun Image.getImage():String{
    val newPath = this.path?.replace("http","https")
    return "${newPath}.${this.extension}"
}