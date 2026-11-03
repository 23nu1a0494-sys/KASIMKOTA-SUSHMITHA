# Embedded Learning Hub

> **"Learn Embedded Systems and RTOS from Basics to Advanced."**

**Embedded Learning Hub** is a modern, responsive educational Android application built with Kotlin and Jetpack Compose. It is designed to help students, beginners, and engineering undergraduates master technical concepts in **Embedded Systems**, **Real-Time Operating Systems (RTOS)**, and **Firmware Engineering** through plain-English explanations, interactive system simulators, hardware code experiments, examination question banks, and MCQ practice arenas.

---

## 🌟 Key Features

### 1. 📊 Modern Learning Dashboard
- **Welcome & Identity**: Displays personalized student profile and continuous study streak tracking.
- **Curriculum Mastery**: Dynamic progress indicator showing total available topics (21 lessons across 3 modules), completed topics count, and pending topics count.
- **Continue Learning**: One-tap resume action that opens the next uncompleted lesson in sequence.
- **Daily Learning Goals**: Interactive daily checklist to track daily study targets.
- **Quick Access Cards**: Direct navigation to curriculum modules, the Exam Prep Arena, MCQ Arena, and Embedded C Hardware Lab.

---

### 2. 📚 Comprehensive Curriculum Modules

#### **Module 1: Embedded Systems Fundamentals**
- **Introduction to Embedded Systems**: Dedicated function, resource constraints, real-time response, and everyday examples.
- **Embedded System Architecture**: Von Neumann vs. Harvard architectures, memory organizations, and system buses.
- **Microcontrollers (MCU) vs. Microprocessors (MPU)**: All-in-one chip SoC design vs. external peripheral chipsets.
- **Hardware and Software Components**: Sensors, actuators, memory, BSP (Board Support Package), and low-level device drivers.
- **Low-Level and High-Level Languages**: Machine code (0s & 1s), Assembly mnemonics, Embedded C/C++, and MicroPython.
- **Assemblers and Compilers**: Native compilers vs. cross-compilers, assemblers, linkers, and target architectures.
- **Source File to Object File Conversion**: Complete 5-stage build pipeline: Preprocessor (`.i`) -> Compiler (`.s`) -> Assembler (`.o`) -> Linker (`.elf`) -> Locator & HEX Generator (`.hex`/`.bin`).

#### **Module 2: RTOS Fundamentals**
- **Introduction to RTOS**: Real-time concepts, determinism, and deadline classifications (Hard, Firm, and Soft Real-Time).
- **Kernel and Kernel Services**: Task management, SysTick timer, IPC, synchronization, and memory protection.
- **Tasks, Processes, and Threads**: Memory isolation, single address space multitasking, and task stack allocation.
- **Task Control Block (TCB)**: Stack pointer preservation, task state, priority, and context switching mechanics.
- **Process Life Cycle**: The four fundamental states: *Ready*, *Running*, *Blocked (Waiting)*, and *Suspended/Terminated*.
- **Task Scheduling & Scheduling Algorithms**: Preemptive Fixed Priority, Round Robin (Time Slicing), First-Come First-Served (FCFS), and Earliest Deadline First (EDF).
- **Mutex and Semaphores**: Mutual exclusion with ownership vs. signaling semaphores, Priority Inversion, and Priority Inheritance.
- **Deadlocks**: The four Coffman conditions (Mutual Exclusion, Hold & Wait, No Preemption, Circular Wait) and lock ordering prevention strategies.
- **Inter-Task Communication (IPC)**: Thread-safe FIFO message queues, single-message mailboxes, byte pipes, and event flag groups.
- **Remote Procedure Call (RPC)**: Distributed execution, marshalling/unmarshalling, and inter-core communication (OpenAMP).
- **Monolithic and Microkernels**: All-in-kernel monolithic design vs. safety-isolated microkernel architectures (QNX, seL4).

#### **Module 3: Embedded Firmware Design**
- **Introduction to Firmware**: Flash memory residency, Reset Vector, hardware initialization, and system boot sequence.
- **Firmware Development Process**: Requirements, board bring-up, HAL drivers, JTAG/SWD debugging, and HIL testing.
- **Super Loop-Based Approach**: Infinite `while(1)` polling loop combined with asynchronous hardware interrupts (Foreground/Background Model).
- **RTOS-Based Firmware Design**: Task decomposition, rate-monotonic priority assignment, and concurrent firmware architectures.
- **Interrupt Handling**: Interrupt Service Routines (ISRs), Interrupt Vector Table (IVT), interrupt latency, and the `volatile` keyword.
- **Timer Management**: Hardware timers, prescalers, Compare Match registers, Fast PWM generation, Input Capture, and Watchdog Timers (WDT).
- **Real-Time Applications**: Production case studies in automotive ABS, avionics fly-by-wire, cardiac pacemakers, and industrial robotics.

