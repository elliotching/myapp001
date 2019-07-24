package hexa.app001;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    private Context context;
    private String mTitle;
    private boolean mOnline;
    private int mImage;
    private String mSubtitle;
    private String mDesc;

    public Contact(String title, boolean online, int image, String subtitle, String description) {
        mTitle = title;
        mOnline = online;
        mImage = image;
        mSubtitle = subtitle;
        mDesc = description;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public boolean isOnline() {
        return mOnline;
    }

    public int getImage() {
        return mImage;
    }

    public String getDesc() {
        return mDesc;
    }

    public String getSubtitle() {
        return mSubtitle;
    }


    // In constructor you will read the variables from Parcel. Make sure to read them in the same sequence in which you have written them in Parcel.
    Contact(Parcel in) {
        this.mTitle = in.readString();
        this.mImage = in.readInt();
        this.mSubtitle = in.readString();
        this.mDesc = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        // Write data in any order
        parcel.writeString(this.getTitle());
        parcel.writeInt(this.getImage());
        parcel.writeString(this.getSubtitle());
        parcel.writeString(this.getDesc());
    }

}