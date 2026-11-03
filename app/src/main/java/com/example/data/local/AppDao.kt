package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    // Topic Progress
    @Query("SELECT * FROM topic_progress")
    fun getAllTopicProgress(): Flow<List<TopicProgressEntity>>

    @Query("SELECT * FROM topic_progress WHERE topicId = :topicId")
    suspend fun getTopicProgress(topicId: String): TopicProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTopicProgress(entity: TopicProgressEntity)

    // Question Bookmarks
    @Query("SELECT * FROM question_bookmarks WHERE isBookmarked = 1")
    fun getBookmarkedQuestions(): Flow<List<QuestionBookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuestionBookmark(entity: QuestionBookmarkEntity)

    @Query("DELETE FROM question_bookmarks WHERE questionId = :questionId")
    suspend fun removeQuestionBookmark(questionId: String)

    // Notes
    @Query("SELECT * FROM student_notes ORDER BY createdAt DESC")
    fun getAllNotes(): Flow<List<StudentNoteEntity>>

    @Query("SELECT * FROM student_notes WHERE topicId = :topicId ORDER BY createdAt DESC")
    fun getNotesForTopic(topicId: String): Flow<List<StudentNoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: StudentNoteEntity)

    @Query("DELETE FROM student_notes WHERE id = :noteId")
    suspend fun deleteNote(noteId: Long)

    // Quiz Records
    @Query("SELECT * FROM quiz_records ORDER BY completedAt DESC")
    fun getAllQuizRecords(): Flow<List<QuizRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizRecord(record: QuizRecordEntity)

    // User Profile
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProfile(profile: UserProfileEntity)

    // Reset Progress
    @Query("DELETE FROM topic_progress")
    suspend fun resetAllTopicProgress()
}
