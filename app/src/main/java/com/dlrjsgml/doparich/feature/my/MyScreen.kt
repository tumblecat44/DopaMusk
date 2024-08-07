package com.dlrjsgml.doparich.feature.my

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dlrjsgml.doparich.backhandler.BackHandlers

@Composable
fun MyScreen(navController: NavHostController){
    BackHandlers(navController = navController)
    Text(text = "MyScreend")
}