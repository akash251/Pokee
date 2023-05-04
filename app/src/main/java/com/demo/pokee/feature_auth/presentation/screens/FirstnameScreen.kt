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
import com.demo.pokee.destinations.LastNameScreenDestination
import com.demo.pokee.feature_auth.presentation.viewmodels.FirstNameScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun FirstnameScreen(
    navigator: DestinationsNavigator,
    viewModel: FirstNameScreenViewModel = hiltViewModel(),
) {

    val firstName by viewModel.firstName.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "What's your first name?",
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = firstName,
            onValueChange = viewModel::onFirstNameChange,
            modifier =  Modifier
                .background(shape = RoundedCornerShape(80), color = Color.White),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (firstName.length >=3) {
                        viewModel.saveFirstName()
                        navigator.navigate(LastNameScreenDestination)
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
                    color = if (firstName.length >= 3) Color(0xFFF89D64) else Color.DarkGray.copy(
                        alpha = 0.6f
                    ), shape = RoundedCornerShape(50)
                ).clickable {
                    if (firstName.length >=3) {
                        viewModel.saveFirstName()
                        navigator.navigate(LastNameScreenDestination)
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