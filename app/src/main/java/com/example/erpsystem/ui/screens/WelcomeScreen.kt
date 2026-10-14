package com.example.erpsystem.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.erpsystem.ui.theme.BurntAmber
import com.example.erpsystem.ui.theme.DeepNavy
import com.example.erpsystem.ui.theme.InkDark
import com.example.erpsystem.ui.theme.SlateMuted
import com.example.erpsystem.ui.theme.SoftCream
import com.example.erpsystem.ui.theme.SurfaceWhite

@Composable
fun WelcomeScreen(onRoleSelected: (String) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = SoftCream
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Section: Context Header Branding
            Column(
                modifier = Modifier.padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to Academic ERP",
                    fontSize = 28.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = InkDark,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "A simple student management system for university departments." + " Access secure portal  to manage department records and monitor students",
                    fontSize = 15.sp,
                    color = SlateMuted,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )
            }

            // Central Section: Multi-Tenant Authorization Cards Stack
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Select Authorization Portal",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = InkDark,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                PortalSelectionCard(
                    title = "Master ERP Admin",
                    description = "Global configuration privileges. Appoint department heads and review cross-campus data logs.",
                    icon = Icons.Default.SupervisorAccount,
                    onClick = { onRoleSelected("ERP_ADMIN") }
                )

                PortalSelectionCard(
                    title = "Department  Admin",
                    description = "Restricted department privileges. Manage your department's records and data.",
                    icon = Icons.Default.Business,
                    onClick = { onRoleSelected("DEPT_ADMIN") }
                )
            }

            // Bottom Section: Footer Branding Note
            Text(
                text = "Secured Node Link · Version 1.0.0",
                fontSize = 12.sp,
                color = SlateMuted,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
    }
}

@Composable
fun PortalSelectionCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BurntAmber,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepNavy
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = SlateMuted,
                    lineHeight = 18.sp
                )
            }
        }
    }
}