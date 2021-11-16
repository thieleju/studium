#!/usr/bin/env python

import time
import explorerhat as hat


def decToBin(decimal):
    binArray = [0] * 4
    temp = decimal

    if temp > 15 or temp < 0:
        print("decimal out of bounds!")
        return

    # example: decimal = 5
    #          1st iteration (i=3):
    #               binArray[3] = 5 % 2 = 1 -> [0, 0, 0, 1]
    #               temp = 5 / 2 = 2
    #          2nd iteration (i=2):
    #               binArray[2] = 2 % 2 = 0 -> [0, 0, 0, 1]
    #               temp = 2 / 2 = 1
    #          3rd iteration (i=1):
    #               binArray[1] = 1 % 2 = 1 -> [0, 1, 0, 1]
    #               temp = 1 / 2 = 0
    #          temp = 0 -> exit loop, binArray = [0, 1, 0, 1]
    i = len(binArray) - 1  # 4-1 = 3
    while temp > 0:
        binArray[i] = temp % 2
        temp //= 2  # double / is needed in python version 3 or greater, otherwise /= returns fractions
        i -= 1

    return binArray


def binToDec(binArray):
    if len(binArray) > 4:
        print("binArray too many elements")
        return

    # i is the index
    # value is the value of the binArray[i]
    # example: binArray = [0, 1, 0, 1] (5 in decimal)
    #          reverse array to [1, 0, 1, 0]
    #          1st iteration (i=0):
    #               binArray[0] == 1 -> sum = 0 + 2^0 -> sum = 1
    #          2nd iteration (i=1):
    #               binArray[1] == 1 -> false
    #          3rd iteration (i=2):
    #               binArray[2] == 1 -> sum = 1 + 2^2 -> sum = 1 + 4 = 5
    #          4th iteration (i=3):
    #               binArray[3] == 0 -> false
    sum = 0
    for i, value in enumerate(reversed(binArray)):
        if value == 1:
            sum += 2**i

    return sum


def visualizeBinary(decimal):
    arr = decToBin(decimal)

    for i in range(4):
        if arr[i] == 1:
            hat.light[i].on()
        elif arr[i] == 0:
            hat.light[i].off()


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
                # toggle bits
                # example: button2 pressed(channel=2), binArray = [0, 0, 0, 1] (1 in decimal)
                #          -> counterInBin[2-1] = 1 - counterInBin[2-1]
                #          -> [0, 0, x, 1] is switched to...
                #                  if x = 0: [0, 0, 0, 1] -> [0, 0, 1-0, 1] -> [0, 0, 1, 1]
                #                  if x = 1: [0, 0, 1, 1] -> [0, 0, 1-1, 1] -> [0, 0, 0, 1]
                counterInBin[channel-1] = 1 - counterInBin[channel-1]

                # convert binary to decimal
                counterInDec = binToDec(counterInBin)

                # toggle lights on off
                visualizeBinary(counterInDec)

    print("Button Press on "+str(channel),
          "NIM="+str(nim), counterInBin, counterInDec)


# Global variables
counterInDec = 0
counterInBin = [0, 0, 0, 0]
nim = False

hat.touch.pressed(evaluateButtons)
hat.pause()
