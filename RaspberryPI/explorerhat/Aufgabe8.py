import time
import explorerhat as hat

print("""SD_GIT_2122_U08
Ampelschaltung""")

# Eine Hilfsfunktion, die uns unsere Zustandsnamen auf Codierungen abbildet
# Hier ist nichts zu verändern, es sei denn, Sie verwenden eine andere
# Kodierung


def stateToCode(name):
    if name == "rot":
        return [0, 0]

    if name == "rotgelb":
        return [0, 1]

    if name == "gruen":
        return [1, 0]

    if name == "gelb":
        return [1, 1]

    else:
        return [0, 0]


# Der initiale Zustand: Die Ampel ist auf rot
state = "rot"

# Die Zeit, die zwischen Umschaltvorgängen vergeht
waitingtime = 3

# Anforderungskontakt gedrückt oder nicht
pedestrian_req = 0

# der Handler, um einen Druck auf die Anforderungstaste entgegenzunehmen


def pedreq(channel, event):
    # wenn Taste 1 gedrückt, setzen wir pedestrian_req

    # Registrierung des Handlers, der die Fußgängeranforderung entgegennimmt
hat.touch.pressed(pedreq)


while 1:
    # zu Beginn jedes Durchlaufs wird der aktuelle Zustand nach
    # außen gegeben. Da output.on() bewirkt, dass der Pin auf
    # GND (also die logische 0) gezogen wird, invertieren wir
    # das jeweilige Bit der Zustandskodierung
    # Hier sollten Sie nichts verändern
    hat.output[1].write(not stateToCode(state)[1])
    hat.output[0].write(not stateToCode(state)[0])

    # Die eigentliche Zustandsmaschine, die basierend auf aktuellem Zustand
    # und optionalem Eingang entscheidet, was der Folgezustand sein wird
    if state == "rot":
        # für Ihre Fehlersuche ggf. hilfreich
        print("code ist {}".format(stateToCode(state)))

        # Onboard LEDs des Explorer HAT ein- und ausschalten
        hat.light.red.on()
        hat.light.yellow.off()
        hat.light.green.off()

        # Hier prüfen wir die Fußgägngeranforderung und schalten
        # die Fußgängerampel ggf. auf grün (blau)
        # if ...

        # durch welche Zuweisung wechseln Sie in den nächsten Zustand?
        #state = ...

        # ein wenig Wartezeit vor nächster Phase
        time.sleep(waitingtime*2)

    # Hier müssen Sie den Zustandsautomaten ergänzen
    # warum nehmen wir elif und nicht einfach ein neues if?

    # elif state == ...
    # ...
