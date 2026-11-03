package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Difficulty
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.NeonTeal
import com.example.ui.theme.PulseOrange
import com.example.ui.theme.SuccessGreen
import com.example.viewmodel.LearningViewModel

@Composable
fun McqPracticeScreen(
    viewModel: LearningViewModel,
    modifier: Modifier = Modifier
) {
    val quizState by viewModel.quizState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Difficulty Level Selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Difficulty.entries.forEach { diff ->
                FilterChip(
                    selected = quizState.difficulty == diff,
                    onClick = { viewModel.startQuiz(diff) },
                    label = { Text(diff.label, fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = ElectricCyan.copy(alpha = 0.2f),
                        selectedLabelColor = ElectricCyan
                    ),
                    modifier = Modifier.testTag("diff_chip_${diff.name.lowercase()}")
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (quizState.isFinished) {
            // Final Results View
            QuizFinishedView(
                score = quizState.score,
                total = quizState.questions.size,
                difficulty = quizState.difficulty.label,
                onRetake = { viewModel.restartQuiz() }
            )
        } else if (quizState.questions.isNotEmpty()) {
            val currentQ = quizState.questions[quizState.currentIndex]
            val totalQuestions = quizState.questions.size
            val progressFraction = (quizState.currentIndex + 1) / totalQuestions.toFloat()

            // Header: Question Counter & Score
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Question ${quizState.currentIndex + 1} of $totalQuestions",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Surface(
                    color = ElectricCyan.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Score: ${quizState.score}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = ElectricCyan,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { progressFraction },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = ElectricCyan,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Question Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("mcq_question_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = currentQ.question,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 22.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 4 Options
            currentQ.options.forEachIndexed { index, optionText ->
                val isSelected = quizState.selectedOptionIndex == index
                val isCorrectAnswer = index == currentQ.correctIndex

                val optionColor = when {
                    quizState.isSubmitted && isCorrectAnswer -> SuccessGreen
                    quizState.isSubmitted && isSelected && !isCorrectAnswer -> ErrorRed
                    isSelected -> ElectricCyan
                    else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                }

                val optionBg = when {
                    quizState.isSubmitted && isCorrectAnswer -> SuccessGreen.copy(alpha = 0.15f)
                    quizState.isSubmitted && isSelected && !isCorrectAnswer -> ErrorRed.copy(alpha = 0.15f)
                    isSelected -> ElectricCyan.copy(alpha = 0.12f)
                    else -> MaterialTheme.colorScheme.surface
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable(enabled = !quizState.isSubmitted) {
                            viewModel.selectQuizOption(index)
                        }
                        .testTag("mcq_option_$index"),
                    colors = CardDefaults.cardColors(containerColor = optionBg),
                    border = androidx.compose.foundation.BorderStroke(if (isSelected || (quizState.isSubmitted && isCorrectAnswer)) 2.dp else 1.dp, optionColor)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(optionColor.copy(alpha = 0.2f))
                                .border(1.dp, optionColor, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            val letter = listOf("A", "B", "C", "D").getOrElse(index) { "$index" }
                            Text(letter, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = optionColor)
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = optionText,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )

                        if (quizState.isSubmitted) {
                            if (isCorrectAnswer) {
                                Icon(Icons.Default.Check, contentDescription = "Correct", tint = SuccessGreen)
                            } else if (isSelected) {
                                Icon(Icons.Default.Close, contentDescription = "Incorrect", tint = ErrorRed)
                            }
                        }
                    }
                }
            }

            // Instant Feedback & Explanation Banner
            AnimatedVisibility(visible = quizState.isSubmitted) {
                Surface(
                    color = if (quizState.isCorrect) SuccessGreen.copy(alpha = 0.15f) else ErrorRed.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (quizState.isCorrect) SuccessGreen else ErrorRed),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (quizState.isCorrect) Icons.Default.CheckCircle else Icons.Default.Info,
                                contentDescription = null,
                                tint = if (quizState.isCorrect) SuccessGreen else ErrorRed,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (quizState.isCorrect) "Correct Answer!" else "Incorrect Answer",
                                fontWeight = FontWeight.Bold,
                                color = if (quizState.isCorrect) SuccessGreen else ErrorRed,
                                fontSize = 13.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = currentQ.explanation,
                            style = MaterialTheme.typography.bodySmall,
                            lineHeight = 18.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Action Button: Submit or Next Question
            if (!quizState.isSubmitted) {
                Button(
                    onClick = { viewModel.submitQuizAnswer() },
                    enabled = quizState.selectedOptionIndex != null,
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("submit_answer_btn")
                ) {
                    Text("Submit Answer", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            } else {
                Button(
                    onClick = { viewModel.nextQuizQuestion() },
                    colors = ButtonDefaults.buttonColors(containerColor = NeonTeal),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("next_question_btn")
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (quizState.currentIndex == totalQuestions - 1) "View Final Score" else "Next Question →",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QuizFinishedView(
    score: Int,
    total: Int,
    difficulty: String,
    onRetake: () -> Unit
) {
    val pct = if (total > 0) (score * 100) / total else 0
    val incorrect = total - score

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("quiz_results_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, ElectricCyan.copy(alpha = 0.4f))
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(AmberGold.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = "Trophy",
                    tint = AmberGold,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Quiz Completed!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Difficulty: $difficulty",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Large Score Indicator
            Text(
                text = "$pct%",
                fontSize = 44.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (pct >= 75) SuccessGreen else if (pct >= 50) AmberGold else PulseOrange
            )
            Text(
                text = if (pct >= 75) "Outstanding! Excellent RTOS mastery." else "Good effort! Review the topics to score 100%.",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Score Breakdown
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Surface(
                    color = SuccessGreen.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("$score", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = SuccessGreen)
                        Text("Correct Answers", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                Surface(
                    color = ErrorRed.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("$incorrect", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = ErrorRed)
                        Text("Incorrect Answers", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onRetake,
                colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("retake_quiz_btn")
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null, tint = Color.Black, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Retake Test", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}
