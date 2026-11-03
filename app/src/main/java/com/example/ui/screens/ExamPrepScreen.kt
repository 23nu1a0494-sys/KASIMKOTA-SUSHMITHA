package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ExamQuestion
import com.example.data.model.QuestionType
import com.example.data.repository.ExamData
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.NeonTeal
import com.example.ui.theme.PulseOrange
import com.example.ui.theme.SuccessGreen
import com.example.viewmodel.LearningViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExamPrepScreen(
    viewModel: LearningViewModel,
    modifier: Modifier = Modifier
) {
    val selectedFilterType by viewModel.examFilterType.collectAsState()
    val onlyBookmarked by viewModel.onlyBookmarkedExam.collectAsState()
    val bookmarkedQuestions by viewModel.bookmarkedQuestions.collectAsState()

    val tabList = listOf(
        Pair("All Questions", null),
        Pair("2 Marks", QuestionType.VERY_SHORT),
        Pair("5 Marks", QuestionType.SHORT),
        Pair("10 Marks", QuestionType.LONG),
        Pair("Viva Voce", QuestionType.VIVA)
    )

    val currentTabIndex = tabList.indexOfFirst { it.second == selectedFilterType }.coerceAtLeast(0)

    val filteredQuestions = ExamData.questions.filter { q ->
        val matchesType = selectedFilterType == null || q.type == selectedFilterType
        val matchesBookmark = !onlyBookmarked || bookmarkedQuestions.any { it.questionId == q.id }
        matchesType && matchesBookmark
    }

    Column(modifier = modifier.fillMaxSize()) {
        // Tab Row
        ScrollableTabRow(
            selectedTabIndex = currentTabIndex,
            containerColor = MaterialTheme.colorScheme.surface,
            edgePadding = 16.dp,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[currentTabIndex]),
                    color = ElectricCyan
                )
            }
        ) {
            tabList.forEachIndexed { index, (label, type) ->
                Tab(
                    selected = currentTabIndex == index,
                    onClick = { viewModel.setExamFilter(type) },
                    text = {
                        Text(
                            text = label,
                            fontWeight = if (currentTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (currentTabIndex == index) ElectricCyan else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    modifier = Modifier.testTag("exam_tab_${label.lowercase().replace(" ", "_")}")
                )
            }
        }

        // Bookmark Filter Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${filteredQuestions.size} Questions Available",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FilterChip(
                selected = onlyBookmarked,
                onClick = { viewModel.toggleOnlyBookmarkedExam() },
                label = { Text("Bookmarked Only", fontSize = 11.sp) },
                leadingIcon = {
                    Icon(
                        imageVector = if (onlyBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (onlyBookmarked) AmberGold else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = AmberGold.copy(alpha = 0.2f),
                    selectedLabelColor = AmberGold
                ),
                modifier = Modifier.testTag("filter_bookmarked_chips")
            )
        }

        // Question List
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredQuestions) { q ->
                val isBookmarked = bookmarkedQuestions.any { it.questionId == q.id }
                ExamQuestionCard(
                    question = q,
                    isBookmarked = isBookmarked,
                    onBookmarkToggle = { viewModel.toggleQuestionBookmark(q.id) }
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExamQuestionCard(
    question: ExamQuestion,
    isBookmarked: Boolean,
    onBookmarkToggle: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val badgeColor = when (question.type) {
        QuestionType.VERY_SHORT -> ElectricCyan
        QuestionType.SHORT -> NeonTeal
        QuestionType.LONG -> PulseOrange
        QuestionType.VIVA -> AmberGold
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable { expanded = !expanded }
            .testTag("exam_question_card_${question.id}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = badgeColor.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = question.type.label,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = badgeColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onBookmarkToggle,
                        modifier = Modifier.size(32.dp).testTag("bookmark_q_btn_${question.id}")
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Save for revision",
                            tint = if (isBookmarked) AmberGold else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Toggle answer",
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Question Text
            Text(
                text = question.question,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 22.sp
            )

            // Keywords Pills
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                question.keywords.forEach { kw ->
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = kw,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            // Expandable Simple Answer & Detailed Answer
            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Simple Answer:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = ElectricCyan
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = question.simpleAnswer,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 20.sp
                            )

                            if (question.detailedAnswer.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "Detailed Examination Answer:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = NeonTeal
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = question.detailedAnswer,
                                    style = MaterialTheme.typography.bodySmall,
                                    lineHeight = 18.sp
                                )
                            }
                        }
                    }

                    // Easy to Remember Points
                    if (question.rememberPoints.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            color = AmberGold.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(10.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, AmberGold.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Lightbulb,
                                        contentDescription = null,
                                        tint = AmberGold,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Easy-to-Remember Points:",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                        color = AmberGold
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                question.rememberPoints.forEach { pt ->
                                    Text(
                                        text = "• $pt",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.padding(vertical = 1.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
