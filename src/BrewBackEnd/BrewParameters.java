package BrewBackEnd;

import android.os.Parcel;
import android.os.Parcelable;

public class BrewParameters implements Parcelable {
	private BrewType brew_style;
	private double duration;
	private double water_weight;
	private double ratio;
	private double temp;
	private final static double oz_to_grams = 28.35;

	// parcel parts
	public BrewParameters(Parcel in) {
		String[] data = new String[5];

		in.readStringArray(data);
		this.brew_style = BrewType.valueOf(data[0]);
		this.duration = Double.parseDouble(data[1]);
		this.water_weight = Double.parseDouble(data[2]);
		this.ratio = Double.parseDouble(data[3]);
		this.temp = Double.parseDouble(data[4]);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

		dest.writeStringArray(new String[] {
				this.brew_style.name(),
				Double.toString(this.duration),
				Double.toString(this.water_weight),
				Double.toString(this.ratio),
				Double.toString(this.temp) });
	}

	public static final Parcelable.Creator<BrewParameters> CREATOR = new Parcelable.Creator<BrewParameters>() {

		@Override
		public BrewParameters createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new BrewParameters(source); // using parcelable constructor
		}

		@Override
		public BrewParameters[] newArray(int size) {
			// TODO Auto-generated method stub
			return new BrewParameters[size];
		}
	};

	// Constructors
	public BrewParameters(BrewType type) {
		this.brew_style = type;
		this.duration = 0;
		this.water_weight = 0;
		this.ratio = 0;
		this.temp = 0;

	}

	public BrewParameters(BrewType type, double time) {
		this.brew_style = type;
		this.duration = time;
		this.water_weight = 0;
		this.temp = 0;
		this.ratio = 0;
	}

	public BrewParameters(BrewType type, double time, double brew_amnt) {
		this.brew_style = type;
		this.duration = time;
		this.water_weight = brew_amnt;
		this.ratio = 0;
		this.temp = 0;
	}

	public BrewParameters(BrewType type, double time, double brew_amnt,
			double brew_ratio) {
		this.brew_style = type;
		this.duration = time;
		this.water_weight = Math.round(brew_amnt);
		this.ratio = brew_ratio;
		this.temp = 0;
	}

	public void setTime(double brew_time) {
		this.duration = brew_time;
	}

	public void setAmountWeight(double brew_amnt) {
		this.water_weight = brew_amnt;
	}

	// sets amount in grams using ounces and returns grams weight
	public double setAmountVolume(double brew_amnt_oz) {
		this.setAmountWeight(Math.round(brew_amnt_oz * oz_to_grams * 100.0)/100.0);
		return this.water_weight;
	}

	public void setTemp(double temperature) {
		this.temp = temperature;
	}

	// always call after setting amount
	public void setRatio(double brew_ratio) {
		this.ratio = brew_ratio;
	}

	public BrewType getStyle() {
		return this.brew_style;
	}

	public double getDuration() {
		return this.duration;
	}

	public double getWaterWeight() {
		return this.water_weight;
	}

	public double getWaterVolume() {
		return Math.round(this.getWaterWeight()  * 100.0 / oz_to_grams)/100.0;
	}

	public double getRatio() {
		return this.ratio;
	}

	public double getCoffeeWeight() {
		return Math.round(100.0* this.water_weight/this.ratio )/100.0;
	}

	public double getTemp() {
		return this.temp;
	}

}
