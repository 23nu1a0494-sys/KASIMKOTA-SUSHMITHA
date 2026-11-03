package com.example.data.model

enum class Difficulty(val label: String) {
    ALL("All Levels"),
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced")
}

data class McqQuestion(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val difficulty: Difficulty,
    val moduleId: String
)
