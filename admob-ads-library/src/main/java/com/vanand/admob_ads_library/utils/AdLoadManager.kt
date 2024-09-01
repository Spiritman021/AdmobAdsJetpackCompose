package com.vanand.admob_ads_library.utils

import android.content.Context
import androidx.compose.runtime.*
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun rememberNativeAd(
    context: Context,
    adUnitId: String,
    refreshInterval: Long = 60000L, // 1 minute
    onAdFailedToLoad: ((LoadAdError) -> Unit)? = null
): State<NativeAd?> {
    val nativeAd = remember { mutableStateOf<NativeAd?>(null) }

    LaunchedEffect(adUnitId, refreshInterval) {
        while (isActive) {
            val adLoader = AdLoader.Builder(context, adUnitId)
                .forNativeAd { ad ->
                    nativeAd.value = ad
                    println("Ad successfully loaded and state updated")
                }
                .withAdListener(object : com.google.android.gms.ads.AdListener() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        nativeAd.value = null
                        println("Ad failed to load: ${adError.message}")
                        onAdFailedToLoad?.invoke(adError)
                    }
                })
                .build()

            adLoader.loadAd(AdRequest.Builder().build())

            delay(refreshInterval)

            nativeAd.value?.destroy()
            nativeAd.value = null
        }
    }

    DisposableEffect(nativeAd) {
        onDispose {
            nativeAd.value?.destroy()
        }
    }

    return nativeAd
}
