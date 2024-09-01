# AdmobAdsJetpackCompose

[![](https://jitpack.io/v/Spiritman021/AdmobAdsJetpackCompose.svg)](https://jitpack.io/#Spiritman021/AdmobAdsJetpackCompose)

AdmobAdsJetpackCompose is a Jetpack Compose library that simplifies the integration of Google AdMob Native Ads in Jetpack Compose-based Android applications.

## Features

- Easy integration of AdMob Native Ads into Jetpack Compose projects.
- Support for small and medium native ad layouts.
- Configurable refresh intervals.
- Lightweight and customizable components.

## Installation

To use the AdmobAdsJetpackCompose library in your project, follow these steps:

### Step 1: Add the JitPack repository to your root `build.gradle` or `settings.gradle`

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add the dependency in your app-level `build.gradle` or `build.gradle.kts`
```gradle
dependencies {
    implementation 'com.github.Spiritman021:AdmobAdsJetpackCompose:1.0'
}
```

## Example
Here's how you can integrate the Admob Native Ads in your Jetpack Compose Project

### 0. Update your manifest file
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="your.package.name">

    <!-- Required Permissions -->
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

    <application
        ...>
        
        <!-- AdMob App ID -->
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="ca-app-pub-3940256099942544~3347511713"/> <!-- Replace with your AdMob App ID -->

        ...

    </application>


</manifest>

```

### 1. Initialize the MobileAds

```kotlin
import com.google.android.gms.ads.MobileAds

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {}
    }
}
```

### 2. Display Native Ad in Composables

```kotlin
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.android.gms.ads.LoadAdError
import com.vanand.admob_ads_library.AdmobNativeAd
import com.vanand.admob_ads_library.utils.NativeAdType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdmobNativeAd(
                context = this,
                adUnitId = "ca-app-pub-3940256099942544/2247696110", // Test ad unit ID for native ads
                modifier = Modifier,
                refreshInterval = 120000L, // Refresh every 2 minutes
                adType = NativeAdType.MEDIUM, // Choose between MEDIUM or SMALL
                onAdFailed = { error: LoadAdError ->
                    println("Ad failed to load: ${error.message}")
                }
            )
        }
    }
}

```

### 3. Customize Ad layout and other fields as per your need

```kotlin
AdmobNativeAd(
    context = this,
    adUnitId = "ca-app-pub-3940256099942544/2247696110", // Test ad unit ID for native ads
    modifier = Modifier,
    refreshInterval = 120000L,
    adType = NativeAdType.MEDIUM,
    layoutResId = R.layout.custom_native_ad_layout, // Your custom layout
    onAdFailed = { error: LoadAdError ->
        println("Ad failed to load: ${error.message}")
    }
)
```
