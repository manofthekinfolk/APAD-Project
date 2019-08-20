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
    fun seteventids(name: Int) {
        this.eventid = name
    }
    fun getEventNames(): String {
        return eventname.toString()
    }

    fun setEventNames(name: String) {
        this.eventname = name
    }

    fun getHostNames(): String {
        return hostname.toString()
    }

    fun setHostNames(name: String) {
        this.hostname = name
    }
    fun getDescriptions(): String {
        return description.toString()
    }

    fun setDescriptions(name: String) {
        this.description = name
    }
    fun getDates(): String {
        return day.toString()
    }

    fun setDates(name: String) {
        this.day = name
    }
    fun getTimeslots(): String {
        return timeslot.toString()
    }

    fun setTimeslots(name: String) {
        this.timeslot = name
    }
    fun getCurrentUsers(): String {
        return currentusers.toString()
    }

    fun setCurrentUsers(name: String) {
        this.currentusers = name
    }
    fun getMaxUsers(): String {
        return maxusers.toString()
    }

    fun setMaxUsers(name: String) {
        this.maxusers = name
    }
    fun getPrices(): String {
        return price.toString()
    }

    fun setPrices(name: String) {
        this.price = name
    }
    fun getVenueIDs(): String {
        return venue_id.toString()
    }

    fun setVenueIDs(name: Int) {
        this.venue_id = name
    }
}