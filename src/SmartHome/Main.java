package SmartHome;

<<<<<<< HEAD
import SmartHome.Devices.*;
import SmartHome.Services.*;
=======
import SmartHome.Devices.Camera;
import SmartHome.Devices.Device;
import SmartHome.Devices.Light;
import SmartHome.Devices.Thermostat;
import SmartHome.Services.HomeController;
>>>>>>> 411b6784b78f247d6c6bcf5ec2422392884056d3

import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static HomeServices controller = new HomeServices();

    public static void main(String[] args) {
        Map<String, Device> loaded = controller.loadDevices();
        for(Map.Entry<String, Device> e : loaded.entrySet())
            controller.addDevice(e.getValue() , false);
        mainMenu();
    }
    static void mainMenu() {
        while (true) {
            System.out.println("========= Welcome to our SmartHome system =========");
            System.out.println("1. Show Info");
            System.out.println("2. Create new system");
            System.out.println("3. Control existing system");
            System.out.println("4. Show All Device Statuses");
            System.out.println("5. Show All Device ID");
            System.out.println("6. Save Devices");
            System.out.println("7. Exit");
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
                    controllerMenu();
                    break;
                case 4:
                    System.out.println("=== Device Statuses ===");
                    System.out.println(controller.listAllDevicesStatuses());
                    break;
                case 5:
                    System.out.println("=== Device ID ===");
                    controller.listAllDevicesID();
                    break;
                case 6:
                    controller.saveDevices();
                    break;
                case 7:
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
<<<<<<< HEAD
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
=======
        System.out.println("Welcome to our Light Menu. \nType 1 to list all Light systems.\nType 2 to make new Light system.\n Type 3 To control light system already exist.\nType 4 to get back.");
        int res = input.nextInt();
        if(res == 1) {
            System.out.println(controller.listAllLightsStatuses());
            lightMenu();
            return;
        }
        else if (res == 2) {
            System.out.println("Enter room name: ");
            input.nextLine();
            String room = input.nextLine();
            Light light = new Light(room + "_light", room);
            controller.addDevice(light);
            System.out.println("Type 1 to turn on the " + room + "_light.\nType 2 to adjust brightness for the " + room + "_light.\nType 3 to turn off the " + room + "_light.\nType 4 to get back.\nEnter your choice: ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    controller.turnOn(light.id);
                    lightMenu();
                    break;
                case 2:
                    System.out.print("Enter brightness [0–100]: ");
                    int value = input.nextInt();
                    controller.adjust(light.id, value);
                    lightMenu();
                    break;
                case 3:
                    controller.turnOff(light.id);
                    lightMenu();
                    break;
                case 4:
                    lightMenu();
                    return;
                default:
                    System.out.println("Invalid response. Try again.");
                    lightMenu();
            }
        }
        else if(res == 3) {
            System.out.println("Enter Your Light System id: ");
            String id = input.next();
            Device light2 = controller.getDeviceByID(id);
            if (light2 == null) {
                System.out.println("No System found with id " + id);
>>>>>>> 411b6784b78f247d6c6bcf5ec2422392884056d3
                lightMenu();
                return;
            }
            if (!(light2 instanceof Light)) {
                System.out.println("System with id " + id + " is not a Light System.");
                lightMenu();
                return;
            }
            String room = light2.Room;
            System.out.println("Type 1 to turn on the " + room + "_light.\nType 2 to adjust brightness for the " + room + "_light.\nType 3 to turn off the " + room + "_light.\nType 4 to get back.\nEnter your choice: ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    controller.turnOn(light2.id);
                    lightMenu();
                    break;
                case 2:
                    System.out.print("Enter brightness [0–100]: ");
                    int value = input.nextInt();
                    controller.adjust(light2.id, value);
                    lightMenu();
                    break;
                case 3:
                    controller.turnOff(light2.id);
                    lightMenu();
                    break;
                case 4:
                    lightMenu();
                    return;
                default:
                    System.out.println("Invalid response. Try again.");
                    lightMenu();
            }
        }
        else {
            controllerMenu();
            return;
        }
    }

    static void cameraMenu() {
<<<<<<< HEAD
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
=======
        System.out.println("Welcome to our Camera Menu. \nType 1 to list all Camera systems.\nType 2 to make new Camera system.\n Type 3 To control Camera system already exist.\nType 4 to get back.");
        int res = input.nextInt();
        if(res == 1) {
            System.out.println(controller.listAllCameraStatuses());
            cameraMenu();
            return;
        }
        else if(res == 2) {
            System.out.println("Enter room name: ");
            input.nextLine();
            String room = input.nextLine();
            Camera cam = new Camera(room + "_cam", room);
            controller.addDevice(cam);
            System.out.println("Type 1 to turn on the " + room + "_camera\n" + "Type 2 to start recording " + room + "\n" + "Type 3 to stop recording " + room + "\n" + "Type 4 to turn off the " + room + "_camera\n" + "Type 5 yo get back.\n" + "Enter your choice: ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    controller.turnOn(cam.id);
                    cameraMenu();
                    break;
                case 2:
                    controller.startRecording(cam.id);
                    cameraMenu();
                    break;
                case 3:
                    controller.stopRecording(cam.id);
                    cameraMenu();
                    break;
                case 4:
                    controller.turnOff(cam.id);
                    cameraMenu();
                    break;
                case 5:
                    controllerMenu();
                    break;
                default:
                    System.out.println("Invalid response. Try again.");
                    cameraMenu();
            }
        }
        else if(res == 3) {
            System.out.println("Enter Your Camera System id: ");
            String id = input.next();
            Device cam = controller.getDeviceByID(id);
            if (cam == null) {
                System.out.println("No System found with id " + id);
>>>>>>> 411b6784b78f247d6c6bcf5ec2422392884056d3
                cameraMenu();
                return;
            }
            if (!(cam instanceof Camera)) {
                System.out.println("System with id " + id + " is not a Camera System.");
                cameraMenu();
                return;
            }
            String room = cam.Room;
            System.out.println("Type 1 to turn on the " + room + "_camera\n" + "Type 2 to start recording " + room + "\n" + "Type 3 to stop recording " + room + "\n" + "Type 4 to turn off the " + room + "_camera\n" + "Type 5 yo get back.\n" + "Enter your choice: ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    controller.turnOn(cam.id);
                    cameraMenu();
                    break;
                case 2:
                    controller.startRecording(cam.id);
                    cameraMenu();
                    break;
                case 3:
                    controller.stopRecording(cam.id);
                    cameraMenu();
                    break;
                case 4:
                    controller.turnOff(cam.id);
                    cameraMenu();
                    break;
                case 5:
                    controllerMenu();
                    break;
                default:
                    System.out.println("Invalid response. Try again.");
                    cameraMenu();
            }
        }
        else {
            controllerMenu();
            return;
        }
    }
    static void thermostatMenu() {
<<<<<<< HEAD
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
=======
        System.out.println("Welcome to our Thermostat Menu. \nType 1 to list all Thermostat systems.\nType 2 to make new Thermostat system.\n Type 3 To control Thermostat system already exist.\nType 4 to get back.");
        int res = input.nextInt();
        if(res == 1) {
            System.out.println(controller.listAllThermostatsStatuses());
            thermostatMenu();
            return;
        }
        else if(res == 2) {
            System.out.println("Enter room name: ");
            input.nextLine();
            String room = input.nextLine();
            Thermostat t = new Thermostat(room + "_thermo", room);
            controller.addDevice(t);
            System.out.println("Type 1 to turn on " + room + "_thermostat.\n Type 2 to adjust " + room + " temperature.\n Type 3 to turn off " + room + "_thermostat. \n Type 4 to get back.\n Enter your choice: ");
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
                    controllerMenu();
                    return;
                default:
                    System.out.println("Invalid response. Try again.");
                    thermostatMenu();
            }
        }
        else if(res == 3) {
            System.out.println("Enter Your Thermostat System id: ");
            String id = input.next();
            Device tem = controller.getDeviceByID(id);
            if (tem == null) {
                System.out.println("No System found with id " + id);
>>>>>>> 411b6784b78f247d6c6bcf5ec2422392884056d3
                thermostatMenu();
                return;
            }
            if (!(tem instanceof Thermostat)) {
                System.out.println("System with id " + id + " is not a Thermostat System.");
                thermostatMenu();
                return;
            }
            String room = tem.Room;
            System.out.println("Type 1 to turn on " + room + "_thermostat.\n Type 2 to adjust " + room + " temperature.\n Type 3 to turn off " + room + "_thermostat. \n Type 4 to get back.\n Enter your choice: ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    controller.turnOn(tem.id);
                    break;
                case 2:
                    System.out.print("Enter temperature [5-30] : ");
                    int temp = input.nextInt();
                    controller.adjust(tem.id, temp);
                    thermostatMenu();
                    break;
                case 3:
                    controller.turnOff(tem.id);
                    break;
                case 4:
                    controllerMenu();
                    return;
                default:
                    System.out.println("Invalid response. Try again.");
                    thermostatMenu();
            }
        }
        else {
            controllerMenu();
            return;
        }
    }
}
