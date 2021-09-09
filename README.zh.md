# EPermission
一个基于Kotlin实现的简单的Android权限申请框架

中文 | [English](/README.md)
[![](https://jitpack.io/v/RickyHal/EPermission.svg)](https://jitpack.io/#RickyHal/EPermission)

# 原理
通过向当前Activity添加一个不可见的Fragment，从而实现权限申请流程的封装。

# 实现
### 不可见的Fragment
```kotlin
internal class EPermissionFragment : Fragment() {
    private var mCallback: EPermissionCallback? = null

    fun requestPermission(callback: EPermissionCallback, vararg permissions: String) {
        mCallback = callback
        // 申请权限
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
                // 提取权限申请结果
                if (result != PackageManager.PERMISSION_GRANTED) {
                    val permission = permissions[index]
                    deniedList.add(permission)
                    // 是否拒绝且不再显示
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
```

### 封装权限申请
```kotlin
// 扩展FragmentActivity
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
    // 添加一个不可见的Fragment
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
    // 申请权限
    fragment.requestPermission(callback, *permissions)
}
```

# 使用方法
项目build.gradle添加
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
        }
}
```

模块build.gradle添加
```groovy
dependencies {
    implementation 'com.github.RickyHal:EPermission:$latest_version'
}
```
在Activity或者Fragment中直接调用
```kotlin
// 申请存储权限
runWithPermissions(
    *EPermissions.STORAGE,
    onDenied = {
        Toast.makeText(this, "STORAGE permission denied", Toast.LENGTH_SHORT).show()
    },
    onDeniedForever = {
        Toast.makeText(this, "STORAGE permission denied forever", Toast.LENGTH_SHORT).show()
    },
    onAllGranted = {
        Toast.makeText(this, "STORAGE permission denied", Toast.LENGTH_SHORT).show()
    }
)
```
也可以用下面这个简单的方法
```kotlin
runWithStoragePermission(onFailed = {
    Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show()
}) {
    Toast.makeText(this, "SMS permission granted", Toast.LENGTH_SHORT).show()
}
```
如果不需要处理失申请权限败的情况，也可以直接这样写
```kotlin
runWithStoragePermission {
    Toast.makeText(this, "SMS permission granted", Toast.LENGTH_SHORT).show()
}
```
如果某些操作执行的时候，只能有权限才去执行，则可以使用下面的方法
```kotlin
doWhenPermissionGranted(*EPermissions.CAMERA){
    Toast.makeText(this, "Do this when camera Permission is granted", Toast.LENGTH_SHORT).show()
}
```
检查权限
```kotlin
if (checkPermissions(*EPermissions.CAMERA)) {
    Toast.makeText(this, "Camera Permission is granted", Toast.LENGTH_SHORT).show()
} else {
    Toast.makeText(this, "Camera Permission is not granted", Toast.LENGTH_SHORT).show()
}
```

## License

> ```
> Copyright 2021 RickyHal
>
> Licensed under the Apache License, Version 2.0 (the "License");
> you may not use this file except in compliance with the License.
> You may obtain a copy of the License at
>
>    http://www.apache.org/licenses/LICENSE-2.0
>
> Unless required by applicable law or agreed to in writing, software
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
> See the License for the specific language governing permissions and
> limitations under the License.
> ```