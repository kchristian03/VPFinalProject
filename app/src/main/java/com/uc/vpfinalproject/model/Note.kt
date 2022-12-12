package com.uc.vpfinalproject.model

import android.os.Parcel

data class Note(var title: String = "", var notes: String= ""){



    companion object {

        fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }

        fun createFromParcel(p0: Parcel?): Note {
            TODO("Not yet implemented")
        }
    }

}