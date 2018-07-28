package com.example.tcp.selecter;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by baipan
 * Date: 2018-07-27
 */
public class MySelecterByService {

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
        System.out.println("Server start：");
        while (true) {
            //阻塞模式的select
            //执行步骤：
            //  1. 首先检查已取消键集合，也就是通过cancel()取消的键。
            //  1.1. 如果该集合不为空，则清空该集合里的键，同时该集合中每个取消的键也将从已注册键集合和已选择键集合中移除。
            //      （一个键被取消时，并不会立刻从集合中移除，而是将该键“拷贝”至已取消键集合中，这种取消策略就是我们常提到的“延迟取消”。）
            //  2. 再次检查已注册键集合（准确说是该集合中每个键的interest集合）。
            //  2.1. 系统底层会依次询问每个已经注册的通道是否准备好选择器所感兴趣的某种操作，
            //  2.1.1. 一旦发现某个通道已经就绪了，则会首先判断该通道是否已经存在在已选择键集合当中，
            //  2.1.1.1. 如果已经存在，则更新该通道在已注册键集合中对应的键的ready集合，
            //  2.1.1.2. 如果不存在，则首先清空该通道的对应的键的ready集合，然后重设ready集合，
            //           最后将该键存至已注册键集合中。这里需要明白，当更新ready集合时，在上次select（）中已经就绪的操作不会被删除，
            //           也就是ready集合中的元素是累积的，比如在第一次的selector对某个通道的read和write操作感兴趣，
            //           在第一次执行select（）时，该通道的read操作就绪，此时该通道对应的键中的ready集合存有read元素，
            //           在第二次执行select()时，该通道的write操作也就绪了，此时该通道对应的ready集合中将同时有read和write元素。
            //  3. 到现在我们已经知道一个通道的的键是如何被添加到已选择键集合中的，
            //     首先要记住：选择器不会主动删除被添加到已选择键集合中的键，而且被添加到已选择键集合中的键的ready集合只能被设置，而不能被清理。
            //     如果我们希望清空已选择键集合中某个键的ready集合该怎么办？
            //  3.1. 我们知道一个键在新加入已选择键集合之前会首先置空该键的ready集合，
            //       这样的话我们可以人为的将某个键从已注册键集合中移除最终实现置空某个键的ready集合。
            //       被移除的键如果在下一次的select（）中再次就绪，它将会重新被添加到已选择的键的集合中。
            //       这就是为什么要在每次迭代的末尾调用keyIterator.remove()。
            int count = selector.select();//阻塞模式，如果在数据没有读取前，另开线程的话，读写线程中 selector.select()会立刻返回
            System.out.println("1 循环 count = "+count);
            if (count > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                System.out.println("iterator size : " + selectionKeys.size());
                while (iterator.hasNext()){
                    //这个key是serverSocketChannel.register(...)返回的那个key，都是最开始注册对象，不会变
                    SelectionKey selectionKey = iterator.next();
                    //selectionKey已经获取值了，可以remove，remove不代表取消注册，取消注册用selectionKey.cancel();
                    //选择器的select()不会自动清除ready（就绪）区的已经处理过的channel，需要手动清除
                    iterator.remove();

                    if (selectionKey.isAcceptable()){
                        //取出SelectionKey所关联的Selector和Channel
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
                        //这个serverSocketChannel就是刚刚创建的那个serverSocketChannel
                        System.out.println("serverSocketChannel " + serverSocketChannel.hashCode());
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        //如果client提前关闭了链接  导致socketChannel是null
                        if (socketChannel == null)
                            return;
                        System.out.println("socketChannel " + socketChannel.hashCode());

                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }else if (selectionKey.isReadable()){
                        selectionKey.cancel();//读取的channel是每个客户端私有的，释放注册节省资源

                        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                        System.out.println("socketChannel " + socketChannel.hashCode());

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        int length = socketChannel.read(byteBuffer);

                        if (length > 0){
                            String content = new String(byteBuffer.array(), 0, byteBuffer.limit());
                            System.out.println(String.format("Server：接收到客户端内容：%s", content));

                            String receiver = "返回：" + content + "\t 成功";
                            byteBuffer = ByteBuffer.wrap(receiver.getBytes());

                            socketChannel.write(byteBuffer);
                            System.out.println(receiver);
                        }else {
                            System.out.println("客户端啥也没有传过来");
                        }
                        System.out.println("\n\n");
                    }
                }
            }
        }
    }

}
