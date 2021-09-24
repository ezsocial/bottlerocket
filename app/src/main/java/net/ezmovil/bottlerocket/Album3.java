package net.ezmovil.bottlerocket;

public class Album3 {
    private String low;
    private String weatherType;
    private String dayOfTheWeek;
    private String high;

    public Album3() {
    }

    public Album3(String low, String weatherType, String dayOfTheWeek, String high) {
        this.low = low;
        this.weatherType = weatherType;
        this.dayOfTheWeek = dayOfTheWeek;
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String name) {
        this.low = low;
    }

    public String getweatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }
}