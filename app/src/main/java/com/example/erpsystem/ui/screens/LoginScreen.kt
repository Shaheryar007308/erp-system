package com.example.erpsystem.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.erpsystem.ui.theme.BurntAmber
import com.example.erpsystem.ui.theme.DeepNavy
import com.example.erpsystem.ui.theme.SlateMuted
import com.example.erpsystem.ui.theme.SoftCream
import com.example.erpsystem.ui.theme.SurfaceWhite
import com.example.erpsystem.viewmodel.LoginUiState
import com.example.erpsystem.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen (
    adminType: String,
    viewModel: LoginViewModel,
    onNavigateBack :()-> Unit,
    onLoginSuccess:(role : String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
val uiState by viewModel.uiState

    LaunchedEffect(uiState) {
        if(uiState is LoginUiState.Success){
            val role = (uiState as LoginUiState.Success).response.user.role
            onLoginSuccess(role)
            viewModel.resetState()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = DeepNavy
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SoftCream)
            )
        },
        containerColor = SoftCream
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Group Context Descriptor
                Text(
                    text = if (adminType == "ERP_ADMIN") "ERP Master Portal" else "Department Admin Node",
                    fontSize = 26.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = DeepNavy
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Provide cryptographically secured credentials to mount active session connections.",
                    fontSize = 13.sp,
                    color = SlateMuted,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontFamily = FontFamily.SansSerif
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Credentials Action Form Card Layout
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {
                        // Email Field Specification
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Secure Administrative Email") },
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = SlateMuted) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = BurntAmber,
                                focusedLabelColor = BurntAmber
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Password Field Specification
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password String") },
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = SlateMuted) },
                            trailingIcon = {
                                val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(imageVector = image, contentDescription = null, tint = SlateMuted)
                                }
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = BurntAmber,
                                focusedLabelColor = BurntAmber
                            )
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Trigger Login Execution Control Action
                        Button(
                            onClick = { viewModel.login(email, password, adminType) },
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = BurntAmber),
                            enabled = uiState !is LoginUiState.Loading
                        ) {
                            if (uiState is LoginUiState.Loading) {
                                CircularProgressIndicator(color = SurfaceWhite, modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
                            } else {
                                Text("Login", color = SurfaceWhite, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        // Real-time Runtime Security Context Error Message UI Injection
                        if (uiState is LoginUiState.Error) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = (uiState as LoginUiState.Error).errorMessage,
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }

}