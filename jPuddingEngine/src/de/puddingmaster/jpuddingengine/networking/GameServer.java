package de.puddingmaster.jpuddingengine.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import de.fridtjof_de.puddingapi.logger;
import de.puddingmaster.jpuddingengine.Game;
import de.puddingmaster.jpuddingengine.Handler;
import de.puddingmaster.jpuddingengine.entites.Player;
import de.puddingmaster.jpuddingengine.worlds.World;

public class GameServer extends Thread {

	private DatagramSocket socket;
	private Handler handler;
	private Game game;
	
	public GameServer(Game game) {
		this.game = game;
		try {
			this.socket = new DatagramSocket(1331);
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
			logger.DEBUG("CLIENT > [" + packet.getAddress().getHostAddress() +":"+ packet.getPort() + "] " + msg);
			if(msg.trim().equalsIgnoreCase("ping")) {		
				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
			}
		}
	}

	public void sendData(byte[] data, InetAddress ipAddress, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}