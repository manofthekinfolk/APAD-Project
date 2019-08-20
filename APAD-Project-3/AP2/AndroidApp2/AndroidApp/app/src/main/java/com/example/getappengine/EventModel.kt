package com.example.getappengine



import java.util.*

class Event_Model {
    var eventid: Int? = null
    var eventname: String? = null
    var hostname: String? = null
    var description: String? = null
    var starttime: String? = null
    var endtime: String? = null
    var capacity: String? = null
    var venue_id: Int? = null

    fun geteventids(): String {
        return eventid.toString()
    }

    fun geteventids(name: Int) {
        this.eventid = name
    }
    fun seteventids(name: Int) {
        this.eventid = name
    }

    fun getDescriptions(): String {
        return description.toString()
    }

    fun setDescriptions(name: String) {
        this.description = name
    }
/*
    fun getTimeslots(): String {
        return timeslot.toString()
    }

    fun setTimeslots(name: String) {
        this.timeslot = name
    }

    fun getTimeslots(): String {
        return timeslot.toString()
    }

    fun setTimeslots(name: String) {
        this.timeslot = name
    }

    fun getMaxUsers(): String {
        return capacity.toString()
    }
*/
    fun setMaxUsers(name: String) {
        this.capacity = name
    }

    fun getVenueIDs(): String {
        return venue_id.toString()
    }

    fun setVenueIDs(name: Int) {
        this.venue_id = name
    }
}
