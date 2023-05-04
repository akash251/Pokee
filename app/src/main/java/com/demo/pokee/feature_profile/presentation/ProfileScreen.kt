package com.demo.pokee.feature_profile.presentation

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.demo.pokee.util.BottomBarWithIconAndLabel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            BottomBarWithIconAndLabel()
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Pokee",color=Color(0xFFF89D64), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontSize = 25.sp)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors()
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(fontSize = 18.sp, text ="Hey, ${state.displayName}", color = Color(0xFFF89D64))
            Spacer(modifier = Modifier.height(25.dp))

            Card(
                shape = RoundedCornerShape(20.dp)

            ) {
                AsyncImage(
                    model = state.imageUri,
                    contentDescription = "profile image",
                    modifier = Modifier
                        .size(300.dp),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "@"+state.userName, color = Color(0xFFF89D64))

            if (state.imageUri.isBlank()){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }
        }

    }


}