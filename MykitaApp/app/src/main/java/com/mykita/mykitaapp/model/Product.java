package com.mykita.mykitaapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shreyas13732 on 5/29/2017.
 */

public class Product implements Parcelable {

    int id;
    int imageRes;
    int amount;
    String title;
    String color;
    String outerMaterial;
    String idealFor;
    String occassion;
    String cost;
    boolean cashOnDelivery;
    boolean creditCard;
    boolean netBanking;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOuterMaterial() {
        return outerMaterial;
    }

    public void setOuterMaterial(String outerMaterial) {
        this.outerMaterial = outerMaterial;
    }

    public String getIdealFor() {
        return idealFor;
    }

    public void setIdealFor(String idealFor) {
        this.idealFor = idealFor;
    }

    public String getOccassion() {
        return occassion;
    }

    public void setOccassion(String occassion) {
        this.occassion = occassion;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public boolean isCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(boolean cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public boolean isCreditCard() {
        return creditCard;
    }

    public void setCreditCard(boolean creditCard) {
        this.creditCard = creditCard;
    }

    public boolean isNetBanking() {
        return netBanking;
    }

    public void setNetBanking(boolean netBanking) {
        this.netBanking = netBanking;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.imageRes);
        dest.writeInt(this.amount);
        dest.writeString(this.title);
        dest.writeString(this.color);
        dest.writeString(this.outerMaterial);
        dest.writeString(this.idealFor);
        dest.writeString(this.occassion);
        dest.writeString(this.cost);
        dest.writeByte(this.cashOnDelivery ? (byte) 1 : (byte) 0);
        dest.writeByte(this.creditCard ? (byte) 1 : (byte) 0);
        dest.writeByte(this.netBanking ? (byte) 1 : (byte) 0);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.id = in.readInt();
        this.imageRes = in.readInt();
        this.amount = in.readInt();
        this.title = in.readString();
        this.color = in.readString();
        this.outerMaterial = in.readString();
        this.idealFor = in.readString();
        this.occassion = in.readString();
        this.cost = in.readString();
        this.cashOnDelivery = in.readByte() != 0;
        this.creditCard = in.readByte() != 0;
        this.netBanking = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
