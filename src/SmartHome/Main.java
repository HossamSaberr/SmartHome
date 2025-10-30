package SmartHome;

import SmartHome.Devices.Camera;
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
                break;
            case 2:
                System.out.print("Enter brightness [0–100]: ");
                int value = input.nextInt();
                if(!controller.adjust(light.id, value))
                    lightMenu();
                break;
            case 3:
                controller.turnOff(light.id);
                break;
            case 4:
                controllerMenu();
                return;
            default:
                System.out.println("Invalid response. Try again.");
                lightMenu();
        }
    }

    static void cameraMenu() {
        System.out.println("Enter room name: ");
        input.nextLine();
        String room = input.nextLine();
        Camera cam = new Camera(room + "_cam", room);
        controller.addDevice(cam);
        System.out.println("Type 1 to turn on the " + room + "_camera\n" + "Type 2 to start recording " + room + "\n" + "Type 3 to stop recording " + room +"\n" + "Type 4 to turn off the " + room +"_camera\n" + "Type 5 yo get back.\n" + "Enter your choice: ");
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
                controllerMenu();
                return;
            default:
                System.out.println("Invalid response. Try again.");
                cameraMenu();
        }
    }
    static void thermostatMenu() {
        System.out.println("Enter room name: ");
        input.nextLine();
        String room = input.nextLine();
        Thermostat t = new Thermostat(room + "_thermo", room);
        controller.addDevice(t);
        System.out.println("Type 1 to turn on " + room + "_thermostat.\n Type 2 to adjust " +room + " temperature.\n Type 3 to turn off " + room + "_thermostat. \n Type 4 to get back.\n Enter your choice: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                controller.turnOn(t.id);
                break;
            case 2:
                System.out.print("Enter temperature [5-30] : ");
                int temp = input.nextInt();
                if(!controller.adjust(t.id, temp))
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
}
