package com.vanand.admob_ads_library.layouts

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.vanand.admob_ads_library.R

@Composable
fun AdmobNativeMedium(
    nativeAd: NativeAd?,
    modifier: Modifier = Modifier.height(250.dp),
    layoutResId: Int = R.layout.native_medium_ad, // layout customization
    onAdFailed: (() -> Unit)? = null
) {
    val context = LocalContext.current

    AndroidView(
        factory = { ctx ->
            val nativeLayout = LayoutInflater.from(ctx).inflate(layoutResId, null, false)

            val nativeAdView = nativeLayout.findViewById<NativeAdView>(R.id.native_ad_view)
            if (nativeAdView.parent != null) {
                (nativeAdView.parent as? ViewGroup)?.removeView(nativeAdView)
            }

            nativeLayout.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            nativeAdView
        },
        update = { nativeAdView ->
            if (nativeAd != null) {
                nativeAdView?.let { adView ->

                    nativeAd.icon?.let { icon ->
                        adView.iconView = adView.findViewById(R.id.icon)
                        (adView.iconView as? ImageView)?.setImageDrawable(icon.drawable)
                    }

                    // Set the headline view
                    nativeAd.headline?.let { headline ->
                        adView.headlineView = adView.findViewById(R.id.headline)
                        (adView.headlineView as? TextView)?.text = headline
                    }

                    nativeAd.body?.let { body ->
                        adView.bodyView = adView.findViewById(R.id.body)
                        (adView.bodyView as? TextView)?.text = body
                    }


                    adView.mediaView = adView.findViewById(R.id.media_view)
                    nativeAd.mediaContent?.let { mediaContent ->
                        adView.mediaView?.setMediaContent(mediaContent)
                    }

                    nativeAd.callToAction?.let { callToAction ->
                        adView.callToActionView = adView.findViewById(R.id.cta)
                        (adView.callToActionView as? TextView)?.text = callToAction
                    }

                    adView.setNativeAd(nativeAd)
                }
            } else {
                onAdFailed?.invoke()
            }
        },
        modifier = modifier
    )

    DisposableEffect(nativeAd) {
        onDispose {
            nativeAd?.destroy()
        }
    }
}
