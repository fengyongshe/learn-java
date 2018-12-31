package com.fys.learnjava.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

  public static void main(String[] args) {
    try {
      DatagramSocket socket = new DatagramSocket(9999);
      byte[] content = new byte[1024];
      DatagramPacket datagramPacket = new DatagramPacket(content, content.length);
      socket.receive(datagramPacket);

      byte[] data = datagramPacket.getData();
      int length = datagramPacket.getLength();
      InetAddress ip = datagramPacket.getAddress();
      int port = datagramPacket.getPort();

      System.out.println("content:" + new String(data, 0 ,length));
      System.out.println("Length:" + length);
      System.out.println("Sender IP:" + ip);
      System.out.println("Sender Port:" + port);

      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
