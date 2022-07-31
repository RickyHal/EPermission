@file:Suppress("unused")

package com.ricky.epermission

import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * @ClassName: EPermission.kt
 * @Description:
 * @Author: Ricky Hal
 * @Date: 2021/7/28 20:50
 */

/**
 *------------------------------ runWithPermissions
 */
fun FragmentActivity.runWithPermissions(
    vararg permissions: String,
    onDenied: (ArrayList<String>) -> Unit = { _ -> },
    onDeniedForever: (ArrayList<String>) -> Unit = { _ -> },
    onAllGranted: () -> Unit = {}
) {
    val unDefinedPermissions = checkManifestPermissions(permissions.asList())
    if (unDefinedPermissions.isNotEmpty()) {
        onDenied(unDefinedPermissions)
    }
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
    requireActivity().runWithPermissions(
        *permissions,
        onDenied = onDenied,
        onDeniedForever = onDeniedForever,
        onAllGranted = onAllGranted
    )
}

fun Context.runWithPermissions(
    vararg permissions: String,
    onDenied: (ArrayList<String>) -> Unit = { _ -> },
    onDeniedForever: (ArrayList<String>) -> Unit = { _ -> },
    onAllGranted: () -> Unit = {}
) {
    if (checkPermissions(*permissions)) {
        onAllGranted()
        return
    }
    val activity = toActivity() ?: throw IllegalStateException("You must use activity to request permission.")
    activity.runWithPermissions(
        *permissions,
        onDenied = onDenied,
        onDeniedForever = onDeniedForever,
        onAllGranted = onAllGranted
    )
}

/**
 *------------------------------ doWhenPermissionGranted
 */
fun Fragment.doWhenPermissionGranted(
    vararg permissions: String,
    block: () -> Unit
) {
    if (checkPermissions(*permissions)) {
        block()
    }
}

fun Context.doWhenPermissionGranted(
    vararg permissions: String,
    block: () -> Unit
) {
    if (checkPermissions(*permissions)) {
        block()
    }
}

/**
 *------------------------------ checkPermissions
 */
fun Fragment.checkPermissions(
    vararg permissions: String
): Boolean {
    return requireContext().checkPermissions(*permissions)
}

fun Context.checkPermissions(
    vararg permissions: String
): Boolean {
    return permissions.all {
        ContextCompat.checkSelfPermission(
            this,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }
}

private fun Context.toActivity(): FragmentActivity? {
    return when (this) {
        is FragmentActivity -> this
        is ContextWrapper -> baseContext as? FragmentActivity
        else -> null
    }
}