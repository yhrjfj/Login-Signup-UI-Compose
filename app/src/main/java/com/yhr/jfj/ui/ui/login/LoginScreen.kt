package com.yhr.jfj.ui.ui.login

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yhr.jfj.ui.R
import com.yhr.jfj.ui.Route
import com.yhr.jfj.ui.ui.components.HeaderText
import com.yhr.jfj.ui.ui.components.LoginTextField
import com.yhr.jfj.ui.ui.components.PasswordField
import com.yhr.jfj.ui.ui.theme.UITheme

// Default value for make app consistent
val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier.padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
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
        // Context
        val context = LocalContext.current

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
            imeAction = ImeAction.Next,
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
            imeAction = ImeAction.Done,
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
            onClick = {
                navController.navigate(Route.HomeScreen().name)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login")
        }

        // Alternative login option
        AlternativeLoginOptions(
            onIconClick = { index ->
                when (index) {
                    0 -> {
                        Toast.makeText(context, "Instagram", Toast.LENGTH_SHORT).show()
                    }

                    1 -> {
                        Toast.makeText(context, "GitHub", Toast.LENGTH_SHORT).show()
                    }

                    2 -> {
                        Toast.makeText(context, "Google", Toast.LENGTH_SHORT).show()
                    }
                }

            },
            onSignUpClick = {
                navController.navigate(Route.SignUpScreen().name)
            },
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    UITheme {
        val navController = rememberNavController()
        LoginScreen(navController = navController)
    }
}

@Composable
fun AlternativeLoginOptions(
    onIconClick: (index: Int) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val iconList = listOf(
        R.drawable.icon_instagram,
        R.drawable.icon_github,
        R.drawable.icon_google,
    )
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Or Sign in With")
        Spacer(Modifier.height(itemSpacing))
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            iconList.forEachIndexed { index, iconResId ->
                Icon(
                    painter = painterResource(iconResId),
                    contentDescription = "alternative Login",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            onIconClick(index)
                        }
                        .clip(CircleShape)
                )
                Spacer(Modifier.width(defaultPadding))
            }
        }
        Spacer(Modifier.height(itemSpacing))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an Account?")
            TextButton(onClick = onSignUpClick) {
                Text("Sign Up")
            }
        }
    }
}