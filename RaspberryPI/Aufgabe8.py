import time
import explorerhat as hat

print("""SD_GIT_2122_U08 Ampelschaltung""")

# !!!! Zustände wurden geändert !!!!


def stateToCode(name):
    if name == "gruen":
        return [0, 0]

    if name == "rot":
        return [0, 1]

    if name == "gelb":
        return [1, 0]

    if name == "rotgelb":
        return [1, 1]


# Der initiale Zustand: Die Ampel ist auf rot
state = "rot"

# Die Zeit, die zwischen Umschaltvorgaengen vergeht
waitingtime = 2

# Anforderungskontakt gedrueckt oder nicht
pedestrian_req = 0

# der Handler, um einen Druck auf die Anforderungstaste entgegenzunehmen


def pedreq(channel, event):
    # wenn Taste 1 gedrueckt, setzen wir pedestrian_req
    print(channel, event)


# Registrierung des Handlers, der die Fussgaengeranforderung entgegennimmt
hat.touch.pressed(pedreq)

while 1:
    hat.output[0].write(stateToCode(state)[0])
    hat.output[1].write(stateToCode(state)[1])

    print("code ist ", stateToCode(state))

    if state == "rot":
        # Onboard LEDs des Explorer HAT ein- und ausschalten
        hat.light.red.on()
        hat.light.yellow.off()
        hat.light.green.off()

        # Hier pruefen wir die Fussgaegngeranforderung und schalten
        # die Fussgaengerampel ggf. auf gruen (blau)
        # if ...

        # durch welche Zuweisung wechseln Sie in den naechsten Zustand?
        state = "rotgelb"
    elif state == "rotgelb":
        hat.light.red.on()
        hat.light.yellow.on()
        hat.light.green.off()
        state = "gruen"
    elif state == "gruen":
        hat.light.red.off()
        hat.light.yellow.off()
        hat.light.green.on()
        state = "gelb"
    elif state == "gelb":
        hat.light.red.off()
        hat.light.yellow.on()
        hat.light.green.off()
        state = "rot"

time.sleep(waitingtime)
