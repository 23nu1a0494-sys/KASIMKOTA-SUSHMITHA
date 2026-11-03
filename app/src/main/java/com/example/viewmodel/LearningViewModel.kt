package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.QuestionBookmarkEntity
import com.example.data.local.StudentNoteEntity
import com.example.data.local.TopicProgressEntity
import com.example.data.local.UserProfileEntity
import com.example.data.model.CodeExample
import com.example.data.model.Difficulty
import com.example.data.model.ExamQuestion
import com.example.data.model.McqQuestion
import com.example.data.model.QuestionType
import com.example.data.model.Topic
import com.example.data.repository.CodeData
import com.example.data.repository.CurriculumData
import com.example.data.repository.ExamData
import com.example.data.repository.LearningRepository
import com.example.data.repository.McqData
import com.example.data.repository.SearchResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class ScreenTab {
    DASHBOARD,
    MODULES,
    TOPIC_DETAIL,
    EXAM_PREP,
    MCQ_ARENA,
    CODE_LAB,
    REVISION,
    SEARCH,
    PROFILE
}

data class QuizSessionState(
    val difficulty: Difficulty = Difficulty.ALL,
    val questions: List<McqQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isSubmitted: Boolean = false,
    val isCorrect: Boolean = false,
    val score: Int = 0,
    val isFinished: Boolean = false
)

class LearningViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LearningRepository

    init {
        val database = AppDatabase.getDatabase(application)
        repository = LearningRepository(database.appDao())
    }

    // Navigation State
    private val _currentTab = MutableStateFlow(ScreenTab.DASHBOARD)
    val currentTab: StateFlow<ScreenTab> = _currentTab.asStateFlow()

    private val _selectedTopicId = MutableStateFlow<String?>(null)
    val selectedTopicId: StateFlow<String?> = _selectedTopicId.asStateFlow()

    private val _selectedCodeId = MutableStateFlow<String>("code_led")
    val selectedCodeId: StateFlow<String> = _selectedCodeId.asStateFlow()

    private val _selectedModuleId = MutableStateFlow<String?>("mod1")
    val selectedModuleId: StateFlow<String?> = _selectedModuleId.asStateFlow()

    // Database Flows
    val topicProgress: StateFlow<List<TopicProgressEntity>> = repository.topicProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarkedQuestions: StateFlow<List<QuestionBookmarkEntity>> = repository.bookmarkedQuestions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allNotes: StateFlow<List<StudentNoteEntity>> = repository.allNotes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val userProfile: StateFlow<UserProfileEntity?> = repository.userProfile
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // Derived Statistics
    val totalTopicsCount: Int = CurriculumData.topics.size

    val completedTopicsCount: StateFlow<Int> = topicProgress.combine(MutableStateFlow(0)) { list, _ ->
        list.count { it.isCompleted }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val progressPercentage: StateFlow<Int> = completedTopicsCount.combine(MutableStateFlow(totalTopicsCount)) { completed, total ->
        if (total > 0) (completed * 100) / total else 0
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // Search
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<SearchResult>>(emptyList())
    val searchResults: StateFlow<List<SearchResult>> = _searchResults.asStateFlow()

    // Exam Filter
    private val _examFilterType = MutableStateFlow<QuestionType?>(null)
    val examFilterType: StateFlow<QuestionType?> = _examFilterType.asStateFlow()

    private val _onlyBookmarkedExam = MutableStateFlow(false)
    val onlyBookmarkedExam: StateFlow<Boolean> = _onlyBookmarkedExam.asStateFlow()

    // MCQ Session State
    private val _quizState = MutableStateFlow(QuizSessionState())
    val quizState: StateFlow<QuizSessionState> = _quizState.asStateFlow()

    init {
        startQuiz(Difficulty.ALL)
    }

    // Navigation Actions
    fun navigateTo(tab: ScreenTab) {
        _currentTab.value = tab
    }

    fun openTopic(topicId: String) {
        _selectedTopicId.value = topicId
        _currentTab.value = ScreenTab.TOPIC_DETAIL
        viewModelScope.launch {
            repository.markTopicViewed(topicId)
        }
    }

    fun openCodeExample(codeId: String) {
        _selectedCodeId.value = codeId
        _currentTab.value = ScreenTab.CODE_LAB
    }

    fun setModuleFilter(moduleId: String?) {
        _selectedModuleId.value = moduleId
    }

    fun getContinueTopic(): Topic {
        val progress = topicProgress.value
        val uncompleted = CurriculumData.topics.firstOrNull { topic ->
            progress.none { it.topicId == topic.id && it.isCompleted }
        }
        return uncompleted ?: CurriculumData.topics.first()
    }

    // Topic Actions
    fun toggleTopicCompleted(topicId: String) {
        viewModelScope.launch {
            repository.toggleTopicCompleted(topicId)
        }
    }

    fun toggleTopicBookmarked(topicId: String) {
        viewModelScope.launch {
            repository.toggleTopicBookmarked(topicId)
        }
    }

    fun addNote(topicId: String, topicTitle: String, content: String) {
        viewModelScope.launch {
            repository.addNote(topicId, topicTitle, content)
        }
    }

    fun deleteNote(noteId: Long) {
        viewModelScope.launch {
            repository.deleteNote(noteId)
        }
    }

    // Exam Actions
    fun setExamFilter(type: QuestionType?) {
        _examFilterType.value = type
    }

    fun toggleOnlyBookmarkedExam() {
        _onlyBookmarkedExam.value = !_onlyBookmarkedExam.value
    }

    fun toggleQuestionBookmark(questionId: String) {
        val isCurrentlyBookmarked = bookmarkedQuestions.value.any { it.questionId == questionId }
        viewModelScope.launch {
            repository.toggleQuestionBookmark(questionId, isCurrentlyBookmarked)
        }
    }

    // MCQ Actions
    fun startQuiz(difficulty: Difficulty) {
        val questions = McqData.getQuestionsForDifficulty(difficulty).shuffled()
        _quizState.value = QuizSessionState(
            difficulty = difficulty,
            questions = questions,
            currentIndex = 0,
            selectedOptionIndex = null,
            isSubmitted = false,
            isCorrect = false,
            score = 0,
            isFinished = false
        )
    }

    fun selectQuizOption(index: Int) {
        if (!_quizState.value.isSubmitted) {
            _quizState.value = _quizState.value.copy(selectedOptionIndex = index)
        }
    }

    fun submitQuizAnswer() {
        val current = _quizState.value
        val selected = current.selectedOptionIndex ?: return
        val currentQuestion = current.questions.getOrNull(current.currentIndex) ?: return
        val isCorrect = selected == currentQuestion.correctIndex
        val newScore = if (isCorrect) current.score + 1 else current.score

        _quizState.value = current.copy(
            isSubmitted = true,
            isCorrect = isCorrect,
            score = newScore
        )
    }

    fun nextQuizQuestion() {
        val current = _quizState.value
        val nextIndex = current.currentIndex + 1
        if (nextIndex >= current.questions.size) {
            // Finished!
            _quizState.value = current.copy(isFinished = true)
            viewModelScope.launch {
                repository.saveQuizResult(
                    difficulty = current.difficulty.label,
                    score = current.score,
                    total = current.questions.size
                )
            }
        } else {
            _quizState.value = current.copy(
                currentIndex = nextIndex,
                selectedOptionIndex = null,
                isSubmitted = false,
                isCorrect = false
            )
        }
    }

    fun restartQuiz() {
        startQuiz(_quizState.value.difficulty)
    }

    // Search Actions
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        _searchResults.value = repository.search(query)
    }

    // Profile Actions
    fun updateProfile(name: String, email: String, dailyGoal: Int) {
        viewModelScope.launch {
            repository.saveUserProfile(name, email, dailyGoal)
        }
    }

    fun resetProgress() {
        viewModelScope.launch {
            repository.resetAllProgress()
        }
    }
}
