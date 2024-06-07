clear all;
close all;
a = arduino("COM4", "Nano3", "Libraries", {'Ultrasonic', 'I2C'});

ldrPin = 'A0'; %pin the light sensor is hooked up to 
ledPin = 'D6'; %pin the LED is hooked up to
buzzerPin = 'D5'; %pin the buzzer is hooked up to
triggerPin = 'D7'; %trigger pin
echoPin = 'D8'; %triggerpin

ultrasonicObj = ultrasonic(a,triggerPin,echoPin, 'OutputFormat','double');
scanI2CBus(a, 0);
oled = Initialize_Oled(a, true);
clearDisplay(oled)

tic; %initialize time
max_samples = 3600; %an hour

lightThreshold = 1; % Threshold to determine if it's dark or light

time_data = zeros(max_samples, 1);
light_data = zeros(max_samples, 1);
Elvis = plot(time_data, light_data);  % Initialize plot
xlabel('Time');
ylabel('Light Level');


while true
    sample = toc;%time elapsed
    ldrValue = readVoltage(a, ldrPin); %amount of light
    distance = readDistance(ultrasonicObj)
    time_data(end+1) = sample;
    light_data(end+1) = ldrValue;
    Setfunction(Elvis, time_data, light_data)
    drawnow;
    if ldrValue > lightThreshold %if light is sufficient
        disp('Wake up');  
        while distance > 1
            % ---------- 'W' ------------
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 
            distance = readDistance(ultrasonicObj)
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1); 
            distance = readDistance(ultrasonicObj)
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);
            distance = readDistance(ultrasonicObj)
            
            % ---------- 'A' ------------
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5);
            distance = readDistance(ultrasonicObj)
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);
            distance = readDistance(ultrasonicObj)
         
            % ---------- 'K' ------------
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);
            distance = readDistance(ultrasonicObj)
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 
            distance = readDistance(ultrasonicObj)
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);
            distance = readDistance(ultrasonicObj)
         
            % ---------- 'E' ------------
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 
            distance = readDistance(ultrasonicObj)

            % ---------- 'U' ------------
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5);
            distance = readDistance(ultrasonicObj)
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 
            distance = readDistance(ultrasonicObj)
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);
            distance = readDistance(ultrasonicObj)
     
            % ---------- 'P' ------------
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 
            distance = readDistance(ultrasonicObj)
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);
            distance = readDistance(ultrasonicObj)
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);
            distance = readDistance(ultrasonicObj)
            % Short;
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 
            distance = readDistance(ultrasonicObj);
            pause(2)
        end
            if distance <= 1
            writePWMDutyCycle(a, ledPin, 0);
            writePWMDutyCycle(a, buzzerPin, 0);
            currentTime = datetime('now', 'Format', 'HH:mm');
            textToDisplay = ['Good Morning, You Woke up at ' char(currentTime)];
            display_write(oled, 1, 1, 1, 128, 1, 8, 1, textToDisplay);
            pause(1000); % (23 hours)
            sample = 0;
            end
    else
        disp('Not yet');
    end
    if sample > max_samples
        while distance > 1
            disp('Wake up');  
            % ---------- 'W' ------------
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1); 
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);

            % ---------- 'A' ------------
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);
        
            % ---------- 'K' ------------
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);

            % ---------- 'E' ------------
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 

            pause (2) %space

            % ---------- 'U' ------------
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);

            % ---------- 'P' ------------
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);
            % Long
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 1);
            % Short
            blinkAndBuzzer(a, ledPin, buzzerPin, 0.33, 0.5, 0.5); 
            %morningMessage = 'Good Morning Elvis,\nToday you woke up again!';
            %writeDisplay(oled, morningMessage);
            pause(5)
        end
        if distance < 1
            writePWMDutyCycle(a, ledPin, 0);
            writePWMDutyCycle(a, buzzerPin, 0);
            currentTime = datetime('now', 'Format', 'HH:mm');
            textToDisplay = ['Good Morning, You Woke up at ' char(currentTime)];
            display_write(oled, 1, 1, 1, 128, 1, 8, 1, textToDisplay);
            pause(1000); % (23 hours)
            sample = 0;
        end
    end
end
% Function to play sound using a buzzer
function playBuzzer(a, pin, dutyCycle, duration)
    writePWMDutyCycle(a, pin, dutyCycle);
    pause(duration);
    writePWMDutyCycle(a, pin, 0);
end

% Function to conjoin LED blinking and sound
function blinkAndBuzzer(a, ledPin, buzzerPin, ledDutyCycle, buzzerDutyCycle, duration)
    ledDutyCycle = max(0, min(ledDutyCycle / 1.0, 1));
    writePWMDutyCycle(a, ledPin, ledDutyCycle);
    playBuzzer(a, buzzerPin, buzzerDutyCycle, duration);
    writePWMDutyCycle(a, ledPin, 0);
end



