package com.example.data.model

data class ComparisonRow(
    val parameter: String,
    val itemA: String,
    val itemB: String
)

data class ComparisonTable(
    val title: String,
    val headerA: String,
    val headerB: String,
    val rows: List<ComparisonRow>
)

data class QuickRevisionItem(
    val id: String,
    val term: String,
    val fullForm: String,
    val oneLineSummary: String,
    val examFormulaOrRule: String = "",
    val category: String
)
