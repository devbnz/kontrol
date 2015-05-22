package com.kontrol;

/**
 * Created by erik on 13.05.15.
 */
public class Jobresponse {

        public String website;
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

