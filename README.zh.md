# EPermission

一个基于Kotlin实现的简单的Android权限申请框架

中文 | [English](/README.md)
[![](https://jitpack.io/v/RickyHal/EPermission.svg)](https://jitpack.io/#RickyHal/EPermission)

<img src="/results/guide.png">

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
        Toast.makeText(this, "STORAGE permission granted", Toast.LENGTH_SHORT).show()
    }
)
```

也可以用下面这个简单的方法

```kotlin
runWithStoragePermission(onFailed = {
    Toast.makeText(this, "Storage permission denied", Toast.LENGTH_SHORT).show()
}) {
    Toast.makeText(this, "Storage permission granted", Toast.LENGTH_SHORT).show()
}
```

一次申请多个权限

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

如果不需要处理失申请权限败的情况，也可以直接这样写

```kotlin
runWithStoragePermission {
    Toast.makeText(this, "STORAGE permission granted", Toast.LENGTH_SHORT).show()
}
```

如果某些操作执行的时候，只能有权限才去执行，则可以使用下面的方法

```kotlin
doWhenPermissionGranted(*EPermissions.CAMERA) {
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