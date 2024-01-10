package com.example.projectakhirpamkurir.navigasi

import HomeScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectakhirpamkurir.R
import com.example.projectakhirpamkurir.ui.theme.halaman.DestinasiEntry
import com.example.projectakhirpamkurir.ui.theme.halaman.DestinasiUtama
import com.example.projectakhirpamkurir.ui.theme.halaman.DetailsDestination
import com.example.projectakhirpamkurir.ui.theme.halaman.DetailsScreen
import com.example.projectakhirpamkurir.ui.theme.halaman.EntryKurirScreen
import com.example.projectakhirpamkurir.ui.theme.halaman.HalamanUtama
import com.example.projectakhirpamkurir.ui.theme.halaman.ItemEditDestination
import com.example.projectakhirpamkurir.ui.theme.halaman.ItemEditScreen

@Composable
fun KurirApp(navController: NavHostController = rememberNavController()){
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KurirTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        })
}
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiUtama.route,
        modifier = Modifier
    )
    {
        composable(DestinasiUtama.route) {
            HalamanUtama(
                onNextButtonClicked = { navController.navigate(DestinasiHome.route)}
            )
        }
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = {
                    navController.navigate("${DetailsDestination.route}/$it")
                }
            )
        }
        composable(DestinasiEntry.route) {
            EntryKurirScreen(
                navigateBack = { navController.popBackStack() },
            )
        }
        composable(
            DetailsDestination.routeWithArg,
            arguments = listOf(navArgument(DetailsDestination.kurirIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEditItem = {
                    navController.navigate("${ItemEditDestination.route}/$it")
                }
            )
        }
        composable(
            ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}

