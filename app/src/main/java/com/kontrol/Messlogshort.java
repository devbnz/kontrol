package com.kontrol;

/**
 * Created by Erik on 5/3/2015.
 */
public class Messlogshort {

    public int unixtime;
    public String longname;
    public String mvalue;
   /* public double lat;
    public double lng;
*/
    @Override
    public String toString() {
        return this.longname;
    }
}
