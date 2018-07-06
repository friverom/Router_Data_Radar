/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package router_data_radar;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author federico
 */
public class Router_Data_Radar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
       
        
        //Create Socket to receive data on port 50107
        DatagramSocket ds = new DatagramSocket(50107);
        DatagramSocket ts1 = new DatagramSocket();
       
        byte[] receive = new byte[1024]; //Buffer can be smaller
        
        //Servers address
        InetAddress str1 = InetAddress.getByName("10.0.2.131");
        InetAddress str2 = InetAddress.getByName("10.0.2.132");
        InetAddress adr = InetAddress.getByName("10.0.2.133");
        
        //Create data packet object to receive data from radar
        
        DatagramPacket DpReceive = null;
        
        //Create data packet object to send data to servers
        DatagramPacket dpStr1 = null;
        DatagramPacket dpStr2 = null;
        DatagramPacket dpAdr = null;
        
        while(true){
            DpReceive = new DatagramPacket(receive, receive.length);
            //Receive data from radar
            ds.receive(DpReceive); //wait for data
            dpStr1=DpReceive;
            dpStr2=DpReceive;
            dpAdr=DpReceive;
            
            //Create UDP packet to server 1
            dpStr1.setAddress(str1);
            dpStr1.setPort(4201);
            ts1.send(dpStr1); 
            
            //Create UDP packet to server 2
            dpStr2.setAddress(str2);
            dpStr2.setPort(4201);
            ts1.send(dpStr2);
            
            //Create UDP packet to ADR server
            dpAdr.setAddress(adr);
            dpAdr.setPort(4201);
            ts1.send(dpAdr); 
            
            //Clear receive buffer
            receive = new byte[1024];
            
        }
    
    }
    
}