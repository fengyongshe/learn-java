package com.fys.learnjava.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

  public static void main(String[] args) {
    try {
      DatagramSocket datagramSocket = new DatagramSocket(6666);
      String str = "Hello World";
      byte[] content= str.getBytes();
      DatagramPacket datagramPacket = new DatagramPacket(content, content.length,
          InetAddress.getLocalHost(), 9999);
      datagramSocket.send(datagramPacket);
      datagramSocket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