---

### 3. 📖 11-Section Topic Masterclass Layout
Every topic explanation page includes:
1. **Introduction**: Conceptual overview connecting everyday concepts to technical reality.
2. **Simple Definition**: Plain-English definition designed for complete beginners.
3. **Detailed Explanation**: In-depth theoretical analysis of concepts, memory maps, and silicon registers.
4. **Step-by-Step Working Process**: Sequenced chronological phases of operation.
5. **Interactive Flowcharts & Visual Models**: Live embedded interactive models and state charts.
6. **Real-World Examples**: Real appliances, automotive systems, and medical instrumentation.
7. **Advantages**: Core engineering benefits.
8. **Disadvantages**: Trade-offs, memory costs, and failure modes.
9. **Applications**: Industrial and consumer embedded applications.
10. **Conclusion**: Concise conceptual summary.
11. **Important Examination Points**: High-yield points, definitions, and rules for test-takers.

*Action Controls: "Mark as Completed", "Save Bookmark", and "Add Personal Note".*

---

### 4. 🔬 Interactive Visual Learning & Hardware Simulators

- **Process Life Cycle State Machine**: Tap between *Ready*, *Running*, *Blocked*, and *Suspended* to observe real-time kernel transitions and explanations.
- **CPU Scheduling Gantt Chart Simulator**: Step through timeline execution for Preemptive Priority, Round Robin (Time Slicing), and FCFS.
- **Mutex vs. Semaphore Lock Simulator**: Simulate task critical section locking, task blocking, and mutual exclusion release.
- **Super Loop vs. RTOS Flowchart**: Visual side-by-side contrast of sequential loop jitter vs. preemptive multitasking.
- **Source-to-Hex Build Pipeline**: Step-by-step pipeline view from C source to machine opcodes.
- **MCU vs. MPU Architecture Diagram**: Single silicon die vs. external motherboard chipset layout.
- **Interrupt Execution Timeline**: Context saving, latency delay, ISR execution, and task resumption.

---

### 5. 💻 Embedded C Programming Lab with Live Simulators
Each programming experiment includes a problem statement, complete register-level C code, line-by-line register breakdowns, expected serial output, real-world applications, and **interactive live hardware simulation**:

1. **LED Blinking (GPIO)**: Direct `DDRB` and `PORTB` bit manipulation with live flashing LED and adjustable blink frequency slider.
2. **Sensor Reading (ADC)**: 10-bit Successive Approximation ADC reading with virtual analog potentiometer slider (`0.0V` - `5.0V`) and binary register display.
3. **Temperature Monitoring & Alert**: LM35 temperature sensor calculation with interactive temperature slider and dynamic audible/visual alarm buzzer trigger (`> 40°C`).
4. **Automatic Fan Control (PWM)**: Timer 1 Fast PWM with duty cycle slider (`0%` - `100%`) controlling an animated spinning cooling fan and RPM tachometer.
5. **Periodic Timer Interrupt**: 1kHz SysTick hardware timer (CTC mode with `OCR0A`) featuring Start, Pause, and Reset controls.
6. **Pushbutton External Interrupt (INT0)**: Falling-edge emergency stop (E-Stop) button with instant hardware interrupt trigger and motor shutdown.
7. **Basic Task Scheduling**: FreeRTOS dual-task preemptive context switcher with sensor and display tasks.

---

### 6. 🎓 Examination Preparation Section
Structured exam repository categorized by standard university marks:
- **A. Very Short Answer Questions (2 Marks)**: Concise, formulaic definitions with core keywords.
- **B. Short Answer Questions (5 Marks)**: Explanations with bullet points and comparison structures.
- **C. Long Answer Questions (10 Marks)**: Comprehensive architectural essays, algorithms, and proofs.
- **D. Important Viva Voce Questions**: Rapid-fire oral examination questions and answers.

*Every question includes highlighted keyword chips, easy-to-remember memory aids, and a "Save for Revision" bookmark action.*

---

### 7. 🎯 Interactive MCQ Arena
- **Four Options**: Formatted multiple-choice questions with one correct answer.
- **Instant Feedback**: Immediate color-coded validation (green for correct, red for incorrect).
- **In-Depth Explanations**: Detailed feedback explaining why the answer is correct.
- **Difficulty Modes**: Filter by *Beginner*, *Intermediate*, *Advanced*, or *All Levels*.
- **Score Reports & Retake**: Comprehensive post-quiz analytics with correct/incorrect breakdown and one-tap test retake.

---

### 8. 📝 Revision Notes & Personal Student Journal
- **Quick Cheat Sheets**: Acronym breakdowns and examination rules (TCB, ISR, IVT, PWM, RMS, EDF, BSP, WDT).
- **Comparison Tables**: Side-by-side comparison tables:
  - Microcontroller (MCU) vs. Microprocessor (MPU)
  - Mutex vs. Binary Semaphore
  - Super Loop vs. RTOS Architecture
  - Hard Real-Time vs. Soft Real-Time
