package com.example.getappengine


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.home_fragment.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.home_fragment, container, false)

        view.searchevent_button.setOnClickListener({
            (activity as NavigationHost).navigateTo(SearcheventFragment(), false)
        })
        view.joinevent_button.setOnClickListener({
            (activity as NavigationHost).navigateTo(JoineventFragment(), false)
        })
        view.scheduledevent_button.setOnClickListener({

            (activity as NavigationHost).navigateTo(ScheduleeventFragment(), false)
        })
        view.information_button.setOnClickListener({
            (activity as NavigationHost).navigateTo(InformationFragment(), false)
        })

        view.logout_button.setOnClickListener({
            (activity as NavigationHost).navigateTo(LoginFragment(), false)
        })

        return view
    }
}
