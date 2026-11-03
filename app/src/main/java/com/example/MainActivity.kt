package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.AppNavBar
import com.example.ui.components.AppTopBar
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.ExamPrepScreen
import com.example.ui.screens.GlobalSearchScreen
import com.example.ui.screens.McqPracticeScreen
import com.example.ui.screens.ModulesScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.ProgrammingLabScreen
import com.example.ui.screens.RevisionNotesScreen
import com.example.ui.screens.TopicDetailScreen
import com.example.ui.theme.EmbeddedHubTheme
import com.example.viewmodel.LearningViewModel
import com.example.viewmodel.ScreenTab

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmbeddedHubTheme {
                MainAppContainer()
            }
        }
    }
}

@Composable
fun MainAppContainer(
    viewModel: LearningViewModel = viewModel()
) {
    val currentTab by viewModel.currentTab.collectAsState()

    // Global Back Handler when on sub-screens
    BackHandler(enabled = currentTab != ScreenTab.DASHBOARD) {
        when (currentTab) {
            ScreenTab.TOPIC_DETAIL -> viewModel.navigateTo(ScreenTab.MODULES)
            ScreenTab.SEARCH -> viewModel.navigateTo(ScreenTab.DASHBOARD)
            ScreenTab.PROFILE -> viewModel.navigateTo(ScreenTab.DASHBOARD)
            else -> viewModel.navigateTo(ScreenTab.DASHBOARD)
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                currentTab = currentTab,
                onBackClick = {
                    when (currentTab) {
                        ScreenTab.TOPIC_DETAIL -> viewModel.navigateTo(ScreenTab.MODULES)
                        ScreenTab.SEARCH -> viewModel.navigateTo(ScreenTab.DASHBOARD)
                        ScreenTab.PROFILE -> viewModel.navigateTo(ScreenTab.DASHBOARD)
                        else -> viewModel.navigateTo(ScreenTab.DASHBOARD)
                    }
                },
                onSearchClick = { viewModel.navigateTo(ScreenTab.SEARCH) },
                onProfileClick = { viewModel.navigateTo(ScreenTab.PROFILE) }
            )
        },
        bottomBar = {
            AppNavBar(
                currentTab = currentTab,
                onTabSelected = { tab -> viewModel.navigateTo(tab) }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(targetState = currentTab, label = "screenTransition") { tab ->
                when (tab) {
                    ScreenTab.DASHBOARD -> DashboardScreen(viewModel = viewModel)
                    ScreenTab.MODULES -> ModulesScreen(viewModel = viewModel)
                    ScreenTab.TOPIC_DETAIL -> TopicDetailScreen(viewModel = viewModel)
                    ScreenTab.EXAM_PREP -> ExamPrepScreen(viewModel = viewModel)
                    ScreenTab.MCQ_ARENA -> McqPracticeScreen(viewModel = viewModel)
                    ScreenTab.CODE_LAB -> ProgrammingLabScreen(viewModel = viewModel)
                    ScreenTab.REVISION -> RevisionNotesScreen(viewModel = viewModel)
                    ScreenTab.SEARCH -> GlobalSearchScreen(viewModel = viewModel)
                    ScreenTab.PROFILE -> ProfileScreen(viewModel = viewModel)
                }
            }
        }
    }
}
