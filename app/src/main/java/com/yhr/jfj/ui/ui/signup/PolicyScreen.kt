package com.yhr.jfj.ui.ui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yhr.jfj.ui.Route

@Composable
fun PolicyScreen(navController: NavController, onBtnClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Policy Screen",
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = {
                onBtnClick()
                navController.navigate(Route.SignUpScreen().name)
            }
        ) {
            Text(text = "Agree")
        }
    }
}