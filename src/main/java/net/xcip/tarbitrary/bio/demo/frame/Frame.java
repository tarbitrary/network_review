package net.xcip.tarbitrary.bio.demo.frame;

import java.io.InputStream;
import java.io.OutputStream;

public interface Frame {

    public void write2Frame(byte[] buf, OutputStream os);

    public void nextFrame(InputStream is);
}
