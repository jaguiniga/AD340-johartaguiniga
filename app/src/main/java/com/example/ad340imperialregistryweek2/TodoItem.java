package com.example.ad340imperialregistryweek2;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;

public class TodoItem  implements Parcelable {
    private String imageUrl;
    private String lat;
    private boolean liked;
    private String longitude;
    private String name;
    private String uid;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public TodoItem() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public TodoItem(String imageUrl, String lat,  String longitude, String name, String uid,  boolean liked) {

        this.imageUrl = imageUrl;
        this.lat = lat;
        this.liked = liked;
        this.longitude = longitude;
        this.name = name;
        this.uid = uid;
    }

    public TodoItem(Parcel in) {
        imageUrl = in.readString();
        lat = in.readString();
        liked = in.readBoolean();
        longitude = in.readString();
        name = in.readString();
        uid = in.readString();

    }

    public static final Creator<TodoItem> CREATOR = new Creator<TodoItem>() {
        @Override
        public TodoItem createFromParcel(Parcel in) {
            return new TodoItem(in);
        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("imageUrl", imageUrl);
        result.put("lat", lat);
        result.put("liked", liked);
        result.put("longitude", longitude);
        result.put("name", name);
        result.put("uid", uid);

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(lat);
        dest.writeBoolean(liked);
        dest.writeString(longitude);
        dest.writeString(name);
        dest.writeString(uid);

    }


}
