package com.ricky.epermission.demo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ricky.epermission.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun requestStoragePermission(view: View) {
        runWithStoragePermission(onFailed = {
            Toast.makeText(this, "Storage permission denied", Toast.LENGTH_SHORT).show()
        }) {
            Toast.makeText(this, "Storage permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    fun requestCameraPermission(view: View) {
        runWithCameraPermission(onFailed = {
            Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }) {
            Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    fun requestLocationPermission(view: View) {
        runWithLocationPermission(onFailed = {
            Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
        }) {
            Toast.makeText(this, "Location permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    fun requestReadPhoneStatePermission(view: View) {
        runWithPhoneStatePermission(onFailed = {
            Toast.makeText(this, "Read phone state permission denied", Toast.LENGTH_SHORT).show()
        }) {
            Toast.makeText(this, "Read phone state permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    fun requestCalendarPermission(view: View) {
        runWithCalendarPermission(onFailed = {
            Toast.makeText(this, "Calendar permission denied", Toast.LENGTH_SHORT).show()
        }) {
            Toast.makeText(this, "Calendar permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    fun requestContactPermission(view: View) {
        runWithContactPermission(onFailed = {
            Toast.makeText(this, "Contact permission denied", Toast.LENGTH_SHORT).show()
        }) {
            Toast.makeText(this, "Contact permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    fun requestCallLogPermission(view: View) {
        runWithCallLogPermission(onFailed = {
            Toast.makeText(this, "Call log permission denied", Toast.LENGTH_SHORT).show()
        }) {
            Toast.makeText(this, "Call log permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    fun requestSMSPermission(view: View) {
        runWithSMSPermission(onFailed = {
            Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show()
        }) {
            Toast.makeText(this, "SMS permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    fun runWithStoragePermission(view: View) {
        runWithPermissions(
            *EPermissions.STORAGE,
            onDenied = {
                Toast.makeText(this, "STORAGE permission denied", Toast.LENGTH_SHORT).show()
            },
            onDeniedForever = {
                Toast.makeText(this, "STORAGE permission denied forever", Toast.LENGTH_SHORT).show()
            },
            onAllGranted = {
                Toast.makeText(this, "STORAGE permission granted", Toast.LENGTH_SHORT).show()
            }
        )
    }

    fun checkCameraPermission(view: View) {
        if (checkPermissions(*EPermissions.CAMERA)) {
            Toast.makeText(this, "Camera Permission is granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Camera Permission is not granted", Toast.LENGTH_SHORT).show()
        }
    }

    fun doWhenCameraPermission(view: View) {
        doWhenPermissionGranted(*EPermissions.CAMERA) {
            Toast.makeText(this, "Do this when camera Permission is granted", Toast.LENGTH_SHORT).show()
        }
    }

    fun requestMultiPermissions(view: View) {
        runWithPermissions(*EPermissions.CAMERA, *EPermissions.STORAGE,
            onDenied = { deniedList ->
                Toast.makeText(this, "permission denied $deniedList", Toast.LENGTH_SHORT).show()
            },
            onDeniedForever = { deniedForeverList ->
                Toast.makeText(this, "permission denied forever $deniedForeverList", Toast.LENGTH_SHORT).show()
            },
            onAllGranted = {
                Toast.makeText(this, "Permission all granted", Toast.LENGTH_SHORT).show()
            })
    }
}