package SmartHome.Devices;

import SmartHome.Interface.Adjustable;

public class Light extends Device implements Adjustable {
    private int brightness_lvl;

    public Light(String id, String room) {
        super(id, room , "Light");
        this.brightness_lvl = 0;
    }

    public boolean adjust(int value) {
        if (value < 0 || value > 100) {
            System.out.println(Room + " " + deviceName() + ": brightness must be 0..100.");
            return false;
        }
        brightness_lvl = value;
        System.out.println(Room + " " + deviceName() + " brightness set to " + brightness_lvl + "%.");
        return true;
    }

    public int getSetting() {
        return brightness_lvl;
    }

    public String getStatus() {
        return "System " + " [" + deviceName() + "] with id " + id + " for "+ Room + " => Status - power: " + (isOn ? "ON" : "OFF") + ", brightness: " + brightness_lvl + "%";
    }
}
