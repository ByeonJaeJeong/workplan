package com.jeongs.workplan.ui.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jeongs.workplan.R
import com.jeongs.workplan.ui.calendar.SharedViewModel

class PreferencesFragment : Fragment() {

    private lateinit var notificationsViewModel: PreferencesViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            sharedViewModel= ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(SharedViewModel::class.java)
        }
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(PreferencesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_preferences, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)


        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it

        })
        return root
    }
}