@file:Suppress("unused")


package com.ricky.epermission

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * @ClassName: EPermissionExt.kt
 * @Description: Request permissions with wrapped
 * @Author: Ricky Hal
 * @Date: 2021/7/28 20:50
 */
fun Fragment.runWithStoragePermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    requireContext().runWithStoragePermission(onFailed, onSuccess)
}

fun Context.runWithStoragePermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    var isDenied = false
    runWithPermissions(
        *EPermissions.STORAGE,
        onDenied = {
            onFailed()
            isDenied = true
        },
        onDeniedForever = { if (!isDenied) onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithCameraPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    requireContext().runWithCalendarPermission(onFailed, onSuccess)
}

fun Context.runWithCameraPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    var isDenied = false
    runWithPermissions(
        *EPermissions.CAMERA,
        onDenied = {
            onFailed()
            isDenied = true
        },
        onDeniedForever = { if (!isDenied) onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithLocationPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    requireContext().runWithLocationPermission(onFailed, onSuccess)
}

fun Context.runWithLocationPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    var isDenied = false
    runWithPermissions(
        *EPermissions.LOCATION,
        onDenied = {
            onFailed()
            isDenied = true
        },
        onDeniedForever = { if (!isDenied) onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithContactPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    requireContext().runWithContactPermission(onFailed, onSuccess)
}

fun Context.runWithContactPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    var isDenied = false
    runWithPermissions(
        *EPermissions.CONTACT,
        onDenied = {
            onFailed()
            isDenied = true
        },
        onDeniedForever = { if (!isDenied) onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithPhoneStatePermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    requireContext().runWithPhoneStatePermission(onFailed, onSuccess)
}

fun Context.runWithPhoneStatePermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    var isDenied = false
    runWithPermissions(
        *EPermissions.READ_PHONE_STATE,
        onDenied = {
            onFailed()
            isDenied = true
        },
        onDeniedForever = { if (!isDenied) onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithCalendarPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    requireContext().runWithCalendarPermission(onFailed, onSuccess)
}

fun Context.runWithCalendarPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    var isDenied = false
    runWithPermissions(
        *EPermissions.READ_CALENDAR,
        onDenied = {
            onFailed()
            isDenied = true
        },
        onDeniedForever = { if (!isDenied) onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithCallLogPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    requireContext().runWithCallLogPermission(onFailed, onSuccess)
}

fun Context.runWithCallLogPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    var isDenied = false
    runWithPermissions(
        *EPermissions.CALL_LOG,
        onDenied = {
            onFailed()
            isDenied = true
        },
        onDeniedForever = { if (!isDenied) onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithSMSPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    requireContext().runWithSMSPermission(onFailed, onSuccess)
}

fun Context.runWithSMSPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    var isDenied = false
    runWithPermissions(
        *EPermissions.SMS,
        onDenied = {
            onFailed()
            isDenied = true
        },
        onDeniedForever = { if (!isDenied) onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithSystemAlertPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    requireContext().runWithSystemAlertPermission(onFailed, onSuccess)
}

fun Context.runWithSystemAlertPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    var isDenied = false
    runWithPermissions(
        *EPermissions.SYSTEM_ALERT_WINDOW,
        onDenied = {
            onFailed()
            isDenied = true
        },
        onDeniedForever = { if (!isDenied) onFailed() },
        onAllGranted = onSuccess
    )
}