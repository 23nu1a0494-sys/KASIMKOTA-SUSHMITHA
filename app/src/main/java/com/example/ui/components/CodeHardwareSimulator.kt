package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.SimulationType
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.NeonTeal
import com.example.ui.theme.PulseOrange
import com.example.ui.theme.SuccessGreen
import kotlinx.coroutines.delay

@Composable
fun InteractiveHardwareSimulator(simulationType: SimulationType, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("hardware_sim_${simulationType.name}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, ElectricCyan.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = "Simulated Hardware",
                    tint = ElectricCyan,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Hardware Output & Register Simulator",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            when (simulationType) {
                SimulationType.LED_BLINK -> LedBlinkSim()
                SimulationType.ADC_SENSOR -> AdcSensorSim()
                SimulationType.TEMP_MONITOR -> TempMonitorSim()
                SimulationType.FAN_CONTROL -> FanControlSim()
                SimulationType.TIMER_INTERRUPT -> TimerInterruptSim()
                SimulationType.PUSHBUTTON_ISR -> PushbuttonIsrSim()
                SimulationType.TASK_SCHEDULER -> TaskSchedulerSim()
            }
        }
    }
}

// 1. LED Blink Sim
@Composable
fun LedBlinkSim() {
    var isPowered by remember { mutableStateOf(true) }
    var blinkSpeedMs by remember { mutableLongStateOf(500L) }
    var ledState by remember { mutableStateOf(false) }

    LaunchedEffect(isPowered, blinkSpeedMs) {
        while (isPowered) {
            delay(blinkSpeedMs)
            ledState = !ledState
        }
        ledState = false
    }

    val ledColor by animateColorAsState(
        if (ledState) ElectricCyan else Color(0xFF1E293B),
        label = "ledColor"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(ledColor)
                        .border(2.dp, if (ledState) Color.White else MaterialTheme.colorScheme.outline, CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = if (ledState) "PORTB5 = HIGH (1)" else "PORTB5 = LOW (0)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = if (ledState) ElectricCyan else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text("Digital Output Pin PB5", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("MCU Run", fontSize = 11.sp, modifier = Modifier.padding(end = 6.dp))
                Switch(
                    checked = isPowered,
                    onCheckedChange = { isPowered = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = ElectricCyan),
                    modifier = Modifier.testTag("led_power_switch")
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Delay Period: ${blinkSpeedMs}ms (${(1000f / (blinkSpeedMs * 2)).format(1)} Hz)",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Slider(
            value = blinkSpeedMs.toFloat(),
            onValueChange = { blinkSpeedMs = it.toLong() },
            valueRange = 100f..1000f,
            steps = 9,
            colors = SliderDefaults.colors(thumbColor = ElectricCyan, activeTrackColor = ElectricCyan)
        )
    }
}

// 2. ADC Sensor Reading Sim
@Composable
fun AdcSensorSim() {
    var inputVoltage by remember { mutableFloatStateOf(2.5f) }
    val rawAdcValue = ((inputVoltage / 5.0f) * 1023).toInt().coerceIn(0, 1023)
    val binaryStr = Integer.toBinaryString(rawAdcValue).padStart(10, '0')

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Turn Virtual Potentiometer Knob (0.0V - 5.0V):", style = MaterialTheme.typography.bodySmall)
        Slider(
            value = inputVoltage,
            onValueChange = { inputVoltage = it },
            valueRange = 0.0f..5.0f,
            colors = SliderDefaults.colors(thumbColor = NeonTeal, activeTrackColor = NeonTeal),
            modifier = Modifier.testTag("adc_slider")
        )

        Spacer(modifier = Modifier.height(6.dp))
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
                    Text("Analog Input", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("${inputVoltage.format(2)} Volts", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = NeonTeal)
                }
            }

            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("10-Bit ADC Code", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("$rawAdcValue / 1023", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = ElectricCyan)
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "ADC Register (ADCH:ADCL): 0b$binaryStr",
            fontFamily = FontFamily.Monospace,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// 3. Temp Monitor Sim
@Composable
fun TempMonitorSim() {
    var tempC by remember { mutableFloatStateOf(32.0f) }
    val isAlert = tempC > 40.0f

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "${tempC.format(1)}°C",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    color = if (isAlert) ErrorRed else SuccessGreen
                )
                Text(
                    text = if (isAlert) "ALARM: CRITICAL OVERHEAT!" else "Status: Safe Normal Range",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = if (isAlert) ErrorRed else SuccessGreen
                )
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = if (isAlert) ErrorRed else MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = if (isAlert) Color.White else MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isAlert) "BUZZER ON" else "BUZZER OFF",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isAlert) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text("Simulate Ambient Temperature (-10°C to 70°C):", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Slider(
            value = tempC,
            onValueChange = { tempC = it },
            valueRange = -10.0f..70.0f,
            colors = SliderDefaults.colors(
                thumbColor = if (isAlert) ErrorRed else PulseOrange,
                activeTrackColor = if (isAlert) ErrorRed else PulseOrange
            ),
            modifier = Modifier.testTag("temp_slider")
        )
    }
}

