package SmartHome.Devices;

import SmartHome.Interface.Adjustable;

public class Thermostat extends Device implements Adjustable {
    private int temperature;

    public Thermostat(String id, String room) {
        super(id, room , "Thermostat");
        this.temperature = 22;
    }

    public boolean adjust(int value) {
        if (value < 5 || value > 30) {
            System.out.println(Room + " " + deviceName() + ": temperature must be 5–30°C.");
            return false;
        }
        temperature = value;
        System.out.println(Room + " " + deviceName() + " temperature set to " + temperature + "°C.");
        return true;
    }

    public int getSetting() {
        return temperature;
    }

    public String getStatus() {
        return Room + " [" + deviceName() + "] Status - power: " + (isOn ? "ON" : "OFF") + ", temp: " + temperature + "°C";
    }
}
