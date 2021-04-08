package com.jeongs.workplan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jeongs.workplan.R

class MonthSelectDialog : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View=
        inflater.inflate(R.layout.bottom_sheet,container,false)
        //layout 연결

}