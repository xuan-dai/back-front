package cn.gorillahug.back.front.netty;

/**
 * @author daixuan
 * @version 2020/8/5 22:01
 */

import lombok.extern.slf4j.Slf4j;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * I/O映射缓冲区
 */
@Slf4j
public class MappedBuffer {

    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("filePath", "rw");
        FileChannel fc = raf.getChannel();

        MappedByteBuffer map = fc.map(FileChannel.MapMode.READ_WRITE, 0, 1024);

        map.put(0, (byte) 72);
        map.put(1023, (byte) 172);

        raf.close();
    }
}
