
package com.example.getappengine

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList

/*
class EventsAdapter(private val context: Context, private val eventModelArrayList: ArrayList<Event_Model>) :
    BaseAdapter(){
    //comment out here

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return eventsModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return eventsModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.event, null, true)

            holder.eventid = convertView!!.findViewById(R.id.eventid) as TextView
            holder.sport = convertView!!.findViewbyId(R.id.sport) as TextView
            holder.description = convertView.findViewById(R.id.description) as TextView
            holder.starttime = convertView.findViewById(R.id.starttime) as TextView
            holder.endtime = convertView.findViewById(R.id.endtime) as TextView
            holder.capacity = convertView.findViewById(R.id.capacity) as TextView
            holder.venueid = convertView.findViewById(R.id.venueid) as TextView


            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.eventid!!.text = "Event ID: " + eventsModelArrayList[position].geteventids() //keep
        holder.sport!!.text = "Sport: " + eventsModelArrayList[position].getHostNames() //keep
        holder.description!!.text = "Description " + eventsModelArrayList[position].getDescription()//keep
        holder.starttime!!.text = "Start Time: " + eventsModelArrayList[position].getTimeslots()//adjust
        holder.endtime!!.text = "End Time: " + eventsModelArrayList[position].getTimeslots()//adjust
        holder.capacity!!.text = "Capacity: " + eventsModelArrayList[position].getCapacity()
        holder.venueid!!.text = "VenueID: " + eventsModelArrayList[position].getVenueIDs()

        return convertView
    }

    private inner class ViewHolder {

        var eventid: TextView? = null
        var sport: TextView? = null
        var description: TextView? = null
        var starttime: TextView? = null
        var endtime: TextView? = null
        var capacity: TextView? = null
        var venueid: TextView? = null

    }

//comment out here

}
*/