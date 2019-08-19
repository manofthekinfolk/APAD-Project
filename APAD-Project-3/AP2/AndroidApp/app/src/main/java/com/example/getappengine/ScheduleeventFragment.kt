package com.example.getappengine

import androidx.fragment.app.Fragment

class ScheduleeventFragment : Fragment() {
/*
    fun getEvents() : String? {
        val idStr = 3.toString()
        val url = "https://my-apad-project.appspot.com/app/events"+"/"+userId
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
                eventsModel.setOrganizers(dataobj.getString("organizer"))
                eventsModel.setDescriptions(dataobj.getString("description"))
                eventsModel.setTimes(dataobj.getString("time"))
                eventModelArrayList.add(eventsModel)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return eventModelArrayList
    }
    */
}

