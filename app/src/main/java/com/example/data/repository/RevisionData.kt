package com.example.data.repository

import com.example.data.model.ComparisonRow
import com.example.data.model.ComparisonTable
import com.example.data.model.QuickRevisionItem

object RevisionData {

    val comparisonTables: List<ComparisonTable> = listOf(
        ComparisonTable(
            title = "Microcontroller (MCU) vs Microprocessor (MPU)",
            headerA = "Microcontroller (MCU)",
            headerB = "Microprocessor (MPU)",
            rows = listOf(
                ComparisonRow("Architecture", "CPU, RAM, ROM, I/O on single chip", "CPU core only; RAM, ROM external"),
                ComparisonRow("Cost & Size", "Very cheap, compact, low pin count", "Expensive, larger PCB footprint"),
                ComparisonRow("Power", "Very low (milliwatts to microwatts)", "High (watts to tens of watts)"),
                ComparisonRow("Applications", "Dedicated control (Washing machine, ABS)", "General computing (Laptops, Smartphones)"),
                ComparisonRow("Boot Time", "Instantaneous (microseconds)", "Slow (seconds, loads OS from disk)")
            )
        ),
        ComparisonTable(
            title = "Mutex vs Binary Semaphore",
            headerA = "Mutex",
            headerB = "Binary Semaphore",
            rows = listOf(
                ComparisonRow("Core Purpose", "Mutual Exclusion (protecting shared data)", "Signaling & Synchronization between tasks/ISRs"),
                ComparisonRow("Ownership", "Has ownership: Only locker can unlock", "No ownership: Any task or ISR can give/take"),
                ComparisonRow("Priority Inversion", "Supports Priority Inheritance to fix inversion", "Does NOT support priority inheritance"),
                ComparisonRow("Interrupt Use", "CANNOT be used inside an ISR", "CAN be given from an ISR (xSemaphoreGiveFromISR)"),
                ComparisonRow("Analogy", "Key to a private restroom door", "Traffic light signal flag")
            )
        ),
        ComparisonTable(
            title = "Super Loop vs RTOS Architecture",
            headerA = "Super Loop (Bare Metal)",
            headerB = "RTOS-Based Firmware",
            rows = listOf(
                ComparisonRow("Execution Flow", "Sequential polling in while(1) loop", "Concurrent preemptive multitasking"),
                ComparisonRow("Timing Determinism", "Poor; functions cause variable jitter", "High; deterministic priority deadlines"),
                ComparisonRow("Stack Usage", "Single shared system stack in RAM", "Dedicated stack per task in RAM"),
                ComparisonRow("Scalability", "Difficult to maintain as features grow", "Highly modular; tasks are decoupled"),
                ComparisonRow("Best For", "Simple low-power 8-bit devices", "Complex multi-interface real-time systems")
            )
        ),
        ComparisonTable(
            title = "Hard Real-Time vs Soft Real-Time",
            headerA = "Hard Real-Time",
            headerB = "Soft Real-Time",
            rows = listOf(
                ComparisonRow("Deadline Miss Result", "Total disaster, loss of life, or damage", "Performance degradation or annoyance"),
                ComparisonRow("Timing Requirement", "Strict worst-case deterministic limit", "Optimized for average throughput"),
                ComparisonRow("Late Data Value", "Zero or negative utility (harmful)", "Still useful though slightly degraded"),
                ComparisonRow("Examples", "Car Airbag, Pacemaker, Flight Autopilot", "Video streaming, Smart TV UI, Microwave clock")
            )
        )
    )

    val quickRevisionItems: List<QuickRevisionItem> = listOf(
        QuickRevisionItem(
            id = "rev_1",
            term = "TCB",
            fullForm = "Task Control Block",
            oneLineSummary = "Kernel data structure in RAM storing task's Stack Pointer, priority, and state.",
            examFormulaOrRule = "Size = ~64 to 128 bytes + allocated Task Stack in RAM",
            category = "RTOS"
        ),
        QuickRevisionItem(
            id = "rev_2",
            term = "ISR",
            fullForm = "Interrupt Service Routine",
            oneLineSummary = "Callback function automatically invoked when a hardware interrupt fires.",
            examFormulaOrRule = "Golden Rule: Keep it short, no delay(), no floating point math!",
            category = "Firmware"
        ),
        QuickRevisionItem(
            id = "rev_3",
            term = "IVT",
            fullForm = "Interrupt Vector Table",
            oneLineSummary = "Array of pointers at base of memory holding starting addresses of all ISRs.",
            examFormulaOrRule = "Located at address 0x00000000 on most ARM Cortex and AVR chips",
            category = "Architecture"
        ),
        QuickRevisionItem(
            id = "rev_4",
            term = "PWM",
            fullForm = "Pulse Width Modulation",
            oneLineSummary = "Technique of rapidly toggling digital pins to simulate analog voltages.",
            examFormulaOrRule = "Duty Cycle (%) = (Time ON / Total Period) * 100",
            category = "Peripherals"
        ),
        QuickRevisionItem(
            id = "rev_5",
            term = "RMS",
            fullForm = "Rate Monotonic Scheduling",
            oneLineSummary = "Optimal static priority rule: Shorter period (higher rate) = Higher priority.",
            examFormulaOrRule = "CPU Utilization Bound: U = n * (2^(1/n) - 1), approaches ~69.3% for large n",
            category = "Scheduling"
        ),
        QuickRevisionItem(
            id = "rev_6",
            term = "EDF",
            fullForm = "Earliest Deadline First",
            oneLineSummary = "Dynamic priority algorithm prioritizing whichever task's deadline is nearest.",
            examFormulaOrRule = "Theoretical max schedulable CPU utilization = 100%",
            category = "Scheduling"
        ),
        QuickRevisionItem(
            id = "rev_7",
            term = "BSP",
            fullForm = "Board Support Package",
            oneLineSummary = "Firmware layer containing hardware initialization, clocks, and board pinout setups.",
            examFormulaOrRule = "Bridges microcontroller silicon to the RTOS and application layer",
            category = "Firmware"
        ),
        QuickRevisionItem(
            id = "rev_8",
            term = "WDT",
            fullForm = "Watchdog Timer",
            oneLineSummary = "Hardware countdown timer that resets microcontroller if firmware crashes.",
            examFormulaOrRule = "Must be 'kicked' or 'fed' before countdown hits 0",
            category = "Safety"
        )
    )
}
