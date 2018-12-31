package com.fys.learnjava.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class ChatClient extends WebSocketClient {

  public ChatClient(URI serverURI) {
    super(serverURI);
  }

  public void onOpen(ServerHandshake serverHandshake) {
    send("Hello, it is me. Mario : ");
    System.out.println("Opened connection");
  }

  public void onMessage(String message) {
    System.out.println("Received: " + message);
  }

  public void onClose(int code, String reason, boolean remote) {
    System.out.println("Connection closed by " + (remote ? "remote peer" : "us") +
      "  Code: " + code + " Reason: " + reason);
  }

  public void onError(Exception ex) {
    ex.printStackTrace();
  }

  public static void main(String[] args) throws URISyntaxException {
    ChatClient client = new ChatClient(new URI("ws://localhost:8887"));
    client.connect();
  }
}
