@file:Suppress("unused")

package com.ricky.epermission

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * @ClassName: EPermission.kt
 * @Description:
 * @Author: Ricky Hal
 * @Date: 2021/7/28 20:50
 */
fun FragmentActivity.runWithPermissions(
    vararg permissions: String,
    onDenied: (ArrayList<String>) -> Unit = { _ -> },
    onDeniedForever: (ArrayList<String>) -> Unit = { _ -> },
    onAllGranted: () -> Unit = {}
) {
    if (checkPermissions(*permissions)) {
        onAllGranted()
        return
    }
    val isFragmentExist = supportFragmentManager.findFragmentByTag(EPermissionFragment.TAG)
    val fragment = if (isFragmentExist != null) {
        isFragmentExist as EPermissionFragment
    } else {
        val invisibleFragment = EPermissionFragment()
        supportFragmentManager.beginTransaction().add(invisibleFragment, EPermissionFragment.TAG).commitNowAllowingStateLoss()
        invisibleFragment
    }
    val callback = object : EPermissionCallback {
        override fun onAllGranted() {
            onAllGranted()
        }

        override fun onDenied(deniedList: ArrayList<String>) {
            onDenied(deniedList)
        }

        override fun onDeniedForever(deniedForeverList: ArrayList<String>) {
            onDeniedForever(deniedForeverList)
        }
    }
    fragment.requestPermission(callback, *permissions)
}

fun Fragment.runWithPermissions(
    vararg permissions: String,
    onDenied: (ArrayList<String>) -> Unit = { _ -> },
    onDeniedForever: (ArrayList<String>) -> Unit = { _ -> },
    onAllGranted: () -> Unit = {}
) {
    if (checkPermissions(*permissions)) {
        onAllGranted()
        return
    }
    requireActivity().runWithPermissions(
        *permissions,
        onDenied = onDenied,
        onDeniedForever = onDeniedForever,
        onAllGranted = onAllGranted
    )
}

fun FragmentActivity.doWhenPermissionGranted(
    vararg permissions: String,
    block: () -> Unit
) {
    if (checkPermissions(*permissions)) {
        block()
    }
}

fun Fragment.doWhenPermissionGranted(
    vararg permissions: String,
    block: () -> Unit
) {
    if (checkPermissions(*permissions)) {
        block()
    }
}

fun FragmentActivity.checkPermissions(
    vararg permissions: String
): Boolean {
    return permissions.all {
        ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }
}

fun Fragment.checkPermissions(
    vararg permissions: String
): Boolean {
    return permissions.all {
        ActivityCompat.checkSelfPermission(
            requireContext(),
            it
        ) == PackageManager.PERMISSION_GRANTED
    }
}