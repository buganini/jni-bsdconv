package io.bsdconv;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.UnsupportedEncodingException;

public class BsdconvTest {

    @Test
    public void create_pack() {
        Bsdconv ins = new Bsdconv("utf-8:nfkd:utf-8");
        assertEquals("ASCII,_UTF-8:_NFKD:_NF-HANGUL-DECOMPOSITION:_NF-ORDER:_UTF-8,ASCII", ins.toString());
    }

    @Test
    public void conv() {
        Bsdconv ins = new Bsdconv("utf-8:upper:utf-8");
        try {
            String r = new String(ins.conv("lala".getBytes("UTF-8")));
            assertEquals("LALA", r);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}