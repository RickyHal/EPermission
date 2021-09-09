# EPermission
A simple Android permission application framework based on Kotlin.

English | [中文](/README.zh.md)
[![](https://jitpack.io/v/RickyHal/EPermission.svg)](https://jitpack.io/#RickyHal/EPermission)

# Theory
Add an invisible fragment to the current activity to encapsulate the permission application process.

# Implementation
### Invisible fragment
```kotlin
internal class EPermissionFragment : Fragment() {
    private var mCallback: EPermissionCallback? = null

    fun requestPermission(callback: EPermissionCallback, vararg permissions: String) {
        mCallback = callback
        // Request permissions
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
                // Get the results
                if (result != PackageManager.PERMISSION_GRANTED) {
                    val permission = permissions[index]
                    deniedList.add(permission)
                    // If user reject and don't show again
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

### Encapsulate request permissions process
```kotlin
// extend FragmentActivity
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
    // Add an invisible Fragment
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
    // Request permissions
    fragment.requestPermission(callback, *permissions)
}
```

# Guide
Project build.gradle
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
        }
}
```

Module build.gradle
```groovy
dependencies {
    implementation 'com.github.RickyHal:EPermission:$latest_version'
}
```

Call directly in activity or fragment
```kotlin
// Request Storage permission
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
or
```kotlin
runWithStoragePermission(onFailed = {
    Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show()
}) {
    Toast.makeText(this, "SMS permission granted", Toast.LENGTH_SHORT).show()
}
```
If you do not need to deal with the unsuccessful condition
```kotlin
runWithStoragePermission {
    Toast.makeText(this, "SMS permission granted", Toast.LENGTH_SHORT).show()
}
```
If you only have permission to perform some operations, you can use the following methods
```kotlin
doWhenPermissionGranted(*EPermissions.CAMERA){
    Toast.makeText(this, "Do this when camera Permission is granted", Toast.LENGTH_SHORT).show()
}
```
Check permissions
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