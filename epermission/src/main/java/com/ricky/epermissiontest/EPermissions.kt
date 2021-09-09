@file:Suppress("unused")

package com.ricky.epermissiontest

import android.Manifest

object EPermissions {
    val STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val READ_STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    val WRITE_STORAGE = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    val CAMERA = arrayOf(Manifest.permission.CAMERA)

    val CALENDAR = arrayOf(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR)
    val READ_CALENDAR = arrayOf(Manifest.permission.READ_CALENDAR)
    val WRITE_CALENDAR = arrayOf(Manifest.permission.WRITE_CALENDAR)

    val CONTACT = arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)
    val READ_CONTACTS = arrayOf(Manifest.permission.READ_CONTACTS)
    val WRITE_CONTACTS = arrayOf(Manifest.permission.WRITE_CONTACTS)

    val GET_ACCOUNTS = arrayOf(Manifest.permission.GET_ACCOUNTS)

    val LOCATION = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    val ACCESS_FINE_LOCATION = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    val ACCESS_COARSE_LOCATION = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
    val ACCESS_BACKGROUND_LOCATION = arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION)

    val RECORD_AUDIO = arrayOf(Manifest.permission.RECORD_AUDIO)

    val READ_PHONE_STATE = arrayOf(Manifest.permission.READ_PHONE_STATE)
    val CALL_PHONE = arrayOf(Manifest.permission.CALL_PHONE)

    val CALL_LOG = arrayOf(Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG)
    val READ_CALL_LOG = arrayOf(Manifest.permission.READ_CALL_LOG)
    val WRITE_CALL_LOG = arrayOf(Manifest.permission.WRITE_CALL_LOG)

    val ADD_VOICEMAIL = arrayOf(Manifest.permission.ADD_VOICEMAIL)

    val USE_SIP = arrayOf(Manifest.permission.USE_SIP)
    val BODY_SENSORS = arrayOf(Manifest.permission.BODY_SENSORS)

    val SMS = arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS)
    val SEND_SMS = arrayOf(Manifest.permission.SEND_SMS)
    val RECEIVE_SMS = arrayOf(Manifest.permission.RECEIVE_SMS)

    val RECEIVE_WAP_PUSH = arrayOf(Manifest.permission.RECEIVE_WAP_PUSH)
    val RECEIVE_MMS = arrayOf(Manifest.permission.RECEIVE_MMS)
    val SYSTEM_ALERT_WINDOW = arrayOf(Manifest.permission.SYSTEM_ALERT_WINDOW)
}