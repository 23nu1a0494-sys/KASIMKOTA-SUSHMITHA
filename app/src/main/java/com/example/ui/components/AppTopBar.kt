package com.example.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ElectricCyan
import com.example.viewmodel.ScreenTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentTab: ScreenTab,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val titleText = when (currentTab) {
        ScreenTab.DASHBOARD -> "Embedded Hub"
        ScreenTab.MODULES -> "Learning Modules"
        ScreenTab.TOPIC_DETAIL -> "Topic Masterclass"
        ScreenTab.EXAM_PREP -> "Exam Preparation"
        ScreenTab.MCQ_ARENA -> "MCQ Arena"
        ScreenTab.CODE_LAB -> "Embedded C Lab"
        ScreenTab.REVISION -> "Revision & Notes"
        ScreenTab.SEARCH -> "Global Search"
        ScreenTab.PROFILE -> "Student Profile"
    }

    val showBack = currentTab == ScreenTab.TOPIC_DETAIL ||
            currentTab == ScreenTab.SEARCH ||
            currentTab == ScreenTab.PROFILE

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = titleText,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 19.sp
                )
            }
        },
        navigationIcon = {
            if (showBack) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.testTag("topbar_back_btn")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Navigate back",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        actions = {
            if (currentTab != ScreenTab.SEARCH) {
                IconButton(
                    onClick = onSearchClick,
                    modifier = Modifier.testTag("topbar_search_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = ElectricCyan
                    )
                }
            }
            if (currentTab != ScreenTab.PROFILE) {
                IconButton(
                    onClick = onProfileClick,
                    modifier = Modifier.testTag("topbar_profile_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}
