package com.example.googlemapspractice

import android.graphics.Color
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions


class MainActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMapClickListener {
    private var puntos: ArrayList<LatLng> = ArrayList()
    private lateinit var mMap:GoogleMap
    private lateinit var proj: Projection
    private lateinit var coord: Point
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment: SupportMapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

    }
    override fun onMapClick(point: LatLng) {
        proj= mMap.projection
        coord= proj.toScreenLocation(point)

        puntos.add(point)
        if (puntos.size == 4) {
            mMap.clear()
            val lineas = PolylineOptions()

            for (punto: LatLng in puntos) {
                lineas.add(punto)
            }
            lineas.add(puntos[0])


            lineas.width(4f)
            lineas.color(Color.BLACK)
            mMap.addPolyline(lineas)

            puntos = ArrayList()
        }

        mMap.addMarker(
            MarkerOptions().position(point)
                .title("Marcador" + puntos.size)
        )


        Toast.makeText(this.applicationContext,
            "Click\n" +"Lat: "+point.latitude +"\n" +"Lng: "+point.longitude+"\n" +"X: "+coord.x+" - Y: "+coord.y,
            Toast.LENGTH_LONG).show()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        mMap.uiSettings.isZoomControlsEnabled = true
        val camUpd1: CameraUpdate =
            CameraUpdateFactory.newLatLngZoom(
                LatLng(-1.0799, -79.50133), 25F)

        mMap.moveCamera(camUpd1)

        mMap.setOnMapClickListener(this)

        mMap.setOnMarkerClickListener(GoogleMap.OnMarkerClickListener { marker ->
            val point: LatLng=marker.position
            for (i in 0 until puntos.size) {
                if(point== puntos[i]){
                    puntos.removeAt(i)
                    break
                }

            }
            marker.remove()
            mMap.clear()
            val lineas = PolylineOptions()

            for (punto: LatLng in puntos) {
                lineas.add(punto)
            }
            lineas.add(puntos[0])


            lineas.width(4f)
            lineas.color(Color.BLACK)
            mMap.addPolyline(lineas)
            return@OnMarkerClickListener true
        })
    }


}