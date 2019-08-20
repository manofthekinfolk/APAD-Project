package com.example.getappengine

object Global {
    //for login, logout, Schedued Events
    private var user: String? = null

    //variables for event search
    private var eventnamesearch: String? = null
    private var venueidsearch: String? = null


    //for loging,logout,myevents
    fun getUser(): String? {
        return user
    }

    fun setUser(str: String) {
        user = str
    }


    //for event search
    fun getEventNameSearches(): String? {
        return eventnamesearch
    }

    fun setEventNameSearches(str: String) {
        eventnamesearch = str
    }
    fun getVenueIdSearches(): String? {
        return venueidsearch
    }
    fun setVenueIdSearches(str: String) {
        venueidsearch = str
    }



}