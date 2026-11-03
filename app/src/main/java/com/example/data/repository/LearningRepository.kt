package com.example.data.repository

import com.example.data.local.AppDao
import com.example.data.local.QuestionBookmarkEntity
import com.example.data.local.QuizRecordEntity
import com.example.data.local.StudentNoteEntity
import com.example.data.local.TopicProgressEntity
import com.example.data.local.UserProfileEntity
import com.example.data.model.CodeExample
import com.example.data.model.ExamQuestion
import com.example.data.model.McqQuestion
import com.example.data.model.Topic
import kotlinx.coroutines.flow.Flow

data class SearchResult(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String, // "Topic", "Exam Question", "Code Lab", "Revision"
    val targetType: String,
    val targetId: String
)

class LearningRepository(private val appDao: AppDao) {

    // Curriculum
    fun getAllTopics(): List<Topic> = CurriculumData.topics
    fun getTopicById(id: String): Topic? = CurriculumData.getTopicById(id)

    // Progress & Persistence
    val topicProgress: Flow<List<TopicProgressEntity>> = appDao.getAllTopicProgress()

    suspend fun toggleTopicCompleted(topicId: String) {
        val current = appDao.getTopicProgress(topicId)
        val newCompleted = !(current?.isCompleted ?: false)
        appDao.saveTopicProgress(
            TopicProgressEntity(
                topicId = topicId,
                isCompleted = newCompleted,
                isBookmarked = current?.isBookmarked ?: false,
                lastAccessedTimestamp = System.currentTimeMillis()
            )
        )
    }

    suspend fun toggleTopicBookmarked(topicId: String) {
        val current = appDao.getTopicProgress(topicId)
        val newBookmarked = !(current?.isBookmarked ?: false)
        appDao.saveTopicProgress(
            TopicProgressEntity(
                topicId = topicId,
                isCompleted = current?.isCompleted ?: false,
                isBookmarked = newBookmarked,
                lastAccessedTimestamp = System.currentTimeMillis()
            )
        )
    }

    suspend fun markTopicViewed(topicId: String) {
        val current = appDao.getTopicProgress(topicId)
        appDao.saveTopicProgress(
            TopicProgressEntity(
                topicId = topicId,
                isCompleted = current?.isCompleted ?: false,
                isBookmarked = current?.isBookmarked ?: false,
                lastAccessedTimestamp = System.currentTimeMillis()
            )
        )
    }

    // Questions & Bookmarks
    val bookmarkedQuestions: Flow<List<QuestionBookmarkEntity>> = appDao.getBookmarkedQuestions()

    suspend fun toggleQuestionBookmark(questionId: String, currentBookmarked: Boolean) {
        if (currentBookmarked) {
            appDao.removeQuestionBookmark(questionId)
        } else {
            appDao.saveQuestionBookmark(QuestionBookmarkEntity(questionId = questionId, isBookmarked = true))
        }
    }

    // Notes
    val allNotes: Flow<List<StudentNoteEntity>> = appDao.getAllNotes()

    fun getNotesForTopic(topicId: String): Flow<List<StudentNoteEntity>> = appDao.getNotesForTopic(topicId)

    suspend fun addNote(topicId: String, topicTitle: String, content: String) {
        appDao.insertNote(
            StudentNoteEntity(
                topicId = topicId,
                topicTitle = topicTitle,
                noteContent = content
            )
        )
    }

    suspend fun deleteNote(id: Long) {
        appDao.deleteNote(id)
    }

    // Quiz Records
    val quizRecords: Flow<List<QuizRecordEntity>> = appDao.getAllQuizRecords()

    suspend fun saveQuizResult(difficulty: String, score: Int, total: Int) {
        val percentage = if (total > 0) (score * 100) / total else 0
        appDao.insertQuizRecord(
            QuizRecordEntity(
                difficulty = difficulty,
                score = score,
                totalQuestions = total,
                percentage = percentage
            )
        )
    }

    // User Profile
    val userProfile: Flow<UserProfileEntity?> = appDao.getUserProfile()

    suspend fun saveUserProfile(name: String, email: String, dailyGoal: Int) {
        appDao.saveUserProfile(
            UserProfileEntity(
                studentName = name,
                studentEmail = email,
                dailyGoalTopics = dailyGoal,
                streakDays = 3
            )
        )
    }

    suspend fun resetAllProgress() {
        appDao.resetAllTopicProgress()
    }

    // Global Search
    fun search(query: String): List<SearchResult> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return emptyList()

        val results = mutableListOf<SearchResult>()

        // Search Topics
        CurriculumData.topics.forEach { topic ->
            if (topic.title.lowercase().contains(q) ||
                topic.simpleDefinition.lowercase().contains(q) ||
                topic.shortSummary.lowercase().contains(q) ||
                topic.applications.any { it.lowercase().contains(q) } ||
                topic.examPoints.any { it.lowercase().contains(q) }
            ) {
                results.add(
                    SearchResult(
                        id = "topic_${topic.id}",
                        title = topic.title,
                        subtitle = topic.simpleDefinition,
                        category = "Topic",
                        targetType = "TOPIC",
                        targetId = topic.id
                    )
                )
            }
        }

        // Search Exam Questions
        ExamData.questions.forEach { eq ->
            if (eq.question.lowercase().contains(q) ||
                eq.simpleAnswer.lowercase().contains(q) ||
                eq.keywords.any { it.lowercase().contains(q) }
            ) {
                results.add(
                    SearchResult(
                        id = "exam_${eq.id}",
                        title = eq.question,
                        subtitle = eq.simpleAnswer,
                        category = "Exam Question (${eq.type.label})",
                        targetType = "EXAM",
                        targetId = eq.id
                    )
                )
            }
        }

        // Search Code Examples
        CodeData.examples.forEach { code ->
            if (code.title.lowercase().contains(q) ||
                code.subtitle.lowercase().contains(q) ||
                code.problemStatement.lowercase().contains(q) ||
                code.code.lowercase().contains(q)
            ) {
                results.add(
                    SearchResult(
                        id = "code_${code.id}",
                        title = code.title,
                        subtitle = code.subtitle,
                        category = "Programming Lab",
                        targetType = "CODE",
                        targetId = code.id
                    )
                )
            }
        }

        // Search Revision
        RevisionData.quickRevisionItems.forEach { item ->
            if (item.term.lowercase().contains(q) ||
                item.fullForm.lowercase().contains(q) ||
                item.oneLineSummary.lowercase().contains(q)
            ) {
                results.add(
                    SearchResult(
                        id = "rev_${item.id}",
                        title = "${item.term} - ${item.fullForm}",
                        subtitle = item.oneLineSummary,
                        category = "Revision Glossary",
                        targetType = "REVISION",
                        targetId = item.id
                    )
                )
            }
        }

        return results
    }
}
