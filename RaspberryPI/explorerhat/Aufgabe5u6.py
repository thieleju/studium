#!/usr/bin/env python

import time
import explorerhat as hat


def decToBin(decimal):
    binArray = [0] * 4
    temp = decimal

    if temp > 15 or temp < 0:
        print("decimal out of bounds!")
        return

    c = 0
    while temp > 0:
        binArray[c] = temp % 2
        temp /= 2
        c += 1

    print("Bin array: "+str(binArray))
    return binArray


def binToDec(binArray):
    if len(binArray) > 4:
        print("binArray too many elements")
        return

    dec = 0
    for i, value in enumerate(reversed(binArray)):
        if value == 1:
            dec += 2**i

    return dec


def visualizeBinary(decimal):
    arr = decToBin(decimal)

    for i in range(4):
        if arr[i] == 1:
            hat.light[3 - i].on()
        elif arr[i] == 0:
            hat.light[3 - i].off()


def increaseCounter():
    global counterInDec
    global counterInBin

    if counterInDec < 15:
        counterInDec += 1
    else:
        counterInDec = 0

    counterInBin = decToBin(counterInDec)
    visualizeBinary(counterInDec)


def decreaseCounter():
    global counterInDec
    global counterInBin

    if counterInDec > 0:
        counterInDec -= 1
    else:
        counterInDec = 15

    counterInBin = decToBin(counterInDec)
    visualizeBinary(counterInDec)


# globals
counterInDec = 0
counterInBin = [0, 0, 0, 0]
nim = False


def evaluateButtons(channel, event):
    global nim
    global counterInDec
    global counterInBin

    if event == 'press':
        # toggle nim mode
        if channel == 5:
            nim = not nim
        if nim == False:
            # number input mode disabled
            if channel == 6:  # inc
                increaseCounter()
            if channel == 7:  # dec
                decreaseCounter()
            if channel == 8:  # reset
                counterInBin = [0, 0, 0, 0]
                counterInDec = 0
                visualizeBinary(counterInDec)
        elif nim == True:
            # number input mode
            if channel == 8:
                nim = False
            if channel <= 4:
                if counterInBin[channel-1] == 1:
                    counterInBin[channel-1] = 0
                else:
                    counterInBin[channel-1] = 1
                # toggle lights on off
                counterInDec = binToDec(counterInBin)
                visualizeBinary(counterInDec)

    print("in evaluateButtons", nim, counterInBin, counterInDec)


hat.touch.pressed(evaluateButtons)
hat.pause()
