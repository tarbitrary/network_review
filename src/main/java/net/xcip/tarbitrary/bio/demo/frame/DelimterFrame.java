package net.xcip.tarbitrary.bio.demo.frame;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p></p>
 *
 * @author tarbitrary
 * @version V1.0
 * @created_time 2019-12-12 19:46
 * @module 现货延期模块
 **/
public class DelimterFrame implements Frame {

    private byte delimter = '\n';
    @Override
    public void write2Frame(byte[] buf, OutputStream os) {
        DataOutputStream dos = new DataOutputStream(os);
        try {
            for (byte curr : buf) {
                if (curr == delimter) {
                    throw new UnsupportedOperationException("不支持数据里面有分割符的情景");
                }
                dos.writeByte(curr);
            }
            dos.writeByte(delimter);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void nextFrame(InputStream is) {

    }
}
