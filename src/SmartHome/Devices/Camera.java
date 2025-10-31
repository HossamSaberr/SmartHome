package SmartHome.Devices;

import SmartHome.Interface.Recordable;

public class Camera extends Device implements Recordable {
    private boolean recording;

    public Camera(String id, String room) {
        super(id, room , "Camera");
        recording = false;
    }

    public void startRecording() {
        if (!isOn) {
            System.out.println(Room + " " + deviceName() + ": turn ON before recording.");
            return;
        }
        if (!recording) {
            recording = true;
            System.out.println(Room + " " + deviceName() + " started recording.");
        }
        else {
            System.out.println(Room + " " + deviceName() + " is already recording.");
        }
    }

    public void stopRecording() {
        if (recording) {
            recording = false;
            System.out.println(Room + " " + deviceName() + " stopped recording.");
        }
        else {
            System.out.println(Room + " " + deviceName() + " was not recording.");
        }
    }

    public boolean isRecording() {
        return recording;
    }

    public String getStatus() {
        return "System " + " [" + deviceName() + "] with id " + id + " for "+ Room + " => Status - power: " + (isOn ? "ON" : "OFF") + ", recording: " + (recording ? "YES" : "NO");
    }
}
