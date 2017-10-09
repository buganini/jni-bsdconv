package io.bsdconv;

import org.junit.Test;

public class BsdconvTest {

    @Test
    public void create_pack(){
        Bsdconv ins = new Bsdconv("utf-8:nfkd:utf-8");
        System.out.print(ins);
    }
}