package com.hackathon.keychain;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

public class UdpConnection implements Runnable {

	private DatagramSocket dataSocket = null;
	private InetAddress ip_address = null;
	private int socket = -1;
	private String message;

	public UdpConnection(InetAddress new_ip_address, int new_socket,
			String message) {
		this.ip_address = new_ip_address;
		this.socket = new_socket;
		this.message = message;
	}

	public DatagramSocket getDataSocket() {
		return dataSocket;
	}

	public void setDataSocket(DatagramSocket dataSocket) {
		this.dataSocket = dataSocket;
	}

	public InetAddress getIpAddress() {
		return ip_address;
	}

	public void setIpAddress(InetAddress address) {
		this.ip_address = address;
	}

	public int getSocket() {
		return socket;
	}

	public void setSocket(int new_socket) {
		this.socket = new_socket;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String temp_message) {
		this.message = temp_message;
	}

	public void run() {
		Log.i("MyActivity", "Starting UDP Run");

		if ((this.socket != 1) && (this.ip_address != null)) {
			try {
				setDataSocket(new DatagramSocket());
			} catch (SocketException e) {
				Log.e("MyActivity", "Unable to generate DatagramSocket");
				e.printStackTrace();
			}
		}

		String messageStr = this.message;
		int msg_length = messageStr.length();
		byte[] msg = messageStr.getBytes();

		/*
		 * byte[] message = new byte[8]; message[0] = (byte) 0x00; message[1] =
		 * (byte) 0x10; message[2] = (byte) 0x10; message[3] = (byte) 0xff;
		 * message[4] = (byte) 0x5A; message[5] = (byte) 0x5A; message[6] =
		 * (byte) 0x12; message[7] = (byte) 0x12;
		 */

		DatagramPacket p = new DatagramPacket(msg, msg_length, ip_address,
				socket);

		Log.i("MyActivity", "Sending message...");
		try {
			dataSocket.send(p);
			Log.i("MyActivity", messageStr);
		} catch (IOException e) {
			Log.e("MyActivity", "Unable to send message");
			e.printStackTrace();
		}
	}
}
