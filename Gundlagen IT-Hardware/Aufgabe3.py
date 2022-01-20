import time
import explorerhat as hat


# hat.motor.forwards()

touched = [False] * 8


def ohai(channel, event):
    if event == "press":
        touched[channel - 1] = True
    if event == "release":
        touched[channel - 1] = False
        
    print(str(channel)+ ": " + str(event))


hat.touch.pressed(ohai)
hat.touch.released(ohai)

# hat.light[0].on()
# hat.light[2].on()

# hat.output[0].on()
# hat.output[2].on()

# input_status = False


while True:
    #print(touched[0], touched[1])
    if touched[0] and touched[1]:
        hat.light.on()
    elif touched[0] or touched[1]:
        hat.light.off()

    # if hat.is_explorer_pro():
    #     print(hat.analog.read())
    # print(hat.input.read())
    # if sum(hat.input.read().values()) == 4:
    #     input_status = True
    # print('Input Status:', input_status)
    # print('Touch Status:', sum(touched) == 8)

    # if input_status and sum(touched) == 8:
    #     hat.light.off()
    #     hat.light.green.on()
    # else:
    #     hat.light.toggle()
    # hat.output.toggle()

    # time.sleep(1)

hat.pause()