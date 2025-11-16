package SmartHome;

import SmartHome.Devices.*;
import SmartHome.Services.*;

import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static HomeServices controller = new HomeServices();

    public static void main(String[] args) {
        Map<String, Device> loaded = controller.loadDevices();
        if(loaded != null)
            for(Map.Entry<String, Device> e : loaded.entrySet())
                controller.addDevice(e.getValue() , false);
        mainMenu();
    }
    static void mainMenu() {
        while (true) {
            System.out.println("========= Welcome to our SmartHome system =========");
            System.out.println("1. Show Info");
            System.out.println("2. Create new system");
            System.out.println("3. Remove Existing Device");
            System.out.println("4. Control existing system");
            System.out.println("5. Show All Device Statuses");
            System.out.println("6. Show All Device ID");
            System.out.println("7. Save Devices");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int response = input.nextInt();
            switch (response) {
                case 1:
                    showInfo();
                    break;
                case 2:
                    createNewSystem();
                    break;
                case 3:
                    removeDeviceMenu();
                    break;
                case 4:
                    controllerMenu();
                    break;
                case 5:
                    System.out.println("=== Device Statuses ===");
                    System.out.println(controller.listAllDevicesStatuses());
                    break;
                case 6:
                    System.out.println("=== Device ID ===");
                    controller.listAllDevicesID();
                    break;
                case 7:
                    controller.saveDevices();
                    break;
                case 8:
                    controller.saveDevices();
                    System.out.println("Thank you for using our system.");
                    return;
                default:
                    System.out.println("Invalid response. Try again.");
            }
        }
    }
    static void showInfo() {
        System.out.println("This system lets you control your smart home devices.");
        System.out.println("You can manage Lights, Cameras, and Thermostats interactively.");
    }
    static void removeDeviceMenu() {
        System.out.print("Enter the device ID to remove: ");
        input.nextLine();
        String id = input.nextLine();
        controller.removeDevice(id);
    }

    private static void createNewSystem() {
        System.out.println("=== Create New System ===");
        System.out.println("1. Light System");
        System.out.println("2. Camera System");
        System.out.println("3. Thermostat System");
        System.out.println("4. Back");
        System.out.print("Enter system Type: ");
        int type = input.nextInt();
        System.out.println("Enter Room Name : ");
        input.nextLine();
        String name = input.nextLine();
        Device d1;
        switch (type) {
            case 1:
                d1 = new Light(name + "_light", name);
                controller.addDevice(d1 , true);
                break;
            case 2:
                d1 = new Camera(name + "_cam", name);
                controller.addDevice(d1 , true);
                break;
            case 3:
                d1 = new Thermostat(name + "_thermostat", name);
                controller.addDevice(d1 , true);
                break;
            case 4:
                break;
            default:
                System.out.println("Erorr , pls try again");
        }
    }
    static void controllerMenu() {
        System.out.println("=== Controller System ===");
        System.out.println("1. Light System");
        System.out.println("2. Camera System");
        System.out.println("3. Thermostat System");
        System.out.println("4. Back");
        System.out.print("Enter your choice: ");
        int response = input.nextInt();
        switch (response) {
            case 1:
                lightMenu();
                break;
            case 2:
                cameraMenu();
                break;
            case 3:
                thermostatMenu();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid response. Please try again.");
                controllerMenu();
        }
    }
    static void lightMenu() {
        System.out.println("Enter System ID: ");
        input.nextLine();
        String id = input.nextLine();
        Device light = controller.find(id);
        System.out.println("=== " + light.getRoom() + "_light Menu ===");
        System.out.println("1. Turn On");
        System.out.println("2. Adjust Brightness");
        System.out.println("3. Turn Off");
        System.out.println("4. Back");
        System.out.print("Enter choice: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                controller.turnOn(light.id);
                break;
            case 2:
                System.out.print("Enter brightness [0–100]: ");
                int value = input.nextInt();
                controller.adjust(light.id, value);
                lightMenu();
                break;
            case 3:
                controller.turnOff(light.id);
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid response. Try again.");
                lightMenu();
        }
    }

    static void cameraMenu() {
        System.out.println("Enter System ID: ");
        input.nextLine();
        String id = input.nextLine();
        Device cam = controller.find(id);
        if(cam == null) {
            System.out.println("There is no device with that id.");
            return;
        }
        System.out.println("=== " + cam.getRoom() + "_camera Menu ===");
        System.out.println("1. Turn On");
        System.out.println("2. Start Recording");
        System.out.println("3. Stop Recording");
        System.out.println("4. Turn Off");
        System.out.println("5. Back");
        System.out.print("Enter choice: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                controller.turnOn(cam.id);
                break;
            case 2:
                controller.startRecording(cam.id);
                break;
            case 3:
                controller.stopRecording(cam.id);
                break;
            case 4:
                controller.turnOff(cam.id);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid response. Try again.");
                cameraMenu();
        }
    }
    static void thermostatMenu() {
        System.out.println("Enter System ID: ");
        input.nextLine();
        String id = input.nextLine();
        Device t = controller.find(id);
        System.out.println("=== " + t.getRoom() + "_thermostat Menu ===");
        System.out.println("1. Turn On");
        System.out.println("2. Adjust Temperature");
        System.out.println("3. Turn Off");
        System.out.println("4. Back");
        System.out.print("Enter choice: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                controller.turnOn(t.id);
                break;
            case 2:
                System.out.print("Enter temperature [5-30] : ");
                int temp = input.nextInt();
                controller.adjust(t.id, temp);
                thermostatMenu();
                break;
            case 3:
                controller.turnOff(t.id);
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid response. Try again.");
                thermostatMenu();
        }
    }
}
