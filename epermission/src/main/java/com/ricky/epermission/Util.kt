@file:Suppress("unused")

package com.ricky.epermission

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.content.pm.ShortcutManagerCompat
import java.util.*


/**
 * 返回应用程序在清单文件中注册的权限
 */
internal fun Context.getManifestPermissions(): List<String> {
    val pm: PackageManager = packageManager
    return try {
        pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS).requestedPermissions.toList()
    } catch (e: Exception) {
        listOf()
    }
}

/**
 * 检测权限有没有在清单文件中注册
 */
internal fun Context.checkManifestPermissions(requestPermissions: List<String>): ArrayList<String> {
    val manifest: List<String> = getManifestPermissions()
    val result = ArrayList<String>()
    if (manifest.isNotEmpty()) {
        for (permission in requestPermissions) {
            if (!manifest.contains(permission)) {
                Log.e("EPermission", "you must add this permission:$permission to AndroidManifest")
                result.add(permission)
            }
        }
    }
    return result
}

/**
 * 是否有安装权限
 */
internal fun Context.hasInstallPermission(): Boolean {
    // Android8.0以上
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        packageManager.canRequestPackageInstalls()
    } else true
}

/**
 * 是否有创建快捷方式权限
 */
internal fun Context.hasShortCutPermission(): Boolean = ShortcutManagerCompat.isRequestPinShortcutSupported(this)

/**
 * 是否有悬浮窗权限
 */
internal fun Context.hasOverlaysPermission(): Boolean {
    // Android6.0以上
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        Settings.canDrawOverlays(this)
    } else true
}


private val MARK = Build.MANUFACTURER.lowercase(Locale.getDefault())
//
///**
// * 跳转到应用设置页面
// */
//internal fun Fragment.openSettingPage() {
//    val intent = Intent()
//    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//    intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
//    intent.data = Uri.fromParts("package", requireContext().packageName, null)
//    startActivityForResult(intent, EPermissionFragment.CODE_REQUEST_SPECIAL_PERMISSION)
//}
//
///**
// * 跳转到应用权限设置页面
// */
//internal fun Context.openPermissionSettingPage(newTask: Boolean) {
//    var intent: Intent? = null
//    when {
//        MARK.contains("huawei") -> intent = huawei(this)
//        MARK.contains("xiaomi") -> intent = xiaomi(this)
//        MARK.contains("oppo") -> intent = oppo(this)
//        MARK.contains("vivo") -> intent = vivo(this)
//        MARK.contains("meizu") -> intent = meizu(this)
//    }
//    if (intent == null || !hasIntent(this, intent)) {
//        intent = google(this)
//    }
//    if (newTask) {
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//    }
//    try {
//        startActivity(intent)
//    } catch (ignored: java.lang.Exception) {
//        intent = google(this)
//        startActivity(intent)
//    }
//}
//
//private fun google(context: Context): Intent {
//    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//    intent.data = Uri.fromParts("package", context.packageName, null)
//    return intent
//}
//
//private fun huawei(context: Context): Intent {
//    val intent = Intent()
//    intent.component = ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity")
//    if (hasIntent(context, intent)) return intent
//    intent.component = ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity")
//    if (hasIntent(context, intent)) return intent
//    intent.component = ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity")
//    return intent
//}
//
//private fun xiaomi(context: Context): Intent {
//    val intent = Intent("miui.intent.action.APP_PERM_EDITOR")
//    intent.putExtra("extra_pkgname", context.packageName)
//    if (hasIntent(context, intent)) return intent
//    intent.setPackage("com.miui.securitycenter")
//    if (hasIntent(context, intent)) return intent
//    intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity")
//    if (hasIntent(context, intent)) return intent
//    intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity")
//    return intent
//}
//
//private fun oppo(context: Context): Intent {
//    val intent = Intent()
//    intent.putExtra("packageName", context.packageName)
//    intent.setClassName("com.color.safecenter", "com.color.safecenter.permission.floatwindow.FloatWindowListActivity")
//    if (hasIntent(context, intent)) return intent
//    intent.setClassName("com.coloros.safecenter", "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity")
//    if (hasIntent(context, intent)) return intent
//    intent.setClassName("com.oppo.safe", "com.oppo.safe.permission.PermissionAppListActivity")
//    return intent
//}
//
//private fun vivo(context: Context): Intent {
//    val intent = Intent()
//    intent.setClassName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.FloatWindowManager")
//    intent.putExtra("packagename", context.packageName)
//    if (hasIntent(context, intent)) return intent
//    intent.component = ComponentName("com.iqoo.secure", "com.iqoo.secure.safeguard.SoftPermissionDetailActivity")
//    return intent
//}
//
//private fun meizu(context: Context): Intent {
//    val intent = Intent("com.meizu.safe.security.SHOW_APPSEC")
//    intent.putExtra("packageName", context.packageName)
//    intent.component = ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity")
//    return intent
//}

//    @SuppressLint("QueryPermissionsNeeded")
//    private fun hasIntent(context: Context, intent: Intent): Boolean {
//        return context.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size > 0
//    }