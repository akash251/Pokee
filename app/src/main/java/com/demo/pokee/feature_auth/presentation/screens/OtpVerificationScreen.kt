package com.demo.pokee.feature_auth.presentation.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demo.pokee.destinations.FirstnameScreenDestination
import com.demo.pokee.feature_auth.presentation.screens.components.OtpTextField
import com.demo.pokee.feature_auth.presentation.viewmodels.OtpVerificationScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalComposeUiApi::class)
@Destination
@Composable
fun OtpVerificationScreen(
    viewModel: OtpVerificationScreenViewModel = hiltViewModel(),
    phoneNumber : String,
    navigator : DestinationsNavigator
) {

    val otpCode by viewModel.otpCode.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val activity = LocalContext.current as Activity

    val counter by viewModel.timer.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = true ){
        viewModel.verifyPhoneNumber(activity, phoneNumber)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Enter your verification code",
            fontSize = 25.sp
        )
      //  Spacer(modifier = Modifier.height(20.dp))

        Spacer(modifier = Modifier.height(20.dp))
        OtpTextField(
            otpText = otpCode,
            onOtpTextChange = {otp,_->
                viewModel.onOtpCodeChange(otp)
            },
            onDoneImeAction = {
                keyboardController?.hide()
                viewModel.verifyOtpCode(
                onVerificationFailed = {
                    Toast.makeText(
                        activity,
                        "Verification failed",
                        Toast.LENGTH_LONG
                    ).show()
                },
                onVerificationSuccess = {
                    Toast.makeText(
                        activity,
                        "Verification success",
                        Toast.LENGTH_LONG
                    ).show()
                    navigator.navigate(FirstnameScreenDestination){
                        navigator.popBackStack()
                    }
                },
                otp = otpCode
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = "Didn't get code? ",
                color = Color.Black.copy(alpha = 0.5f),
            )
            Text(
                text = "Resend",
                color = if (counter != 0) Color.Black else Color(0xFFF89D64) ,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "$counter Seconds",
            color =  Color(0xFFF89D64) ,
            fontWeight = FontWeight.Bold
        )
        
        if (isLoading){
            Spacer(modifier = Modifier.height(20.dp))
            CircularProgressIndicator()
            Text(text = "Verifying otp please wait")
        }


    }

}