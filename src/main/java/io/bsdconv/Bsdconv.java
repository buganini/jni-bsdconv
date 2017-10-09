package io.bsdconv;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class Bsdconv {
    static {
        try {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                @Override
                public Void run() {
                    System.loadLibrary("bsdconv-jni");
                    return null;
                }
            });

        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }

    private long ins;

    private static native long _bsdconv_create(String conversion);
    private static native byte[] _bsdconv_conv(long ins, byte[] data);
    private static native void _bsdconv_init(long ins);
    private static native byte[] _bsdconv_conv_chunk(long ins, byte[] data);
    private static native byte[] _bsdconv_conv_chunk_last(long ins, byte[] data);
    private static native String _bsdconv_pack(long ins);

    public Bsdconv(String conversion) {
        this.ins = _bsdconv_create(conversion);
    }

    public byte[] conv(byte[] data){
        return _bsdconv_conv(this.ins, data);
    }

    public void init(){
        _bsdconv_init(this.ins);
    }

    public byte[] conv_chunk(byte[] data){
        return _bsdconv_conv_chunk(this.ins, data);
    }

    public byte[] conv_chunk_last(byte[] data){
        return _bsdconv_conv_chunk_last(this.ins, data);
    }

    @Override
    public String toString() {
        return _bsdconv_pack(this.ins);
    }
}
