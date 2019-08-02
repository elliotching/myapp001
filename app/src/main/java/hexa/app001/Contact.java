package hexa.app001;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.ObservableField;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Contact extends RealmObject implements Parcelable {
//    private Context context;
    @PrimaryKey
    private long id;
    private String mTitle;
    private String mImage;
    private String mSubtitle;
    private String mDesc;

    public Contact(){
        super();
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

    public long getId() {
        return id;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setImage(String imageURL) {
        this.mImage = imageURL;
    }

    public void setSubtitle(String mSubtitle) {
        this.mSubtitle = mSubtitle;
    }

    public void setDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getTitle() {
        return mTitle;
    }


    public String getImage() {
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
        this.id = in.readLong();
        this.mTitle = in.readString();
        this.mImage = in.readString();
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
        parcel.writeLong(this.id);
        parcel.writeString(this.getTitle());
        parcel.writeString(this.getImage());
        parcel.writeString(this.getSubtitle());
        parcel.writeString(this.getDesc());
    }

    public void setId(int id) {
        this.id = id;
    }
}