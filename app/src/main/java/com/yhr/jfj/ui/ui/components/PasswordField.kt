package com.yhr.jfj.ui.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.yhr.jfj.ui.ui.theme.UITheme

@Composable
fun PasswordField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    visualTransformation: VisualTransformation

) {
    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None else visualTransformation,
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }
            ) {
                Icon(
                    imageVector = if (passwordVisibility.value) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                    contentDescription = null
                )
            }
        },
        shape = RoundedCornerShape(30)
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordFieldPreview() {
    UITheme {
        PasswordField(
            modifier = Modifier,
            value = "",
            onValueChange = {},
            label = "Password",
            leadingIcon = Icons.Rounded.Lock,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            visualTransformation = PasswordVisualTransformation()
        )
    }
}