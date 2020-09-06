package cn.gorillahug.back.front.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * java - nio模型，向seletor中注册事件，等待selector通知后，处理相应的事件
 * @author daixuan
 * @version 2020/8/10 20:47
 */
public class SelectorExample {

    Selector selector;
    // 缓冲区
    ByteBuffer buffer = ByteBuffer.allocate(10);

    /**
     * 开始监听
     */
    public void listen() {
        try {
            while (true) {
                selector = getSelector();
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    process(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册事件
     */
    private Selector getSelector() throws IOException {
        // 获取的是WindowsSelectorImpl
        Selector sel = Selector.open();

        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);

        int port = 18080;
        ServerSocket socket = server.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        socket.bind(address);

        server.register(sel, SelectionKey.OP_ACCEPT);
        return sel;
    }

    /**
     * 根据不同的事件做处理
     *
     * @param key
     * @throws IOException
     */
    private void process(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel channel = server.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        }
        // 读数据
        else if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            int len = channel.read(buffer);
            if (len > 0) {
                buffer.flip();
                String content = new String(buffer.array(), 0, len);
                SelectionKey sKey = channel.register(selector, SelectionKey.OP_WRITE);
                sKey.attach(content);
            } else {
                channel.close();
            }
            buffer.clear();
        }
        // 写事件
        else if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            String content = (String) key.attachment();
            ByteBuffer block = ByteBuffer.wrap(("输出内容" + content).getBytes());
            if (block != null) {
                channel.write(block);
            } else {
                channel.close();
            }
        }
    }
}
