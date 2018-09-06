package com.sun;

/**
 * 
 */
public class Pic implements Comparable<Pic> {
    public int[][] picArr;
    public int age = 1;
    public double similarAbs = 0;
    public double similarRev = 0;

    public Pic(int[][] picArr) {
        this.picArr = picArr;
    }

    public Pic() {
    }

    @Override
    public int compareTo(Pic o) {
        if (this.similarRev < o.similarRev) {
            return -1;
        } else if (this.similarRev > o.similarRev) {
            return 1;
        } else {
            return 0;
        }
    }
}
