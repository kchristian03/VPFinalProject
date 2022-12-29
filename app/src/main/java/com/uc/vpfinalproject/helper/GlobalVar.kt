package com.uc.vpfinalproject.helper

import com.uc.vpfinalproject.model.Note


class GlobalVar {

    companion object{
        val STORAGE_PERMISSION_CODE: Int = 100
        var listLogs = ArrayList<Note>()
    }

}