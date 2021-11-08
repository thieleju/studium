import time
import explorerhat as hat

def toggle_output(channel, event):
	if channel > 4:
		return
	if event == 'press':
		hat.output[channel - 1].on()
   	if event == 'release':
		hat.output[channel - 1].off()

	#print("Got "+str(event)+ " on "+str(channel))


hat.touch.pressed(toggle_output)
hat.touch.released(toggle_output)

hat.pause()

