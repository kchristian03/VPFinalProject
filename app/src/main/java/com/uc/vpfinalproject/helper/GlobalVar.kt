package com.uc.vpfinalproject.helper

import com.uc.vpfinalproject.model.Note
import java.util.*
import kotlin.collections.ArrayList


class GlobalVar {

    companion object{
        val STORAGE_PERMISSION_CODE: Int = 100
        var listLogs = ArrayList<Note>()
        var currrentUserStreak = 0
        var lastIncrementDate: String? = null
    }

}