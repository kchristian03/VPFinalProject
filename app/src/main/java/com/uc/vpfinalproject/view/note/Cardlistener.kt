package com.uc.vpfinalproject.view.note

import android.view.View

interface Cardlistener {

    fun onCardClick(position: Int)
    fun onCardClicked(view: View, position: Int)
}