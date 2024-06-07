# Light-Based-Alarm-Clock

**Introduction**
The Light Based Morning Alarm Clock is an innovative alarm system that wakes you up based on light intensity. The alarm will only start ringing once a sufficient amount of light has been detected. This unique feature helps you adjust gradually to changes in daylight throughout the year, promoting a healthier sleep cycle.

**Overview**
With the Light Based Morning Alarm Clock, you'll wake up gradually as the year progresses, instead of having to adjust abruptly for daylight saving time. This device uses a light-dependent resistor (LDR) to detect ambient light levels and triggers a buzzer and LED to wake you up. An internal clock ensures you wake up at the same time even on days with insufficient light.

**Features**
Light Detection: Uses an LDR to detect light intensity.
Gradual Adjustment: Gradually adjusts wake-up time based on changing daylight.
Internal Clock: Ensures wake-up time consistency.
Alarm System: Triggers a buzzer and LED when the light threshold is met.
Distance Sensor: Uses an ultrasonic sensor to detect proximity and control the alarm.

**Hardware Requirements**
Arduino (compatible board)
LDR (Light Dependent Resistor)
Buzzer
LED
Ultrasonic sensor (for distance measurement)
Resistors, breadboard, and jumper wires

**Software Requirements**
Firmata4j
Princeton StdDraw

**Usage **
Wake-up Logic:
The alarm will trigger when the light level exceeds the predefined threshold.
If the light level is insufficient, the internal clock will ensure you wake up at the same time as the previous day.
The ultrasonic sensor monitors distance to control the alarm state.

**Research Justification**
The Light Based Morning Alarm Clock addresses the adverse effects of abrupt time changes due to daylight saving time. Research indicates that people sleep 40 minutes less on average during the winter daylight saving period, which can lead to sleep deprivation and decreased sleep efficiency. This project aims to mitigate these effects by gradually adjusting your wake-up time based on natural light.

A study by Yvonne Harrison highlights the increased risk of pedestrian accidents in the dark due to sleep deprivation and fatigue. By gradually adjusting your wake-up time, this alarm clock aims to improve your overall alertness and reduce the risk of accidents.

Contributors
Elvis Kuang (author)
Aradhya Chawla (OLED libararies)
