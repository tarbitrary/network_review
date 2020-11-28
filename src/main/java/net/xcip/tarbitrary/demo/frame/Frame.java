package net.xcip.tarbitrary.demo.frame;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 成帧器
 */
public interface Frame {

    public void write2Frame(byte[] buf, OutputStream os);

    public byte[] nextFrame(InputStream is);
}
