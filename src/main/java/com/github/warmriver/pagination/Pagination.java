package com.github.warmriver.pagination;

import com.github.warmriver.pagination.tools.BitManipulation;

public class Pagination {
    private final static int defaultSegmentLength = 4;
    private int[][] segments;
    private Pagination(int[][] segments) {
        this.segments = segments;
    }

    /**
     * 每个offset分配的值为-1（包括）到 2的(64/n)次方-2(包括）, 初始cursor传递0时，返回的offset均是0，返回-1代表一个数据源已经被遍历完了，
     * 或者是这个数据源的offset超出了范围，用户需要自己估算offset以免溢出
     * @param n 分页的offset的数目，必须在0（包括）到8（包括）之间且必须能被64整除，
     * @return Pagination对象，用来解析offset或者计算复合cursor
     */
    public static Pagination getNormalPagination(int n){
        if (64 % n != 0 || n <= 0 || n > 8)
            // throw new UsageErrorException("segment must be dividable by 64 and should between 0 and 8(exclusive)");
            n = defaultSegmentLength;
        int segmentLength = 64 / n, bitOffset = 0;
        int[][] segments = new int[n][2];
        for(int i = 0; i < n; i ++) {
            segments[i][0] = segmentLength;
            segments[i][1] = bitOffset;
            bitOffset += segmentLength;
        }
        return new Pagination(segments);
    }
    /**
     *
     * @param segments 数组的长度为多数据源的offset的数目，数组的第二维
     * @return Pagination对象，用来解析offset或者计算复合cursor
     */
    public static Pagination getSelfPagination(int[][] segments){
        return new Pagination(segments);
    }

    public long getOffset(long cursur, int segmentIndex) {
       // if(segmentIndex < 0 || segmentIndex >= segments.length) throw new UsageErrorException("segmentIndex out of boundary");
        if(segmentIndex < 0 || segmentIndex >= segments.length) return -1l;
        return BitManipulation.getOffsetValue(segments[segmentIndex], cursur);
    }

    public long getCusor(long...offsets) {
        //if(offsets.length != segments.length) throw new UsageErrorException("offsets size must be the same as that of segments");
        if(offsets.length != segments.length) return -1;
        return BitManipulation.getCursor(segments, offsets);
    }
}

