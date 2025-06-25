package org.override.book.logicave.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.override.book.logicave.utils.RoutesLogic

@Composable
internal fun Navigation(
    navController: NavHostController,
    images: List<DrawableResource>
) {
    NavHost(
        navController = navController,
        startDestination = RoutesLogic.PAG1.route
    ) {
        composable(RoutesLogic.PAG1.route) {
            Image(
                painter = painterResource(images[0]),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        composable(RoutesLogic.PAG2.route) {
            Image(
                painter = painterResource(images[1]),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        composable(RoutesLogic.PAG3.route) {
            Image(
                painter = painterResource(images[2]),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        composable(RoutesLogic.PAG4.route) {
            Image(
                painter = painterResource(images[3]),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        composable(RoutesLogic.PAG5.route) {
            Image(
                painter = painterResource(images[4]),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        composable(RoutesLogic.PAG6.route) {
            Image(
                painter = painterResource(images[5]),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        composable(RoutesLogic.PAG7.route) {
            Image(
                painter = painterResource(images[6]),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
    }
}