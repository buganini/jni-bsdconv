package io.bsdconv;

public class Bsdconv {
    static {
        try {
            System.load("/home/buganini/repo/buganini/jni-bsdconv/src/main/jni/bsdconv.so");
//            System.loadLibrary("bsdconv");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }

    private long ins;

    private static native long _bsdconv_create(String conversion);
    private static native String _bsdconv_pack(long ins);

    public Bsdconv(String conversion) {
        this.ins = _bsdconv_create(conversion);
    }

    @Override
    public String toString() {
        return _bsdconv_pack(this.ins);
    }
}
