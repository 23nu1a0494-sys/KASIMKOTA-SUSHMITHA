package com.example.data.repository

import com.example.data.model.DiagramType
import com.example.data.model.Topic

object CurriculumData {

    val topics: List<Topic> = listOf(
        // MODULE 1
        Topic(
            id = "m1_intro",
            moduleId = "mod1",
            title = "Introduction to Embedded Systems",
            shortSummary = "What is an embedded system, dedicated functions, and everyday examples.",
            introduction = "Think about a digital wristwatch, a microwave oven, or a modern car. Unlike a general-purpose desktop computer which can run thousands of different software programs (like word processing, gaming, web browsing), an embedded system is designed to perform ONE specific task or a small set of dedicated tasks reliably and repeatedly.",
            simpleDefinition = "An Embedded System is a special computer system with dedicated hardware and software designed to do a single job automatically.",
            detailedExplanation = "An embedded system is a combination of computer hardware (a processor or microcontroller, memory, and input/output pins) and specialized software (called firmware) embedded inside a larger mechanical or electrical system.\n\nKey characteristics of embedded systems:\n1. Dedicated Function: It does not browse the web or run arbitrary apps; a washing machine embedded system controls the motor, water valves, and wash cycles.\n2. Resource Constrained: It runs on small microchips with very limited RAM (often 2KB to 64KB) and Flash ROM (16KB to 512KB).\n3. High Reliability: Devices like pacemakers or automotive braking units cannot afford to crash or display the 'blue screen of death'.\n4. Power Efficient: Many embedded devices operate on small coin-cell batteries for months or years.",
            stepByStepProcess = listOf(
                "Sensors capture physical inputs (temperature, speed, button click).",
                "Analog-to-Digital Converter (ADC) translates real-world signals into digital 0s and 1s.",
                "Microcontroller CPU processes the data according to the embedded firmware program.",
                "CPU sends commands to output actuators (motors, displays, relays, buzzers)."
            ),
            diagramType = DiagramType.MCU_VS_MPU,
            diagramExplanation = "Embedded System Loop: Physical World -> Sensors -> Microcontroller -> Actuators -> Physical Output.",
            realWorldExamples = listOf(
                "Automotive Anti-lock Braking System (ABS): Detects wheel slip and pulses the brakes in milliseconds.",
                "Microwave Oven Controller: Reads keypad inputs, regulates the magnetron timer, and buzzes on completion.",
                "Smart Fitness Band: Counts steps using an accelerometer and tracks heart rate via photodiode sensors.",
                "Cardiac Pacemaker: Delivers electrical pulses to keep a human heart beating at normal rhythms."
            ),
            advantages = listOf(
                "Extremely low manufacturing and operating cost.",
                "Ultra-compact physical footprint.",
                "Very low power consumption (can run on batteries).",
                "High speed and determinism for dedicated tasks."
            ),
            disadvantages = listOf(
                "Difficult to upgrade hardware once deployed.",
                "Severe memory and CPU resource constraints.",
                "Debugging hardware and timing bugs can be challenging."
            ),
            applications = listOf(
                "Automotive & Electric Vehicles",
                "Consumer Electronics & Home Appliances",
                "Medical Instrumentation & Healthcare",
                "Industrial Automation & Robotics",
                "Aerospace, Avionics & Defense"
            ),
            conclusion = "Embedded systems form the invisible backbone of modern civilization. Almost every modern electronic product with buttons or a screen contains one or more embedded microcontrollers.",
            examPoints = listOf(
                "Define Embedded System: Hardware + Software + Mechanical part designed for a dedicated purpose.",
                "State three main characteristics: Dedicated function, resource-constrained, real-time response.",
                "Contrast with General Purpose Systems: Desktop PCs run multiple arbitrary apps, while embedded systems do one specific job."
            ),
            estimatedReadMinutes = 5
        ),

        Topic(
            id = "m1_arch",
            moduleId = "mod1",
            title = "Embedded System Architecture",
            shortSummary = "Von Neumann vs Harvard, Central Processing Unit, Memory, and Peripherals.",
            introduction = "The architecture of an embedded system defines how its internal parts—the CPU, memory, and input/output peripherals—are connected and communicate with each other.",
            simpleDefinition = "Embedded System Architecture is the blueprint of how the processor, memory, and input/output parts connect and work together.",
            detailedExplanation = "Every embedded system architecture consists of four fundamental pillars:\n1. The Core Processor: Executes arithmetic and logic instructions.\n2. Memory Units: Non-volatile Flash/ROM stores permanent code; volatile SRAM stores temporary runtime variables.\n3. Input/Output (I/O) Peripherals: GPIO pins, UART, I2C, SPI, timers, and ADC converters.\n4. Bus Architecture: Address Bus, Data Bus, and Control Bus.\n\nTwo major architectural models exist:\n• Von Neumann Architecture: A single shared bus and memory space for both program instructions and data.\n• Harvard Architecture: Separate physical memory and separate buses for program instructions and data, allowing the CPU to read an instruction and read/write data simultaneously.",
            stepByStepProcess = listOf(
                "CPU generates the instruction address on the Program Address Bus.",
                "Program Flash Memory sends the opcode back via the Instruction Bus.",
                "Instruction Decoder decodes the binary opcode.",
                "ALU executes the calculation, reading or storing values in SRAM via the Data Bus.",
                "I/O Controllers write output states to physical pins."
            ),
            diagramType = DiagramType.MCU_VS_MPU,
            diagramExplanation = "Harvard Architecture features separate Program Bus and Data Bus, doubling memory throughput.",
            realWorldExamples = listOf(
                "ARM Cortex-M processors (STM32, NXP) use modified Harvard architecture for fast deterministic control.",
                "AVR ATmega328P (used in Arduino Uno) has 32KB Flash for code and 2KB SRAM for variables on separate buses."
            ),
            advantages = listOf(
                "Harvard architecture allows simultaneous instruction fetching and data manipulation.",
                "Hardware buses are optimized for low power and high deterministic timing.",
                "Integrated on-chip peripherals eliminate bulky external chip packages."
            ),
            disadvantages = listOf(
                "Fixed address spaces require careful compiler memory management.",
                "Complexity in memory mapping across different peripheral registers."
            ),
            applications = listOf(
                "Motor speed control controllers",
                "Industrial PLC (Programmable Logic Controller) boards",
                "Drone flight management units"
            ),
            conclusion = "Choosing the right architecture (Harvard vs Von Neumann) determines instruction throughput, memory access speeds, and overall system cost.",
            examPoints = listOf(
                "Differentiate Von Neumann vs Harvard: Von Neumann shares 1 bus for code and data; Harvard has separate buses.",
                "List the three computer buses: Address bus (unidirectional), Data bus (bidirectional), Control bus.",
                "State what SRAM and Flash are used for: Flash holds program code; SRAM holds active variables."
            ),
            estimatedReadMinutes = 6
        ),

        Topic(
            id = "m1_mcu_vs_mpu",
            moduleId = "mod1",
            title = "Microcontrollers and Microprocessors",
            shortSummary = "All-in-one chip (MCU) vs external peripheral powerhouses (MPU).",
            introduction = "Students often confuse microprocessors and microcontrollers. While both process information, a microcontroller is a complete self-contained computer on a single silicon chip, whereas a microprocessor is just the raw CPU brain that needs external memory and chips.",
            simpleDefinition = "A Microcontroller (MCU) has CPU, RAM, ROM, and I/O ports all built on one chip. A Microprocessor (MPU) only contains the CPU core and requires external RAM and ROM chips.",
            detailedExplanation = "• Microcontroller (MCU): Designed for compact, low-cost embedded systems. Examples include 8051, PIC, AVR ATmega328P, and ARM Cortex-M. It has internal RAM (a few kilobytes), internal Flash, timers, ADC, and GPIO pins integrated onto the exact same silicon die.\n\n• Microprocessor (MPU): Designed for high-performance computing. Examples include Intel Core i7, AMD Ryzen, and ARM Cortex-A (in smartphones). An MPU cannot work alone; you must connect external DDR RAM chips, external SSD/Flash chips, and motherboard chipset peripherals.",
            stepByStepProcess = listOf(
                "Microcontroller powers up instantly from on-chip Flash memory within microseconds.",
                "It directly toggles its internal I/O pins without external bus transactions.",
                "Microprocessor on the other hand boots by loading bootloader code from external flash to DDR RAM, taking seconds."
            ),
            diagramType = DiagramType.MCU_VS_MPU,
            diagramExplanation = "Microcontroller: Everything on one chip. Microprocessor: CPU core with external RAM, ROM, and I/O buses.",
            realWorldExamples = listOf(
                "Microcontroller example: Smart thermostat, digital thermometer, car power window controller.",
                "Microprocessor example: Desktop computer, smartphone main application processor, smart TV multimedia hub."
            ),
            advantages = listOf(
                "Microcontrollers have much lower system cost and zero PCB bus complexity.",
                "Microcontrollers offer instant-on boot time and micro-second deterministic responses.",
                "Microprocessors offer gigahertz-speed processing and gigabytes of memory space."
            ),
            disadvantages = listOf(
                "Microcontrollers cannot run complex operating systems like full Ubuntu or Windows.",
                "Microprocessors consume significant power (watts to dozens of watts) and require heat sinks."
            ),
            applications = listOf(
                "MCU: Automotive ECU, home automation, toy controllers, medical sensors.",
                "MPU: High-resolution graphical user interfaces, video streaming, computer vision."
            ),
            conclusion = "For specific embedded control tasks requiring low power and low cost, microcontrollers reign supreme. When heavy computation or full OS software is required, microprocessors are chosen.",
            examPoints = listOf(
                "Key difference: MCU = CPU + RAM + ROM + I/O on single chip. MPU = CPU core only.",
                "Cost comparison: MCU systems are much cheaper and smaller than MPU systems.",
                "Power consumption: MCUs consume milliwatts or microwatts; MPUs consume watts."
            ),
            estimatedReadMinutes = 6
        ),

        Topic(
            id = "m1_hw_sw",
            moduleId = "mod1",
            title = "Hardware and Software Components",
            shortSummary = "Sensors, actuators, memory, firmware, BSP, and device drivers.",
            introduction = "An embedded device is nothing without both its physical electronic hardware and its embedded software instructions working in perfect harmony.",
            simpleDefinition = "Embedded hardware consists of physical chips, sensors, and actuators. Embedded software (firmware) is the program stored inside chip memory that controls that hardware.",
            detailedExplanation = "Embedded Hardware Components:\n1. Processing Unit (MCU/DSP/FPGA)\n2. Memory Elements: Flash/ROM (non-volatile), SRAM (volatile), EEPROM (save user settings)\n3. Input Devices: Switches, sensors (temperature, pressure, optical, ultrasonic)\n4. Output Devices: LEDs, LCD displays, relays, solenoids, stepper motors\n5. Communication Interfaces: UART, SPI, I2C, CAN Bus (automotive), USB\n\nEmbedded Software Stack:\n1. Application Layer: User logic and control state machine.\n2. RTOS / Middleware: Task scheduler, network stack, filesystem.\n3. Device Drivers: Code that knows how to read/write hardware registers.\n4. Board Support Package (BSP): Startup assembly, clock configuration, vector table.\n5. Bare Metal Hardware.",
            stepByStepProcess = listOf(
                "Hardware power-on reset triggers the MCU reset vector.",
                "BSP code configures the internal oscillator (e.g. 16 MHz) and sets up the stack pointer.",
                "Device drivers initialize peripheral registers (baud rate, pin direction).",
                "Application logic enters the main loop or starts the RTOS scheduler."
            ),
            diagramType = DiagramType.SUPERLOOP_VS_RTOS,
            diagramExplanation = "The embedded software stack bridges physical hardware pins with application algorithms.",
            realWorldExamples = listOf(
                "Air Conditioner: Temperature sensor (Hardware input) -> MCU Firmware compares setpoint -> Compressor relay (Hardware output) is turned ON."
            ),
            advantages = listOf(
                "Tight hardware-software integration yields peak energy efficiency.",
                "Device drivers abstract raw memory addresses for clean application development."
            ),
            disadvantages = listOf(
                "Writing low-level drivers requires thorough understanding of hardware datasheets.",
                "Hardware faults (floating pins, noise) can masquerade as software bugs."
            ),
            applications = listOf(
                "Smart electricity meters",
                "Automated greenhouse climate control",
                "Wearable medical monitors"
            ),
            conclusion = "Mastering embedded development requires thinking like an electrical engineer (understanding voltages, pins, noise) and a software engineer (clean algorithms, memory management).",
            examPoints = listOf(
                "Name 4 hardware components: Core processor, Memory (Flash/RAM), Sensors, Actuators.",
                "Define BSP (Board Support Package): Software layer that initializes processor clocks, pins, and boards.",
                "Explain the role of Device Drivers: Functions that read and write hardware registers."
            ),
            estimatedReadMinutes = 5
        ),

        Topic(
            id = "m1_languages",
            moduleId = "mod1",
            title = "Low-Level and High-Level Languages",
            shortSummary = "Machine code, Assembly language, C, C++, and Python in embedded systems.",
            introduction = "To make a microcontroller execute instructions, we write programs. But how close should the language be to the physical silicon transistors?",
            simpleDefinition = "Low-level languages (Assembly, Machine Code) communicate directly with CPU registers. High-level languages (C, C++, Rust) use human-readable words and need a compiler.",
            detailedExplanation = "1. Machine Language: Pure binary 1s and 0s (e.g. 10110000 00000001). The only thing CPU transistors actually understand.\n\n2. Assembly Language (Low-Level): Symbolic mnemonics (e.g., 'MOV R1, #05H', 'ADD R1, R2'). Very fast and gives direct control over CPU registers and flag bits, but it is processor-specific (ARM assembly does not work on 8051 or x86) and hard to write.\n\n3. Embedded C / C++ (High-Level): The dominant king of embedded systems for 40+ years. It provides direct memory pointer access, bitwise manipulation, fast compilation, and portability across different microcontroller families.\n\n4. MicroPython / Rust: Modern alternatives gaining popularity for rapid IoT prototyping and memory-safe firmware.",
            stepByStepProcess = listOf(
                "Engineer writes clean, maintainable logic in Embedded C.",
                "Cross-compiler translates C statements into target Assembly instructions.",
                "Assembler converts mnemonics into binary Machine Code opcodes.",
                "MCU hardware decoder reads and executes binary opcodes."
            ),
            diagramType = DiagramType.SOURCE_TO_HEX,
            diagramExplanation = "Language Hierarchy: Human thought -> High-Level C -> Low-Level Assembly -> Machine Code (0s & 1s).",
            realWorldExamples = listOf(
                "90%+ of automotive ECUs and medical devices are written in Embedded C/C++ for speed and determinism.",
                "Time-critical bootloader reset handlers are often written in pure Assembly."
            ),
            advantages = listOf(
                "Embedded C gives the speed of assembly with the readability of high-level code.",
                "Portability: The same C logic can be recompiled for ARM, RISC-V, or PIC with minimal driver adjustments."
            ),
            disadvantages = listOf(
                "Assembly code is completely non-portable between different chip architectures.",
                "High-level interpreted languages (like standard Python) are too slow and memory-hungry for microcontrollers."
            ),
            applications = listOf(
                "Critical device bootloaders",
                "Automotive motor controllers",
                "IoT edge nodes"
            ),
            conclusion = "Embedded C strikes the perfect golden balance between direct silicon hardware control and developer productivity, making it the industry standard.",
            examPoints = listOf(
                "Why is Embedded C preferred?: Fast execution, low memory overhead, bit manipulation, and portability.",
                "What is a mnemonic?: An assembly shorthand word like MOV, ADD, JMP, or SUB.",
                "Explain portability: C code can be compiled for multiple CPUs; assembly is locked to one CPU family."
            ),
            estimatedReadMinutes = 5
        ),

        Topic(
            id = "m1_compilers",
            moduleId = "mod1",
            title = "Assemblers and Compilers",
            shortSummary = "Native compilers vs cross-compilers, assemblers, and linkers.",
            introduction = "When developing for embedded systems, your development laptop (x86/x64 Intel or Apple Silicon) is NOT the target machine that will run your firmware (e.g. 32-bit ARM Cortex-M or 8-bit AVR). This requires special translation tools.",
            simpleDefinition = "A Compiler translates high-level code (like C) into assembly or machine code. An Assembler converts assembly mnemonics into machine code. A Cross-Compiler runs on your PC but creates code for a different microcontroller.",
            detailedExplanation = "Key definitions:\n• Native Compiler: Runs on Host PC and generates binary code that runs on the same Host PC (e.g., GCC building an app on Linux for Linux).\n• Cross-Compiler: Runs on Host PC (e.g., Windows/macOS) and produces binary machine code for a completely different target chip architecture (e.g., `arm-none-eabi-gcc` running on Windows producing ARM Cortex-M0 code).\n• Assembler: A simple 1-to-1 translator converting mnemonic codes (ADD, MOV) into binary opcode values.",
            stepByStepProcess = listOf(
                "Cross-compiler parses high-level syntax and optimizes loop operations.",
                "Generates target assembly files (.s or .asm).",
                "Assembler converts assembly instructions into relocatable object files (.o or .obj).",
                "Linker combines object files and standard libraries into a binary image."
            ),
            diagramType = DiagramType.SOURCE_TO_HEX,
            diagramExplanation = "Cross-Compilation Flow: Host PC executes compilation tools -> Target MCU receives machine code.",
            realWorldExamples = listOf(
                "Using arm-none-eabi-gcc on your laptop to compile code for an STM32 microcontroller.",
                "Using Keil uVision or MPLAB X IDE to assemble code for an 8051 or PIC microcontroller."
            ),
            advantages = listOf(
                "Allows developers to use powerful multi-core desktop computers to compile code for tiny 8-bit chips.",
                "Optimizers remove dead code and unroll loops to shrink binary size."
            ),
            disadvantages = listOf(
                "Cross-toolchains require complex toolchain setup (sysroots, headers, linker scripts)."
            ),
            applications = listOf(
                "All modern embedded firmware development toolchains"
            ),
            conclusion = "Without cross-compilers, writing modern firmware would be nearly impossible, as tiny microcontrollers lack the memory and storage to compile their own programs.",
            examPoints = listOf(
                "Define Cross-Compiler: A compiler that runs on one architecture (Host) and generates code for another (Target).",
                "Difference between Compiler and Assembler: Compiler takes high-level C; Assembler takes low-level assembly mnemonics.",
                "What is a Host vs Target?: Host is your development PC; Target is the microcontroller."
            ),
            estimatedReadMinutes = 5
        ),

        Topic(
            id = "m1_src_to_obj",
            moduleId = "mod1",
            title = "Source File to Object File Conversion",
            shortSummary = "The complete build pipeline: Preprocessor -> Compiler -> Assembler -> Linker -> Locator -> HEX/BIN.",
            introduction = "How does your human-written `main.c` text file actually end up flashed as electrical charges inside a microcontroller's silicon memory cells? The build pipeline consists of distinct, fascinating stages.",
            simpleDefinition = "Source to Object conversion is the 5-step journey that turns human C code into flashable binary machine code: Preprocessing -> Compiling -> Assembling -> Linking -> Locating.",
            detailedExplanation = "The 5 Build Stages:\n1. Preprocessing (`.c` -> `.i`): Handles all `#include`, `#define`, and conditional `#ifdef` macros. Replaces header files with raw text.\n2. Compiling (`.i` -> `.s`): Analyzes syntax, applies optimization, and produces target-specific assembly code.\n3. Assembling (`.s` -> `.o`): Translates assembly mnemonics into relocatable object code (binary opcodes with placeholder memory addresses).\n4. Linking (`.o` + `.a` -> `.elf`): Resolves symbol cross-references across multiple files, connects math and C runtime libraries.\n5. Locating & HEX Generation (`.elf` -> `.hex`/`.bin`): Reads the Linker Script (`.ld`), assigns real physical Flash/RAM memory addresses, and creates the Intel HEX or raw binary file to flash into the chip.",
            stepByStepProcess = listOf(
                "Step 1: Preprocessor expands headers (#include) and macro constants (#define).",
                "Step 2: Compiler checks syntax and generates assembly (.s).",
                "Step 3: Assembler converts instructions to relocatable machine code (.o).",
                "Step 4: Linker combines all .o files and resolves function calls.",
                "Step 5: Locator assigns physical memory addresses based on linker script (.ld), producing .hex for programmer flashing."
            ),
            diagramType = DiagramType.SOURCE_TO_HEX,
            diagramExplanation = "Build Pipeline Diagram: main.c -> Preprocessor -> Compiler -> Assembler -> Linker/Locator -> firmware.hex.",
            realWorldExamples = listOf(
                "Intel HEX file (.hex) loaded onto an Arduino via AVRDUDE programmer over USB.",
                ".bin binary image burned into STM32 internal Flash memory via ST-Link."
            ),
            advantages = listOf(
                "Relocatable object files allow modular programming: you only recompile modified files.",
                "Linker scripts allow precise hardware memory layout (e.g., placing bootloader at 0x08000000)."
            ),
            disadvantages = listOf(
                "Linker errors ('undefined reference', 'region RAM overflowed') can be cryptic for beginners."
            ),
            applications = listOf(
                "GCC toolchains, LLVM Clang, IAR Embedded Workbench, Keil MDK"
            ),
            conclusion = "Understanding the build pipeline removes the mystery of compilation errors and empowers engineers to control exact memory layouts through linker scripts.",
            examPoints = listOf(
                "List the stages in order: Preprocessing -> Compilation -> Assembly -> Linking -> Locating.",
                "What does the Preprocessor do?: Replaces #include with file contents, expands #define macros.",
                "What is a Linker Script?: A configuration file specifying exact Flash and RAM memory start addresses."
            ),
            estimatedReadMinutes = 6
        ),

        // MODULE 2: RTOS FUNDAMENTALS
        Topic(
            id = "m2_intro_rtos",
            moduleId = "mod2",
            title = "Introduction to RTOS",
            shortSummary = "What is a Real-Time Operating System, predictability, and deadline guarantees.",
            introduction = "In a standard computer (like Windows or Android), if an app lags for half a second while playing a music file, it is merely annoying. But in an airplane flight autopilot, a medical ventilator, or a car airbag, if a task is delayed by even 5 milliseconds, catastrophic failure or death can occur.",
            simpleDefinition = "A Real-Time Operating System (RTOS) is an operating system where the correctness of a task depends not just on its logical result, but on the exact TIME at which the result is delivered.",
            detailedExplanation = "Key concept: Determinism. An RTOS provides guaranteed worst-case execution times for critical tasks.\n\nClassification of Real-Time Systems:\n1. Hard Real-Time: Missing a single deadline causes total system failure or loss of life (e.g. Airbag deployment, nuclear plant shutdown, pacemaker).\n2. Firm Real-Time: Missing occasional deadlines does not cause disaster, but the delayed output is completely useless (e.g. Video streaming frame, radar tracking sweep).\n3. Soft Real-Time: Missing a deadline merely degrades performance or user experience (e.g. Microwave screen update, smartphone touch response, vending machine).",
            stepByStepProcess = listOf(
                "Event occurs (e.g., collision sensor triggers an interrupt).",
                "RTOS kernel halts low-priority tasks immediately (Preemption).",
                "Context switch saves current task state and loads high-priority airbag task.",
                "Airbag task executes and completes within strict deadline (< 20 milliseconds)."
            ),
            diagramType = DiagramType.SUPERLOOP_VS_RTOS,
            diagramExplanation = "General OS focuses on average throughput; RTOS focuses on strict deterministic deadlines.",
            realWorldExamples = listOf(
                "Automotive Airbag Controller: Must trigger pyrotechnic inflator within 15 to 30 milliseconds of crash impact.",
                "Mars Curiosity Rover: Runs VxWorks RTOS to navigate terrain and communicate with Earth.",
                "FreeRTOS in IoT: Used in millions of smart home appliances and industrial motor drives."
            ),
            advantages = listOf(
                "Guaranteed deterministic response times for critical events.",
                "Modular software design: developers write independent tasks instead of giant messy loops.",
                "Priority-based preemptive scheduling ensures important tasks run first."
            ),
            disadvantages = listOf(
                "Slight memory overhead (each task requires its own stack in RAM).",
                "Context switching consumes a small percentage of CPU cycles."
            ),
            applications = listOf(
                "Aerospace, Avionics & Satellites",
                "Medical Life Support Systems",
                "Automotive Powertrain & ADAS",
                "Industrial Robotics & CNC Machines"
            ),
            conclusion = "RTOS is not about being the 'fastest' operating system; it is about being 100% predictable and guaranteed to meet timing deadlines under all conditions.",
            examPoints = listOf(
                "Define RTOS: Operating system where correctness depends on logical result AND timely delivery.",
                "Differentiate Hard vs Soft Real-Time: Hard = deadline miss causes catastrophe; Soft = deadline miss degrades quality.",
                "What is Determinism?: The ability to guarantee worst-case timing behavior consistently."
            ),
            estimatedReadMinutes = 6
        ),

        Topic(
            id = "m2_kernel",
            moduleId = "mod2",
            title = "Kernel and Kernel Services",
            shortSummary = "The core engine of an RTOS: Task management, time slicing, memory, and sync.",
            introduction = "The kernel is the beating heart of an operating system. In an embedded RTOS, the kernel is designed to be microscopic (often just a few kilobytes of code) yet lightning-fast.",
            simpleDefinition = "The Kernel is the core program of an RTOS that manages CPU time, decides which task runs, and coordinates memory and hardware communication.",
            detailedExplanation = "Primary RTOS Kernel Services:\n1. Task Management & Scheduling: Creating, deleting, suspending, and executing tasks according to priority.\n2. Time Management: System tick timer, task sleep (`vTaskDelay`), software timers, and timeouts.\n3. Inter-Task Communication (IPC): Message queues, pipes, mailboxes, and event groups.\n4. Task Synchronization: Mutexes and semaphores to protect shared resources.\n5. Memory Management: Deterministic fixed-size block allocation to avoid memory fragmentation.\n6. Interrupt Handling: Hooking Interrupt Service Routines (ISRs) and deferring processing to tasks.",
            stepByStepProcess = listOf(
                "Hardware timer triggers periodic SysTick interrupt (e.g. every 1 millisecond).",
                "Kernel increments system tick counter and evaluates ready tasks.",
                "Scheduler selects highest priority task ready to execute.",
                "Dispatcher performs context switch if a higher priority task is ready."
            ),
            diagramType = DiagramType.PROCESS_LIFECYCLE,
            diagramExplanation = "The RTOS kernel acts as the traffic controller managing tasks, interrupts, and shared memory.",
            realWorldExamples = listOf(
                "FreeRTOS kernel takes only ~6KB to 10KB of Flash and is used on millions of microcontrollers worldwide.",
                "Zephyr RTOS provides modular kernel services for modern IoT connectivity."
            ),
            advantages = listOf(
                "Ultra-small footprint suitable for microcontrollers with 16KB RAM.",
                "Zero dynamic heap fragmentation when using static memory allocation."
            ),
            disadvantages = listOf(
                "Improper task design can lead to priority inversion or deadlocks."
            ),
            applications = listOf(
                "Smart watches, quadcopter flight stabilization, smart energy meters"
            ),
            conclusion = "The RTOS kernel provides the foundational plumbing so application engineers can write clean, decoupled, and priority-driven embedded code.",
            examPoints = listOf(
                "List 4 kernel services: Task scheduling, Time management, Inter-task communication, Synchronization.",
                "What is a SysTick?: A periodic hardware timer interrupt used by the kernel to keep track of time.",
                "Why is dynamic malloc risky in RTOS?: It can cause unpredictable execution time and heap fragmentation."
            ),
            estimatedReadMinutes = 5
        ),

        Topic(
            id = "m2_tasks_threads",
            moduleId = "mod2",
            title = "Tasks, Processes and Threads",
            shortSummary = "Differences in memory address spaces, TCB, and lightweight execution units.",
            introduction = "In computing, we often hear the words 'process', 'thread', and 'task' used interchangeably. However, their internal memory layout and separation differ significantly between desktop OSes and embedded RTOSes.",
            simpleDefinition = "A Task (or Thread) in an RTOS is a small, independent function running its own execution loop with its own private stack, sharing the common chip memory.",
            detailedExplanation = "• Process: In desktop systems (Linux/Windows), a process has its own isolated virtual memory space guarded by an MMU (Memory Management Unit). One process cannot accidentally overwrite another's memory.\n• Thread / RTOS Task: Most microcontrollers lack an MMU. Therefore, in an RTOS like FreeRTOS, all 'tasks' share the same single physical memory address space. However, each task possesses its own private stack memory for local variables and return addresses.\n\nStructure of an RTOS Task in C:\n```c\nvoid Task_SensorRead(void *pvParameters) {\n    for(;;) { // Infinite loop!\n        ReadSensor();\n        vTaskDelay(pdMS_TO_TICKS(100));\n    }\n}\n```",
            stepByStepProcess = listOf(
                "Application calls task creation function (e.g. xTaskCreate).",
                "Kernel allocates a Task Control Block (TCB) and private Stack memory.",
                "Initial CPU register values (PC, LR, SP) are pushed onto the new task stack.",
                "Task is placed in the READY list waiting for scheduler execution."
            ),
            diagramType = DiagramType.PROCESS_LIFECYCLE,
            diagramExplanation = "Tasks share global RAM but maintain private stacks for local variables and register states.",
            realWorldExamples = listOf(
                "Task 1: Read temperature every 100ms. Task 2: Refresh OLED screen every 50ms. Task 3: Handle Bluetooth packets."
            ),
            advantages = listOf(
                "Lightweight: Context switching between RTOS tasks takes only a few microseconds.",
                "Simple data sharing via pointers and shared buffers."
            ),
            disadvantages = listOf(
                "No hardware memory protection: a bug in one task (like a buffer overflow) can corrupt other tasks."
            ),
            applications = listOf(
                "Multi-function embedded products like 3D printers, medical monitors, and smart meters"
            ),
            conclusion = "Designing an embedded application as multiple small, focused tasks makes complex firmware easier to write, debug, and maintain.",
            examPoints = listOf(
                "Why do RTOS tasks run in infinite loops?: Embedded systems never exit; they run continuously until powered off.",
                "Process vs Task: Process has separate virtual memory (needs MMU); RTOS task shares common memory but has private stack.",
                "What is a task stack used for?: Storing local variables, function call return addresses, and CPU registers during context switch."
            ),
            estimatedReadMinutes = 5
        ),

        Topic(
            id = "m2_tcb",
            moduleId = "mod2",
            title = "Task Control Block (TCB)",
            shortSummary = "The identity card and state keeper of every task in memory.",
            introduction = "When the RTOS stops Task A to let high-priority Task B run, how does it remember where Task A was and what its variables were? It saves everything inside a data structure called the Task Control Block (TCB).",
            simpleDefinition = "The Task Control Block (TCB) is a data structure used by the RTOS kernel to store all vital information, state, priority, and stack pointer of a specific task.",
            detailedExplanation = "Contents of a Task Control Block:\n1. Stack Pointer (SP): Points to the current top of the task's private stack.\n2. Task State: Current status (Ready, Running, Blocked, Suspended).\n3. Priority Level: Number indicating execution importance (0 to configMAX_PRIORITIES).\n4. Task ID / Name: For debugging and identification (e.g. 'Task_GPS').\n5. Stack Base & Limit: Used to detect stack overflow bugs.\n6. Event Pointers: Links to queues or semaphores the task is waiting on.\n7. List Item Links: Pointers allowing the kernel to place the TCB in Ready or Blocked linked lists.",
            stepByStepProcess = listOf(
                "Timer interrupt occurs; scheduler decides to switch from Task 1 to Task 2.",
                "CPU pushes active registers (R0-R15) onto Task 1's stack.",
                "Task 1's updated Stack Pointer is written into Task 1's TCB.",
                "Scheduler fetches Task 2's TCB and retrieves Task 2's Stack Pointer.",
                "CPU pops Task 2's registers from its stack; CPU jumps to Task 2's Program Counter."
            ),
            diagramType = DiagramType.PROCESS_LIFECYCLE,
            diagramExplanation = "TCB stores: Stack Pointer, Task Priority, Task State, and Linked-List pointers.",
            realWorldExamples = listOf(
                "Every task in FreeRTOS, Zephyr, and RT-Thread has a dedicated TCB allocated in SRAM upon creation."
            ),
            advantages = listOf(
                "Enables seamless preemption and deterministic context restoration in less than 5 microseconds."
            ),
            disadvantages = listOf(
                "Consumes SRAM (approx 64 to 128 bytes per TCB plus the task's stack size)."
            ),
            applications = listOf(
                "All preemptive multitasking embedded operating systems"
            ),
            conclusion = "The TCB is the passport of an RTOS task; without it, multitasking would be impossible because the CPU would lose its place.",
            examPoints = listOf(
                "What is stored in a TCB?: Stack pointer, Task state, Priority, Task ID, and pointers to list nodes.",
                "What is Context Switching?: Saving the current task's CPU registers into its stack/TCB and restoring another task's registers.",
                "Where is the TCB stored?: In RAM (allocated either dynamically or statically)."
            ),
            estimatedReadMinutes = 5
        ),

        Topic(
            id = "m2_lifecycle",
            moduleId = "mod2",
            title = "Process Life Cycle",
            shortSummary = "The four fundamental states: Ready, Running, Blocked (Waiting), and Terminated/Suspended.",
            introduction = "A task in an RTOS does not simply run forever. Depending on what is happening in the system, it continuously moves through different life cycle states.",
            simpleDefinition = "The Process Life Cycle describes the different states a task can be in (Running, Ready, Blocked, Suspended) and the rules for transitioning between them.",
            detailedExplanation = "The 4 Core Task States:\n1. RUNNING: The task currently has control of the CPU and is executing instructions. (On a single-core MCU, only ONE task can be Running at any instant).\n2. READY: The task is fully prepared to execute, but a task of equal or higher priority is currently using the CPU.\n3. BLOCKED (WAITING): The task is waiting for an external event, time delay (`vTaskDelay`), or synchronization signal (semaphore/queue). It consumes 0% CPU cycles while blocked!\n4. SUSPENDED / TERMINATED: Explicitly paused by user command (`vTaskSuspend`) or finished execution.",
            stepByStepProcess = listOf(
                "Task is created -> enters READY state.",
                "Scheduler picks highest priority ready task -> moves to RUNNING state.",
                "Running task calls delay or waits for sensor data -> moves to BLOCKED state.",
                "Sensor data arrives via interrupt -> task transitions back to READY state.",
                "Scheduler selects it again -> returns to RUNNING state."
            ),
            diagramType = DiagramType.PROCESS_LIFECYCLE,
            diagramExplanation = "Interactive State Machine: Ready <-> Running -> Blocked -> Ready.",
            realWorldExamples = listOf(
                "A keypad scanner task stays in the BLOCKED state 99% of the time, consuming zero CPU. The instant a user presses a key, an interrupt moves it to READY."
            ),
            advantages = listOf(
                "Blocked tasks consume zero CPU power, allowing lower-priority tasks or CPU sleep modes to conserve energy."
            ),
            disadvantages = listOf(
                "If a developer forgets to put a task into the Blocked state (e.g. using a busy-wait loop), low-priority tasks will starve."
            ),
            applications = listOf(
                "Power-sensitive battery-operated IoT devices, event-driven embedded firmware"
            ),
            conclusion = "Good RTOS design ensures tasks spend most of their time in the Blocked state waiting for real events, maximizing battery life and system responsiveness.",
            examPoints = listOf(
                "Name the 4 task states: Running, Ready, Blocked (Waiting), Suspended/Terminated.",
                "What causes a transition from Running to Blocked?: Calling a time delay or waiting on an unready semaphore/queue.",
                "How many tasks can be in the Running state on a single-core CPU?: Exactly one."
            ),
            estimatedReadMinutes = 6
        ),

        Topic(
            id = "m2_scheduling",
            moduleId = "mod2",
            title = "Task Scheduling & Scheduling Algorithms",
            shortSummary = "Preemptive, Cooperative, Round Robin, Priority-based, and Earliest Deadline First (EDF).",
            introduction = "The scheduler is the RTOS brain algorithm that decides: 'Among all the tasks that are Ready right now, which one gets the CPU next?'",
            simpleDefinition = "Task Scheduling is the algorithm used by the RTOS kernel to assign CPU execution time to ready tasks according to set rules and priorities.",
            detailedExplanation = "Major Scheduling Approaches:\n\n1. First-Come, First-Served (FCFS / FIFO): Tasks run in order of arrival. Simple but terrible for real-time systems because a long task blocks urgent tasks.\n\n2. Round Robin (Time Slicing): Every ready task gets an equal slice of CPU time (e.g. 5ms). When time expires, CPU gives the next task a turn. Great for fair sharing among equal priority tasks.\n\n3. Fixed-Priority Preemptive Scheduling (The Industry Standard): Each task has a assigned priority number. The highest-priority ready task ALWAYS runs immediately. If a higher-priority task wakes up, it preempts (interrupts) the lower task.\n\n4. Earliest Deadline First (EDF): Dynamic priority algorithm where whichever task has the closest upcoming deadline is granted highest priority.",
            stepByStepProcess = listOf(
                "SysTick timer fires interrupt or an event wakes a task.",
                "Scheduler inspects the ready task lists sorted by priority.",
                "Finds the highest priority task ready to run.",
                "If different from current running task, performs context switch immediately."
            ),
            diagramType = DiagramType.SCHEDULING_GANTT,
            diagramExplanation = "Gantt Chart illustrates Preemptive Priority scheduling kicking out lower priority tasks.",
            realWorldExamples = listOf(
                "In a drone: Flight stabilization task (Priority 5, 2ms cycle) preempts Camera streaming task (Priority 1) whenever sensor telemetry updates."
            ),
            advantages = listOf(
                "Preemptive priority ensures life-critical tasks respond within microseconds.",
                "Round Robin ensures fair time-sharing for user interface tasks."
            ),
            disadvantages = listOf(
                "Task Starvation: If high-priority tasks run continuously, lower-priority tasks will never get CPU time."
            ),
            applications = listOf(
                "Automotive cruise control, industrial motor drives, satellite attitude controllers"
            ),
            conclusion = "Preemptive priority scheduling forms the bedrock of embedded real-time systems, guaranteeing that critical events always take precedence.",
            examPoints = listOf(
                "What is Preemptive Scheduling?: Kernel forces current task to yield immediately when a higher priority task becomes ready.",
                "Explain Round Robin: Each ready task gets an equal time quantum (slice).",
                "What is Task Starvation?: Low-priority tasks never executing because higher-priority tasks occupy 100% of CPU time."
            ),
            estimatedReadMinutes = 7
        ),

        Topic(
            id = "m2_mutex_semaphore",
            moduleId = "mod2",
            title = "Mutex and Semaphores",
            shortSummary = "Binary semaphores, counting semaphores, mutex ownership, and priority inversion.",
            introduction = "What happens if Task A and Task B both try to send data over the exact same serial UART port at the exact same microsecond? The output will be garbled nonsense! We need a locking mechanism.",
            simpleDefinition = "A Mutex (Mutual Exclusion) is a single key used to lock a shared resource so only one task can use it at a time. A Semaphore is a signaling counter used for task synchronization.",
            detailedExplanation = "• Mutex (Mutual Exclusion):\n- Has OWNERSHIP: Only the task that locked the mutex can unlock it.\n- Includes Priority Inheritance to prevent priority inversion bugs.\n- Used strictly for protecting shared resources (shared memory, I2C bus, display).\n\n• Binary Semaphore (0 or 1):\n- No ownership! Used as an event signaling flag (e.g. ISR signals task: 'Data arrived!').\n\n• Counting Semaphore (Value > 1):\n- Tracks available units of multiple identical resources (e.g. 5 free memory buffers in a pool).\n\n• Priority Inversion & Priority Inheritance:\nWhen low-priority Task L holds a mutex needed by high-priority Task H, but medium-priority Task M preempts Task L! To solve this, Priority Inheritance temporarily boosts Task L's priority to match Task H until it releases the mutex.",
            stepByStepProcess = listOf(
                "Task 1 requests mutex (`xSemaphoreTake`). Key available -> Task 1 locks mutex.",
                "Task 1 enters Critical Section and writes data to shared memory.",
                "Task 2 requests the same mutex -> mutex is locked, so Task 2 enters BLOCKED state.",
                "Task 1 finishes and releases mutex (`xSemaphoreGive`).",
                "Task 2 automatically unblocks, acquires key, and proceeds safely."
            ),
            diagramType = DiagramType.MUTEX_SEMAPHORE,
            diagramExplanation = "Mutex = Resource Lock with ownership. Semaphore = Signaling mechanism without ownership.",
            realWorldExamples = listOf(
                "Mutex: Two tasks writing to an SPI LCD screen take turns via LCD_Mutex.",
                "Binary Semaphore: ADC conversion complete interrupt signals `xSemaphoreGiveFromISR` to wake up DSP filtering task.",
                "Mars Pathfinder Rover Bug (1997): Famous priority inversion incident fixed remotely by enabling priority inheritance."
            ),
            advantages = listOf(
                "Prevents race conditions and memory corruption in multi-threaded firmware.",
                "Priority inheritance prevents catastrophic priority inversion."
            ),
            disadvantages = listOf(
                "Risk of Deadlocks if tasks acquire multiple locks in opposite orders."
            ),
            applications = listOf(
                "Shared communication buses (I2C/SPI/UART), shared global buffers, event signaling"
            ),
            conclusion = "Use a Mutex when locking a shared resource (Mutual Exclusion). Use a Semaphore when signaling that an event occurred (Synchronization).",
            examPoints = listOf(
                "Difference between Mutex and Semaphore: Mutex has ownership and priority inheritance; Semaphore is a signaling mechanism.",
                "What is Priority Inversion?: A medium-priority task indirectly delays a high-priority task waiting on a low-priority task.",
                "How is Priority Inversion solved?: By Priority Inheritance (temporarily raising low-priority task to high priority)."
            ),
            estimatedReadMinutes = 7
        ),

        Topic(
            id = "m2_deadlocks",
            moduleId = "mod2",
            title = "Deadlocks and Prevention",
            shortSummary = "The deadly embrace, Coffman conditions, and circular wait prevention.",
            introduction = "Imagine two trains on the same track facing each other, both refusing to back up. Neither train can ever move forward. In RTOS, this situation is called a Deadlock.",
            simpleDefinition = "A Deadlock is a permanent freeze condition where two or more tasks are stuck waiting indefinitely for resources held by each other.",
            detailedExplanation = "Classic Deadlock Scenario (Deadly Embrace):\n• Task 1 holds Mutex A and wants Mutex B.\n• Task 2 holds Mutex B and wants Mutex A.\nNeither task can proceed; both wait forever, causing the embedded system to freeze!\n\nCoffman Conditions (All 4 must hold for deadlock to occur):\n1. Mutual Exclusion: Resources cannot be shared simultaneously.\n2. Hold and Wait: A task holds one resource while waiting for another.\n3. No Preemption: Resources cannot be forcibly taken away from a task.\n4. Circular Wait: A closed loop chain of tasks waiting for each other.\n\nDeadlock Prevention Techniques:\n• Lock Ordering: Always acquire locks in the exact same global order (e.g., always acquire Mutex A before Mutex B).\n• Timeouts: Never wait indefinitely (`portMAX_DELAY`). Always specify a timeout (e.g. `xSemaphoreTake(mutex, 50ms)`). If timeout expires, release existing locks and retry.",
            stepByStepProcess = listOf(
                "Task 1 locks Resource A.",
                "Context switch to Task 2; Task 2 locks Resource B.",
                "Task 2 tries to lock Resource A -> blocks.",
                "Context switch to Task 1; Task 1 tries to lock Resource B -> blocks.",
                "Deadlock achieved: both tasks blocked forever."
            ),
            diagramType = DiagramType.MUTEX_SEMAPHORE,
            diagramExplanation = "Circular Wait: Task 1 -> holds A, wants B. Task 2 -> holds B, wants A.",
            realWorldExamples = listOf(
                "Industrial robotic arm freezing mid-motion because motor controller task and camera vision task deadlocked on shared CAN bus and shared coordinate buffer."
            ),
            advantages = listOf(
                "Enforcing lock hierarchy and timeouts guarantees deadlock-free firmware."
            ),
            disadvantages = listOf(
                "Deadlocks can be intermittent and difficult to reproduce in lab testing."
            ),
            applications = listOf(
                "Complex multi-threaded firmware, automotive ECUs, flight computers"
            ),
            conclusion = "Never allow indefinite lock blocking in production embedded firmware; always provide timeout bailouts and enforce strict lock acquisition hierarchies.",
            examPoints = listOf(
                "Define Deadlock: A condition where two or more tasks cannot proceed because each holds a resource the other needs.",
                "Name the 4 Coffman conditions: Mutual Exclusion, Hold & Wait, No Preemption, Circular Wait.",
                "How to prevent deadlocks?: Use lock ordering hierarchy and always use non-infinite lock timeouts."
            ),
            estimatedReadMinutes = 6
        ),

        Topic(
            id = "m2_ipc",
            moduleId = "mod2",
            title = "Inter-Task Communication (IPC)",
            shortSummary = "Message queues, mailboxes, pipes, and shared memory buffers.",
            introduction = "In a multi-tasking application, tasks must share data safely. If Task 1 reads GPS latitude/longitude and Task 2 displays it on screen, how does Task 1 send the coordinates to Task 2 without memory corruption?",
            simpleDefinition = "Inter-Task Communication (IPC) is the mechanism provided by the RTOS kernel that allows independent tasks to safely exchange data and messages.",
            detailedExplanation = "Major RTOS IPC Mechanisms:\n\n1. Message Queues (FIFO):\n- The most popular IPC method in RTOS.\n- First-In, First-Out ring buffer storing fixed-size items.\n- Thread-safe: built-in locking and task blocking.\n- If the queue is empty, reader task blocks automatically until data is posted. If the queue is full, writer task can block.\n\n2. Mailboxes:\n- A queue with a depth of exactly 1 message.\n- Ideal for posting latest sensor readings where old readings can be overwritten.\n\n3. Pipes:\n- Unstructured stream of raw bytes (unlike discrete message structs in queues).\n\n4. Event Groups / Event Flags:\n- Bitmask where each bit represents a specific boolean event (e.g. Bit 0 = WiFi connected, Bit 1 = SD card mounted, Bit 2 = Battery low). A task can wait for ANY or ALL bits.",
            stepByStepProcess = listOf(
                "Queue created with capacity (e.g., 10 messages of struct SensorData).",
                "Task 1 fills struct and calls `xQueueSend(queue, &data, timeout)`.",
                "Data is safely copied into kernel queue buffer.",
                "Task 2 calls `xQueueReceive(queue, &buffer, timeout)`.",
                "Task 2 unblocks immediately and processes the message."
            ),
            diagramType = DiagramType.PROCESS_LIFECYCLE,
            diagramExplanation = "Producer Task -> Message Queue FIFO Buffer -> Consumer Task.",
            realWorldExamples = listOf(
                "CAN Bus Receiver ISR posts raw CAN frames into a queue; an Automotive Engine Management task reads frames from the queue and updates fuel injection."
            ),
            advantages = listOf(
                "Thread-safe by design: developers don't need manual mutexes around queue operations.",
                "Automatic blocking saves CPU cycles when no data is pending."
            ),
            disadvantages = listOf(
                "Copying large payloads by value consumes RAM and CPU time; pass pointers to buffers instead."
            ),
            applications = listOf(
                "Sensor data pipelines, network protocol stacks, UI event dispatchers"
            ),
            conclusion = "Message queues are the gold standard for clean, thread-safe communication between embedded tasks and interrupt handlers.",
            examPoints = listOf(
                "What is a Message Queue?: A thread-safe FIFO buffer used to pass structured data between tasks.",
                "Difference between Queue and Mailbox: Queue holds multiple messages; Mailbox typically holds one message.",
                "What are Event Flags?: A bitmask where individual bits represent status flags tasks can wait on."
            ),
            estimatedReadMinutes = 6
        ),

        Topic(
            id = "m2_rpc",
            moduleId = "mod2",
            title = "Remote Procedure Call (RPC)",
            shortSummary = "Executing procedures on separate processors across serial, CAN, or Ethernet links.",
            introduction = "In complex modern embedded systems (like autonomous vehicles or smart drones), a single microcontroller is not enough. You might have a high-level processor running Linux for computer vision, connected to an ARM microcontroller controlling the motors. How does code on one processor trigger a function on another?",
            simpleDefinition = "Remote Procedure Call (RPC) is a protocol that allows a program on one computer or core to execute a function on a completely different computer or core as if it were a local function call.",
            detailedExplanation = "How Embedded RPC Works:\n1. Client Stub: Code running on Processor 1 bundles the function parameters into a serialized packet (Marshalling).\n2. Communication Layer: The packet is transmitted across a physical bus (UART, SPI, CAN Bus, Ethernet, or Shared Dual-Port RAM).\n3. Server Stub: Processor 2 receives the packet, unpacks the arguments (Unmarshalling), and calls the actual local function.\n4. Response: The return value is marshalled and transmitted back to Processor 1.",
            stepByStepProcess = listOf(
                "Client program calls `SetMotorSpeed(1200)`.",
                "Client stub marshals function ID and parameter (1200) into binary payload.",
                "Payload transmitted over SPI/CAN link to secondary MCU.",
                "Server stub unmarshals packet and calls real motor PWM driver.",
                "Server sends acknowledgment/result back to client."
            ),
            diagramType = DiagramType.SOURCE_TO_HEX,
            diagramExplanation = "Client Core -> Marshalling -> Bus Transfer -> Unmarshalling -> Server Core Execution.",
            realWorldExamples = listOf(
                "Dual-core microcontrollers (e.g., STM32H7 with Cortex-M7 + Cortex-M4) using OpenAMP RPC to pass commands between cores.",
                "Raspberry Pi calling sensor reading functions on an Arduino over UART RPC."
            ),
            advantages = listOf(
                "Hides the complex networking and serialization protocols behind standard C function calls.",
                "Enables clean multi-core and distributed embedded architectures."
            ),
            disadvantages = listOf(
                "Network latency and serialization overhead make it slower than true local function calls.",
                "Need to handle communication timeout and connection loss errors."
            ),
            applications = listOf(
                "Multi-core SoC chips, industrial distributed fieldbus systems, automotive gateways"
            ),
            conclusion = "RPC abstracts physical communication cables, enabling modular distributed embedded computing across multiple specialized chips.",
            examPoints = listOf(
                "Define RPC: Remote Procedure Call allows a program to execute a subroutine on another processor.",
                "What is Marshalling and Unmarshalling?: Marshalling is packing function arguments into a network packet; Unmarshalling is unpacking them on the receiving side.",
                "Name a protocol used for embedded RPC: OpenAMP, gRPC over Ethernet, or custom UART packet stubs."
            ),
            estimatedReadMinutes = 5
        ),

        Topic(
            id = "m2_kernels_arch",
            moduleId = "mod2",
            title = "Monolithic and Microkernels",
            shortSummary = "All-in-kernel monolithic designs vs minimal-core microkernel architectures.",
            introduction = "How much software should live inside the privileged kernel space? Should device drivers, network stacks, and file systems live inside the core kernel, or run as isolated user processes?",
            simpleDefinition = "A Monolithic Kernel runs all OS services (scheduling, drivers, filesystem, network) together in one memory space. A Microkernel keeps only the absolute bare minimum (scheduling, IPC, memory) in kernel space and runs drivers as separate tasks.",
            detailedExplanation = "1. Monolithic Kernel Architecture:\n- Common in general desktop OSes (Linux, Windows).\n- All drivers, filesystems, and network stacks share the same memory space as the scheduler.\n- Advantage: Extremely fast because services communicate via direct memory function calls.\n- Disadvantage: If a single USB device driver has a bug, the entire system crashes.\n\n2. Microkernel Architecture:\n- Common in safety-critical RTOSes (QNX Neutrino, seL4, PikeOS).\n- Kernel space contains ONLY: Task scheduling, Inter-process communication (IPC), and basic memory mapping.\n- Drivers and filesystems run as isolated user tasks. If the audio driver crashes, the kernel restarts it without crashing the flight computer!\n- Disadvantage: Frequent IPC message-passing incurs slight performance overhead.",
            stepByStepProcess = listOf(
                "Monolithic: User App calls file read -> direct kernel function execution.",
                "Microkernel: User App calls file read -> IPC message sent to isolated File System Task -> File System Task sends IPC to Disk Driver Task."
            ),
            diagramType = DiagramType.MCU_VS_MPU,
            diagramExplanation = "Monolithic: Huge single blob. Microkernel: Tiny core surrounded by isolated service processes.",
            realWorldExamples = listOf(
                "Microkernel example: QNX Neutrino in car infotainment and medical systems; seL4 in military defense drones.",
                "Monolithic example: Embedded Linux running on high-end gateway routers."
            ),
            advantages = listOf(
                "Microkernels provide unmatched fault isolation, crash resilience, and formal mathematical verification.",
                "Monolithic kernels offer maximum raw throughput and simple driver development."
            ),
            disadvantages = listOf(
                "Microkernels suffer slight latency penalties due to frequent IPC context switches."
            ),
            applications = listOf(
                "Aerospace flight computers, medical life-support devices, automotive safety domain controllers"
            ),
            conclusion = "Where absolute human safety and crash immunity are required, microkernels lead the industry. Where throughput and rich hardware support are needed, monolithic designs dominate.",
            examPoints = listOf(
                "Difference between Monolithic and Microkernel: Monolithic runs all services in kernel space; Microkernel only keeps scheduler/IPC in kernel and runs drivers in user space.",
                "Why are Microkernels safer?: A crashed driver does not bring down the entire operating system.",
                "Give an example of a Microkernel RTOS: QNX Neutrino, seL4."
            ),
            estimatedReadMinutes = 6
        ),

        // MODULE 3: EMBEDDED FIRMWARE DESIGN
        Topic(
            id = "m3_intro_firmware",
            moduleId = "mod3",
            title = "Introduction to Firmware",
            shortSummary = "What is firmware, flash memory residency, and hardware initialization.",
            introduction = "Firmware is the permanent software programmed into read-only memory on an electronic device. It is neither purely hardware nor standard user software—it sits right in between, giving life to bare silicon.",
            simpleDefinition = "Firmware is a specialized computer program permanently stored in non-volatile memory (ROM/Flash) that directly controls and operates electronic hardware.",
            detailedExplanation = "Why is it called 'Firm-ware'?\n- Hardware is hard (physical silicon, wires, cannot be changed without replacing parts).\n- Software is soft (apps, easy to install, delete, and alter on the fly).\n- Firmware is 'firm' (etched in non-volatile memory, rarely changed, but can be updated via special flashing or over-the-air firmware update mechanisms).\n\nKey Firmware Responsibilities:\n1. Power-On Self Test (POST): Testing RAM, clocks, and internal buses on startup.\n2. Hardware Initialization: Configuring PLL clock multipliers, setting GPIO input/output modes, and enabling internal pull-up resistors.\n3. Vector Table Setup: Configuring address pointers for hardware interrupt routines.\n4. Core Control Loop: Executing real-time business logic.",
            stepByStepProcess = listOf(
                "Power supply stabilizes at 3.3V / 5V.",
                "Hardware reset circuitry releases CPU Reset line.",
                "CPU loads initial Stack Pointer from Flash address 0x00000000.",
                "CPU jumps to Reset Handler address (Reset Vector).",
                "System startup code copies `.data` segment from Flash to RAM and clears `.bss`.",
                "Calls `main()` function."
            ),
            diagramType = DiagramType.SUPERLOOP_VS_RTOS,
            diagramExplanation = "Firmware Boot Pipeline: Power On -> Reset Vector -> SystemInit() -> main().",
            realWorldExamples = listOf(
                "UEFI / BIOS on your motherboard.",
                "The hex code running inside an electric toothbrush, microwave timer, or TV remote control."
            ),
            advantages = listOf(
                "Non-volatile: boots instantly upon power application without external hard drives.",
                "Guarantees that hardware devices function safely and deterministically."
            ),
            disadvantages = listOf(
                "Bricking risk: If a firmware update is interrupted (power loss mid-flash), the device may become completely unusable."
            ),
            applications = listOf(
                "All electronic hardware devices, IoT nodes, automotive ECUs, consumer appliances"
            ),
            conclusion = "Firmware is the soul of embedded systems, transforming cold silicon and copper traces into smart, functioning devices.",
            examPoints = listOf(
                "Define Firmware: Low-level software stored permanently in non-volatile memory that directly controls hardware.",
                "Where is firmware stored?: In non-volatile memory such as Flash ROM or EEPROM.",
                "What is a Reset Vector?: The specific memory address the CPU reads immediately upon powering on or resetting."
            ),
            estimatedReadMinutes = 5
        ),

        Topic(
            id = "m3_dev_process",
            moduleId = "mod3",
            title = "Firmware Development Process",
            shortSummary = "Requirements, architecture, BSP, drivers, verification, and In-System Programming (ISP).",
            introduction = "Developing embedded firmware is fundamentally different from building a web page. You are dealing with real electrical voltages, timing constraints, hardware revs, and physical safety.",
            simpleDefinition = "The Firmware Development Process is the engineering workflow of designing, coding, simulating, flashing, and testing software on target physical microcontroller hardware.",
            detailedExplanation = "Standard Embedded Development Life Cycle:\n1. Requirements & Hardware Selection: Choosing the right MCU based on pin count, Flash/RAM requirements, interfaces (CAN, I2C, SPI), and unit cost.\n2. Board Bring-up & BSP Development: Getting custom prototype circuit boards to boot, testing power rails and crystal oscillators.\n3. Low-Level Device Drivers (HAL): Writing register-level drivers for GPIO, UART, Timers, and ADCs.\n4. Middleware & Application Logic: Implementing state machines, control algorithms, or RTOS tasks.\n5. In-Circuit Debugging (SWD/JTAG): Setting breakpoints in live silicon, inspecting registers, and tracing memory.\n6. Hardware-in-the-Loop (HIL) Testing: Connecting simulated sensors to verify real-time timing deadlines.\n7. Release & Production Flashing: Generating locked production HEX files with bootloader encryption.",
            stepByStepProcess = listOf(
                "Write C code in an Integrated Development Environment (IDE) like VS Code or Keil.",
                "Build firmware binary using cross-compiler toolchain.",
                "Connect Hardware Debugger (ST-LINK, J-Link) via SWD/JTAG pins.",
                "Flash binary to MCU Flash memory and step through instructions.",
                "Analyze physical waveforms on an oscilloscope or logic analyzer."
            ),
            diagramType = DiagramType.SOURCE_TO_HEX,
            diagramExplanation = "IDE -> Compiler -> Debug Probe (JTAG/SWD) -> Target Silicon Chip -> Logic Analyzer.",
            realWorldExamples = listOf(
                "Automotive engineers using JTAG debuggers to calibrate engine fuel-injection maps on an ECU test bench."
            ),
            advantages = listOf(
                "Rigorous hardware-in-the-loop testing ensures zero field recalls.",
                "Hardware abstraction layers (HAL) make firmware reusable across PCB revisions."
            ),
            disadvantages = listOf(
                "Requires expensive lab equipment: digital oscilloscopes, protocol analyzers, and calibrated power supplies."
            ),
            applications = listOf(
                "Professional embedded product design across automotive, industrial, and consumer markets"
            ),
            conclusion = "Firmware engineering requires continuous validation on real silicon hardware, verifying electrical signals alongside software logic.",
            examPoints = listOf(
                "What is JTAG / SWD?: Standard hardware debugging interfaces used to flash code and set breakpoints in silicon.",
                "What is Board Bring-Up?: The initial phase of powering on a new custom PCB and verifying clocks, voltages, and basic processor execution.",
                "What is HAL (Hardware Abstraction Layer)?: A software layer that hides raw register addresses behind standardized C function calls."
            ),
            estimatedReadMinutes = 6
        ),

        Topic(
            id = "m3_superloop",
            moduleId = "mod3",
            title = "Super Loop-Based Approach",
            shortSummary = "Polling architecture, infinite while(1), interrupt-driven foreground/background model.",
            introduction = "The simplest and most widely used architecture for small microcontrollers is the Super Loop. Almost every beginner Arduino sketch (`setup()` and `loop()`) uses this exact design.",
            simpleDefinition = "A Super Loop is an infinite while(1) loop that executes tasks sequentially one after another, combined with hardware interrupts for urgent events (Foreground/Background Model).",
            detailedExplanation = "Structure of a Super Loop:\n```c\nvoid main(void) {\n    System_Init(); // Setup clocks, pins, UART\n    while (1) {    // The Super Loop (Background)\n        Read_Sensors();\n        Process_Data();\n        Update_Display();\n        Transmit_Telemetry();\n    }\n}\n```\n\nForeground / Background Concept:\n• Background: The infinite `while(1)` loop handling non-critical tasks (display updating, sensor calculations).\n• Foreground: Hardware Interrupt Service Routines (ISRs) that instantly break into the loop when time-critical hardware events occur (button pressed, timer tick, serial byte received).",
            stepByStepProcess = listOf(
                "Processor completes one-time hardware initialization.",
                "Enters while(1) infinite loop.",
                "Executes Task 1, then Task 2, then Task 3 sequentially.",
                "If an interrupt occurs, CPU halts loop, jumps to ISR immediately, and returns to loop upon completion."
            ),
            diagramType = DiagramType.SUPERLOOP_VS_RTOS,
            diagramExplanation = "Super Loop: Sequential execution loop interrupted by asynchronous hardware ISRs.",
            realWorldExamples = listOf(
                "Digital Weighing Scale: Continuous loop reads load cell, computes kilograms, and refreshes 7-segment display.",
                "TV Remote Control: Sleeps in low power until a key interrupt fires, sends infrared burst, returns to sleep."
            ),
            advantages = listOf(
                "Zero RTOS RAM overhead (no task control blocks, no multiple stacks).",
                "Extremely simple to understand, write, and debug.",
                "No context switching CPU cycles wasted."
            ),
            disadvantages = listOf(
                "Poor scalability: If `Process_Data()` takes 50 milliseconds, `Update_Display()` is delayed by 50ms (Jitter).",
                "Blocking code (`delay_ms(500)`) freezes the entire system background loop."
            ),
            applications = listOf(
                "Low-cost 8-bit microcontrollers (PIC, AVR, 8051), simple consumer electronics, battery sensors"
            ),
            conclusion = "For small, straightforward projects with few timing constraints, the Super Loop is king. For complex multi-interface systems, an RTOS becomes necessary.",
            examPoints = listOf(
                "Explain the Foreground/Background model: Background = infinite while(1) loop; Foreground = Interrupt Service Routines (ISRs).",
                "What is the biggest drawback of a Super Loop?: A delay or long calculation in one function delays every subsequent function.",
                "Why is Super Loop memory efficient?: The entire program shares a single stack in RAM."
            ),
            estimatedReadMinutes = 6
        ),

        Topic(
            id = "m3_rtos_firmware",
            moduleId = "mod3",
            title = "RTOS-Based Firmware Design",
            shortSummary = "Decoupling complex timing, preemptive multi-tasking, and priority assignment.",
            introduction = "When an embedded product grows complex—handling an LCD screen, WiFi networking, motor control, and audio alerts—a Super Loop turns into unmaintainable 'spaghetti code'. That is when you switch to RTOS-based firmware.",
            simpleDefinition = "RTOS-Based Firmware Design is an architecture that breaks a complex system into independent, concurrent tasks scheduled preemptively according to strict priorities.",
            detailedExplanation = "Comparison: Super Loop vs RTOS Design:\n• Super Loop: Everything is sequential. If you add a slow SD card write operation, your motor controller stutters.\n• RTOS: You create a high-priority MotorTask (runs every 1ms), a medium-priority NetworkTask (runs whenever WiFi packets arrive), and a low-priority DisplayTask (runs in leftover time). The motor NEVER stutters because it preempts the display!\n\nDesigning with an RTOS:\n1. Task Decomposition: Divide firmware into logical, decoupled domains (Sensor task, Control task, Comm task, UI task).\n2. Priority Assignment: Assign higher priorities to tasks with tighter timing deadlines (Rate Monotonic Scheduling).\n3. IPC Channels: Connect tasks via Message Queues and Mutexes rather than messy global variables.",
            stepByStepProcess = listOf(
                "Identify hardware interfaces and timing deadlines.",
                "Create tasks with allocated stack sizes (`xTaskCreate`).",
                "Create synchronization semaphores and communication queues.",
                "Call `vTaskStartScheduler()` to give the kernel control of CPU.",
                "Tasks execute preemptively based on incoming events and hardware interrupts."
            ),
            diagramType = DiagramType.SUPERLOOP_VS_RTOS,
            diagramExplanation = "Super Loop vs RTOS: Sequential polling loop vs priority-preemptive concurrent task threads.",
            realWorldExamples = listOf(
                "Modern Smartwatch: High priority: PPG Heart Rate sensor sampling (100Hz). Medium priority: Bluetooth sync. Low priority: Step counter animation."
            ),
            advantages = listOf(
                "Deterministic deadline guarantees: Critical tasks run on time regardless of other software operations.",
                "Team scalability: Multiple engineers can develop independent tasks without breaking each other's timing.",
                "Power management: Kernel automatically puts the microcontroller into low-power sleep when all tasks are blocked."
            ),
            disadvantages = listOf(
                "Requires more RAM (separate stack for each task).",
                "Steeper learning curve (must understand concurrency, race conditions, and deadlocks)."
            ),
            applications = listOf(
                "Medical ventilators, automotive gateways, commercial drones, industrial IoT controllers"
            ),
            conclusion = "RTOS turns impossible timing conflicts into manageable, independent tasks, making complex real-time firmware development predictable and robust.",
            examPoints = listOf(
                "When should you switch from Super Loop to RTOS?: When the system has multiple independent timing deadlines and complex communication protocols.",
                "How does RTOS improve power consumption?: When all tasks are blocked waiting for events, the kernel idles the CPU in deep sleep.",
                "What is Rate Monotonic Scheduling (RMS)?: A rule assigning higher priority to tasks that run more frequently (shorter periods)."
            ),
            estimatedReadMinutes = 6
        ),

        Topic(
            id = "m3_interrupts",
            moduleId = "mod3",
            title = "Interrupt Handling (ISR, IVT, Latency)",
            shortSummary = "Interrupt Service Routines, Vector Tables, nesting, and minimizing latency.",
            introduction = "Imagine you are reading a book and your phone rings. You place a bookmark on your page, answer the call, hang up, and immediately resume reading where you left off. That is exactly how hardware interrupts work inside a microcontroller!",
            simpleDefinition = "An Interrupt is an urgent electrical hardware signal that temporarily halts the CPU's current program to execute a special high-priority function called an Interrupt Service Routine (ISR).",
            detailedExplanation = "Crucial Interrupt Concepts:\n1. Interrupt Vector Table (IVT): An array of memory addresses stored at the beginning of Flash memory. Each hardware peripheral (Timer 1, UART RX, Pin Change INT0) has an assigned index in the IVT pointing to its ISR.\n2. Interrupt Service Routine (ISR): The callback function that runs in response to the interrupt.\n3. Interrupt Latency: The total elapsed time from the physical electrical signal arriving at the pin until the first instruction of the ISR executes.\n4. Golden Rule of ISRs: Keep ISRs ultra-short! Never call delay functions (`delay_ms`), never do floating-point math, and never wait for slow buses inside an ISR. Read the hardware register, signal a task via a semaphore or queue, and exit immediately (Deferred Interrupt Processing).",
            stepByStepProcess = listOf(
                "External pin detects falling voltage edge (e.g. emergency stop button pressed).",
                "Interrupt Controller halts current instruction and saves CPU Program Counter and status flags to stack.",
                "CPU looks up vector address in the Interrupt Vector Table (IVT).",
                "CPU branches to and executes the Interrupt Service Routine (ISR).",
                "ISR executes, clears the interrupt hardware flag, and executes `RETI` (Return from Interrupt).",
                "CPU pops saved registers from stack and resumes previous code as if nothing happened."
            ),
            diagramType = DiagramType.INTERRUPT_TIMELINE,
            diagramExplanation = "Main Program -> Hardware Trigger -> Context Save -> ISR Execution -> Context Restore -> Resume.",
            realWorldExamples = listOf(
                "Car Airbag Accelerometer: Detects deceleration threshold, triggers hardware interrupt pin, ISR instantly deploys inflator.",
                "Rotary Optical Encoder on motor: Every pulse triggers a pin interrupt to count wheel rotations with zero missed counts."
            ),
            advantages = listOf(
                "Eliminates wasteful CPU polling loops (CPU doesn't have to keep asking 'Did you click yet?').",
                "Provides microsecond-level response times to critical physical events."
            ),
            disadvantages = listOf(
                "Shared variables between ISRs and main code must be declared `volatile` to prevent compiler register caching bugs.",
                "Nested interrupts can cause stack overflows if not carefully sized."
            ),
            applications = listOf(
                "Emergency stop buttons, communication packet reception (UART/CAN), encoder counting, precision timer ticks"
            ),
            conclusion = "Interrupts provide the rapid reflexes of an embedded system, allowing microcontrollers to respond instantly to the physical world.",
            examPoints = listOf(
                "What is an ISR?: Interrupt Service Routine—a function executed automatically when a hardware interrupt triggers.",
                "What is the Interrupt Vector Table (IVT)?: A fixed table in memory containing the memory addresses of all ISRs.",
                "Why must shared variables in an ISR be declared 'volatile'?: It tells the compiler the variable can change unexpectedly outside normal program flow, preventing caching in CPU registers.",
                "What is the Golden Rule of ISRs?: Keep them as short and fast as possible."
            ),
            estimatedReadMinutes = 6
        ),

        Topic(
            id = "m3_timers",
            moduleId = "mod3",
            title = "Timer Management",
            shortSummary = "Hardware timers, prescalers, counter registers, PWM generation, and input capture.",
            introduction = "A computer processor without a timer is like a musician without a metronome. It might play notes quickly, but it has no sense of rhythm or elapsed seconds. Timers give microcontrollers the ability to measure and control time with nanosecond precision.",
            simpleDefinition = "A Hardware Timer is an on-chip digital counter peripheral that increments or decrements at a clock frequency to measure time intervals, trigger periodic interrupts, or generate PWM signals.",
            detailedExplanation = "Key Timer Components:\n1. Prescaler: A digital clock divider. If your MCU clock is 16 MHz, a prescaler of 64 divides the clock so the timer increments at 250 kHz (every 4 microseconds).\n2. Counter Register (TCNT / CNT): The digital register that counts up (e.g. 0 to 65535 on a 16-bit timer).\n3. Compare Match Register (OCR / CCR): A register holding a target threshold. When the counter reaches this number, it can fire an interrupt or toggle an output pin automatically.\n\nTimer Operating Modes:\n• Periodic Tick: Fires an ISR at exact fixed intervals (e.g., 1ms system heartbeat).\n• Pulse Width Modulation (PWM): Rapidly switches a digital pin on and off to simulate variable analog voltage (dimming LEDs, controlling DC motor speed, positioning servo motors).\n• Input Capture: Measures the exact microsecond duration of an incoming pulse (e.g. reading ultrasonic distance sensor echo pulse).\n• Watchdog Timer (WDT): A safety timer that reboots the microcontroller if software locks up or enters an infinite loop.",
            stepByStepProcess = listOf(
                "System crystal oscillator feeds clock pulses into the Prescaler.",
                "Prescaler divides the clock and increments the Counter Register.",
                "Counter value continuously compares against Output Compare Register.",
                "When equal, hardware pin toggles (PWM edge) or Compare Match interrupt fires.",
                "Counter resets to 0 and cycle repeats."
            ),
            diagramType = DiagramType.INTERRUPT_TIMELINE,
            diagramExplanation = "Crystal Clock -> Prescaler -> Counter Register -> Compare Unit -> PWM Pin / Timer ISR.",
            realWorldExamples = listOf(
                "Drone Motor Speed Control: Hardware timers output 400Hz PWM pulses to Electronic Speed Controllers (ESCs).",
                "Ultrasonic Distance Sensor: Timer Input Capture measures the exact round-trip echo pulse duration in microseconds to calculate distance = (time * speed of sound) / 2."
            ),
            advantages = listOf(
                "Hardware timers run completely in parallel with the CPU, consuming 0% processor load while counting.",
                "Generates jitter-free, cycle-accurate timing signals."
            ),
            disadvantages = listOf(
                "Improper prescaler math can lead to timer overflow bugs or inaccurate baud rates."
            ),
            applications = listOf(
                "LED dimming, motor speed control, RTOS SysTick clock, ultrasonic ranging, Watchdog protection"
            ),
            conclusion = "Timers are the most versatile hardware peripheral in any microcontroller, handling everything from RTOS scheduling to motor power control.",
            examPoints = listOf(
                "What is a Prescaler?: A hardware clock divider that slows down the timer input clock frequency.",
                "What is PWM used for?: Simulating analog voltage to control LED brightness or DC motor speed.",
                "What is a Watchdog Timer (WDT)?: A safety timer that resets the microcontroller if the main firmware freezes or crashes."
            ),
            estimatedReadMinutes = 6
        ),

        Topic(
            id = "m3_realtime_apps",
            moduleId = "mod3",
            title = "Real-Time Applications",
            shortSummary = "Deep dive into Hard, Firm, and Soft real-time systems in production.",
            introduction = "Now that we have explored hardware, RTOS scheduling, firmware design, interrupts, and timers, let us study how these come together in real-world engineering applications.",
            simpleDefinition = "Real-Time Applications are embedded systems deployed in physical environments where operations must be executed within rigorous timing constraints.",
            detailedExplanation = "Comprehensive Comparison:\n\n1. Hard Real-Time System:\n- Failure Condition: Catastrophic disaster, property destruction, or human injury.\n- Tolerance: Zero deadline misses tolerated.\n- Examples:\n  • Pacemaker: Must trigger electrical pulse synchronously with cardiac rhythm.\n  • Automotive Anti-lock Braking System (ABS): Must modulate brake fluid pressure 15 times a second to prevent tire skidding on ice.\n  • Nuclear Reactor Control Rod Positioning.\n\n2. Firm Real-Time System:\n- Failure Condition: Result is totally discarded, but nobody gets hurt.\n- Tolerance: Occasional deadline misses tolerated, but late data is 100% useless.\n- Examples:\n  • Airport Air Traffic Radar: If an antenna sweep frame arrives late, it is discarded because the airplane has already moved.\n  • Industrial Automated Optical Inspection (AOI) camera on a fast conveyor belt.\n\n3. Soft Real-Time System:\n- Failure Condition: Performance degradation or annoyance, but data is still usable.\n- Tolerance: Deadlines are target goals rather than strict barriers.\n- Examples:\n  • Smart TV remote control or streaming video player buffer.\n  • Washing machine cycle display update.",
            stepByStepProcess = listOf(
                "Physical event detected by real-time sensor array.",
                "Dedicated hardware interrupt or prioritized RTOS task triggered.",
                "Deterministic control algorithm calculates response within guaranteed deadline.",
                "Actuators execute physical response before deadline expires."
            ),
            diagramType = DiagramType.SCHEDULING_GANTT,
            diagramExplanation = "Timing Deadline Timeline: Event Arrives -> Deadline Window -> Execution Completes Successfully.",
            realWorldExamples = listOf(
                "Fly-by-Wire Flight Control: Pilot pulls control stick; flight computers recalculate rudder and flap positions every 10ms to keep unstable jet fighter in the air.",
                "Automated External Defibrillator (AED): Analyzes heart ECG rhythm and administers shock within precise microsecond time window."
            ),
            advantages = listOf(
                "Saves human lives and prevents catastrophic industrial equipment destruction.",
                "Guarantees that automated physical systems behave reliably under all operating conditions."
            ),
            disadvantages = listOf(
                "Requires extensive formal verification, safety certifications (DO-178C for avionics, ISO 26262 for automotive), and redundant backup hardware."
            ),
            applications = listOf(
                "Aviation, Aerospace, Automotive, Rail Transport, Medical Robotics, Power Grids"
            ),
            conclusion = "Real-time systems form the invisible safeguard protecting human lives in transport, medicine, and critical infrastructure every single day.",
            examPoints = listOf(
                "Classify Real-Time Systems: Hard (zero deadline misses), Firm (late data useless), Soft (late data degrades experience).",
                "Give 2 examples of Hard Real-Time: Car Airbag inflator, cardiac pacemaker.",
                "What is ISO 26262?: The international functional safety standard for automotive electronics."
            ),
            estimatedReadMinutes = 6
        )
    )

    fun getTopicsForModule(moduleId: String): List<Topic> {
        return topics.filter { it.moduleId == moduleId }
    }

    fun getTopicById(id: String): Topic? {
        return topics.find { it.id == id }
    }
}
