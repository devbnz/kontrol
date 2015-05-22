
package com.kontrol;


public class Market {
    public int id;
    public String name;
    public String longname;
    public String views;
    public String videos;
    public String subscriber;
    public String fetchid;
    public String comments;
    public double lat;
    public double lng;

    @Override
    public String toString() {
        return this.longname;
    }
}
