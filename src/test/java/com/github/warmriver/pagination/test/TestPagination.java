package com.github.warmriver.pagination.test;

import com.github.warmriver.pagination.Pagination;
import org.junit.Assert;
import org.junit.Test;

public class TestPagination {
    @Test
    public void mainTest() {

        Pagination pagination = Pagination.getNormalPagination(4);
        long cusor = pagination.getCusor(0, 0, 0, 0);
        System.out.println(cusor);
        long n1 = pagination.getOffset(cusor, 0);
        long n2 = pagination.getOffset(cusor, 1);
        long n3 = pagination.getOffset(cusor, 2);
        long n4 = pagination.getOffset(cusor, 3);
        System.out.println("n1: " + n1 + "; n2: " + n2 + "; n3: " + n3 + "; n4: " + n4);

    }

    @Test
    public void testGetCursor() {
        Pagination pagination = Pagination.getNormalPagination(4);
        long cusor = pagination.getCusor(-1, 10, 0, 0);
        long n1 = pagination.getOffset(cusor, 0);
        Assert.assertEquals(-1l, n1);
        long n2 = pagination.getOffset(cusor, 1);
        Assert.assertEquals(10l, n2);
        long n3 = pagination.getOffset(cusor, 2);
        Assert.assertEquals(0l, n3);
        long n4 = pagination.getOffset(cusor, 3);
        Assert.assertEquals(0l, n4);
    }

    @Test
    public void testMaxLength() {
        Pagination pagination = Pagination.getNormalPagination(4);
        long cusor = pagination.getCusor(-1, (1 << 16) - 2, 0, 0);
        long n2 = pagination.getOffset(cusor, 1);
        Assert.assertEquals((1 << 16) -2l, n2);

        cusor = pagination.getCusor(-1, (1 << 16) - 1, 0, 0);
        n2 = pagination.getOffset(cusor, 1);
        Assert.assertNotEquals((1 << 16) -1l, n2);
    }
}
