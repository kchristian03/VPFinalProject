package com.uc.vpfinalproject.Notes

import android.view.View

interface Cardlistener {

    fun onCardClick(position: Int)
    fun onCardClicked(view: View, position: Int)
}