package com.vanand.admob_ads_library


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.google.android.gms.ads.LoadAdError
import com.vanand.admob_ads_library.layouts.AdmobNativeMedium
import com.vanand.admob_ads_library.layouts.AdmobNativeSmall
import com.vanand.admob_ads_library.utils.NativeAdType
import com.vanand.admob_ads_library.utils.rememberNativeAd

@Composable
fun AdmobNativeAd(
    context: Context,
    adUnitId: String,
    modifier: Modifier = Modifier,
    refreshInterval: Long = 60000L, // 1 minute
    adType: NativeAdType = NativeAdType.MEDIUM, // Default
    onAdFailed: ((LoadAdError) -> Unit)? = null
) {
    val nativeAd by rememberNativeAd(
        context = context,
        adUnitId = adUnitId,
        refreshInterval = refreshInterval,
        onAdFailedToLoad = onAdFailed
    )

    if (nativeAd == null) {
        println("Native Ad is null, not displaying")
    } else {
        println("Native Ad is available, displaying")
    }

    when (adType) {
        NativeAdType.SMALL -> {
             AdmobNativeSmall(
                 nativeAd = nativeAd,
                 modifier = modifier,
                 layoutResId = R.layout.native_small_ad,
                 onAdFailed = {
                     println("Ad failed to display")

                 }
             )
        }

        NativeAdType.MEDIUM -> {
            AdmobNativeMedium(
                nativeAd = nativeAd,
                modifier = modifier,
                layoutResId = R.layout.native_medium_ad,
                onAdFailed = {
                    println("Ad failed to display")
                }
            )
        }
    }
}
