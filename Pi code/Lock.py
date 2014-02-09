import time
import socket

UDP_IP = "192.168.0.15"
UDP_PORT = 40

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind(('', UDP_PORT))

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

data = None
password = None
message = None

print('before loop')
while True:
	print('start')
	while data == None:	
		data, addr = sock.recvfrom(UDP_PORT)
		
		if data == None:
			continue
		message , password = data.split(";")
		print(data)
	
	if (message == 'unlock' )&(password == "banana"):
		setServo(30)
		print('unlock')
	
	elif (message == 'lock')&(password == "banana"):
		setServo(160)
		print('lock')
	time.sleep(1)	
	data = None
	password = None
	message = None

