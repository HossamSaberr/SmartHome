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

    public Device getDeviceByID(String id) {
        return find(id);
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

    public String listAllLightsStatuses() {
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (Device d : devices) {
            if(d.deviceName() == "Light") {
                sb.append(d.getStatus()).append("\n");
                found = true;
            }
        }
        if(found)
            return sb.toString();
        else
            return "There is no Light System Yet.\n";
    }

    public String listAllCameraStatuses() {
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (Device d : devices) {
            if(d.deviceName() == "Camera") {
                sb.append(d.getStatus()).append("\n");
                found = true;
            }
        }
        if(found)
            return sb.toString();
        else
            return "There is no Camera System Yet.\n";
    }

    public String listAllThermostatsStatuses() {
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (Device d : devices) {
            if(d.deviceName() == "Thermostat") {
                sb.append(d.getStatus()).append("\n");
                found = true;
            }
        }
        if(found)
            return sb.toString();
        else
            return "There is no Thermostat System Yet.\n";
    }

    public String listAllDevicesStatuses() {
        StringBuilder sb = new StringBuilder();
        for (Device d : devices) {
            sb.append(d.getStatus()).append("\n");
        }
        return sb.toString();
    }
}


public boolean removeDevice(String id) {
        // removeIf returns true if at least one element was removed.
        // This efficiently finds and removes the device in a single pass.
        return devices.removeIf(d -> d.id.equals(id));
    }