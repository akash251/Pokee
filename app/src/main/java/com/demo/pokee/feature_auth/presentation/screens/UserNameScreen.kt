package com.demo.pokee.feature_auth.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demo.pokee.destinations.ProfileImageScreenDestination
import com.demo.pokee.feature_auth.presentation.viewmodels.UserNameScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun UserNameScreen(
    navigator: DestinationsNavigator,
    viewModel: UserNameScreenViewModel = hiltViewModel(),
) {

    val userName by viewModel.userName.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Enter user name",
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = userName,
            onValueChange = viewModel::onUserNameChange,
            modifier =  Modifier
                .background(shape = RoundedCornerShape(80), color = Color.White),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (userName.length >=3) {
                        viewModel.saveUserName()
                        navigator.navigate(ProfileImageScreenDestination)
                    }
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(25.dp))

        Box(
            modifier = Modifier

                .fillMaxWidth(0.8f)
                .background(
                    color = if (userName.length >= 3) Color(0xFFF89D64) else Color.DarkGray.copy(
                        alpha = 0.6f
                    ), shape = RoundedCornerShape(50)
                ).clickable {
                    if (userName.length >=3) {
                        viewModel.saveUserName()
                        navigator.navigate(ProfileImageScreenDestination)
                    }
                },
            contentAlignment = Alignment.Center,
        ){
            Text(
                text = "Next",
                modifier = Modifier.padding(vertical = 20.dp),
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }




}