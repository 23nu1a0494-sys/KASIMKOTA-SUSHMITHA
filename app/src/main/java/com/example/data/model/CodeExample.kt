package com.example.data.model

enum class SimulationType {
    LED_BLINK,
    ADC_SENSOR,
    TEMP_MONITOR,
    FAN_CONTROL,
    TIMER_INTERRUPT,
    PUSHBUTTON_ISR,
    TASK_SCHEDULER
}

data class CodeLineExplanation(
    val lineCode: String,
    val explanation: String
)

data class CodeExample(
    val id: String,
    val title: String,
    val subtitle: String,
    val problemStatement: String,
    val code: String,
    val lineExplanations: List<CodeLineExplanation>,
    val expectedOutput: String,
    val realWorldApplication: String,
    val simulationType: SimulationType
)
