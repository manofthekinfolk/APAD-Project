package com.example.getappengine


class Event_Model {

    var sportname: String? = null
    var description: String? = null
    var starttime: String? = null
    var endtime: String? = null


    fun getsportname(): String {
        return sportname.toString()
    }

    fun setsportname(sportname: String) {
        this.sportname = sportname
    }


    fun getDescriptions(): String {
        return description.toString()
    }

    fun setDescriptions(description: String) {
        this.description = description
    }


    fun getstarttime(): String {
        return starttime.toString()
    }

    fun setstarttime(starttime: String) {
        this.starttime = starttime
    }

    fun getendtime(): String {
        return endtime.toString()
    }

    fun setendtime(endtime: String) {
        this.endtime = endtime
    }


}