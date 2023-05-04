package com.demo.pokee.feature_auth.presentation.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demo.pokee.destinations.OtpVerificationScreenDestination
import com.demo.pokee.feature_auth.presentation.viewmodels.PhoneNumberScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun PhoneNumberScreen(navigator:DestinationsNavigator, viewModel: PhoneNumberScreenViewModel= hiltViewModel()) {

    val phoneNumber by viewModel.phoneNumber.collectAsStateWithLifecycle()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        
        Text(
            text = "Enter your phone number",
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = viewModel::onPhoneNumberChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (phoneNumber.length == 13) {
                        navigator.navigate(OtpVerificationScreenDestination(phoneNumber = phoneNumber))
                    } else{
                        Toast.makeText(
                            context,
                            "Invalid phone number",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        )


        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "We'll send you a verification code",
            color = Color.Black.copy(alpha = 0.5f),
            fontSize = 10.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(
                    color = if (phoneNumber.length == 13) Color(0xFFF89D64) else Color.DarkGray.copy(
                        alpha = 0.6f
                    ), shape = RoundedCornerShape(50)
                ).clickable {
                    if (phoneNumber.length == 13) {
                        navigator.navigate(OtpVerificationScreenDestination(phoneNumber = phoneNumber))
                    } else{
                        Toast.makeText(
                            context,
                            "Invalid phone number",
                            Toast.LENGTH_SHORT
                        ).show()
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