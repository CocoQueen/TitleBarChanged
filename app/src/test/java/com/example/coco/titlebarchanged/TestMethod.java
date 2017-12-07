package com.example.coco.titlebarchanged;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by coco on 2017/12/1.
 */

public class TestMethod {
    @Test
    public void testWay(){

        new MainActivity().onScroll(100);
        assertEquals(4, 2 + 2);
    }
}