- **Personal Notes Manager**: Create, view, edit, and delete private revision notes stored offline via Room database.
- **Bookmarked Items**: Dedicated tab consolidating all saved topics and exam questions for rapid pre-exam review.

---

### 9. 🔍 Global Search & Profile
- **Global Search**: Real-time cross-database search indexing curriculum topics, definitions, exam questions, code snippets, and glossaries.
- **Student Profile**: Editable student credentials, daily study goals, study streak counter, and curriculum reset controls.

---

## 🛠️ Technology Stack & Architecture

- **Platform**: Android (minSdk 24, targetSdk 36, compileSdk 36)
- **Language**: Kotlin 2.x
- **UI Framework**: Jetpack Compose with Material Design 3 (M3)
- **Theme**: Custom Cyber-Navy & Electric Cyan technical palette with full light and dark mode support
- **Architecture**: MVVM (Model-View-ViewModel) + Repository Pattern
- **Local Persistence**: Android Jetpack Room Database (`RoomDatabase`, `AppDao`, reactive Kotlin `Flow`)
- **Asynchronous Concurrency**: Kotlin Coroutines (`StateFlow`, `viewModelScope`)
- **Testing**: JUnit 4 unit tests + Robolectric local JVM testing

---

## 📁 Project Structure

```
app/src/main/java/com/example/
├── MainActivity.kt                  # Entry point, Edge-to-Edge setup, Navigation Scaffold
├── data/
│   ├── local/
│   │   ├── AppDao.kt                # Reactive Room database queries and mutations
│   │   ├── AppDatabase.kt           # Room Database singleton
│   │   └── Entities.kt              # Progress, Bookmark, Note, Quiz, and Profile entities
│   ├── model/
│   │   ├── CodeExample.kt           # Programming models & simulation types
│   │   ├── ExamQuestion.kt          # Examination questions & mark types
│   │   ├── McqQuestion.kt           # MCQ question models & difficulty ratings
│   │   ├── RevisionSummary.kt       # Comparison tables & cheat sheet definitions
│   │   └── Topic.kt                 # Curriculum topic schema & diagram types
│   └── repository/
│       ├── CodeData.kt              # 7 embedded C programs & register breakdowns
│       ├── CurriculumData.kt        # 21 curriculum topics across Modules 1, 2, and 3
│       ├── ExamData.kt              # High-yield exam question bank (2M, 5M, 10M, Viva)
│       ├── LearningRepository.kt    # Unified data broker & global search engine
│       ├── McqData.kt               # MCQ question bank with explanations
│       └── RevisionData.kt          # Comparison tables & glossary terms
├── ui/
│   ├── components/
│   │   ├── AppNavBar.kt             # Material 3 Bottom Navigation Bar with insets
│   │   ├── AppTopBar.kt             # Contextual Top App Bar with search and profile actions
│   │   ├── CodeHardwareSimulator.kt # Live hardware simulators (LED, ADC, Fan, Timers)
│   │   └── DiagramComponents.kt     # Interactive system diagrams & state machines
│   ├── screens/
│   │   ├── DashboardScreen.kt       # Primary overview, streak, metrics, and shortcuts
│   │   ├── ExamPrepScreen.kt        # 2M, 5M, 10M, Viva questions & bookmark filters
│   │   ├── GlobalSearchScreen.kt    # Instant search across all content
│   │   ├── McqPracticeScreen.kt     # Interactive test arena with instant feedback
│   │   ├── ModulesScreen.kt         # Module 1, 2, 3 curriculum explorer
│   │   ├── ProfileScreen.kt         # Student details, streak, and study goals
│   │   ├── ProgrammingLabScreen.kt  # Embedded C code lab with hardware simulators
│   │   ├── RevisionNotesScreen.kt   # Quick revision summaries, tables, & personal notes
│   │   └── TopicDetailScreen.kt     # 11-section masterclass learning view
│   └── theme/
│       ├── Color.kt                 # Tech Navy, Electric Cyan, Neon Teal, Amber Gold
│       ├── Theme.kt                 # EmbeddedHubTheme definition
│       └── Type.kt                  # Typography definitions
└── viewmodel/
    └── LearningViewModel.kt         # Central UI state management & navigation flow
```

---

## 🚀 Building & Running

### Gradle Commands
To compile and assemble the debug APK:
```bash
gradle :app:assembleDebug
```

To run all unit tests and Robolectric tests:
```bash
gradle :app:testDebugUnitTest
```

---

## 📜 License
Developed as an educational engineering platform for learning Embedded Systems and Real-Time Operating Systems.
