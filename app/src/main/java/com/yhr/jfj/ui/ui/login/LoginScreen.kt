package com.yhr.jfj.ui.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yhr.jfj.ui.R
import com.yhr.jfj.ui.ui.components.HeaderText
import com.yhr.jfj.ui.ui.components.LoginTextField
import com.yhr.jfj.ui.ui.components.PasswordField
import com.yhr.jfj.ui.ui.theme.UITheme

// Default value for make app consistent
val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // State for user name
        val (userName, setUserName) = rememberSaveable {
            mutableStateOf("")
        }
        // State for password
        val (password, setPassword) = rememberSaveable {
            mutableStateOf("")
        }
        // State for check
        val (checked, onCheckChange) = rememberSaveable {
            mutableStateOf(false)
        }

        // Header part
        HeaderText(
            text = "Login",
            modifier = Modifier.padding(vertical = defaultPadding)
        )

        // Username
        LoginTextField(
            value = userName,
            onValueChange = setUserName,
            label = "Username",
            leadingIcon = Icons.Default.Person,
            keyboardType = KeyboardType.Text,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(itemSpacing))

        // Password
        PasswordField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = setPassword,
            label = "Password",
            leadingIcon = Icons.Rounded.Lock,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(itemSpacing))

        // Check box and forget password
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checked, onCheckedChange = onCheckChange)
                Text(text = "Remember me")
            }
            Row {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Forget Password")
                }
            }
        }

        Spacer(modifier = Modifier.height(itemSpacing))

        // Login Button
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login")
        }

        // Alternative login option
        AlternativeLoginOptions(onIconClick = {}, onSignUpClick = { /*TODO*/ })
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    UITheme {
        LoginScreen()
    }
}

@Composable
fun AlternativeLoginOptions(
    onIconClick: (index: Int) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconList = listOf(
        R.drawable.icon_instagram,
        R.drawable.icon_github,
        R.drawable.icon_google
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomCenter),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Or Sign in with")
        Spacer(modifier = Modifier.height(itemSpacing))
        Row {
            iconList.forEachIndexed { index, iconRedId ->
                Icon(
                    painter = painterResource(id = iconRedId),
                    contentDescription = null,
                    modifier = modifier
                        .size(32.dp)
                        .clickable {
                            onIconClick(index)
                        }
                        .clip(CircleShape)
                )
            }
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don't have an account?")
            TextButton(onClick = { onSignUpClick() }) {
                Text(text = "Sign Up")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlternativeLoginOptionsPreview() {

}