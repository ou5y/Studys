package com.example.tcp.selecter;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Created by baipan
 * Date: 2018-07-27
 */
public class MySelecterByClient {

    private static class MySelectHolder{
        private static Selector selector;
        static {
            try {
                selector = Selector.open();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static Selector getSelector(){
        return MySelectHolder.selector;
    }


    public static void start() throws Exception{
        Selector selector = MySelectHolder.selector;
        System.out.println("Client start");
        while (true) {
            int count = selector.select();
            System.out.println("1 循环 count = "+count);
            if (count > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                System.out.println("iterator size : " + selectionKeys.size());
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();

                    //改变兴趣
                    //selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE)

                    SocketChannel socketChannel =(SocketChannel)selectionKey.channel();
                    if (selectionKey.isWritable()){
                        System.out.println("write socketChannel " + socketChannel.hashCode());
                        //selectionKey.cancel();//取消后的selectKey不能再次被注册，，，所以这儿不能cancel

                        String content = "张三" + new Random().nextInt(10) + "号";
                        ByteBuffer byteBuffer = ByteBuffer.wrap(content.getBytes());
                        socketChannel.write(byteBuffer);
                        System.out.println("Client：发送成功：" + content);

                        //改变注册已读取回来的内容
                        selectionKey.interestOps(SelectionKey.OP_READ);
                        //socketChannel.register(MySelecterByClient.getSelector(), SelectionKey.OP_READ);
                    }else if (selectionKey.isReadable()){
                        System.out.println("read socketChannel " + socketChannel.hashCode());
                        selectionKey.cancel();//取消注册, 释放资源

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int length = socketChannel.read(byteBuffer);
                        if (length > 0){
                            String content = new String(byteBuffer.array(), 0, byteBuffer.limit(), Charset.defaultCharset());
                            System.out.println(String.format("Client：接收Server返回的内容：%s", content));
                        }else{
                            System.out.println(String.format("Client：接收Server返回的内容：%s", "null"));
                        }
                    }
                }
            }
        }
    }

}
