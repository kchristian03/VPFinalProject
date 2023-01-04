package com.uc.vpfinalproject.model

import android.os.Parcel

data class Note(var id: String, var title: String = "", var content: String= "", var uuid: String?){



    companion object {

        fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }

        fun createFromParcel(p0: Parcel?): Note {
            TODO("Not yet implemented")
        }
    }

}