import time
import explorerhat as hat

print("Example reading analog one and two every 1s")

while True:
	one = hat.analog.one.read()
	two = hat.analog.two.read()
	print(one, two)
	time.sleep(0.5)
