package com.example.data.repository

import com.example.data.model.CodeExample
import com.example.data.model.CodeLineExplanation
import com.example.data.model.SimulationType

object CodeData {

    val examples: List<CodeExample> = listOf(
        CodeExample(
            id = "code_led",
            title = "1. LED Blinking with Direct Register Access",
            subtitle = "GPIO Configuration, DDR, PORT, and Bit Manipulation",
            problemStatement = "Configure Port B Pin 5 as an output pin and toggle an LED every 500 milliseconds using direct microcontroller hardware register bitwise operations.",
            code = """
#include <avr/io.h>
#include <util/delay.h>

#define LED_PIN (1 << 5)  // PB5 is Pin 13 on Arduino Uno

int main(void) {
    // 1. Set PB5 as OUTPUT in Data Direction Register
    DDRB |= LED_PIN;

    while (1) {
        // 2. Toggle PB5 using bitwise XOR operator (^)
        PORTB ^= LED_PIN;
        
        // 3. Busy-wait delay for 500 milliseconds
        _delay_ms(500);
    }

    return 0;
}
            """.trimIndent(),
            lineExplanations = listOf(
                CodeLineExplanation("#define LED_PIN (1 << 5)", "Bit-shifts 1 left by 5 positions (binary 00100000) to target Pin 5 of Port B."),
                CodeLineExplanation("DDRB |= LED_PIN;", "Data Direction Register B: Setting bit 5 to '1' configures PB5 as an OUTPUT pin."),
                CodeLineExplanation("PORTB ^= LED_PIN;", "Bitwise XOR operator: If PB5 is HIGH, it becomes LOW; if LOW, it becomes HIGH."),
                CodeLineExplanation("_delay_ms(500);", "Hardware calibrated busy-wait delay holding the pin state for half a second.")
            ),
            expectedOutput = "LED connected to PB5 illuminates for 500ms, turns OFF for 500ms, and blinks indefinitely at 1 Hz frequency.",
            realWorldApplication = "System heartbeat indicators, automotive turn blinkers, power status LEDs on medical appliances.",
            simulationType = SimulationType.LED_BLINK
        ),

        CodeExample(
            id = "code_sensor",
            title = "2. Sensor Reading via Analog-to-Digital Converter (ADC)",
            subtitle = "Analog Channel Selection, ADC Clock Prescaler, and 10-bit Reading",
            problemStatement = "Read an analog voltage from channel ADC0 (0V to 5V) using the on-chip 10-bit successive approximation Analog-to-Digital Converter.",
            code = """
#include <avr/io.h>

void ADC_Init(void) {
    // Select Vref = AVcc with external capacitor at AREF pin
    ADMUX = (1 << REFS0);
    
    // Enable ADC and set prescaler to 128 (16MHz / 128 = 125kHz ADC clock)
    ADCSRA = (1 << ADEN) | (1 << ADPS2) | (1 << ADPS1) | (1 << ADPS0);
}

uint16_t ADC_Read(uint8_t channel) {
    // Clear old channel bits and set target channel (0-7)
    ADMUX = (ADMUX & 0xF0) | (channel & 0x0F);
    
    // Start conversion by setting ADSC bit
    ADCSRA |= (1 << ADSC);
    
    // Wait for conversion to complete (ADSC drops to 0)
    while (ADCSRA & (1 << ADSC));
    
    // Return 10-bit result combining ADCL and ADCH
    return ADC;
}
            """.trimIndent(),
            lineExplanations = listOf(
                CodeLineExplanation("ADMUX = (1 << REFS0);", "Configures the reference voltage to AVcc (5.0V), providing the full 0V-5V dynamic scale."),
                CodeLineExplanation("ADCSRA |= (1 << ADEN);", "ADC Enable bit: powers up the analog comparator and successive-approximation registers."),
                CodeLineExplanation("ADCSRA |= (1 << ADSC);", "ADC Start Conversion: Begins sampling the analog voltage on the selected pin."),
                CodeLineExplanation("while (ADCSRA & (1 << ADSC));", "Hardware polling loop: CPU waits until the hardware hardware conversion flag clears.")
            ),
            expectedOutput = "Returns an integer between 0 (for 0.0V ground) and 1023 (for 5.0V maximum supply voltage).",
            realWorldApplication = "Potentiometer volume controls, battery level voltage monitors, joystick gaming controllers.",
            simulationType = SimulationType.ADC_SENSOR
        ),

        CodeExample(
            id = "code_temp",
            title = "3. Temperature Monitoring and Alert System",
            subtitle = "Sensor Calculation, Threshold Comparison, and Alarm Trigger",
            problemStatement = "Sample an LM35 precision temperature sensor (10mV per degree Celsius) and sound an alarm if the temperature exceeds 40°C.",
            code = """
#include <avr/io.h>
#define ALARM_PIN (1 << 4) // PD4

void Check_Temperature(void) {
    uint16_t raw_adc = ADC_Read(0);
    
    // Voltage in millivolts = (raw_adc * 5000) / 1024
    float voltage_mv = (raw_adc * 5000.0f) / 1024.0f;
    
    // LM35 delivers 10mV per 1°C
    float temperature_c = voltage_mv / 10.0f;
    
    if (temperature_c > 40.0f) {
        PORTD |= ALARM_PIN;   // Turn Alarm Buzzer ON
    } else {
        PORTD &= ~ALARM_PIN;  // Turn Alarm Buzzer OFF
    }
}
            """.trimIndent(),
            lineExplanations = listOf(
                CodeLineExplanation("float voltage_mv = (raw_adc * 5000.0f) / 1024.0f;", "Converts raw 10-bit integer (0-1023) to millivolts (0-5000mV)."),
                CodeLineExplanation("float temperature_c = voltage_mv / 10.0f;", "LM35 linear formula: Every 10 millivolts represents 1.0 degree Celsius."),
                CodeLineExplanation("if (temperature_c > 40.0f)", "Compares current reading against the safety critical threshold."),
                CodeLineExplanation("PORTD |= ALARM_PIN;", "Sets PD4 HIGH to energize the alarm buzzer relay.")
            ),
            expectedOutput = "Continuous temperature readout. When temperature exceeds 40°C, the alarm buzzer activates immediately.",
            realWorldApplication = "Incubators for newborns, lithium-ion battery pack thermal management, server room climate alerts.",
            simulationType = SimulationType.TEMP_MONITOR
        ),

        CodeExample(
            id = "code_fan",
            title = "4. Automatic Fan Speed Control via PWM",
            subtitle = "Timer1 8-bit Fast PWM, OCR1A, and Duty Cycle Modulation",
            problemStatement = "Generate a Pulse Width Modulation (PWM) signal on Pin OC1A to smoothly modulate cooling fan speed between 0% and 100% duty cycle.",
            code = """
#include <avr/io.h>

void PWM_Fan_Init(void) {
    // Set PB1 (OC1A) as OUTPUT
    DDRB |= (1 << 1);
    
    // Fast PWM 8-bit mode (TOP = 255), Non-inverting mode on OC1A
    TCCR1A = (1 << COM1A1) | (1 << WGM10);
    // Prescaler = 64
    TCCR1B = (1 << WGM12) | (1 << CS11) | (1 << CS10);
}

void Set_Fan_Speed(uint8_t duty_cycle_percent) {
    // Map 0-100% to 0-255 Output Compare Register
    OCR1A = (duty_cycle_percent * 255) / 100;
}
            """.trimIndent(),
            lineExplanations = listOf(
                CodeLineExplanation("TCCR1A = (1 << COM1A1) | (1 << WGM10);", "Configures Timer 1 in Fast PWM mode with non-inverting compare output."),
                CodeLineExplanation("TCCR1B = (1 << WGM12) | ...", "Selects clock prescaler 64 to deliver a smooth ~976 Hz PWM switching frequency."),
                CodeLineExplanation("OCR1A = (duty_cycle_percent * 255) / 100;", "Loads the compare threshold: High voltage output remains ON until counter reaches OCR1A.")
            ),
            expectedOutput = "Modulates DC motor fan speed seamlessly from silent slow spin (20%) to maximum RPM airflow (100%).",
            realWorldApplication = "Computer CPU coolers, electric vehicle cabin climate blower fans, drone propeller ESCs.",
            simulationType = SimulationType.FAN_CONTROL
        ),

        CodeExample(
            id = "code_timer",
            title = "5. Periodic Timer Interrupt (1 Millisecond Heartbeat)",
            subtitle = "Clear Timer on Compare Match (CTC Mode) & ISR(TIMER0_COMPA_vect)",
            problemStatement = "Configure 8-bit Timer0 in CTC mode to trigger a hardware interrupt every exactly 1.0 millisecond for system timekeeping.",
            code = """
#include <avr/io.h>
#include <avr/interrupt.h>

volatile uint32_t system_millis = 0;

void Timer0_CTC_Init(void) {
    // 1. Configure CTC mode (Clear Timer on Compare match)
    TCCR0A = (1 << WGM01);
    
    // 2. Target compare count for 1ms: (16MHz / 64 prescaler / 1000Hz) - 1 = 249
    OCR0A = 249;
    
    // 3. Enable Timer Compare Match A Interrupt
    TIMSK0 = (1 << OCIE0A);
    
    // 4. Start timer with prescaler 64
    TCCR0B = (1 << CS01) | (1 << CS00);
    
    // 5. Enable Global Interrupts (I-bit in SREG)
    sei();
}

ISR(TIMER0_COMPA_vect) {
    system_millis++; // Increment every 1 millisecond
}
            """.trimIndent(),
            lineExplanations = listOf(
                CodeLineExplanation("volatile uint32_t system_millis = 0;", "'volatile' ensures compiler reads fresh RAM value every time, preventing register caching."),
                CodeLineExplanation("OCR0A = 249;", "Compare target register. 16,000,000 / (64 * 250) = exactly 1,000 interrupts per second (1ms)."),
                CodeLineExplanation("TIMSK0 = (1 << OCIE0A);", "Enables Timer Interrupt Mask register for Compare Match A."),
                CodeLineExplanation("sei();", "Executes CPU assembly instruction 'SEI' (Set Global Interrupt Enable flag).")
            ),
            expectedOutput = "Generates a cycle-accurate 1kHz clock tick used as the foundation for millis() and RTOS time slices.",
            realWorldApplication = "RTOS kernel SysTick heartbeat, digital stopwatch timers, software timeout managers.",
            simulationType = SimulationType.TIMER_INTERRUPT
        ),

        CodeExample(
            id = "code_interrupt",
            title = "6. External Hardware Interrupt (INT0 Button Press)",
            subtitle = "Asynchronous Edge Detection and Zero-Polling Button Response",
            problemStatement = "Detect an emergency push-button press instantly on Pin PD2 (INT0) using falling-edge hardware interrupt triggering.",
            code = """
#include <avr/io.h>
#include <avr/interrupt.h>

volatile uint8_t emergency_stopped = 0;

void INT0_Init(void) {
    // Set PD2 as INPUT and enable internal PULL-UP resistor
    DDRD &= ~(1 << 2);
    PORTD |= (1 << 2);
    
    // Trigger INT0 on Falling Edge (Button pressed pulls pin to GND)
    EICRA |= (1 << ISC01);
    EICRA &= ~(1 << ISC00);
    
    // Enable external interrupt request INT0
    EIMSK |= (1 << INT0);
    
    sei(); // Enable global interrupts
}

ISR(INT0_vect) {
    // Runs instantly within 4 CPU clock cycles!
    emergency_stopped = 1;
    PORTB &= ~(1 << 0); // Cut motor power immediately
}
            """.trimIndent(),
            lineExplanations = listOf(
                CodeLineExplanation("PORTD |= (1 << 2);", "Enables the internal 20k pull-up resistor so the input pin stays cleanly at 5V until pressed."),
                CodeLineExplanation("EICRA |= (1 << ISC01);", "External Interrupt Control Register: Configures falling-edge voltage trigger."),
                CodeLineExplanation("EIMSK |= (1 << INT0);", "External Interrupt Mask: Unmasks INT0 line to the CPU interrupt controller."),
                CodeLineExplanation("ISR(INT0_vect)", "Hardware vector callback executed immediately when the push-button connects pin to ground.")
            ),
            expectedOutput = "Pin voltage drops from 5V to 0V when button is pushed; CPU branches to ISR in nanoseconds and cuts motor power.",
            realWorldApplication = "Emergency stop (E-Stop) buttons on factory equipment, car door latch sensors, collision bumpers.",
            simulationType = SimulationType.PUSHBUTTON_ISR
        ),

        CodeExample(
            id = "code_scheduler",
            title = "7. Basic Task Scheduling (FreeRTOS Multitasking)",
            subtitle = "Task Creation, Priorities, vTaskDelay, and Preemptive Kernel",
            problemStatement = "Create two concurrent FreeRTOS tasks: Task1 reads an ultrasonic sensor every 50ms (Priority 2) and Task2 updates an OLED display every 200ms (Priority 1).",
            code = """
#include "FreeRTOS.h"
#include "task.h"

// Task 1: High Priority (Runs every 50ms)
void Task_Sensor(void *pvParameters) {
    for (;;) {
        Read_Ultrasonic_Sensor();
        // Delay task for 50ms without consuming CPU cycles
        vTaskDelay(pdMS_TO_TICKS(50));
    }
}

// Task 2: Low Priority (Runs every 200ms)
void Task_Display(void *pvParameters) {
    for (;;) {
        Update_OLED_Screen();
        vTaskDelay(pdMS_TO_TICKS(200));
    }
}

int main(void) {
    // Create Task 1 (Priority 2 - Higher)
    xTaskCreate(Task_Sensor, "Sensor", 128, NULL, 2, NULL);
    
    // Create Task 2 (Priority 1 - Lower)
    xTaskCreate(Task_Display, "Display", 128, NULL, 1, NULL);
    
    // Start FreeRTOS preemptive scheduler
    vTaskStartScheduler();
    
    // Never reached!
    for(;;);
}
            """.trimIndent(),
            lineExplanations = listOf(
                CodeLineExplanation("xTaskCreate(Task_Sensor, ..., 2, ...);", "Allocates TCB and 128 words of stack in RAM for Task_Sensor with Priority 2."),
                CodeLineExplanation("vTaskDelay(pdMS_TO_TICKS(50));", "Places the task in BLOCKED state for 50 milliseconds, giving CPU to lower priority tasks."),
                CodeLineExplanation("vTaskStartScheduler();", "Transfers total CPU control to the FreeRTOS kernel; begins SysTick timer and preemptive scheduling.")
            ),
            expectedOutput = "Both tasks execute concurrently in parallel time. The sensor task reliably preempts the display task every 50ms without display flicker.",
            realWorldApplication = "Robotic autonomous navigation, smart medical telemetry monitors, 3D printer controllers.",
            simulationType = SimulationType.TASK_SCHEDULER
        )
    )

    fun getExampleById(id: String): CodeExample? {
        return examples.find { it.id == id }
    }
}
