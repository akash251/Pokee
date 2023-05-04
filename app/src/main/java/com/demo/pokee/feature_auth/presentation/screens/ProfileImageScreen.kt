package com.demo.pokee.feature_auth.presentation.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.demo.pokee.destinations.ProfileScreenDestination
import com.demo.pokee.feature_auth.presentation.viewmodels.ProfileImageViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ProfileImageScreen(
    navigator: DestinationsNavigator,
    viewModel: ProfileImageViewModel = hiltViewModel(),
) {

    val imageUriString by viewModel.profileImageUriString.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val imageChooserLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            it?.let { uri ->
                viewModel.onProfileChange(uri,context)
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,


        ) {


        Card(
            shape = RoundedCornerShape(20.dp)

        ) {
            AsyncImage(
                model = Uri.parse(imageUriString),
                contentDescription = "profile image",
                modifier = Modifier
                    .size(300.dp),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = if (imageUriString.isEmpty()) "Select" else "Change",
            color = Color(0xFFF89D64),
            modifier = Modifier
                .clickable {
                    imageChooserLauncher.launch("image/*")
                }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(
                    color = if (imageUriString.isNotBlank()) Color(0xFFF89D64) else Color.DarkGray.copy(
                        alpha = 0.6f
                    ), shape = RoundedCornerShape(50)
                )
                .clickable {
                    viewModel.saveProfileImage()
                    navigator.navigate(ProfileScreenDestination) {
                        navigator.popBackStack()
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Next",
                modifier = Modifier.padding(vertical = 20.dp),
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}