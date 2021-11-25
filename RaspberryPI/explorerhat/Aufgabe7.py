import time
import explorerhat as hat


def nametonumber(name):
    if name == "one":
        return 0
    if name == "two":
        return 1
    if name == "three":
        return 2
    if name == "four":
        return 3
    else:
        return -1


# Reset auf Low setzen
hat.output[3].on()

# PL auf High
hat.output[2].off()


def evaluateButtons(channel, event):
    # count up:
    #   CPD = high (output 1 off)
    #   CPU = output 0 from on to off (low to high)
    # hochzählen, CPU flanke, CPD auf High
    if channel == 6 and event == 'press':
        hat.output[0].on()
        hat.output[1].off()
    if channel == 6 and event == 'release':
        hat.output[0].off()

    # count down:
    #   CPD = output 1 from on to off (low to high)
    #   CPU = high (output 0 off)
    if channel == 7 and event == 'press':
        hat.output[1].on()
        hat.output[0].off()
    if channel == 7 and event == 'release':
        hat.output[0].off()

    # flanke für reset
    if channel == 8 and event == 'press':
        hat.output[3].off()
    if channel == 8 and event == 'release':
        hat.output[3].on()


def evaluateInput(input):
    global binArray
    number = nametonumber(input.name)
    hat.light[(number-3)*(-1)].write(input.read())
    print("Input {} changed to {}".format(input.name, input.read()))


hat.input.on_changed(evaluateInput)
hat.touch.pressed(evaluateButtons)
hat.touch.released(evaluateButtons)
hat.pause()
