package com.dlrjsgml.doparich.backhandler

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dlrjsgml.doparich.root.NavGroup

@Composable
fun BackHandlers(navController: NavHostController) {
    BackHandler(enabled = true, onBack = {
        navController.popBackStack()
        navController.navigate(NavGroup.HOME)
    })
}