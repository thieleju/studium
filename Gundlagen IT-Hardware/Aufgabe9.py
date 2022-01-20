import time
import explorerhat as hat
import RPi.GPIO as GPIO

# These lines configure the PWM pin for pulse width modulation
# You don't need to make any changes to these lines
GPIO.setmode(GPIO.BCM)
GPIO.setup(18, GPIO.OUT)
buzzer = GPIO.PWM(18, 400)


#####################################
# Here we define some parameters
# the minimum and maximum frequency our buzzer should use as well as the duration of the buzzer sound
minfreq = 100
maxfreq = 800
buzzdur = 0.25

# the value range we expect from our trim resistor
minvolt = 0
maxvolt = 5.2

# our sampling mode is either time continuous or time discrete
# allowed values are "timeCont" for time continous or "timeDisc" for time discrete sampling
mode = "timeDisc"
samplingfreq = 60  # for mode "timeDisc" the sampling frequency is used

# here we set, how many bits our A/D converter should employ
bits = 1
#####################################


#####################################
# no changes needed in this section

def decToBin(decimal, bits):
    # as a helper function, we copied our decToBin function from a former exercise
    # it converts a decimal number between 0 and 15 into a four bit array
    binArray = [0] * bits
    for i in reversed(range(0, bits)):
        binArray[i] = decimal % 2
        decimal = decimal//2
    # print("{} as binary is {}".format(tmp, binArray[::-1]))
    return binArray


def binToDec(binArray):
    # as a helper function, binToDec transforms a binary representation
    # of a number into a decimal representation
    binArray = binArray[::-1]
    result = 0
    for i in range(0, len(binArray)):
        result = result + binArray[i]*(2**i)
    # print("{} as decimal is {}".format(binArray[::-1], result))
    return result


def buzz(freq, dur):
    # A little helper function to trigger the buzzer. We pass the frequency as well
    # as the buzzer duration
    buzzer.ChangeFrequency(freq)
    buzzer.start(10)
    time.sleep(dur)
    buzzer.stop()
#####################################


def toggleLED(quantValue):
    # Here we toggle the LEDs according to our digitized voltage value
    if len(quantValue) > 4:
        print("You try to address too many LEDs")
        return

    # get binary array
    binaryArray = decToBin(binToDec(quantValue), len(quantValue))

    # set lights and output to binary array
    for i in range(0, len(binaryArray)):
        # write output bits
        hat.output[3 - i].write(binaryArray[i])
        # toggle lights
        if binaryArray[i] == 1:
            hat.light[3-i].on()
        elif binaryArray[i] == 0:
            hat.light[3-i].off()


def toggleBuzz(quantValue):
    # toggleBuzz invokes the buzzer with a frequency corresponding to the quantized value
    buzzfreq = mapValue(binToDec(quantValue))
    buzz(buzzfreq, buzzdur)


def evaluateButtons(channel, event):
    # Here we set the explorer hat buttons to select who many bits our A/D converter should employ
    global bits
    if event == "press" and channel <= 4:
        bits = channel


def mapValue(value):
    # 0 - 5.2 / minvolt - maxvolt
    # 100 - 800 / minfreq - maxfreq
    # map the value linear to the frequency range
    # max-frequency * volt(value) / max-voltage
    # for 5.2: 100 + (800 - 100) * (5.2 - 0) / (5.2 - 0) = 800
    # for 3.0: 100 + (800 - 100) * (3.0 - 0) / (5.2 - 0) = 403
    return minfreq + (maxfreq - minfreq) * (value - minvolt) / (maxvolt - minvolt)


def AD1Bit(value):
    # the 1 Bit A/D converter
    # it should return either a [0] or [1]
    if value > (maxvolt-minvolt)/2:
        result = [1]
    else:
        result = [0]

    toggleLED(result)
    toggleBuzz(result)
    return result


def AD2Bit(value):
    # the 2 Bit A/D converter
    quarterVolt = (maxvolt - minvolt) / 4
    if value < quarterVolt:
        result = [0, 0]
    elif value < quarterVolt * 2:
        result = [0, 1]
    elif value < quarterVolt * 3:
        result = [1, 0]
    elif value < quarterVolt * 4:
        result = [1, 1]
    else:
        result = [1, 1]

    toggleLED(result)
    toggleBuzz(result)
    return result


def ADnBit(value, bit):
    # the n Bit A/D uses between 1 and 4 bit for conversion
    if bit > 4 or bit < 1:
        print("Invalid number of bits")
        return

    # calculate the fraction of the voltage range for given bits
    fractionVolt = (maxvolt - minvolt) / (2**bit)
    # calculate the state (2**bit states) the value is in (get as whole number)
    state = int(value / fractionVolt)
    # translate the state into a binary representation and reverse it ([::-1])
    # state = 3 with 4 bits -> [0, 0, 1, 1]
    # state = 4 with 3 bits -> [1, 0, 0]
    # state = 13 with 4 bits -> [1, 1, 0, 1]
    result = decToBin(state, bit)[::-1]

    toggleLED(result)
    toggleBuzz(result)
    return result


# We register the handler evaluteButtons for button press events
hat.touch.pressed(evaluateButtons)


while 1:
    # here we read the current voltage value from our analog pin
    currentValue = hat.analog.one.read()

    # here we invoke mapValue in order to directly map a voltage value to a frequency
    # we then call the buzz function with the resulting frequency in order to "hear" our
    # value. Try then changing the trimmer setting with a screwdriver while running this code
    # For the following tasks, you need to comment these two lines out
    # resultingFreq = mapValue(currentValue)
    # buzz(resultingFreq, buzzdur)

    # here we trigger the A/D conversion either for 1 or more Bits
    # please comment the two lines of code above (resultingFreq =..., buzz...) out
    # result = AD1Bit(currentValue)
    # result = AD2Bit(currentValue)
    result = ADnBit(currentValue, bits)

    # When invoking the A/D conversion you may uncomment this print call
    print("Mapping {} to {} by using {}-bit A/D conversion".format(currentValue, result, len(result)))

    # in case we use time discrete mode, we use the sampling frequency defined above
    if mode == "timeDisc":
        time.sleep(60/samplingfreq)
