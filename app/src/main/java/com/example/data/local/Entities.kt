package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topic_progress")
data class TopicProgressEntity(
    @PrimaryKey val topicId: String,
    val isCompleted: Boolean = false,
    val isBookmarked: Boolean = false,
    val lastAccessedTimestamp: Long = 0L
)

@Entity(tableName = "question_bookmarks")
data class QuestionBookmarkEntity(
    @PrimaryKey val questionId: String,
    val isBookmarked: Boolean = true,
    val bookmarkedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "student_notes")
data class StudentNoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val topicId: String,
    val topicTitle: String,
    val noteContent: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "quiz_records")
data class QuizRecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val difficulty: String,
    val score: Int,
    val totalQuestions: Int,
    val percentage: Int,
    val completedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val studentName: String = "Embedded Learner",
    val studentEmail: String = "student@embeddedhub.edu",
    val dailyGoalTopics: Int = 2,
    val streakDays: Int = 3
)
