import explorerhat as hat


def handle_analog(pin, value):
	print(pin.name, value)

#hat.output.one.on()
#hat.output[0].on()

#hat.output.one.blink(1,1)
#hat.output[0].blink(1,1)

hat.light.blink(2, 2)
hat.output.blink(2, 2)

hat.analog.one.changed(handle_analog)

hat.pause()
