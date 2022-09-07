package com.example.googlemapspractice.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.googlemapspractice.Adapter.InfoWindow
import com.example.googlemapspractice.Model.MarkerFaculty
import com.example.googlemapspractice.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONArray

class MainActivity :
    AppCompatActivity(),
    OnMapReadyCallback {

    lateinit var mMap: GoogleMap
    val url = "https://my-json-server.typicode.com/kestradalp/marcadoresUteq/markedsUteq"
    var markerList: List<MarkerFaculty> = java.util.ArrayList<MarkerFaculty>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment: SupportMapFragment = getSupportFragmentManager()
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this);

    }

    fun getJsonFromAPI() {
        val queueRequest = Volley.newRequestQueue(this)

        val request = object : StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                markerList = MarkerFaculty.jsonObjectsBuild(JSONArray(response))
                addMarkeds()
                mMap.setInfoWindowAdapter(InfoWindow(this, markerList))

            },
            Response.ErrorListener {
                Log.d("Error: ", it.toString())
            }) {
        }
        queueRequest.add(request)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        mMap.getUiSettings().setZoomControlsEnabled(true)

        val camUpd = CameraUpdateFactory.newLatLngZoom(
            LatLng(-1.080398826, -79.50148388),
            19f
        )
        mMap.moveCamera(camUpd)

        getJsonFromAPI()
    }

    fun addMarkeds() {

        for (marcadorUteq in markerList) {
            val ltFacultad =
                LatLng(
                    marcadorUteq.latV0.toDouble(),
                    marcadorUteq.latV1.toDouble()
                )
            mMap.addMarker(
                MarkerOptions()
                    .position(ltFacultad)
                    .title(marcadorUteq.id)
            )
        }
    }

}

