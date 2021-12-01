import time
import explorerhat as hat

print("""SD_GIT_2122_U08 Ampelschaltung""")


def stateToCode(name):
    # Zustaende wurden geaendert !
    if name == "gruen":
        return [0, 0]

    if name == "rot":
        return [0, 1]

    if name == "gelb":
        return [1, 0]

    if name == "rotgelb":
        return [1, 1]


def setLights(state):
    if state == "rot":
        hat.light.red.on()
        hat.light.yellow.off()
        hat.light.green.off()
    elif state == "rotgelb":
        hat.light.red.on()
        hat.light.yellow.on()
        hat.light.green.off()
    elif state == "gruen":
        hat.light.red.off()
        hat.light.yellow.off()
        hat.light.green.on()
    elif state == "gelb":
        hat.light.red.off()
        hat.light.yellow.on()
        hat.light.green.off()


def pedreq(channel, event):
    # wenn Taste 1 gedrueckt, setzen wir pedestrian_req
    if event == "press" and channel == 1:
        global pedestrian_req
        pedestrian_req = 1


# Der initiale Zustand: Die Ampel ist auf rot
state = "rot"
bluelight = 1
keepGreen = 0

# Die Zeit in s, die zwischen Umschaltvorgaengen vergeht
waitingtime = 1.5

# Anforderungskontakt gedrueckt oder nicht
pedestrian_req = 0

# Registrierung des Handlers, der die Fussgaengeranforderung entgegennimmt
hat.touch.pressed(pedreq)

while 1:
    if keepGreen == 1:
        # keep lights green because pedestrian is crossing the street (state still changes)
        hat.output[0].write(0)
        hat.output[1].write(0)
        setLights("gruen")
    else:
        hat.output[0].write(stateToCode(state)[0])
        hat.output[1].write(stateToCode(state)[1])
        setLights(state)

    # blink blue light as long as pedestrian is requesting to cross street
    if pedestrian_req == 1 and keepGreen == 0:
        hat.light.blue.on() if bluelight == 1 else hat.light.blue.off()
        bluelight = not bluelight

    # change to next state (regardless of pedestrian_req or )
    if state == "rot":
        # decide what to do:
        #   a) pedestrian finished crossing street -> reset and continue with yellow
        #   b) pedestrian starts to cross street -> keep green light
        #   c) continue with normal loop

        if pedestrian_req == 1 and keepGreen == 1:
            # reset and continue with yellow light, turn blue light off
            keepGreen = 0
            pedestrian_req = 0
            hat.light.blue.off()
            state = "gelb"
        elif pedestrian_req == 1:
            # pedestrian starts to cross street, turn blue light on
            keepGreen = 1
            hat.light.blue.on()
            state = "gruen"
        else:
            # normal loop
            state = "rotgelb"
    elif state == "rotgelb":
        state = "gruen"
    elif state == "gruen":
        state = "gelb"
    elif state == "gelb":
        state = "rot"

    print("pedestrian_req", pedestrian_req, "keepGreen",
          keepGreen, stateToCode(state), state)

    time.sleep(waitingtime)
