package com.example.googlemapspractice.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.googlemapspractice.Model.MarkerFaculty
import com.example.googlemapspractice.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Picasso

class InfoWindow(val context: Context, val markersFaculds: List<MarkerFaculty>) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(marker: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View? {
        var infView: View = LayoutInflater.from(context).inflate(R.layout.info_window, null)

        val faculty: TextView = infView.findViewById<View>(R.id.txtName) as TextView
        val ubication: TextView = infView.findViewById<View>(R.id.txtUbication) as TextView
        val dean: TextView = infView.findViewById<View>(R.id.txtDean) as TextView
        val logo: ImageView = infView.findViewById<View>(R.id.imgLogo) as ImageView

        for (markerFaculd in markersFaculds) {
            if (markerFaculd.id.equals(marker.title)) {
                faculty.setText(markerFaculd.name)
                ubication.setText(markerFaculd.ubication)
                dean.setText(markerFaculd.dean)
                Picasso.get()
                    .load(markerFaculd.logo.toString()).into(logo)
            }
        }

        return infView
    }

}