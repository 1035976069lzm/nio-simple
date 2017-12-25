package com.sld.test;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * 
 * @author lzm
 * @date 2017年12月25日
 */
public class BufferTest {
	/**
	 * nio 定义缓冲区，它们是数据容器
	 * 
	 * NIO API的中心抽象是： Buffers ，它们是数据的容器;
	 * 
	 * Charsets及其相关的解码器和编码器 ， 它在字节和Unicode字符之间进行转换;
	 * 
	 * Channels各种类型，代表连接 能够执行I / O操作的实体;
	 * 
	 * 选择器和选择键 ，与 可选择的渠道定义了一个multiplexed, non-blocking I/O设施。
	 * 
	 * 一、缓冲区（Buffer）：在 Java NIO 中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
	 * 
	 * 根据数据类型不同（boolean 除外），提供了相应类型的缓冲区： ByteBuffer CharBuffer ShortBuffer
	 * IntBuffer LongBuffer FloatBuffer DoubleBuffer
	 * 
	 * 上述缓冲区的管理方式几乎一致，通过 allocate() 获取缓冲区
	 * 
	 * 二、缓冲区存取数据的两个核心方法： put() : 存入数据到缓冲区中 get() : 获取缓冲区中的数据
	 * 
	 * 三、缓冲区中的四个核心属性： capacity : 容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。 limit :
	 * 界限，表示缓冲区中可以操作数据的大小。（limit 后数据不能进行读写） position : 位置，表示缓冲区中正在操作数据的位置。
	 * 
	 * mark : 标记，表示记录当前 position 的位置。可以通过 reset() 恢复到 mark 的位置
	 * 
	 * 0 <= mark <= position <= limit <= capacity
	 * 
	 * 四、直接缓冲区与非直接缓冲区： 非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中 直接缓冲区：通过
	 * allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
	 */
	@Test
	public void test02() {
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		System.out.println(buffer.isDirect());
	}

	@Test
	public void testMark() {
		String str = "abcde";
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put(str.getBytes());
		System.out.println(buffer.position());
		buffer.flip();
		byte[] dst = new byte[2];
		buffer.get(dst);
		System.out.println(buffer.position());
		buffer.mark();// 标记
		buffer.get(dst);
		System.out.println(buffer.position());
		buffer.reset();// 回到标记位置
		System.out.println(buffer.position());
		if (buffer.hasRemaining()) {
			System.out.println(buffer.remaining());
		}

	}

	@Test
	public void test01() {
		System.out.println("------allocate------");
		int capacity = 1024;
		// 分配容量
		ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
		// 当前位置
		System.out.println("position = " + byteBuffer.position());
		// 大小限制
		System.out.println("limit = " + byteBuffer.limit());
		// 容量
		System.out.println("capacity = " + byteBuffer.capacity());
		System.out.println("------put------");
		String str = "abcde";
		byteBuffer.put(str.getBytes());
		System.out.println("position = " + byteBuffer.position());
		System.out.println("limit = " + byteBuffer.limit());
		System.out.println("capacity = " + byteBuffer.capacity());
		System.out.println("------flip------");
		byteBuffer.flip();
		System.out.println("position = " + byteBuffer.position());
		System.out.println("limit = " + byteBuffer.limit());
		System.out.println("capacity = " + byteBuffer.capacity());
		byte[] b = new byte[byteBuffer.limit()];
		byteBuffer.get(b);
		System.out.println("------get------");
		System.out.println(new String(b));
		System.out.println("position = " + byteBuffer.position());
		System.out.println("limit = " + byteBuffer.limit());
		System.out.println("capacity = " + byteBuffer.capacity());

		byte[] b1 = new byte[byteBuffer.limit()];
		System.out.println("------rewind------");
		// * 可重复读取
		byteBuffer.rewind();
		System.out.println("position = " + byteBuffer.position());
		System.out.println("limit = " + byteBuffer.limit());
		System.out.println("capacity = " + byteBuffer.capacity());

		byteBuffer.get(b1);
		System.out.println(new String(b));
		System.out.println("------get------");
		System.out.println("position = " + byteBuffer.position());
		System.out.println("limit = " + byteBuffer.limit());
		System.out.println("capacity = " + byteBuffer.capacity());
		// * 属性恢复为初始位置，但是，数据依然存在
		byteBuffer.clear();
		System.out.println("------clear------");
		System.out.println("position = " + byteBuffer.position());
		System.out.println("limit = " + byteBuffer.limit());
		System.out.println("capacity = " + byteBuffer.capacity());

	}

}
