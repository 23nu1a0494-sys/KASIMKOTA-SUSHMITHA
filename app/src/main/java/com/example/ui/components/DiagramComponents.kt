package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DiagramType
import com.example.ui.theme.AmberGold
import com.example.ui.theme.CyberNavyCard
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.NeonTeal
import com.example.ui.theme.PulseOrange
import com.example.ui.theme.SuccessGreen

@Composable
fun InteractiveDiagramRouter(diagramType: DiagramType, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("diagram_card_${diagramType.name}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Visual Model",
                    tint = ElectricCyan,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Interactive Diagram & System Model",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            when (diagramType) {
                DiagramType.PROCESS_LIFECYCLE -> ProcessLifecycleDiagram()
                DiagramType.SCHEDULING_GANTT -> SchedulingGanttSimulator()
                DiagramType.MUTEX_SEMAPHORE -> MutexSemaphoreSimulator()
                DiagramType.SUPERLOOP_VS_RTOS -> SuperLoopVsRtosDiagram()
                DiagramType.SOURCE_TO_HEX -> SourceToHexPipelineDiagram()
                DiagramType.MCU_VS_MPU -> McuVsMpuDiagram()
                DiagramType.INTERRUPT_TIMELINE -> InterruptTimelineDiagram()
                DiagramType.NONE -> {
                    Text(
                        text = "Standard architectural flow applies.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

// 1. Process Lifecycle Interactive Diagram
@Composable
fun ProcessLifecycleDiagram() {
    var activeState by remember { mutableStateOf("READY") }

    val stateExplanations = mapOf(
        "READY" to "Task is placed in Ready queue, fully prepared to execute as soon as CPU becomes free.",
        "RUNNING" to "Task has control of the CPU and executes instructions. Only ONE task runs at a time on a single core.",
        "BLOCKED" to "Task called a delay (vTaskDelay) or is waiting for an event/semaphore. Consumes 0% CPU cycles!",
        "SUSPENDED" to "Task was explicitly paused by software API call (vTaskSuspend). Cannot run until vTaskResume."
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Tap any state circle to simulate kernel transition:",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LifecycleNode("READY", activeState == "READY", ElectricCyan) { activeState = "READY" }
            Icon(Icons.Default.ArrowForward, contentDescription = null, tint = MaterialTheme.colorScheme.outline, modifier = Modifier.size(16.dp))
            LifecycleNode("RUNNING", activeState == "RUNNING", NeonTeal) { activeState = "RUNNING" }
            Icon(Icons.Default.ArrowForward, contentDescription = null, tint = MaterialTheme.colorScheme.outline, modifier = Modifier.size(16.dp))
            LifecycleNode("BLOCKED", activeState == "BLOCKED", PulseOrange) { activeState = "BLOCKED" }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            LifecycleNode("SUSPENDED", activeState == "SUSPENDED", Color(0xFF9E9E9E)) { activeState = "SUSPENDED" }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Current State: $activeState",
                    fontWeight = FontWeight.Bold,
                    color = when (activeState) {
                        "READY" -> ElectricCyan
                        "RUNNING" -> NeonTeal
                        "BLOCKED" -> PulseOrange
                        else -> Color(0xFF9E9E9E)
                    },
                    fontSize = 14.sp
                )
                Text(
                    text = stateExplanations[activeState] ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun LifecycleNode(name: String, isActive: Boolean, activeColor: Color, onClick: () -> Unit) {
    val bgColor by animateColorAsState(if (isActive) activeColor.copy(alpha = 0.25f) else MaterialTheme.colorScheme.surface, label = "nodeBg")
    val borderColor by animateColorAsState(if (isActive) activeColor else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f), label = "nodeBorder")

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(70.dp, 44.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .border(if (isActive) 2.dp else 1.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable { onClick() }
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium,
            color = if (isActive) activeColor else MaterialTheme.colorScheme.onSurface
        )
    }
}

// 2. Scheduling Gantt Simulator
@Composable
fun SchedulingGanttSimulator() {
    var algorithm by remember { mutableStateOf("PRIORITY") } // "PRIORITY", "ROUND_ROBIN", "FCFS"
    var currentStep by remember { mutableIntStateOf(0) }

    val steps = when (algorithm) {
        "PRIORITY" -> listOf(
            Triple("Task 1 (Priority 3 - High)", "Running on CPU (Preempts all lower tasks)", NeonTeal),
            Triple("Task 1 Completes", "Context switch -> Next highest ready task runs", ElectricCyan),
            Triple("Task 2 (Priority 2 - Med)", "Now executing while Task 3 waits", AmberGold),
            Triple("Task 3 (Priority 1 - Low)", "Executes only after Task 1 & 2 complete", PulseOrange)
        )
        "ROUND_ROBIN" -> listOf(
            Triple("Task A (Slice 1)", "Runs for 10ms time quantum", ElectricCyan),
            Triple("Task B (Slice 1)", "SysTick timer preempts Task A; Task B gets 10ms", NeonTeal),
            Triple("Task C (Slice 1)", "Fair share: Task C gets 10ms quantum", PulseOrange),
            Triple("Task A (Slice 2)", "Cycle wraps around back to Task A", ElectricCyan)
        )
        else -> listOf(
            Triple("Task 1 Arrived at 0ms", "Executes to completion (25ms total)", ElectricCyan),
            Triple("Task 2 Waiting in Queue", "Cannot execute until Task 1 finishes!", PulseOrange),
            Triple("Task 2 Executes at 25ms", "Executes to completion (10ms total)", NeonTeal),
            Triple("Task 3 Finishes last", "Suffered long turnaround wait time", AmberGold)
        )
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            FilterChip(
                selected = algorithm == "PRIORITY",
                onClick = { algorithm = "PRIORITY"; currentStep = 0 },
                label = { Text("Preemptive Priority", fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(selectedContainerColor = ElectricCyan.copy(alpha = 0.2f))
            )
            FilterChip(
                selected = algorithm == "ROUND_ROBIN",
                onClick = { algorithm = "ROUND_ROBIN"; currentStep = 0 },
                label = { Text("Round Robin", fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(selectedContainerColor = NeonTeal.copy(alpha = 0.2f))
            )
            FilterChip(
                selected = algorithm == "FCFS",
                onClick = { algorithm = "FCFS"; currentStep = 0 },
                label = { Text("FCFS", fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(selectedContainerColor = PulseOrange.copy(alpha = 0.2f))
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Gantt Chart Visualization Bars
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
        ) {
            steps.forEachIndexed { index, item ->
                val isCurrent = index <= currentStep
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(if (isCurrent) item.third.copy(alpha = 0.8f) else Color.Transparent)
                        .border(0.5.dp, MaterialTheme.colorScheme.surface)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "T${index + 1}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isCurrent) Color.Black else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        val current = steps[currentStep]
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = "Timeline Phase ${currentStep + 1} of ${steps.size}: ${current.first}",
                    fontWeight = FontWeight.Bold,
                    color = current.third,
                    fontSize = 13.sp
                )
                Text(
                    text = current.second,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedButton(
                onClick = { currentStep = (currentStep + 1) % steps.size },
                modifier = Modifier.testTag("step_gantt_btn")
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Step Simulation", fontSize = 12.sp)
            }
        }
    }
}

// 3. Mutex vs Semaphore Simulator
@Composable
fun MutexSemaphoreSimulator() {
    var isLocked by remember { mutableStateOf(false) }
    var task1Waiting by remember { mutableStateOf(false) }
    var task2Waiting by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Shared Resource: SPI OLED Display Bus",
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = if (isLocked) ErrorRed.copy(alpha = 0.2f) else SuccessGreen.copy(alpha = 0.2f)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (isLocked) Icons.Default.Lock else Icons.Default.LockOpen,
                        contentDescription = null,
                        tint = if (isLocked) ErrorRed else SuccessGreen,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = if (isLocked) "MUTEX LOCKED" else "MUTEX AVAILABLE",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = if (isLocked) ErrorRed else SuccessGreen
                        )
                        Text(
                            text = if (isLocked) "Holder: Task 1" else "Any task can acquire",
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    isLocked = !isLocked
                    if (!isLocked) {
                        task1Waiting = false
                        task2Waiting = false
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = if (isLocked) SuccessGreen else ElectricCyan),
                modifier = Modifier.testTag("toggle_mutex_btn")
            ) {
                Text(if (isLocked) "Release" else "Lock Mutex", fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Task 1 (Telemetry)", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = ElectricCyan)
                    Text(
                        text = if (isLocked) "In Critical Section (Writing)" else "Idle / Ready",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Task 2 (UI Refresh)", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = NeonTeal)
                    Text(
                        text = if (isLocked) "BLOCKED (Waiting for lock)" else "Ready",
                        fontSize = 10.sp,
                        color = if (isLocked) PulseOrange else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

// 4. Super Loop vs RTOS Flowchart
@Composable
fun SuperLoopVsRtosDiagram() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Super Loop side
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("Super Loop", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = AmberGold)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("1. setup()", fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                    Text("2. while (1) {", fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                    Text("     ReadSensor();", fontSize = 10.sp, fontFamily = FontFamily.Monospace, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("     CalcData();", fontSize = 10.sp, fontFamily = FontFamily.Monospace, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("     DrawUI();", fontSize = 10.sp, fontFamily = FontFamily.Monospace, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("   }", fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Problem: Jitter & delays block next functions.", fontSize = 9.sp, color = ErrorRed)
                }
            }

            // RTOS side
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("RTOS Multitasking", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = NeonTeal)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("• Task 1 [Pri 3 - High]", fontSize = 10.sp, color = NeonTeal)
                    Text("  Runs every 1ms (Motor)", fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("• Task 2 [Pri 2 - Med]", fontSize = 10.sp, color = ElectricCyan)
                    Text("  Sensor reads (100ms)", fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("• Task 3 [Pri 1 - Low]", fontSize = 10.sp, color = AmberGold)
                    Text("  Screen refresh", fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Benefit: 100% deterministic deadlines.", fontSize = 9.sp, color = SuccessGreen)
                }
            }
        }
    }
}

// 5. Source to Hex Pipeline Diagram
@Composable
fun SourceToHexPipelineDiagram() {
    val stages = listOf(
        Pair("1. Preprocessor", "Expands #include, #define macros -> .i"),
        Pair("2. Compiler", "Synthesizes C logic into Assembly -> .s"),
        Pair("3. Assembler", "Converts mnemonics to machine code -> .o"),
        Pair("4. Linker", "Combines object files & resolves symbols -> .elf"),
        Pair("5. Locator / Hex", "Applies Linker Script (.ld) -> firmware.hex")
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        stages.forEachIndexed { index, (stage, desc) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .clip(CircleShape)
                        .background(ElectricCyan.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("${index + 1}", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ElectricCyan)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(stage, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Text(desc, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

// 6. MCU vs MPU Diagram
@Composable
fun McuVsMpuDiagram() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text("Microcontroller (MCU)", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = ElectricCyan)
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, ElectricCyan.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                        .padding(6.dp)
                ) {
                    Column {
                        Text("Single Silicon Die:", fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        Text("• CPU Core (ARM Cortex-M)", fontSize = 9.sp)
                        Text("• Flash Memory (32K-512K)", fontSize = 9.sp)
                        Text("• SRAM (2K-64K)", fontSize = 9.sp)
                        Text("• Timers + ADC + GPIO", fontSize = 9.sp)
                    }
                }
                Text("Cost: ~$1 - $5", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SuccessGreen, modifier = Modifier.padding(top = 4.dp))
            }
        }

        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text("Microprocessor (MPU)", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = PulseOrange)
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, PulseOrange.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                        .padding(6.dp)
                ) {
                    Column {
                        Text("Multi-Chip Motherboard:", fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        Text("• CPU (Intel / Cortex-A)", fontSize = 9.sp)
                        Text("• External DDR4/5 RAM", fontSize = 9.sp)
                        Text("• External SSD/eMMC Flash", fontSize = 9.sp)
                        Text("• Motherboard Chipset", fontSize = 9.sp)
                    }
                }
                Text("Cost: ~$50 - $300+", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = AmberGold, modifier = Modifier.padding(top = 4.dp))
            }
        }
    }
}

// 7. Interrupt Timeline Diagram
@Composable
fun InterruptTimelineDiagram() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Interrupt Latency & Execution Timeline:", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Box(
                modifier = Modifier
                    .weight(3f)
                    .background(ElectricCyan.copy(alpha = 0.4f))
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Main Program", fontSize = 9.sp, fontWeight = FontWeight.Bold)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(ErrorRed.copy(alpha = 0.8f))
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Trigger", fontSize = 8.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
            Box(
                modifier = Modifier
                    .weight(2f)
                    .background(AmberGold.copy(alpha = 0.7f))
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Latency", fontSize = 8.sp, fontWeight = FontWeight.Bold)
            }
            Box(
                modifier = Modifier
                    .weight(4f)
                    .background(NeonTeal.copy(alpha = 0.7f))
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("ISR Running", fontSize = 9.sp, fontWeight = FontWeight.Bold)
            }
            Box(
                modifier = Modifier
                    .weight(3f)
                    .background(ElectricCyan.copy(alpha = 0.4f))
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Resumed", fontSize = 9.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            "Latency = Time between hardware edge assertion and instruction 1 of ISR.",
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
