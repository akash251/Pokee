package com.demo.pokee.feature_auth.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.pokee.destinations.PhoneNumberScreenDestination
import com.demo.pokee.destinations.AuthenticationScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AuthenticationScreen(
    navigator: DestinationsNavigator
) {
    val colors = listOf(
        Color(0xFFF89D64).copy(alpha = 0.5f),
        Color(0xFFF89D64).copy(alpha = 0.6f),
        Color(0xFFF89D64).copy(alpha = 0.7f),
        Color(0xFFF89D64).copy(alpha = 0.8f),
        Color(0xFFF89D64).copy(alpha = 0.9f),
        Color(0xFFF89D64),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )
    ) {

        Box(modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "pokee",
                color = Color.White,
                fontSize = 40.sp,
            )
        }

        Box(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Box(
                    modifier = Modifier
                        .clickable {
                            navigator.navigate(PhoneNumberScreenDestination)
                        }
                        .fillMaxWidth(0.8f)
                        .background(Color.White, shape = RoundedCornerShape(30)),
                    contentAlignment = Alignment.Center,
                ){
                    Text(
                        text = "CREATE ACCOUNT",
                        modifier = Modifier.padding(vertical = 10.dp),
                        color = Color.Black.copy(alpha = 0.5f)
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .clickable { navigator.navigate(PhoneNumberScreenDestination) }
                        .fillMaxWidth(0.8f)
                        .background(Color.White, shape = RoundedCornerShape(30)),
                    contentAlignment = Alignment.Center,
                ){
                    Text(
                        text = "SIGN IN",
                        modifier = Modifier.padding(vertical = 10.dp),
                        color = Color.Black.copy(alpha = 0.5f)
                    )
                }
                Box(
                    Modifier.fillMaxWidth(0.8f).fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "By continuing,you agree to Pokee's Terms & Conditions and confirm you've read Pokee's Privacy Policy",
                        fontSize = 10.sp,
                        color = Color.White.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center,
                        lineHeight = 12.sp,
                    )
                }
            }

        }



    }
    
}