package com.example.googlemapspractice.Model

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MarkerFaculty(val strgJsonObjMarker: String) {

    lateinit var id: String
    lateinit var name: String
    lateinit var ubication: String
    lateinit var dean: String
    lateinit var latV0: String
    lateinit var latV1: String
    lateinit var logo: String

    companion object {
        @Throws(JSONException::class)
        fun jsonObjectsBuild(myJsonMarker: JSONArray): ArrayList<MarkerFaculty> {
            val markedArray: ArrayList<MarkerFaculty> = ArrayList<MarkerFaculty>()
            for (i in 0 until myJsonMarker.length()) {
                markedArray.add(MarkerFaculty(myJsonMarker.getJSONObject(i).toString()))
            }
            return markedArray
        }
    }

    init {
        val jsonObjMarker: JSONObject = JSONObject(strgJsonObjMarker.toString())
        id = jsonObjMarker.getString("id")
        name = jsonObjMarker.getString("name")
        ubication = jsonObjMarker.getString("ubication")
        dean = jsonObjMarker.getString("dean")
        latV0 = jsonObjMarker.getString("latV0")
        latV1 = jsonObjMarker.getString("latV1")
        logo = jsonObjMarker.getString("logo")
    }

}