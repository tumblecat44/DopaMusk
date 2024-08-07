package com.dlrjsgml.doparich.root

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dlrjsgml.doparich.MainViewModel
import com.dlrjsgml.doparich.R
import com.dlrjsgml.doparich.feature.account.AccountScreen
import com.dlrjsgml.doparich.feature.board.BoardScreen
import com.dlrjsgml.doparich.feature.home.HomeScreen
import com.dlrjsgml.doparich.feature.login.LoginScreen
import com.dlrjsgml.doparich.feature.login.LoginViewModel
import com.dlrjsgml.doparich.feature.my.MyScreen
import com.dlrjsgml.doparich.feature.write.WriteScreen
import com.dlrjsgml.doparich.ui.theme.Blue700
import com.dlrjsgml.doparich.ui.theme.Gray200
import com.dlrjsgml.doparich.ui.theme.Gray600
import com.dlrjsgml.doparich.ui.theme.White
import com.dlrjsgml.doparich.ui.theme.caption2


@Composable
fun NavGraph(
    navController: NavHostController,
) {
    val backstackEntry by navController.currentBackStackEntryAsState()
    val selectRoute = backstackEntry?.destination?.route
    var isShowNavBar by remember {
        mutableStateOf(false)
    }




    Surface(
        modifier = Modifier.fillMaxSize(),
        color = White
    ) {
        Scaffold(
            bottomBar = {
                if (isShowNavBar) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 0.5.dp,
                                Gray200,
                                RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                            )
                            .background(
                                White,
                                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                            )
                    )
                    {
                        NavCard(
                            modifier = Modifier
                                .weight(1f)
                                .noRippleClickable {
                                    navController.popBackStack()
                                    navController.navigate(
                                        NavGroup.HOME
                                    )

                                },
                            resId = R.drawable.ic_home,
                            isSelected = selectRoute == NavGroup.HOME
                        )
                        NavCard(
                            modifier = Modifier
                                .weight(1f)
                                .noRippleClickable {
                                    navController.popBackStack()
                                    navController.navigate(
                                        NavGroup.BOARD
                                    )
                                },
                            resId = R.drawable.ic_text_write,
                            isSelected = selectRoute == NavGroup.BOARD
                        )
                        NavCard(
                            modifier = Modifier
                                .weight(1f)
                                .noRippleClickable {
                                    navController.popBackStack()
                                    navController.navigate(
                                        NavGroup.USER
                                    )
                                },
                            resId = R.drawable.ic_user,
                            isSelected = selectRoute == NavGroup.USER
                        )


                    }
                }
            }
        ) {
            NavHost(
                modifier = Modifier
                    .padding(it),
                navController = navController,
                startDestination = NavGroup.LOGIN,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
            ) {
                composable(NavGroup.LOGIN) {
                    LoginScreen(navController)
                }
                composable(NavGroup.ACCOUNT) {
                    AccountScreen(navController)
                }
                composable(
                    NavGroup.HOME
                ) {
                    val myInfo = "이건희"
                    HomeScreen(
                        navBottomVisible = { isShowNavBar = it }, navController,
                        LoginViewModel()
                    )
                }

                composable(NavGroup.BOARD) {
                    BoardScreen(navController)
                }

                composable(NavGroup.USER) {
                    MyScreen(navController)
                }
                composable(NavGroup.WRITE) {
                    WriteScreen(navController)
                }
            }
        }
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) { onClick() }
}

@Composable
fun NavCard(
    modifier: Modifier = Modifier,
    @DrawableRes resId: Int,
    isSelected: Boolean,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = resId),
            contentDescription = null,
            colorFilter = ColorFilter.tint(if (isSelected) Blue700 else Gray600)
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}