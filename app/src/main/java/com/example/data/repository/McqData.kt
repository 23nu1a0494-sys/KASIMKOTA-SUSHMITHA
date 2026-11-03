package com.example.data.repository

import com.example.data.model.Difficulty
import com.example.data.model.McqQuestion

object McqData {

    val questions: List<McqQuestion> = listOf(
        // BEGINNER
        McqQuestion(
            id = "mcq_b1",
            question = "Which component is NOT typically integrated on a single microcontroller (MCU) chip?",
            options = listOf(
                "Central Processing Unit (CPU)",
                "Internal SRAM memory",
                "Dedicated GPU with GDDR6 memory",
                "General-Purpose Input/Output (GPIO) pins"
            ),
            correctIndex = 2,
            explanation = "A microcontroller integrates CPU, RAM, Flash/ROM, and GPIO on a single chip, but lacks large high-power external GPUs like GDDR6 graphic cards.",
            difficulty = Difficulty.BEGINNER,
            moduleId = "mod1"
        ),
        McqQuestion(
            id = "mcq_b2",
            question = "In an RTOS, what happens when a task enters the BLOCKED state?",
            options = listOf(
                "It continues running while executing an empty while(1) loop",
                "It is halted by the kernel and consumes 0% CPU time while waiting for an event",
                "It is permanently deleted from RAM",
                "The entire microcontroller resets"
            ),
            correctIndex = 1,
            explanation = "When a task is Blocked (e.g. waiting for a delay or queue message), the RTOS removes it from the CPU, consuming zero CPU cycles and conserving power.",
            difficulty = Difficulty.BEGINNER,
            moduleId = "mod2"
        ),
        McqQuestion(
            id = "mcq_b3",
            question = "Which memory stores the permanent compiled firmware program in a microcontroller?",
            options = listOf(
                "SRAM",
                "Flash ROM",
                "Cache",
                "DRAM"
            ),
            correctIndex = 1,
            explanation = "Flash ROM is non-volatile memory that retains its stored program even when power is turned off. SRAM loses data upon power loss.",
            difficulty = Difficulty.BEGINNER,
            moduleId = "mod1"
        ),
        McqQuestion(
            id = "mcq_b4",
            question = "What does the 'Super Loop' architecture in embedded firmware consist of?",
            options = listOf(
                "An infinite while(1) loop combined with hardware interrupts",
                "A distributed network of cloud servers",
                "A recursive function that repeats 10,000 times",
                "Multiple virtual machines running in hypervisors"
            ),
            correctIndex = 0,
            explanation = "The Super Loop (Foreground/Background architecture) has an infinite while(1) loop for background tasks interrupted by hardware ISRs for foreground tasks.",
            difficulty = Difficulty.BEGINNER,
            moduleId = "mod3"
        ),

        // INTERMEDIATE
        McqQuestion(
            id = "mcq_i1",
            question = "What is the primary difference between a Mutex and a Binary Semaphore?",
            options = listOf(
                "A Semaphore can only be used on 32-bit microcontrollers",
                "A Mutex has an owner and supports Priority Inheritance; a Semaphore has no owner and is used for signaling",
                "A Mutex is implemented in hardware while a Semaphore is purely software",
                "A Binary Semaphore can hold up to 255 counts"
            ),
            correctIndex = 1,
            explanation = "A Mutex incorporates ownership (only the locker can unlock it) and Priority Inheritance to avoid priority inversion. Semaphores are signaling counters without ownership.",
            difficulty = Difficulty.INTERMEDIATE,
            moduleId = "mod2"
        ),
        McqQuestion(
            id = "mcq_i2",
            question = "Why is the C keyword 'volatile' applied to a shared flag modified inside an Interrupt Service Routine (ISR)?",
            options = listOf(
                "To make the code execute 10 times faster",
                "To prevent the compiler from caching the variable in a CPU register instead of reading physical RAM",
                "To place the variable in Flash memory instead of SRAM",
                "To encrypt the memory contents"
            ),
            correctIndex = 1,
            explanation = "Without 'volatile', the compiler optimizer assumes code outside the loop cannot change the variable, caching it in a CPU register and causing infinite loops.",
            difficulty = Difficulty.INTERMEDIATE,
            moduleId = "mod3"
        ),
        McqQuestion(
            id = "mcq_i3",
            question = "In Harvard Architecture, what is the main architectural advantage over Von Neumann?",
            options = listOf(
                "It uses less silicon area and fewer wires",
                "It has separate buses for instructions and data, allowing simultaneous instruction fetch and data read/write",
                "It completely eliminates the need for RAM",
                "It only works with 64-bit microprocessors"
            ),
            correctIndex = 1,
            explanation = "Harvard architecture has separate buses and physical memory spaces for program and data, doubling bandwidth and eliminating the Von Neumann bus bottleneck.",
            difficulty = Difficulty.INTERMEDIATE,
            moduleId = "mod1"
        ),
        McqQuestion(
            id = "mcq_i4",
            question = "What is a Task Control Block (TCB) primarily used for during a Context Switch?",
            options = listOf(
                "Storing the Wi-Fi password",
                "Saving and restoring the task's Stack Pointer, priority, and register states",
                "Converting C code into Assembly code",
                "Controlling the motor PWM frequency"
            ),
            correctIndex = 1,
            explanation = "The TCB stores the task's context, specifically its active Stack Pointer, priority, state flags, and linked-list node pointers.",
            difficulty = Difficulty.INTERMEDIATE,
            moduleId = "mod2"
        ),

        // ADVANCED
        McqQuestion(
            id = "mcq_a1",
            question = "Which scenario causes Priority Inversion in a preemptive priority-based RTOS?",
            options = listOf(
                "When high-priority Task H is blocked waiting for a mutex held by low-priority Task L, and medium-priority Task M preempts Task L",
                "When two tasks have the exact same priority and run in Round Robin mode",
                "When an interrupt occurs during normal program execution",
                "When a task runs out of stack space"
            ),
            correctIndex = 0,
            explanation = "Priority inversion occurs when Medium Task M delays Low Task L, indirectly causing High Task H (waiting for L's mutex) to wait longer than M, inverting priority hierarchy.",
            difficulty = Difficulty.ADVANCED,
            moduleId = "mod2"
        ),
        McqQuestion(
            id = "mcq_a2",
            question = "Which of the following is NOT one of the four Coffman conditions necessary for a Deadlock to occur?",
            options = listOf(
                "Mutual Exclusion",
                "Hold and Wait",
                "Linear Preemption of Resources",
                "Circular Wait"
            ),
            correctIndex = 2,
            explanation = "The 4 Coffman conditions are: 1. Mutual Exclusion, 2. Hold and Wait, 3. NO Preemption (resources cannot be forcibly taken), and 4. Circular Wait.",
            difficulty = Difficulty.ADVANCED,
            moduleId = "mod2"
        ),
        McqQuestion(
            id = "mcq_a3",
            question = "What is the optimal priority assignment rule under Rate Monotonic Scheduling (RMS)?",
            options = listOf(
                "Tasks with longer periods are assigned higher priorities",
                "Tasks with shorter execution periods (higher request rates) are assigned higher priorities",
                "Priorities are chosen completely at random each millisecond",
                "All tasks are always assigned equal priority"
            ),
            correctIndex = 1,
            explanation = "Rate Monotonic Scheduling (RMS) proves mathematically that assigning higher priorities to tasks with shorter periods (higher rates) maximizes schedulable CPU utilization.",
            difficulty = Difficulty.ADVANCED,
            moduleId = "mod2"
        ),
        McqQuestion(
            id = "mcq_a4",
            question = "In an embedded toolchain, what is the role of the Linker Script (.ld file)?",
            options = listOf(
                "It formats the C source code with proper indentations",
                "It defines the physical memory map (Flash origin/length, RAM origin/length) and places code segments into memory",
                "It converts Python scripts into C headers",
                "It acts as the hardware USB driver"
            ),
            correctIndex = 1,
            explanation = "The Linker Script instructs the locator on where physical Flash and RAM start in memory and where `.text` (code), `.data` (initialized vars), and `.bss` (uninitialized vars) segments must be mapped.",
            difficulty = Difficulty.ADVANCED,
            moduleId = "mod1"
        )
    )

    fun getQuestionsForDifficulty(difficulty: Difficulty): List<McqQuestion> {
        return if (difficulty == Difficulty.ALL) questions else questions.filter { it.difficulty == difficulty }
    }
}
