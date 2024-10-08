package com.dlrjsgml.doparich.feature.main.my

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dlrjsgml.doparich.root.NavGroup
import com.dlrjsgml.doparich.ui.component.AnimatedButton
import com.dlrjsgml.doparich.ui.component.MyButton
import com.dlrjsgml.doparich.ui.theme.title1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


@Composable
fun MyScreen(
    navController: NavHostController,
    viewModel: MyViewModel = hiltViewModel(),
) {
    val context = LocalContext.current


    val uiState by viewModel.uiState.collectAsState()
    viewModel.getUserInfos()
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "${uiState.name}님",
            style = title1
        )

        Button(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel.saveIdReSet()
                navController.popBackStack()
                navController.navigate(NavGroup.LOGIN)
            },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "로그아웃")
        }

        AnimatedButton()
    }




}