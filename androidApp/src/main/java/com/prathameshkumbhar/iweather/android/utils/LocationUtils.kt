package com.prathameshkumbhar.iweather.android.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import com.google.android.gms.location.LocationServices

@SuppressLint("MissingPermission")
fun Context.getLastKnownLocation(
    onSuccess: (Location) -> Unit,
    onFailure: () -> Unit
) {
    val fusedClient = LocationServices.getFusedLocationProviderClient(this)

    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
    ) {
        onFailure()
        return
    }

    fusedClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            onSuccess(location)
        } else {
            onFailure()
        }
    }.addOnFailureListener {
        onFailure()
    }
}
