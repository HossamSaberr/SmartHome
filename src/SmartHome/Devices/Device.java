package SmartHome.Devices;

public abstract class Device {
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

    protected String deviceName() {
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
