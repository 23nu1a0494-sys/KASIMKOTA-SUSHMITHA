package com.example.data.repository

import com.example.data.model.ExamQuestion
import com.example.data.model.QuestionType

object ExamData {

    val questions: List<ExamQuestion> = listOf(
        // 2 MARKS (VERY SHORT)
        ExamQuestion(
            id = "q_2m_1",
            question = "Define an Embedded System with two examples.",
            type = QuestionType.VERY_SHORT,
            moduleId = "mod1",
            simpleAnswer = "An Embedded System is a microcontroller or microprocessor-based hardware system with dedicated software designed to perform a dedicated function within a larger electrical or mechanical system.",
            detailedAnswer = "It is characterized by dedicated purpose, resource constraints, and real-time execution. Examples include: (1) Anti-lock Braking System (ABS) in automobiles, (2) Digital microwave oven controller.",
            keywords = listOf("Dedicated function", "Microcontroller", "Resource-constrained", "ABS", "Microwave"),
            rememberPoints = listOf(
                "Remember formula: Hardware + Software + Dedicated Task = Embedded System.",
                "Always give 2 everyday examples (e.g. Washing machine, Smart band)."
            )
        ),
        ExamQuestion(
            id = "q_2m_2",
            question = "State the difference between Microcontroller and Microprocessor.",
            type = QuestionType.VERY_SHORT,
            moduleId = "mod1",
            simpleAnswer = "A Microcontroller (MCU) integrates CPU, RAM, ROM/Flash, and I/O ports all on a single chip. A Microprocessor (MPU) contains only the CPU core and requires external memory and I/O chips to function.",
            detailedAnswer = "MCUs are cost-effective and power-efficient for dedicated control (e.g. 8051, STM32). MPUs are high-performance for general computing (e.g. Intel Core i7, ARM Cortex-A).",
            keywords = listOf("Single chip", "On-chip RAM/ROM", "CPU core only", "External memory"),
            rememberPoints = listOf(
                "MCU = All-in-one chip (compact, low power).",
                "MPU = Raw CPU brain (needs external chips, high power)."
            )
        ),
        ExamQuestion(
            id = "q_2m_3",
            question = "What is a Task Control Block (TCB) in an RTOS?",
            type = QuestionType.VERY_SHORT,
            moduleId = "mod2",
            simpleAnswer = "A Task Control Block (TCB) is a data structure in RAM maintained by the RTOS kernel that stores all context information of a task, including its Stack Pointer, priority, current state, and task ID.",
            detailedAnswer = "During a context switch, the CPU registers are saved to the task's stack, and the resulting Stack Pointer address is stored into its TCB so the task can be safely resumed later.",
            keywords = listOf("Data structure", "Stack Pointer", "Task state", "Priority", "Context switch"),
            rememberPoints = listOf(
                "Think of TCB as the task's 'Passport' or 'ID card'.",
                "Holds: Stack Pointer, State, Priority, ID."
            )
        ),
        ExamQuestion(
            id = "q_2m_4",
            question = "What is Priority Inversion and how is it resolved?",
            type = QuestionType.VERY_SHORT,
            moduleId = "mod2",
            simpleAnswer = "Priority Inversion occurs when a high-priority task is indirectly blocked by a medium-priority task because a low-priority task holds a shared resource. It is resolved using Priority Inheritance.",
            detailedAnswer = "In Priority Inheritance, the low-priority task holding the shared mutex temporarily inherits the high priority of the waiting task until it finishes and releases the mutex.",
            keywords = listOf("Priority Inversion", "Shared resource", "Priority Inheritance", "Mutex"),
            rememberPoints = listOf(
                "Problem: Low holds lock, Medium preempts Low, High waits forever.",
                "Solution: Priority Inheritance boosts Low's priority temporarily."
            )
        ),
        ExamQuestion(
            id = "q_2m_5",
            question = "Why is the 'volatile' keyword essential in embedded C?",
            type = QuestionType.VERY_SHORT,
            moduleId = "mod3",
            simpleAnswer = "The 'volatile' keyword tells the C compiler that a variable's value can change at any time from outside the normal code flow (such as by a hardware register or an ISR), preventing the compiler from optimizing it into a CPU register.",
            detailedAnswer = "Without volatile, a loop like 'while(!flag);' might be optimized into an infinite loop because the compiler caches 'flag' in a register and never re-reads memory.",
            keywords = listOf("Volatile", "Compiler optimization", "Hardware register", "ISR flag"),
            rememberPoints = listOf(
                "Always use volatile for: (1) Hardware I/O registers, (2) Flags shared between an ISR and main loop."
            )
        ),
        ExamQuestion(
            id = "q_2m_6",
            question = "Define Interrupt Latency.",
            type = QuestionType.VERY_SHORT,
            moduleId = "mod3",
            simpleAnswer = "Interrupt Latency is the total time delay between the assertion of a hardware interrupt signal and the execution of the very first instruction in the corresponding Interrupt Service Routine (ISR).",
            detailedAnswer = "It includes hardware synchronization, finishing the current CPU instruction, pushing registers onto the stack, and vector table lookup.",
            keywords = listOf("Time delay", "Hardware signal", "ISR execution", "Context save"),
            rememberPoints = listOf(
                "Latency = Hardware trigger time to First line of ISR code."
            )
        ),

        // 5 MARKS (SHORT ANSWER)
        ExamQuestion(
            id = "q_5m_1",
            question = "Explain the complete Source to Object File conversion process in embedded systems.",
            type = QuestionType.SHORT,
            moduleId = "mod1",
            simpleAnswer = "The build process consists of 5 sequential stages: Preprocessing, Compiling, Assembling, Linking, and Locating/Hex generation.",
            detailedAnswer = "1. Preprocessor: Expands header files (#include), replaces macros (#define), and strips comments, creating `.i` file.\n2. Compiler: Performs lexical analysis, syntax parsing, and optimization to convert C into assembly code (`.s`).\n3. Assembler: Converts assembly mnemonics into relocatable machine code opcodes (`.o` or `.obj`).\n4. Linker: Combines multiple `.o` object files and resolves function calls and external library references.\n5. Locator / Hex Utility: Uses the Linker Script (`.ld`) to map program segments to physical microcontroller Flash and RAM addresses, generating the final Intel HEX (`.hex`) or binary (`.bin`) file.",
            keywords = listOf("Preprocessor", "Compiler", "Assembler", "Linker", "Locator", "Linker Script"),
            rememberPoints = listOf(
                "Acronym to memorize: P-C-A-L-L (Preprocessor, Compiler, Assembler, Linker, Locator).",
                "Input: main.c -> Output: firmware.hex."
            )
        ),
        ExamQuestion(
            id = "q_5m_2",
            question = "Compare Mutex and Semaphore with clear use cases.",
            type = QuestionType.SHORT,
            moduleId = "mod2",
            simpleAnswer = "A Mutex is a locking mechanism with ownership used for Mutual Exclusion. A Semaphore is a signaling mechanism without ownership used for synchronization.",
            detailedAnswer = "Key Comparison Points:\n1. Ownership: A Mutex can ONLY be unlocked by the task that locked it. A Semaphore can be signaled ('given') by any task or ISR.\n2. Priority Inversion: Mutexes support Priority Inheritance to prevent priority inversion; semaphores do not.\n3. Counting: Mutex is strictly binary (locked/unlocked). Semaphores can be Binary (0 or 1) or Counting (0 to N).\n4. Use Case: Use Mutex to protect shared hardware (e.g. SPI bus). Use Semaphore to signal events (e.g. ISR signals task that ADC conversion is complete).",
            keywords = listOf("Mutex", "Semaphore", "Ownership", "Priority Inheritance", "Signaling", "Mutual Exclusion"),
            rememberPoints = listOf(
                "Mutex = Lock (has owner, protects shared resource).",
                "Semaphore = Traffic light / Signaler (no owner, signals events)."
            )
        ),
        ExamQuestion(
            id = "q_5m_3",
            question = "Explain the Process Life Cycle states in an RTOS.",
            type = QuestionType.SHORT,
            moduleId = "mod2",
            simpleAnswer = "An RTOS task moves dynamically between four primary states: Running, Ready, Blocked (Waiting), and Suspended.",
            detailedAnswer = "1. RUNNING: The task currently has CPU control and executes instructions. Only one task runs at a time on a single core.\n2. READY: The task is eligible to run immediately, but a higher or equal priority task is currently occupying the CPU.\n3. BLOCKED: The task is waiting for a time delay (vTaskDelay) or synchronization event (queue message, semaphore). Consumes 0% CPU.\n4. SUSPENDED: Explicitly paused by API call (vTaskSuspend). Won't be scheduled until explicitly resumed.\n\nState Transitions: Ready -> Running (by Scheduler); Running -> Blocked (waiting event); Blocked -> Ready (event arrives); Running -> Ready (preempted).",
            keywords = listOf("Running", "Ready", "Blocked", "Suspended", "State transitions", "Scheduler"),
            rememberPoints = listOf(
                "Draw the 4 circles: Ready <-> Running -> Blocked -> Ready.",
                "Blocked tasks consume ZERO CPU power."
            )
        ),
        ExamQuestion(
            id = "q_5m_4",
            question = "Differentiate between Super Loop and RTOS-based firmware design.",
            type = QuestionType.SHORT,
            moduleId = "mod3",
            simpleAnswer = "Super Loop executes tasks sequentially in an infinite loop; RTOS executes independent tasks concurrently using a priority-based preemptive scheduler.",
            detailedAnswer = "Comparison:\n• Task Scheduling: Super Loop relies on sequential polling and simple hardware interrupts; RTOS uses preemptive priority scheduling.\n• Jitter & Latency: In Super Loop, long functions delay subsequent operations; in RTOS, high-priority tasks preempt lower tasks deterministically.\n• RAM Overhead: Super Loop uses a single stack in RAM (minimal RAM); RTOS requires a dedicated stack for every individual task (higher RAM).\n• Complexity: Super Loop is best for simple 8-bit devices; RTOS is ideal for complex multi-interface systems.",
            keywords = listOf("Super Loop", "Sequential polling", "Preemption", "Jitter", "Stack per task"),
            rememberPoints = listOf(
                "Super Loop: Simple, 1 stack, prone to jitter if functions block.",
                "RTOS: Deterministic, multiple stacks, preemptive priorities."
            )
        ),

        // 10 MARKS (LONG ANSWER)
        ExamQuestion(
            id = "q_10m_1",
            question = "Explain RTOS Scheduling Algorithms in detail: Preemptive Fixed Priority, Round Robin, and Earliest Deadline First (EDF). Include diagrams and trade-offs.",
            type = QuestionType.LONG,
            moduleId = "mod2",
            simpleAnswer = "The RTOS scheduler chooses which ready task receives CPU time. The three principal algorithms are Preemptive Priority, Round Robin, and Earliest Deadline First.",
            detailedAnswer = "1. Preemptive Fixed-Priority Scheduling (Most Popular):\n- Each task is assigned a constant priority number.\n- The scheduler guarantees that the highest-priority ready task ALWAYS runs.\n- Preemption: If a high-priority task unblocks, the running lower-priority task is immediately halted (context saved) and the high-priority task takes over.\n- Trade-off: High predictability for critical events, but risk of Task Starvation if high-priority tasks monopolize CPU.\n\n2. Round Robin Scheduling (Time Slicing):\n- Used among tasks that share the exact same priority level.\n- Each ready task is granted an equal quantum (slice) of CPU time (e.g. 10ms).\n- When the time slice expires via the SysTick timer, the task is moved to the tail of the ready list and the next task runs.\n- Trade-off: Fair CPU distribution, but high context-switch overhead if quantum is too small.\n\n3. Earliest Deadline First (EDF - Dynamic Priority):\n- Priority is not fixed; it is calculated dynamically.\n- Whichever task has the closest upcoming absolute deadline is assigned highest priority.\n- Optimality: Can theoretically achieve 100% CPU utilization under periodic task workloads.\n- Trade-off: Complex runtime overhead calculating deadlines; unpredictable behavior under CPU overload conditions.\n\n4. Rate Monotonic Scheduling (RMS):\n- Static priority assignment rule: Shorter period = Higher priority.",
            keywords = listOf("Preemptive Priority", "Round Robin", "Earliest Deadline First", "SysTick", "Starvation", "Context Switch"),
            rememberPoints = listOf(
                "Structure answer into: 1. Fixed Priority Preemptive, 2. Round Robin, 3. Earliest Deadline First (EDF), 4. Summary comparison table.",
                "Always mention 'Preemption' means kicking out a running task when a more urgent one wakes up."
            )
        ),
        ExamQuestion(
            id = "q_10m_2",
            question = "Describe the Four Coffman Conditions for Deadlock and explain how to prevent Deadlocks in an embedded RTOS.",
            type = QuestionType.LONG,
            moduleId = "mod2",
            simpleAnswer = "Deadlock is a state where two or more tasks are stuck permanently waiting for resources held by each other. It requires 4 conditions to exist and can be prevented by breaking any one condition.",
            detailedAnswer = "A. The Four Coffman Conditions:\n1. Mutual Exclusion: At least one resource must be held in a non-shareable mode (only 1 task can hold it at a time).\n2. Hold and Wait: A task must be currently holding at least one resource and requesting additional resources held by other tasks.\n3. No Preemption: Resources cannot be forcibly taken away; they can only be released voluntarily by the holding task.\n4. Circular Wait: A closed loop chain of tasks exists: Task 1 waits for Resource B held by Task 2, while Task 2 waits for Resource A held by Task 1.\n\nB. Prevention and Avoidance Strategies in RTOS:\n1. Enforce Global Lock Ordering: Establish a strict hierarchy. If tasks need Mutex A and Mutex B, ALL tasks MUST acquire Mutex A first, then Mutex B. This mathematically destroys Circular Wait!\n2. Lock Timeouts: Never call `xSemaphoreTake(mutex, portMAX_DELAY)`. Always pass a finite timeout (e.g. 50ms). If timeout expires, release all currently held locks, wait random backoff, and retry.\n3. All-or-Nothing Acquisition: Acquire all needed resources simultaneously before starting critical work.\n4. Priority Ceiling Protocols: Prevent deadlocks by dynamically adjusting task priority to the ceiling of all resources held.",
            keywords = listOf("Deadlock", "Coffman Conditions", "Mutual Exclusion", "Hold and Wait", "No Preemption", "Circular Wait", "Lock Ordering"),
            rememberPoints = listOf(
                "Remember the 4 conditions: M-H-N-C (Mutual exclusion, Hold & wait, No preemption, Circular wait).",
                "Best practical embedded fix: Strict global lock acquisition ordering + Non-infinite timeouts."
            )
        ),

        // VIVA VOCE QUESTIONS
        ExamQuestion(
            id = "q_viva_1",
            question = "What is the difference between Hard and Soft Real-Time Systems?",
            type = QuestionType.VIVA,
            moduleId = "mod2",
            simpleAnswer = "In a Hard Real-Time system (like an airbag or pacemaker), missing a deadline causes catastrophic failure or death. In a Soft Real-Time system (like a video player or microwave display), missing a deadline only degrades quality.",
            detailedAnswer = "Hard systems require 100% deterministic mathematical verification; soft systems optimize for average throughput.",
            keywords = listOf("Hard RTOS", "Soft RTOS", "Deadline miss", "Catastrophic failure"),
            rememberPoints = listOf(
                "Airbag = Hard real-time.",
                "Smartphone touch/video = Soft real-time."
            )
        ),
        ExamQuestion(
            id = "q_viva_2",
            question = "Can we call delay_ms() inside an Interrupt Service Routine (ISR)? Why?",
            type = QuestionType.VIVA,
            moduleId = "mod3",
            simpleAnswer = "NO! Absolutely never call delay functions inside an ISR because it halts the CPU, blocks all other equal and lower interrupts, and causes massive system jitter.",
            detailedAnswer = "An ISR must execute in microseconds: read hardware, clear the flag, post to a queue or semaphore, and exit immediately.",
            keywords = listOf("Never delay in ISR", "Blocks interrupts", "Keep ISR short"),
            rememberPoints = listOf(
                "Immediate examiner response: 'Never! Keep ISRs ultra-fast; use semaphores to defer work to a task.'"
            )
        ),
        ExamQuestion(
            id = "q_viva_3",
            question = "What is a Watchdog Timer (WDT) and why is it critical?",
            type = QuestionType.VIVA,
            moduleId = "mod3",
            simpleAnswer = "A Watchdog Timer is an independent hardware countdown timer that resets the microcontroller if the firmware locks up in an infinite loop or crashes.",
            detailedAnswer = "Firmware must periodically 'kick' or 'feed' the watchdog. If software freezes, the timer underflows and triggers a clean hardware reset.",
            keywords = listOf("Watchdog Timer", "Hardware countdown", "Infinite loop recovery", "Feed the dog"),
            rememberPoints = listOf(
                "Analogy: A safety dead-man's switch on a train."
            )
        ),
        ExamQuestion(
            id = "q_viva_4",
            question = "What is Von Neumann bottleneck?",
            type = QuestionType.VIVA,
            moduleId = "mod1",
            simpleAnswer = "Because Von Neumann architecture uses a single shared bus for both data and instructions, the CPU cannot read an instruction and read/write data at the same instant, creating a throughput bottleneck.",
            detailedAnswer = "Harvard architecture solves this by providing separate physical buses for code and data.",
            keywords = listOf("Single bus", "Throughput bottleneck", "Harvard architecture"),
            rememberPoints = listOf(
                "Single shared bus = traffic jam between code and data."
            )
        )
    )

    fun getQuestionsForType(type: QuestionType): List<ExamQuestion> {
        return questions.filter { it.type == type }
    }
}
