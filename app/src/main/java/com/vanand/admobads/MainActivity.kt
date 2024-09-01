package com.vanand.admobads

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.ads.LoadAdError
import com.vanand.admob_ads_library.AdmobNativeAd
import com.vanand.admob_ads_library.utils.NativeAdType
import com.vanand.admobads.ui.theme.AdmobAdsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdmobAdsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MyAdScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyAdScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    AdmobNativeAd(
        context = context,
        adUnitId = "ca-app-pub-3940256099942544/2247696110",
        modifier = modifier,
        adType =  NativeAdType.SMALL,
        refreshInterval = 120000L, // Refresh every 2 minutes
        onAdFailed = { error: LoadAdError ->
            println("Ad failed to load: ${error.message}")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AdmobAdsTheme {
        MyAdScreen()
    }
}
