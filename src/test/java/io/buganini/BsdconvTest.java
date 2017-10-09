package io.buganini;

import org.junit.Test;

import static org.junit.Assert.*;

public class BsdconvTest {

    @Test
    public void create_pack(){
        Bsdconv ins = new Bsdconv("utf-8:nfkd:utf-8");
        System.out.print(ins);
    }
}