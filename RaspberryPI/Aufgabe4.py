
import explorerhat as hat


def touchHandler(channel, event):
    if channel > 4:
        return
    if event == "press":
        hat.light[channel - 1].on()
    if event == "release":
        hat.light[channel - 1].off()


hat.touch.pressed(touchHandler)
hat.touch.released(touchHandler)


hat.pause()
