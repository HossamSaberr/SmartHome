package SmartHome.Services;

import SmartHome.Interface.Adjustable;
import SmartHome.Interface.Recordable;
import SmartHome.Devices.Device;

import java.util.ArrayList;
import java.util.List;

public class HomeController {
    private final List<Device> devices = new ArrayList<>();

    public HomeController() {}

    public void addDevice(Device d) {
        devices.add(d);
        System.out.println("Added Successfully.\n : " + d.getStatus());
    }

    private Device find(String id) {
        for (Device d : devices) {
            if (d.id.equals(id)) {
                return d;
            }
        }
        return null;
    }

    public void turnOn(String id) {
        Device d = find(id);
        if (d == null) {
            System.out.println("No device with id " + id);
            return;
        }
        d.turnOn();
    }

    public void turnOff(String id) {
        Device d = find(id);
        if (d == null) {
            System.out.println("No device with id " + id);
            return;
        }
        d.turnOff();
    }

    public boolean adjust(String id, int value) {
        Device d = find(id);
        if (d == null) {
            System.out.println("No device with id " + id);
            return false;
        }
        if (d instanceof Adjustable) {
            Adjustable a = (Adjustable) d;
            if(a.adjust(value))
                return true;
            else
                return false;
        }
        else {
            System.out.println(d.Room + " does not support adjustment.");
            return false;
        }
    }

    public void startRecording(String id) {
        Device d = find(id);
        if (d == null) {
            System.out.println("No device with id " + id);
            return;
        }
        if (d instanceof Recordable) {
            Recordable r = (Recordable) d;
            r.startRecording();
        }
        else {
            System.out.println(d.Room + " does not support record.");
        }
    }

    public void stopRecording(String id) {
        Device d = find(id);
        if (d == null) {
            System.out.println("No device with id " + id);
            return;
        }
        if (d instanceof Recordable) {
            Recordable r = (Recordable) d;
            r.stopRecording();
        }
        else {
            System.out.println(d.Room + " does not support record.");
        }
    }

    public String listAllDevicesStatuses() {
        StringBuilder sb = new StringBuilder();
        for (Device d : devices) {
            sb.append(d.getStatus()).append("\n");
        }
        return sb.toString();
    }
}
