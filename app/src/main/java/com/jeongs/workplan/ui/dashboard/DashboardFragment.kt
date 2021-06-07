package com.jeongs.workplan.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jeongs.workplan.R
import com.jeongs.workplan.ui.home.SharedViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var sharedViewModel : SharedViewModel

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
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        var nStmt_yearEnd_textView= root.findViewById<TextView>(R.id.nStmt_yearEnd_textView)
        dashboardViewModel.text.observe(viewLifecycleOwner,{
            nStmt_yearEnd_textView.text=it
        })


        return root
    }
}