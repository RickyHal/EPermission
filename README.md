# EPermission

A simple Android permission application framework based on Kotlin.

English | [中文](/README.zh.md)
[![](https://jitpack.io/v/RickyHal/EPermission.svg)](https://jitpack.io/#RickyHal/EPermission)

<img src="/results/guide.png" width="500">

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

Request multiple permissions

```kotlin
runWithPermissions(*EPermissions.CAMERA, *EPermissions.STORAGE,
    onDenied = { deniedList ->
        Toast.makeText(this, "permission denied $deniedList", Toast.LENGTH_SHORT).show()
    },
    onDeniedForever = { deniedForeverList ->
        Toast.makeText(this, "permission denied forever $deniedForeverList", Toast.LENGTH_SHORT).show()
    },
    onAllGranted = {
        Toast.makeText(this, "Permission all granted", Toast.LENGTH_SHORT).show()
    })
```

If you do not need to deal with the unsuccessful condition

```kotlin
runWithStoragePermission {
    Toast.makeText(this, "SMS permission granted", Toast.LENGTH_SHORT).show()
}
```

If you only have permission to perform some operations, you can use the following methods

```kotlin
doWhenPermissionGranted(*EPermissions.CAMERA) {
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