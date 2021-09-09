package com.ricky.epermissiontest

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * @ClassName: EPermissionFragment
 * @Description:
 * @Author: Ricky Hal
 * @Date: 2021/7/28 20:51
 */
internal class EPermissionFragment : Fragment() {
    companion object {
        const val TAG = "simple_permission_fragment"
        const val CODE_REQUEST_PERMISSION = 777
    }

    private var mCallback: EPermissionCallback? = null

    fun requestPermission(callback: EPermissionCallback, vararg permissions: String) {
        mCallback = callback
        requestPermissions(permissions, CODE_REQUEST_PERMISSION)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CODE_REQUEST_PERMISSION) {
            val deniedList = ArrayList<String>()
            val deniedForeverList = ArrayList<String>()
            grantResults.forEachIndexed { index, result ->
                if (result != PackageManager.PERMISSION_GRANTED) {
                    val permission = permissions[index]
                    deniedList.add(permission)
                    if (!shouldShowRequestPermissionRationale(permission)) {
                        deniedForeverList.add(permission)
                    }
                }
            }
            if (deniedList.isEmpty()) mCallback?.onAllGranted()
            if (deniedList.isNotEmpty()) mCallback?.onDenied(deniedList)
            if (deniedForeverList.isNotEmpty()) mCallback?.onDeniedForever(deniedForeverList)
        }
    }

    override fun onDestroy() {
        mCallback = null
        super.onDestroy()
    }
}