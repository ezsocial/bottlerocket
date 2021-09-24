package net.ezmovil.bottlerocket.info;

public final class infoWeatherImage {
    public static String _txtImage;
    public static String _name;
    public static String _geonameid;

    public infoWeatherImage() {
    	;
    }

    public void setTxt(String txtImage) {
        _txtImage = txtImage;
    }
    
    public String getTxt()
    {
        return _txtImage;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getName()
    {
        return _name;
    }

    public void setGeonameid(String geonameid) {
        _geonameid = geonameid;
    }

    public String getGeonameid()
    {
        return _geonameid;
    }
}
