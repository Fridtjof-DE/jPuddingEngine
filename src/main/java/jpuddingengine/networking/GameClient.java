package jpuddingengine.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import jpuddingengine.Engine;
import jpuddingengine.Handler;

public class GameClient extends Thread {

	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Engine engine;
	
	public GameClient(Engine engine, String ipAddress) {
		this.engine = engine;
		try {
			this.socket = new DatagramSocket();
			try {
				this.ipAddress = InetAddress.getByName(ipAddress);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String msg = new String(packet.getData());
			System.out.println("SERVER > [" + packet.getAddress().getHostAddress()+":"+packet.getPort() + "] " + msg);
		}
	}
	
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
