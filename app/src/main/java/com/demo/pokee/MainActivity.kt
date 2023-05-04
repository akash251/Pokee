package com.demo.pokee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.demo.pokee.destinations.AuthenticationScreenDestination
import com.demo.pokee.destinations.FirstnameScreenDestination
import com.demo.pokee.destinations.LastNameScreenDestination
import com.demo.pokee.destinations.ProfileImageScreenDestination
import com.demo.pokee.destinations.ProfileScreenDestination
import com.demo.pokee.destinations.UserNameScreenDestination
import com.demo.pokee.ui.theme.PokeeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val mainViewModel : MainViewModel by viewModels()
            val navController = rememberNavController()
            val mainState by mainViewModel.mainState.collectAsStateWithLifecycle()
            LaunchedEffect(key1 = true ){
                mainViewModel.getMainState()
            }

            PokeeTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold {
                        Column(
                            modifier = Modifier.padding(it)
                        ) {
                           DestinationsNavHost(
                               navController=navController,
                               navGraph = NavGraphs.root,
                               startRoute = if (!mainState.isUserLoggedIn){
                                   AuthenticationScreenDestination
                               } else if (!mainState.isFirstNameProvided){
                                   FirstnameScreenDestination
                               } else if (!mainState.isLastNameProvided){
                                    LastNameScreenDestination
                               } else if (!mainState.isUserNameProvided){
                                   UserNameScreenDestination
                               } else if (!mainState.isImageProvided){
                                   ProfileImageScreenDestination
                               } else ProfileScreenDestination
                           )
                        }
                    }
                }
            }
        }
    }
}