package edu.purdue.vieck.htmlcolorwheel;

/**
 * Created by vieck on 6/28/15.
 */
public class CustomColor {

    private String name;
    private String hexValue;
    private long ID;

    public CustomColor() {
    }


    public CustomColor(String name, String hexValue, long ID) {
        this.name = name;
        this.ID = ID;
        this.hexValue = hexValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getHexValue() {
        return hexValue;
    }

    public void setHexValue(String hexValue) {
        this.hexValue = hexValue;
    }


}
