package com.example.beerbuddy.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.beerbuddy.R
import com.example.beerbuddy.navigation.BEER_ID_KEY
import com.example.beerbuddy.navigation.BeerDetailsDestination
import com.example.beerbuddy.navigation.NavigationItem
import com.example.beerbuddy.ui.beerdetails.BeerDetailsRoute
import com.example.beerbuddy.ui.favorites.FavoritesRoute
import com.example.beerbuddy.ui.home.HomeScreenRoute
import com.example.beerbuddy.ui.home.HomeViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import kotlin.random.Random

@Composable
fun MainScreen() {

    val homeViewModel: HomeViewModel = getViewModel()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val showBottomBar by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route == NavigationItem.HomeDestination.route ||
                    navBackStackEntry?.destination?.route == NavigationItem.FavoritesDestination.route
        }
    }

    val showTopBarWithSearch by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route == NavigationItem.HomeDestination.route
        }
    }

    val showTopBarWithBack by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route == BeerDetailsDestination.route
        }
    }

    val showBackIcon = !showBottomBar

    Scaffold(
        topBar = {
            if (showTopBarWithSearch) {
                TopBarSearch(
                    query = homeViewModel.query.value,
                    onTextChange = { homeViewModel.setQuery(it) },
                    onRandomClick = {
                        navController.navigate(
                            BeerDetailsDestination.createNavigationRoute(
                                Random.nextInt(1, 292)
                            )
                        )
                    }
                )
            }
            if (showTopBarWithBack) {
                TopBarBack(
                    navigationIcon = {
                        if (showBackIcon) BackIcon(onBackClick = navController::popBackStack)
                    }
                )
            }
        },
        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.HomeDestination,
                        NavigationItem.FavoritesDestination,
                    ), onNavigateToDestination = {
                        navController.popBackStack(
                            NavigationItem.HomeDestination.route,
                            inclusive = false,
                        )
                        navController.navigate(it.route) { launchSingleTop = true }
                    }, currentDestination = navBackStackEntry?.destination
                )
        },
    ) { padding ->
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize(),
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.HomeDestination.route,
                modifier = Modifier.padding(padding),
            ) {
                composable(NavigationItem.HomeDestination.route) {
                    HomeScreenRoute(
                        onNavigateToBeerDetails = {
                            navController.navigate(
                                BeerDetailsDestination.createNavigationRoute(it)
                            )
                        },
                        viewModel = homeViewModel
                    )
                }
                composable(NavigationItem.FavoritesDestination.route) {
                    FavoritesRoute(
                        onNavigateToBeerDetails = {
                            navController.navigate(
                                BeerDetailsDestination.createNavigationRoute(it)
                            )
                        },
                        viewModel = getViewModel()
                    )
                }
                composable(
                    route = BeerDetailsDestination.route,
                    arguments = listOf(navArgument(BEER_ID_KEY) { type = NavType.IntType }),
                ) {
                    BeerDetailsRoute(viewModel = getViewModel {
                        parametersOf(
                            it.arguments?.getInt(
                                BEER_ID_KEY
                            ) ?: throw IllegalArgumentException("No beer id found.")
                        )
                    })
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TopBarSearch(query: String, onTextChange: (String) -> Unit, onRandomClick: () -> Unit) {

    val keyboardController = LocalSoftwareKeyboardController.current
    Box() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { onTextChange(it) },
                label = { Text(text = "Unesi ime piva") },
                modifier = Modifier.weight(1f),
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    keyboardController?.hide()
                })
            )
            IconButton(onClick = { onRandomClick() }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_random),
                    modifier = Modifier
                        .size(60.dp)
                        .fillMaxHeight()
                        .padding(top = 10.dp),
                    contentDescription = "Random Button",
                )
            }
        }
    }
}

@Composable
fun TopBarBack(
    navigationIcon: @Composable () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        navigationIcon?.invoke()
    }
}


@Composable
private fun BackIcon(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = "Back Button",
        modifier = modifier
            .clickable { onBackClick() }
            .size(50.dp)
            .padding(start = 10.dp),
        alignment = Alignment.Center,
    )
}

@Composable
private fun BottomNavigationBar(
    destinations: List<NavigationItem>,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            destinations.forEach { destination ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (currentDestination != null) {
                        Image(
                            painter = painterResource(
                                id = if (currentDestination.route == destination.route) {
                                    destination.selectedIconId
                                } else {
                                    destination.unselectedIconId
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .clickable {
                                    onNavigateToDestination(destination)
                                },
                        )
                    }
                    Text(
                        text = stringResource(id = destination.labelId),
                        style = MaterialTheme.typography.h3
                    )
                }
            }
        }
    }
}


