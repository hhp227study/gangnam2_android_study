package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.core.util.DeepLink.parseRecipeDeepLink
import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.main.MainScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail.RecipeDetailRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail.RecipeDetailViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes.SavedRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.SearchRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.signin.SignInScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.signup.SignUpScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.splash.SplashRoot
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@SuppressLint("VisibleForTests")
@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    deepLinkUri: Uri? = null
) {
    val deepLinkTarget = remember(deepLinkUri) {
        parseRecipeDeepLink(deepLinkUri)
    }
    val topLevelBackStack = rememberNavBackStack(
        if (deepLinkTarget != null) Route.Main else Route.Splash
    )

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = topLevelBackStack,
        entryProvider = entryProvider {
            entry<Route.Splash> {
                SplashRoot(
                    onNavigate = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    }
                )
            }
            entry<Route.SignIn> {
                SignInScreen(
                    onSignInClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.Main)
                    },
                    onSignUpClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignUp)
                    }
                )
            }
            entry<Route.SignUp> {
                SignUpScreen(
                    onSignUp = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.Main)
                    },
                    onSignInClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    }
                )
            }
            entry<Route.Main> {
                val backStack = rememberNavBackStack(Route.Home)

                LaunchedEffect(deepLinkTarget) {
                    when (deepLinkTarget) {
                        DeepLinkTarget.SavedRecipes -> {
                            backStack.clear()
                            backStack.add(Route.SavedRecipes)
                        }
                        is DeepLinkTarget.RecipeDetail -> {
                            backStack.clear()
                            backStack.add(Route.Home)
                            topLevelBackStack.add(
                                Route.RecipeDetails(deepLinkTarget.id)
                            )
                        }
                        null -> Unit
                    }
                }
                MainScreen(
                    backStack = backStack,
                    body = {
                        NavDisplay(
                            modifier = modifier,
                            backStack = backStack,
                            entryProvider = entryProvider {
                                entry<Route.Home> { HomeRoot(
                                    onSearchKeywordFocusChanged = {
                                        if (it) {
                                            topLevelBackStack.add(Route.Search)
                                        }
                                    }
                                ) }
                                entry<Route.SavedRecipes> {
                                    SavedRecipesRoot(onRecipeClick = {
                                        topLevelBackStack.add(Route.RecipeDetails(it))
                                    })
                                }
                                entry<Route.Notification> {
                                    // TODO
                                }
                                entry<Route.Profile> {
                                    // TODO
                                }
                            }
                        )
                    }
                )
            }
            entry<Route.Search> {
                SearchRoot(
                    onRecipeClick = { recipeId ->
                        topLevelBackStack.add(Route.RecipeDetails(recipeId))
                    },
                    onBackClick = {
                        topLevelBackStack.removeLastOrNull()
                    }
                )
            }
            entry<Route.RecipeDetails> { route ->
                val viewModel: RecipeDetailViewModel = koinViewModel(
                    parameters = {
                        parametersOf(route.recipeId)
                    }
                )

                RecipeDetailRoot(
                    viewModel = viewModel,
                    onNavigateUp = {
                        topLevelBackStack.removeIf { it == route }
                    }
                )
            }
        }
    )
}