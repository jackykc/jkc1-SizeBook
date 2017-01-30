package com.example.assignment1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by jkc1 on 2017-01-27.
 */
//http://stackoverflow.com/questions/13709362/passing-arraylist-of-objects-through-intent-java-android?rq=1
// http://stackoverflow.com/questions/222214/managing-constructors-with-many-parameters-in-java/222295#222295
//http://stackoverflow.com/questions/920306/sending-data-back-to-the-main-activity-in-android

public class Record implements Parcelable {

    private String name;
    private String date;
    private float neck;
    private float bust; // ask in forums later
    private float chest;
    private float waist;
    private float hip;
    private float inseam;
    private String comment;

    public Record() {
        this.name = "A text";
        this.date = "yyyy-mm-dd";
        this.neck = 0;
        this.bust = 0;
        this.chest = 0;
        this.waist = 0;
        this.hip = 0;
        this.inseam = 0;
        this.comment = "A comment";
    }

    private Record(Parcel in) {
        name = in.readString();
        date = in.readString();
        neck = in.readFloat();
        bust = in.readFloat();
        chest = in.readFloat();
        waist = in.readFloat();
        hip = in.readFloat();
        inseam = in.readFloat();
        comment = in.readString();
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(date);
        out.writeFloat(neck);
        out.writeFloat(bust);
        out.writeFloat(chest);
        out.writeFloat(waist);
        out.writeFloat(hip);
        out.writeFloat(inseam);
        out.writeString(comment);
    }

    public int describeContents() {
        return 0;
    }

    // Just cut and paste this for now
    public static final Parcelable.Creator<Record> CREATOR = new Parcelable.Creator<Record>() {
        public Record createFromParcel(Parcel in) {
            return new Record(in);
        }

        public Record[] newArray(int size) {
            return new Record[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getNeck() {
        return neck;
    }

    public void setNeck(float neck) {
        this.neck = neck;
    }

    public float getBust() {
        return bust;
    }

    public void setBust(float bust) {
        this.bust = bust;
    }

    public float getChest() {
        return chest;
    }

    public void setChest(float chest) {
        this.chest = chest;
    }

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public float getHip() {
        return hip;
    }

    public void setHip(float hip) {
        this.hip = hip;
    }

    public float getInseam() {
        return inseam;
    }

    public void setInseam(float inseam) {
        this.inseam = inseam;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return name + " | " + date.toString();
    }
}
