package com.junkfood.seal.ui.page

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.junkfood.seal.ui.page.videolist.VideoListPage
import com.junkfood.seal.ui.page.download.DownloadViewModel
import com.junkfood.seal.ui.page.videolist.VideoListViewModel
import com.junkfood.ui.animatedComposable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeEntry(
    downloadViewModel: DownloadViewModel = hiltViewModel(),
    videoListViewModel: VideoListViewModel = hiltViewModel()
) {
    val useDarkTheme = isSystemInDarkTheme()

    val systemUiController = rememberSystemUiController()

    systemUiController.run {
        setStatusBarColor(Color.Transparent, !useDarkTheme)
        setSystemBarsColor(Color.Transparent, !useDarkTheme)
        setNavigationBarColor(Color.Transparent, !useDarkTheme)
    }


    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = "home") {
        animatedComposable("home") {
            DownloadPage(navController = navController, downloadViewModel = downloadViewModel)
        }
        animatedComposable("settings") { SettingsPage(navController) }
        animatedComposable("download") { DownloadPreferences(navController) }
        animatedComposable("videolist") { VideoListPage(navController) }
    }

}