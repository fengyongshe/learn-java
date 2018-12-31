package com.fys.learnjava.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class ChatServer extends WebSocketServer {

  public ChatServer(int port) {
    super(new InetSocketAddress(port));
  }

  public ChatServer(InetSocketAddress address) {
    super(address);
  }

  public void onOpen(WebSocket conn, ClientHandshake handshake) {
    conn.send("Welcome to the server!");
    broadcast("new connection: " + handshake.getResourceDescriptor());
    System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
  }

  public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    broadcast(conn + " has left the room!");
    System.out.println( conn + " has left the room1");
  }

  public void onMessage(WebSocket conn, String message) {
    broadcast( message );
    System.out.println( conn + " : " + message);
  }

  @Override
  public void onMessage(WebSocket conn, ByteBuffer message) {
    broadcast(message.array());
    System.out.println(conn + ":" + message);
  }

  public void onError(WebSocket conn, Exception ex) {
    ex.printStackTrace();
    if (conn != null) {}
  }

  public void onStart() {
    System.out.println("Server started!");
    setConnectionLostTimeout(0);
    setConnectionLostTimeout(100);
  }

  public static void main(String[] args) throws Exception {
    int port = 8887;
    ChatServer server = new ChatServer(port);
    server.start();
    System.out.println("ChatServer starteed on port: " + server.getPort());
    BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      String in = sysin.readLine();
      server.broadcast(in);
      if (in.equals("exit")) {
        server.stop(1000);
        break;
      }
    }
  }
}
