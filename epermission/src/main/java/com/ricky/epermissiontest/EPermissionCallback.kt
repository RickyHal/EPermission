package com.ricky.epermissiontest

/**
 * @ClassName: PermissionCallback
 * @Description:
 * @Author: Ricky Hal
 * @Date: 2021/7/28 21:07
 */
internal interface EPermissionCallback {
    fun onAllGranted()
    fun onDenied(deniedList: ArrayList<String>)
    fun onDeniedForever(deniedForeverList: ArrayList<String>)
}