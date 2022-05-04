package cn.zhang.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 一、缓冲区(Buffer)：在Java NIO中负责数据的存取。缓冲区就是数组。用于存储不同类型的数据
 * 更具数据类型的不同（boolean除外），提供了相应的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 上述缓冲区的管理方式几乎一致，通过allocate()获取缓冲区
 *
 * 二、缓冲区存取数据两个核心方法：
 * put()：存入数据到缓冲区
 * get()：获取缓冲区中的数据
 *
 * 三、缓冲区的核心属性
 *     private int mark = -1;
 *     private int position = 0;：位置，表示缓冲区正在操作数据的位置。
 *     private int limit;：界限，表示缓冲区可以操作数据的大小。（limit后数据不能进行读写）
 *     private int capacity;：容量，表示缓冲区最大存储数据的容量。一旦声明不可改变。
 *     position <= limit <= capacity
 *
 *
 */
public class TestBuffer {

    @Test
    public void test1(){
        String str = "abcde";
        //1.分配一个指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("---------allocate----------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //2.利用put()写入数据到缓冲区
        buffer.put(str.getBytes());
        System.out.println("--------put()-------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //3.切换模式
        buffer.flip();
        System.out.println("--------flip()-------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
    }

}
