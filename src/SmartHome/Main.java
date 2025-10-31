package SmartHome;

import SmartHome.Devices.Camera;
import SmartHome.Devices.Device;
import SmartHome.Devices.Light;
import SmartHome.Devices.Thermostat;
import SmartHome.Services.HomeController;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static HomeController controller = new HomeController();
    public static void main(String[] args) {
        mainMenu();
    }
    static void mainMenu() {
        System.out.println("========= Welcome to our SmartHome system ========= \n For info type 1.\n To make Controller System type 2.\n To exit type 3.\n Enter your choice: ");
        int response = input.nextInt();
        switch (response) {
            case 1:
                showInfo();
                break;
            case 2:
                controllerMenu();
                break;
            case 3:
                System.out.println("Thank you for using our system.");
                return;
            default:
                System.out.println("Invalid response. Please try again.");
                mainMenu();
        }
    }
    static void showInfo() {
        System.out.println("This system lets you control your smart home devices.\nYou can manage Lights, Cameras, and Thermostats interactively.\n");
        mainMenu();
    }
    static void controllerMenu() {
        System.out.println("Controller System: \n Type 1 to making Light System. \n Type 2 to making Camera System.\n Type 3 to making Thermostat System.\n Type 4 to get back.\n Enter your choice: ");
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
                mainMenu();
                break;
            default:
                System.out.println("Invalid response. Please try again.");
                controllerMenu();
        }
    }
    static void lightMenu() {
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
