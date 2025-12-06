package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BorderCheckbox
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in.SocialButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SignUpScreen(
    onSignUp: () -> Unit = {},
    onGoogleLoginClick: () -> Unit = {},
    onFacebookLoginClick: () -> Unit = {},
    onSignIn: () -> Unit = {}
) {
    var checked by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.weight(54f))
            Column(Modifier.wrapContentWidth().padding(end = 120.dp)) {
                Text(
                    text = "Create an account",
                    fontWeight = FontWeight.Bold,
                    style = AppTextStyles.largeTextRegular,
                    maxLines = 1
                )
                Text(
                    text = "Let's help you set up your account, it won't take long.",
                    modifier = Modifier.padding(top = 5.dp),
                    fontWeight = FontWeight.Medium,
                    style = AppTextStyles.smallerTextRegular
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            InputField(
                label = "Name",
                value = name,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { name = it },
                placeholder = "Enter Name"
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputField(
                label = "Email",
                value = email,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { email = it },
                placeholder = "Enter Email"
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputField(
                label = "Password",
                value = password,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { password = it },
                placeholder = "Enter Password"
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputField(
                label = "Confirm Password",
                value = confirmPassword,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { confirmPassword = it },
                placeholder = "Retype Password"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(start = 10.dp)) {
                BorderCheckbox(checked) {
                    checked = it
                }
                Text(
                    text = "Accept terms & Condition",
                    color = AppColors.secondary100,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable { checked = !checked },
                    style = AppTextStyles.smallerTextRegular
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            BigButton("Sign Up", onClick = onSignUp)
            Spacer(modifier = Modifier.height(14.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f).padding(end = 7.dp), color = Color.LightGray)
                Text(
                    text = "Or Sign in With",
                    color = AppColors.gray4,
                    style = AppTextStyles.smallerTextRegular
                )
                HorizontalDivider(modifier = Modifier.weight(1f).padding(start = 7.dp), color = Color.LightGray)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                SocialButton(
                    iconRes = R.drawable.ic_google,
                    modifier = Modifier.size(44.dp),
                    onClick = onGoogleLoginClick
                )
                Spacer(modifier = Modifier.width(25.dp))
                SocialButton(
                    iconRes = R.drawable.ic_facebook,
                    modifier = Modifier.size(44.dp),
                    onClick = onFacebookLoginClick
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already a member? ",
                    fontSize = 14.sp, color = Color.Black,
                    style = AppTextStyles.smallerTextRegular
                )
                Text(
                    text = "Sign In",
                    fontSize = 14.sp,
                    color = Color(0xFFFF9800),
                    modifier = Modifier.clickable { onSignIn() },
                    style = AppTextStyles.smallerTextRegular
                )
            }
            Spacer(modifier = Modifier.weight(30f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}