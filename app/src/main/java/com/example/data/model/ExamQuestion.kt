package com.example.data.model

enum class QuestionType(val label: String, val marks: Int) {
    VERY_SHORT("2 Marks (Very Short)", 2),
    SHORT("5 Marks (Short Answer)", 5),
    LONG("10 Marks (Long Answer)", 10),
    VIVA("Viva Voce Question", 1)
}

data class ExamQuestion(
    val id: String,
    val question: String,
    val type: QuestionType,
    val moduleId: String,
    val simpleAnswer: String,
    val detailedAnswer: String = "",
    val keywords: List<String>,
    val rememberPoints: List<String>
)
