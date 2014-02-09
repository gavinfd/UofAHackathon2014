import socket
UDP_IP = "192.168.0.15"
UDP_PORT = 40

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind(('', UDP_PORT))

while True:
	data, addr = sock.recvfrom(UDP_PORT)
	print(data);

