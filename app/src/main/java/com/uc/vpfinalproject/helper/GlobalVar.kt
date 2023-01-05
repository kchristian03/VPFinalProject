package com.uc.vpfinalproject.helper

import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.uc.vpfinalproject.model.Note
import com.uc.vpfinalproject.model.auth.DataUserResponse
import com.uc.vpfinalproject.model.note.Data
import com.uc.vpfinalproject.model.note.DataNote
import com.uc.vpfinalproject.model.note.GetNoteResponse
import com.uc.vpfinalproject.view.MainActivity
import java.util.*
import kotlin.collections.ArrayList


class GlobalVar {

    companion object{
        val STORAGE_PERMISSION_CODE: Int = 100
        var listLogs = ArrayList<Note>()
        var currrentUserStreak = 0
        var lastIncrementDate: String? = null

        var userData: DataUserResponse? = null
        var listNote = ArrayList<Data>()
    }

}