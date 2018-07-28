package com.example.tcp.client;

import com.example.tcp.selecter.MySelecterByClient;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * Created by baipan
 * Date: 2018-07-27
 */
public class Client {
    //    private static String host = "192.168.3.137";
    private static int port = 5006;
    private static SocketChannel socketChannel;

    static {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(port));
            socketChannel.configureBlocking(false);
            socketChannel.register(MySelecterByClient.getSelector(), SelectionKey.OP_WRITE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        try {
            System.out.println("socketChannel " + socketChannel.hashCode());
            MySelecterByClient.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
