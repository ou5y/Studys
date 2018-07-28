package com.example.tcp.service;

import com.example.tcp.selecter.MySelecterByService;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by baipan
 * Date: 2018-07-27
 */
public class MyService {

//    private static String host = "192.168.3.170";
    private static int port = 5006;
    private static ServerSocketChannel serverSocketChannel;

    static {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            //底层是一个SelectionKey数组，每个注册就是数组个数+1
            serverSocketChannel.register(MySelecterByService.getSelector(), SelectionKey.OP_ACCEPT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        try {
            System.out.println("serverSocketChannel " + serverSocketChannel.hashCode());
            MySelecterByService.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
