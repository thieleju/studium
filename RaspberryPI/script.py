import RPi.GPIO as GPIO
import time

# set mode to boardnumbers or GPIO number
# GPIO number
GPIO.setmode(GPIO.BCM)

pin = input("Choose output bin to blink\n")

iterations = input("How many times?\n")

GPIO.setup(pin, GPIO.OUT)

for i in range(iterations):
    GPIO.output(pin, GPIO.HIGH)
    time.sleep(0.5)
    GPIO.output(pin, GPIO.LOW)
    time.sleep(0.5)
