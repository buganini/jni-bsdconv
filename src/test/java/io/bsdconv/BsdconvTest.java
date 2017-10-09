package io.bsdconv;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void conv_chunk(){
        Bsdconv ins = new Bsdconv("big5:utf-8");
        ins.init();
        StringBuffer sb = new StringBuffer();
        sb.append(new String(ins.conv_chunk(new byte[]{(byte)0xa5})));
        sb.append(new String(ins.conv_chunk(new byte[]{(byte)0x5c})));
        sb.append(new String(ins.conv_chunk(new byte[]{(byte)0xaf})));
        sb.append(new String(ins.conv_chunk(new byte[]{(byte)0xe0})));
        sb.append(new String(ins.conv_chunk_last(new byte[]{})));
        assertEquals("功能", sb.toString());
    }
}