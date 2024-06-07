package eecs1021;

import org.firmata4j.*;
import org.firmata4j.fsm.Event;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BonusprojectTest {

    @Test
    void testReadAnalogVoltage() {
        // Create a mock IODevice object
        IODevice mockArduino = new MockIODevice();

        // Call the method under test
        double voltage = LBAC.readanalogVoltage(mockArduino, 1); // Assuming pin number 1

        // Check the result
        // Since we don't have an actual Arduino device to read from, we can only check if the method returns a valid voltage
        assertTrue(voltage >= 0 && voltage <= 5.0, "Voltage should be between 0 and 5.0V");
    }

    // Mock IODevice class for testing purposes
    private static class MockIODevice implements IODevice {
        @Override
        public void start() throws IOException {

        }

        @Override
        public void stop() throws IOException {

        }

        @Override
        public void ensureInitializationIsDone() throws InterruptedException {

        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public Set<Pin> getPins() {
            return null;
        }

        @Override
        public int getPinsCount() {
            return 0;
        }

        @Override
        public Pin getPin(int i) {
            return null;
        }

        @Override
        public I2CDevice getI2CDevice(byte b) throws IOException {
            return null;
        }

        @Override
        public void addEventListener(IODeviceEventListener ioDeviceEventListener) {

        }

        @Override
        public void removeEventListener(IODeviceEventListener ioDeviceEventListener) {

        }

        @Override
        public String getProtocol() {
            return null;
        }

        @Override
        public void addProtocolMessageHandler(String s, Consumer<Event> consumer) {

        }

        @Override
        public void sendMessage(String s) throws IOException {

        }

        @Override
        public void sendMessage(byte... bytes) throws IOException {

        }
        // Implement necessary methods if required for testing
    }
}
