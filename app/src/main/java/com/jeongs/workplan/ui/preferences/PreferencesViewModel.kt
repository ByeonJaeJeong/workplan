package com.jeongs.workplan.ui.preferences

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PreferencesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "환경설정 하는 프래그먼트"
    }
    val text: LiveData<String> = _text
}