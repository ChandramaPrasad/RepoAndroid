package com.motion.pi;

import android.os.Parcel;
import android.os.Parcelable;

public class AlaramData  implements Parcelable{

	public String id ="";
	public String from_location ="";
	public String to_location = "";
	public String time ="";


	public AlaramData(String from_location,String to_location,String time){

		this.from_location = from_location;
		this.to_location = to_location;
		this.time = time;

	}

	public AlaramData(Parcel source) {

		this.id = source.readString();
		this.from_location = source.readString();
		this.to_location = source.readString();
		this.time = source.readString();

	}

	public AlaramData() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(from_location);
		dest.writeString(to_location);
		dest.writeString(time);


	}

	public static final Parcelable.Creator<AlaramData> CREATOR = new Parcelable.Creator<AlaramData>() {

		@Override
		public AlaramData createFromParcel(Parcel source) {

			return new AlaramData(source);
		}

		@Override
		public AlaramData[] newArray(int size) {
			// TODO Auto-generated method stub
			return new AlaramData[size];
		}
	};



}
