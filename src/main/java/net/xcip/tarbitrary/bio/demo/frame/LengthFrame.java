package net.xcip.tarbitrary.bio.demo.frame;

import java.io.*;

public class LengthFrame implements Frame {

    public static final int SHIFT = 8;
    public static final int MASK = 0xff;
    @Override
    public void write2Frame(byte[] buf, OutputStream os) {
        int length = buf.length;
        try {
            os.write((length >> SHIFT) & MASK);
            os.write(length & MASK);
            os.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public byte[] nextFrame(InputStream is) {
        DataInputStream dataInputStream = new DataInputStream(is);
        try {
            int length = dataInputStream.readUnsignedShort();
            byte[] buf = new byte[length];
            dataInputStream.readFully(buf);

            return buf;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
