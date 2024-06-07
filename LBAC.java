package eecs1021;

import edu.princeton.cs.introcs.StdDraw;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;

import java.io.IOException;
import java.util.ArrayList;

public class LBAC {
    private static final int LDR_PIN = 15; // Analog pin A0
    private static final int LED_PIN = 6; // Digital pin D6
    private static final int BUZZER_PIN = 5; // Digital pin D5
    private static final int TRIGGER_PIN = 7; // Digital pin D7
    private static final int ECHO_PIN = 8;

    static final int LIGHT_THRESHOLD = 5;

    static final int MAX_SAMPLES = 3600;

    public static void main(String[] args) throws IOException, InterruptedException {
        String myPort = "COM3";
        IODevice arduino = new FirmataDevice(myPort);
        arduino.start();
        System.out.println("Board started.");
        arduino.ensureInitializationIsDone();
        // Use 0x3C for the Grove OLED
        StdDraw.setXscale(-10, 100);
        StdDraw.setYscale(-1, 5);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.line(0,0,0,5);
        StdDraw.line(0,0,100,0);
        StdDraw.text(50,-0.5,"time");
        StdDraw.text(-8,2.5,"light");
        StdDraw.text(50,5,"light vs time");
        double[] timeData = new double[MAX_SAMPLES];
        double[] LightData = new double[MAX_SAMPLES];
        ArrayList<Double> LightList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        while (true) {
            System.out.println("working");
            double elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000.0;
            double ldrValue = readanalogVoltage(arduino, LDR_PIN);
            System.out.println(ldrValue);
            int lastIndex = timeData.length - 1;
            timeData[lastIndex] = elapsedSeconds;
            LightData[lastIndex] = ldrValue;
            LightList.add(ldrValue);
            double distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
            StdDraw.point(elapsedSeconds, ldrValue);
            StdDraw.show();
            System.out.println("a");
            Thread.sleep(100);
            if (ldrValue > LIGHT_THRESHOLD) {
                System.out.println("Wake up");
                while (distance > 1) {
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                    ;
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                    ;
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                    ;
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                    ;
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                    ;
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                    ;
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                    ;
                    distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                    Thread.sleep(2000);
                    // Perform action when light level is above threshold
                }

                if (distance <= 1) {
                    writePWMDutyCycle(arduino, LED_PIN, 0);
                    writePWMDutyCycle(arduino, BUZZER_PIN, 0);
                    Thread.sleep(82800);
                }
            }


            if (elapsedSeconds > MAX_SAMPLES) {
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                ;
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                ;
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                ;
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                ;
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                ;
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                ;
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 1.0);
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                blinkAndBuzzer(arduino, LED_PIN, BUZZER_PIN, 0.33, 0.5, (long) 0.5);
                ;
                distance = distance = readDistance(arduino, TRIGGER_PIN, ECHO_PIN);
                Thread.sleep(2000);
            }
            if (distance <= 1) {
                writePWMDutyCycle(arduino, LED_PIN, 0);
                writePWMDutyCycle(arduino, BUZZER_PIN, 0);
                Thread.sleep(82800);
            }
        }
    }

    // Function to write PWM duty cycle to a pin
    public static void writePWMDutyCycle(IODevice arduino, int pin, double value) {
        try {
            Pin digitalPin = arduino.getPin(pin);
            digitalPin.setMode(Pin.Mode.PWM);
            digitalPin.setValue((long) value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void playBuzzer(IODevice arduino, int pin, double dutyCycle, long duration) {
        writePWMDutyCycle(arduino, pin, dutyCycle);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writePWMDutyCycle(arduino, pin, 0);
    }

    // Function to conjoin LED blinking and sound
    public static void blinkAndBuzzer(IODevice arduino, int ledPin, int buzzerPin, double ledDutyCycle,
                                      double buzzerDutyCycle, long duration) {
        ledDutyCycle = Math.max(0, Math.min(ledDutyCycle / 1.0, 1));
        writePWMDutyCycle(arduino, ledPin, ledDutyCycle);
        playBuzzer(arduino, buzzerPin, buzzerDutyCycle, duration);
        writePWMDutyCycle(arduino, ledPin, 0);
    }

    public static double readDistance(IODevice arduino, int trigPin, int echoPin) {
        try {
            // Trigger the ultrasonic sensor to send out ultrasonic waves
            Pin trigger = arduino.getPin(trigPin);
            trigger.setMode(Pin.Mode.OUTPUT);
            trigger.setValue(1); // Set trigger pin high to trigger the sensor
            Thread.sleep(10); // Wait for a short time for the sensor to send ultrasonic waves
            trigger.setValue(0); // Reset trigger pin

            // Read the echo pin to measure the time it takes for the echo signal to return
            Pin echo = arduino.getPin(echoPin);
            echo.setMode(Pin.Mode.INPUT);
            long startTime = System.nanoTime();
            while (echo.getValue() == 0) {
                // Wait until the echo signal returns (pin value goes high)
            }
            long endTime = System.nanoTime();

            // Calculate distance based on the time it took for the echo signal to return
            double duration = (endTime - startTime) / 1000000.0; // Convert nanoseconds to milliseconds
            double distance = duration * 0.034 / 2; // Distance = time * speed of sound / 2
            return distance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static double readanalogVoltage(IODevice arduino, int pin) {
        try {
            Pin analogPin = arduino.getPin(pin);
            analogPin.setMode(Pin.Mode.ANALOG);
            analogPin.setValue(0); // Ensure pin is in output mode (Firmata workaround)
            Thread.sleep(100); // Delay for pin stabilization
            double voltage = analogPin.getValue() * 5.0 / 1023.0;
            return voltage;
        } catch (Exception e) {
            System.err.println("Error reading voltage from pin " + pin + ": " + e.getMessage());
            return 0;
        }
    }

    static double readdigitalVoltage(IODevice arduino, int pin) {
        try {
            Pin analogPin = arduino.getPin(pin);
            analogPin.setMode(Pin.Mode.INPUT);
            analogPin.setValue(0); // Ensure pin is in output mode (Firmata workaround)
            Thread.sleep(100); // Delay for pin stabilization
            double voltage = analogPin.getValue() * 5.0 / 1023.0;
            return voltage;
        } catch (Exception e) {
            System.err.println("Error reading voltage from pin " + pin + ": " + e.getMessage());
            return 0;
        }
    }

    static int readDigitalPin(IODevice arduino, int pin) {
        try {
            Pin digitalPin = arduino.getPin(pin);
            digitalPin.setMode(Pin.Mode.INPUT);
            return Math.toIntExact(digitalPin.getValue());
        } catch (Exception e) {
            System.err.println("Error reading digital pin " + pin + ": " + e.getMessage());
            return 0;
        }
    }

    static void writeDigitalPin(IODevice arduino, int pin, int value) {
        try {
            Pin digitalPin = arduino.getPin(pin);
            digitalPin.setMode(Pin.Mode.OUTPUT);
            digitalPin.setValue(value);
        } catch (Exception e) {
            System.err.println("Error writing digital pin " + pin + ": " + e.getMessage());

        }
    }
}
