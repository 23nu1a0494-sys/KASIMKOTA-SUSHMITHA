package com.example.data.model

enum class ModuleCategory(val id: String, val title: String, val description: String) {
    MODULE_1(
        "mod1",
        "Module 1: Embedded Systems Fundamentals",
        "Core architecture, microcontrollers vs microprocessors, memory, hardware/software, and compiler toolchains."
    ),
    MODULE_2(
        "mod2",
        "Module 2: RTOS Fundamentals",
        "Real-Time OS concepts, task lifecycle, scheduling algorithms, synchronization, IPC, and kernel architectures."
    ),
    MODULE_3(
        "mod3",
        "Module 3: Embedded Firmware Design",
        "Firmware architectures, Super Loop vs RTOS, hardware interrupts, timer peripherals, and real-time design."
    )
}

enum class DiagramType {
    NONE,
    PROCESS_LIFECYCLE,
    SCHEDULING_GANTT,
    MUTEX_SEMAPHORE,
    SUPERLOOP_VS_RTOS,
    SOURCE_TO_HEX,
    MCU_VS_MPU,
    INTERRUPT_TIMELINE
}

data class Topic(
    val id: String,
    val moduleId: String,
    val title: String,
    val shortSummary: String,
    val introduction: String,
    val simpleDefinition: String,
    val detailedExplanation: String,
    val stepByStepProcess: List<String>,
    val diagramType: DiagramType = DiagramType.NONE,
    val diagramExplanation: String = "",
    val realWorldExamples: List<String>,
    val advantages: List<String>,
    val disadvantages: List<String>,
    val applications: List<String>,
    val conclusion: String,
    val examPoints: List<String>,
    val estimatedReadMinutes: Int = 6
)
