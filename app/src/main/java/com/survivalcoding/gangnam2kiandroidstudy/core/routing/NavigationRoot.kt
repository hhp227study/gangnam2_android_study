package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.main.MainScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes.SavedRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.SearchRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.signin.SignInScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.signup.SignUpScreen

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val topLevelBackStack = rememberNavBackStack(Route.SignIn)

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = topLevelBackStack,
        entryProvider = entryProvider {
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
                                    SavedRecipesRoot()
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
                SearchRoot(onBackClick = { topLevelBackStack.removeLastOrNull() })
            }
            entry<Route.RecipeDetails> {  }
        }
    )
}