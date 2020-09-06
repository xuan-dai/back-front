package cn.gorillahug.back.front.netty;

/**
 * @author daixuan
 * @version 2020/8/5 22:01
 */

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;

/**
 * 创建子分区
 * 概念buffer position limit capacity slice readOnlyBuffer
 */
@Slf4j
public class BufferSlice {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();

        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 10;
            slice.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        // 只读缓冲区
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        try {
            readOnlyBuffer.put(0, (byte) 100);
        } catch (ReadOnlyBufferException e) {
            log.error("无法修改只读缓冲区内的数据");
        }

        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.get(i);
            b += 20;
            buffer.put(i, b);
        }

        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }
    }
}
