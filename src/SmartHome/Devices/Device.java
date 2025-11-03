package SmartHome.Devices;

import java.io.Serializable;

public abstract class Device implements Serializable {
    public String id;
    public String Room;
    public String Type;
    public boolean isOn;

    public Device(String id, String room , String type) {
        this.id = id;
        this.Room = room;
        this.Type = type;
        this.isOn = false;
    }

<<<<<<< HEAD
    public String getId() {
        return id;
    }

    public String getRoom() {
        return Room;
    }

    public String getType() {
        return Type;
    }

    public boolean isOn() {
        return isOn;
    }

    protected String deviceName() {
=======
    public String deviceName() {
>>>>>>> 411b6784b78f247d6c6bcf5ec2422392884056d3
        return Type;
    }

    public void turnOn() {
        if (!isOn) {
            isOn = true;
            System.out.println(Room + " " + deviceName() + " is now ON.");
        }
        else {
            System.out.println(Room + " " + deviceName() + " was already ON.");
        }
    }

    public void turnOff() {
        if (isOn) {
            isOn = false;
            System.out.println(Room + " " + deviceName() + " is now OFF.");
        }
        else {
            System.out.println(Room + " " + deviceName() + " was already OFF.");
        }
    }

    public abstract String getStatus();
}
