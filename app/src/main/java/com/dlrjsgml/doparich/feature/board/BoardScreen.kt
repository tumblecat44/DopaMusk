package com.dlrjsgml.doparich.feature.board

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dlrjsgml.doparich.backhandler.BackHandlers

@Composable
fun BoardScreen(navController: NavHostController) {
    BackHandlers(navController = navController)
    Text(text = "Hello WOrdl")

}




