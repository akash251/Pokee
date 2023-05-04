package com.demo.pokee.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun BottomBarWithIconAndLabel() {

    val items = listOf(
        BottomBarItem("Home", Icons.Default.Home),
        BottomBarItem("Send", Icons.Default.Send),
        BottomBarItem("Search", Icons.Default.Search),
        BottomBarItem("Profile", Icons.Default.Person),
    )
    val selectedItem = remember {
        mutableStateOf(BottomBarItem("Profile", Icons.Default.Person))
    }

    BottomAppBar(
        containerColor = Color(0xFFF89D64)
    ) {
        items.forEach {
            NavigationBarItem(selected = selectedItem.value == it,
                onClick = { selectedItem.value = it },
                icon = {
                    Icon(imageVector = it.icon, contentDescription = "")

                },
                //to show label of only selected item
                alwaysShowLabel = false,

                label = {
                    Text(text = it.label)
                }
            )
        }
    }


}



data class BottomBarItem(
    val label: String,
    val icon: ImageVector
)