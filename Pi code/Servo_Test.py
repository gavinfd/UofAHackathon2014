import time

def set(property, value):
	try: 
		f = open('/sys/class/rpi-pwm/pwm0/' + property, 'w')	
		f.write(value)
		f.close()
	except:
		print('ERROR')

def setServo(angle):
	set("servo",str(angle))

set("delayed", "0")
set("mode","servo")
set("servo_max","180")
set("active","1")

delay_period = 0.1

while True:
	print('in loop')
	for angle in range(0,180):
		setServo(angle)
		time.sleep(delay_period)
	print('finished first loop')
	for angle in range (0,180):
		setServo(180 - angle)
		time.sleep(delay_period)