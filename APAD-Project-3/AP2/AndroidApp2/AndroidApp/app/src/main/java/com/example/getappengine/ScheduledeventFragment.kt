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

class ScheduledeventFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.scheduleevent_fragment, container, false)

        view.back_button.setOnClickListener({
            (activity as NavigationHost).navigateTo(HomeFragment(), false)
        })

        view.logout_button.setOnClickListener({
            (activity as NavigationHost).navigateTo(LoginFragment(), false)
        })
        // Set an error if the password is less than 8 characters.
        /*
        view.showevent_button.setOnClickListener({

            fun getEvents() : String? {
                val idStr = 3.toString()
                val url = "https://cloudsql-pickupsport.appspot.com/events"+"/"+//userID
                //val url = "https://my-apad-project.appspot.com/app/events"+"/"+8
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(url)
                    .header("User-Agent", "Android")
                    .build()
                val response = client.newCall(request).execute()
                val bodyStr =  response?.body()?.string() // this can be consumed only once
                println("bodyStr")
                println(bodyStr)
                return bodyStr
            }
            fun getInfo(response: String): ArrayList<Event_Model> {
                val eventModelArrayList = ArrayList<Event_Model>()
                try {
                    val dataArray = JSONArray(response)
                    for (i in 0 until dataArray.length()) {
                        val eventsModel = Event_Model()
                        val dataobj = dataArray.getJSONObject(i)
                        eventsModel.setDescriptions(dataobj.getString("description"))
                        eventModelArrayList.add(eventsModel)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                return eventModelArrayList
            }

*/
        return view

    }
}

