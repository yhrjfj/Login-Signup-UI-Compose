package com.yhr.jfj.ui.ui.signup

import androidx.compose.foundation.layout.Box
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

@Composable
fun PolicyScreen(onBtnClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Policy Screen",
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = {
                onBtnClick()
            }
        ) {
            Text(text = "Agree")
        }
    }
}