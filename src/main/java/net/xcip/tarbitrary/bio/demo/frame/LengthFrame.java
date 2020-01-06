package net.xcip.tarbitrary.bio.demo.frame;

import java.io.InputStream;
import java.io.OutputStream;

public class LengthFrame implements Frame {
    @Override
    public void write2Frame(byte[] buf, OutputStream os) {

    }

    @Override
    public byte[] nextFrame(InputStream is) {
        return new byte[0];
    }
}
