package com.yhr.jfj.ui.ui.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yhr.jfj.ui.Route
import com.yhr.jfj.ui.ui.components.HeaderText
import com.yhr.jfj.ui.ui.components.LoginTextField
import com.yhr.jfj.ui.ui.components.PasswordField
import com.yhr.jfj.ui.ui.theme.UITheme

// Default value for make app consistent
val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun SignUpScreen(navController: NavController) {
    // State for first name
    val (firstName, onFirstNameChange) = rememberSaveable {
        mutableStateOf("")
    }
    // State for last name
    val (lastName, onLastNameChange) = rememberSaveable {
        mutableStateOf("")
    }
    // State for email
    val (email, onEmailChange) = rememberSaveable {
        mutableStateOf("")
    }
    // State for password
    val (password, onPasswordChange) = rememberSaveable {
        mutableStateOf("")
    }
    // State for confirm password
    val (confirmPassword, onConfirmPasswordChange) = rememberSaveable {
        mutableStateOf("")
    }
    // State for checkbox
    val (agree, onAgreeChange) = rememberSaveable {
        mutableStateOf(false)
    }
    // State for checking, id password same or not
    var isPasswordSame by remember {
        mutableStateOf(false)
    }
    // State for checking is there any empty field or not
    val isFieldsNotEmpty = firstName.isNotEmpty() && lastName.isNotEmpty() &&
            email.isNotEmpty() && password.isNotEmpty() &&
            confirmPassword.isNotEmpty() && agree
    val context = LocalContext.current

    // Main content
    Column(
        modifier = Modifier
            .padding(defaultPadding)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        HeaderText(
            text = "Sign Up",
            modifier = Modifier.padding(itemSpacing)
        )

        // First name
        LoginTextField(
            value = firstName,
            onValueChange = onFirstNameChange,
            label = "First name",
            leadingIcon = Icons.Rounded.Person,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(itemSpacing))

        // Last name
        LoginTextField(
            value = lastName,
            onValueChange = onLastNameChange,
            label = "Last name",
            leadingIcon = Icons.Rounded.Person,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(itemSpacing))

        // Email
        LoginTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "Email",
            leadingIcon = Icons.Rounded.Email,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(itemSpacing))

        // Password
        PasswordField(
            value = password,
            onValueChange = onPasswordChange,
            label = "Password",
            leadingIcon = Icons.Rounded.Lock,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(itemSpacing))

        // Confirm Password
        PasswordField(
            modifier = Modifier.fillMaxWidth(),
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = "Confirm Password",
            leadingIcon = Icons.Rounded.Lock,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(itemSpacing))

        // Confirm privacy and policy
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val privacyText = "Privacy"
            val policyText = "Policy"
            val annotatedString = buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                    append("I agree with")
                }
                append(" ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    pushStringAnnotation(tag = privacyText, annotation = privacyText)
                    append(privacyText)
                }
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                    append(" & ")
                }
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    pushStringAnnotation(tag = policyText, annotation = policyText)
                    append(policyText)
                }
            }
            Checkbox(checked = agree, onCheckedChange = onAgreeChange)
            ClickableText(text = annotatedString) { offset ->
                annotatedString.getStringAnnotations(offset, offset).forEach {
                    when (it.tag) {
                        privacyText -> {
                            navController.navigate(Route.PrivacyScreen().name)
                            Toast.makeText(context, "Go to privacy page", Toast.LENGTH_SHORT).show()
                        }

                        policyText -> {
                            navController.navigate(Route.PolicyScreen().name)
                            Toast.makeText(context, "Go to policy page", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(itemSpacing))

        // Signup button
        Button(
            onClick = {
                if (isFieldsNotEmpty) {
                    navController.navigate(Route.HomeScreen().name)
                    Toast.makeText(context, "Sign Up", Toast.LENGTH_SHORT).show()
                }
            },
            enabled = isFieldsNotEmpty,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sign Up")
        }

        Spacer(modifier = Modifier.height(itemSpacing))

        // If already hava an account the navigate to login screen
        Row {
            val signInText = "Sign In"
            val annotatedSignInText = buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                    append("Already have an account?")
                }
                append(" ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    pushStringAnnotation(tag = signInText, annotation = signInText)
                    append(signInText)
                }
            }
            ClickableText(text = annotatedSignInText) { offset ->
                annotatedSignInText.getStringAnnotations(offset, offset).forEach {
                    when (it.tag) {
                        signInText -> {
                            navController.navigate(Route.LoginScreen().name)
                            Toast.makeText(context, "Go to sign in screen", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SignUpScreenPreview() {
    UITheme {
        val navController = rememberNavController()
        SignUpScreen(navController = navController)
    }
}