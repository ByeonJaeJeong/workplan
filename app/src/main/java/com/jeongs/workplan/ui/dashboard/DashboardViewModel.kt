package com.jeongs.workplan.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private var _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    var text: MutableLiveData<String> = _text
}