package io.bsdconv;

public class Bsdconv {
    static {
        try {
            System.loadLibrary("bsdconv-jni");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }

    private long ins;

    private static native long _bsdconv_create(String conversion);
    private static native byte[] _bsdconv_conv(long ins, byte[] data);
    private static native void _bsdconv_init(long ins);
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

    @Override
    public String toString() {
        return _bsdconv_pack(this.ins);
    }
}
