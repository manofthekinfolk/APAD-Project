package com.example.getappengine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.scheduleevent_fragment.view.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONException

/*
data class EventInfo(var eventid: Int=0,var sport: String="", var description: String="", var capacity: Int=0,
                     var starttime : String = "",var endtime : String="", var venue : String = "")
                     */
class SearcheventFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.eventsearch_fragment, container, false)
        view.back_button.setOnClickListener({

                (activity as NavigationHost).navigateTo(HomeFragment(), false)

        })
        view.logout_button.setOnClickListener({
                (activity as NavigationHost).navigateTo(Fragment(), false)
        })
/*
    //add the url connection part here, include the names in val event below
    val event = EventInfo(eventid=,sport = , description = ,capacity = ,starttime = ,endtime = ,venue = )
    val json = Gson().toJson(event)
    fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_convert)
        btn_origin_json.setOnClikListener{
            tv_json.text="Events:\n$json"
        }
        btn_convert_json.setOnClickListener{
            val showevent = Gson().fromJson(json, Eventinfo:: class.java)
            tv_json.text = "\n\tEvent ID=${showevent.eveentid}" +
                    "\n\tSport=${showevent.sport}" +
                    "\n\tDescription=${showevent.description}" +
                    "\n\tCapacity=${showevent.capacity}" +
                    "\n\tStart time=${showevent.starttime}" +
                    "\n\tEnd time=${showevent.endtime}" +
                    "\n\tVenue=${showevent.venue}"
        }
    }
    */
        return view

}}



