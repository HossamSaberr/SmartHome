package SmartHome.Services;

import SmartHome.Interface.Adjustable;
import SmartHome.Interface.Recordable;
import SmartHome.Devices.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeServices {
    private String saveFile = "devices.dat";
    private Map<String, Device> devices = new HashMap<>();
    public HomeServices() {}

    public void addDevice(Device d , boolean print) {
        devices.put(d.id , d);
        if(print)
            System.out.println("Added Successfully.\n");
    }

    public boolean removeDevice(String id) {
        Device removed = devices.remove(id);
        if (removed != null) {
            System.out.println("Device [" + id + "] removed successfully.");
            return true;
        }
        else {
            System.out.println("No device with ID [" + id + "] found.");
            return false;
        }
    }


    public Device find(String id) {
        for (Map.Entry<String, Device> d : devices.entrySet()) {
            if (d.getKey().equals(id)) {
                return d.getValue();
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

    public void saveDevices() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            out.writeObject(devices);
            System.out.println("SmartHome configuration saved successfully.\n");
        } catch (IOException e) {
            System.out.println("Error saving devices: " + e.getMessage() + "\n");
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Device> loadDevices() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(saveFile))) {
            Map<String, Device> devices = (Map<String, Device>) in.readObject();
            System.out.println("Devices restored from saved data.\n");
            return devices;
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh./n");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading devices: " + e.getMessage() + "\n");
        }
        return null;
    }

    public void listAllDevicesID() {
        if (devices.isEmpty()) {
            System.out.println("No systems created yet.");
            return;
        }
        for (Map.Entry<String, Device> d : devices.entrySet()) {
            System.out.println("ID: " + d.getKey() + " → " + d.getValue().getRoom() + " " + d.getValue().getType());
        }
    }

    public String listAllDevicesStatuses() {
        StringBuilder sb = new StringBuilder();
        boolean existAny = false;
        for (Map.Entry<String, Device> d : devices.entrySet()) {
            existAny = true;
            sb.append(d.getValue().getStatus()).append("\n");
        }
        if(existAny)
            return sb.toString();
        else
            return "There is no devices yet.";
    }
}
