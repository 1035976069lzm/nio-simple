package com.sld.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * @author lzm
 * @date 2017年12月25日
 */
public class ChannelTest {

	/**
	 * 根据流获取通道
	 * 
	 * @throws IOException
	 */
	@Test
	public void test01() throws IOException {

		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			fis = new FileInputStream("dbutils.png");
			inChannel = fis.getChannel();
			fos = new FileOutputStream("1.png");
			outChannel = fos.getChannel();

			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while (inChannel.read(buffer) != -1) {
				buffer.flip();
				outChannel.write(buffer);
				buffer.clear();
			}

		} catch (Exception e) {
		} finally {
			outChannel.close();
			inChannel.close();
			fos.close();
			fis.close();
		}

	}

}
