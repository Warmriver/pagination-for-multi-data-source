package com.github.warmriver.pagination.tools;


public class BitManipulation {

    private final static int[] bit_offset_photo = {20, 0};
    private final static int[] bit_offset_level = {20, 20};
    private final static int[] bit_offset_user = {12, 40};
    private final static int[] bit_offset_label = {12, 52};

    public static long getSubjectUserOffset(long n) {
        return (((1 << bit_offset_user[0]) - 1) & (n >> bit_offset_user[1])) - 1;
    }

    public static long getSubjectLevelOffset(long n) {
        return (((1 << bit_offset_level[0]) - 1) & (n >> bit_offset_level[1])) - 1;
    }

    public static long getSubjectPhotoOffset(long n) {
        return (((1 << bit_offset_photo[0]) - 1) & (n >> bit_offset_photo[1])) - 1;
    }

    public static long getSubjectLabelOffset(long n) {
        return (((1 << bit_offset_label[0]) - 1) & (n >> bit_offset_label[1])) - 1;
    }

    public static long getOffsetValue(int[] segment, long cursor) {
        if(cursor == 0) return 0;
        if(cursor == -1) return -1;
        return (((1 << segment[0]) - 1) & (cursor >> segment[1])) - 1;
    }

    public static long getCursor(long photoOffset, long levelOffset, long userOffset, long labelOffset) {
        photoOffset += 1;
        levelOffset += 1;
        levelOffset <<= bit_offset_level[1];
        userOffset += 1;
        userOffset <<= bit_offset_user[1];
        labelOffset += 1;
        labelOffset <<= bit_offset_label[1];

        return photoOffset| levelOffset | userOffset | labelOffset;
    }
    public static long getCursor(int[][] segments, long...offsets) {
        long cursor = 0l;
        for(int i = 0; i < segments.length; i++) {
            int[] bitLengthAndOffset = segments[i];
            long value = offsets[i];
            value += 1;
            value <<= bitLengthAndOffset[1];
            cursor |= value;
        }
        return cursor;
    }
}