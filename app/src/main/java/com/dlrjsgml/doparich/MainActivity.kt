package com.dlrjsgml.doparich

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.dlrjsgml.doparich.root.NavGraph
import com.dlrjsgml.doparich.ui.theme.DoparichTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoparichTheme {
                val navHostController = rememberNavController()
                NavGraph(navController = navHostController)

            }
        }
    }
}