// 4. Fan Control PWM Sim
@Composable
fun FanControlSim() {
    var dutyCycle by remember { mutableFloatStateOf(50f) }
    val infiniteTransition = rememberInfiniteTransition(label = "fanSpin")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (1200 / (dutyCycle / 20f).coerceAtLeast(0.1f)).toInt(),
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "fanRotation"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "PWM Duty: ${dutyCycle.toInt()}%",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = ElectricCyan
                )
                Text(
                    text = "Estimated RPM: ${(dutyCycle * 28).toInt()} RPM (OCR1A = ${(dutyCycle * 2.55f).toInt()})",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Animated Fan Blade Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .border(2.dp, ElectricCyan.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .rotate(if (dutyCycle > 0f) rotation else 0f),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.size(32.dp, 6.dp).background(ElectricCyan, RoundedCornerShape(3.dp)))
                    Box(modifier = Modifier.size(6.dp, 32.dp).background(ElectricCyan, RoundedCornerShape(3.dp)))
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text("Modulate Fan Speed via PWM Register:", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Slider(
            value = dutyCycle,
            onValueChange = { dutyCycle = it },
            valueRange = 0f..100f,
            colors = SliderDefaults.colors(thumbColor = ElectricCyan, activeTrackColor = ElectricCyan),
            modifier = Modifier.testTag("pwm_slider")
        )
    }
}

// 5. Timer Interrupt Sim
@Composable
fun TimerInterruptSim() {
    var isRunning by remember { mutableStateOf(false) }
    var millisCount by remember { mutableLongStateOf(0L) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(10)
            millisCount += 10
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "SysTick: ${millisCount} ms",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = NeonTeal
                )
                Text(
                    text = "Timer0 Compare Match ISR(TIMER0_COMPA_vect)",
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Button(
                    onClick = { isRunning = !isRunning },
                    colors = ButtonDefaults.buttonColors(containerColor = if (isRunning) PulseOrange else SuccessGreen),
                    modifier = Modifier.testTag("timer_run_btn")
                ) {
                    Text(if (isRunning) "Pause" else "Start", fontSize = 11.sp)
                }
                OutlinedButton(
                    onClick = { millisCount = 0L; isRunning = false },
                    modifier = Modifier.testTag("timer_reset_btn")
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = "Reset", modifier = Modifier.size(14.dp))
                }
            }
        }
    }
}

// 6. Pushbutton ISR Sim
@Composable
fun PushbuttonIsrSim() {
    var motorActive by remember { mutableStateOf(true) }
    var interruptFiredCount by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = if (motorActive) "Conveyor Motor: RUNNING" else "MOTOR E-STOPPED!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = if (motorActive) SuccessGreen else ErrorRed
                )
                Text("ISR Interrupt Count: $interruptFiredCount", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            Button(
                onClick = {
                    motorActive = false
                    interruptFiredCount++
                },
                colors = ButtonDefaults.buttonColors(containerColor = ErrorRed),
                modifier = Modifier.testTag("estop_btn")
            ) {
                Text("PUSH E-STOP (INT0)", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        if (!motorActive) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = { motorActive = true },
                    modifier = Modifier.testTag("reset_motor_btn")
                ) {
                    Text("Clear Emergency & Reset Motor", fontSize = 11.sp)
                }
            }
        }
    }
}

// 7. Task Scheduler Sim
@Composable
fun TaskSchedulerSim() {
    var activeTask by remember { mutableStateOf("Task 1: Sensor") }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1200)
            activeTask = if (activeTask.startsWith("Task 1")) "Task 2: Display" else "Task 1: Sensor"
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Preemptive Task Switcher (Context Switch):", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val isT1 = activeTask.startsWith("Task 1")
            Surface(
                color = if (isT1) ElectricCyan.copy(alpha = 0.25f) else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, if (isT1) ElectricCyan else Color.Transparent),
                modifier = Modifier.weight(1f)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("Task 1 (Priority 2)", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = ElectricCyan)
                    Text(if (isT1) "State: RUNNING" else "State: BLOCKED (50ms)", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Surface(
                color = if (!isT1) NeonTeal.copy(alpha = 0.25f) else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, if (!isT1) NeonTeal else Color.Transparent),
                modifier = Modifier.weight(1f)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("Task 2 (Priority 1)", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = NeonTeal)
                    Text(if (!isT1) "State: RUNNING" else "State: READY", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

private fun Float.format(decimals: Int): String = "%.${decimals}f".format(this)
