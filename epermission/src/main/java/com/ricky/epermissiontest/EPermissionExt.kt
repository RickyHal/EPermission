@file:Suppress("unused")


package com.ricky.epermissiontest

import android.Manifest
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * @ClassName: EPermissionExt.kt
 * @Description: Request permissions with wrapped
 * @Author: Ricky Hal
 * @Date: 2021/7/28 20:50
 */

fun FragmentActivity.runWithStoragePermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.STORAGE,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithStoragePermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.STORAGE,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}


fun FragmentActivity.runWithCameraPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        Manifest.permission.CAMERA,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithCameraPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        Manifest.permission.CAMERA,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}

fun FragmentActivity.runWithLocationPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.LOCATION,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithLocationPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.LOCATION,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}

fun FragmentActivity.runWithContactPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.CONTACT,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithContactPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.CONTACT,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}

fun FragmentActivity.runWithPhoneStatePermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.READ_PHONE_STATE,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithPhoneStatePermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.READ_PHONE_STATE,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}

fun FragmentActivity.runWithCalendarPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.READ_CALENDAR,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithCalendarPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.CALENDAR,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}


fun FragmentActivity.runWithCallLogPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.CALL_LOG,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithCallLogPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.CALL_LOG,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}


fun FragmentActivity.runWithSMSPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.SMS,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}

fun Fragment.runWithSMSPermission(
    onFailed: () -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    runWithPermissions(
        *EPermissions.SMS,
        onDenied = { onFailed() },
        onDeniedForever = { onFailed() },
        onAllGranted = onSuccess
    )
}

