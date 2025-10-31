package SmartHome;

import SmartHome.Devices.Camera;
import SmartHome.Devices.Device;
import SmartHome.Devices.Light;
import SmartHome.Devices.Thermostat;
import SmartHome.Services.HomeController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final HomeController controller = new HomeController();

    public static void main(String[] args) {
        runApplicationLoop();
    }

    // Main application loop for menu navigation
    private static void runApplicationLoop() {
        boolean running = true;
        while (running) {
            System.out.println("\n========= Welcome to our SmartHome system =========");
            System.out.println(" 1. Show Info");
            System.out.println(" 2. Controller System");
            System.out.println(" 3. Exit");
            System.out.print("Enter your choice: ");
            int response = getUserIntInput();
            switch (response) {
                case 1:
                    showInfo();
                    break;
                case 2:
                    runControllerMenu();
                    break;
                case 3:
                    System.out.println("Thank you for using our system.");
                    running = false; // Exit the main loop
                    break;
                default:
                    System.out.println("Invalid response. Please try again.");
            }
        }
        input.close(); // Close the scanner when the application exits
    }

    private static void showInfo() {
        System.out.println("\nThis system lets you control your smart home devices.");
        System.out.println("You can manage Lights, Cameras, and Thermostats interactively.\n");
    }

    // Controller System menu loop
    private static void runControllerMenu() {
        boolean inControllerMenu = true;
        while (inControllerMenu) {
            System.out.println("\n--- Controller System Menu ---");
            System.out.println(" 1. Manage Light System");
            System.out.println(" 2. Manage Camera System");
            System.out.println(" 3. Manage Thermostat System");
            System.out.println(" 4. Remove Existing Device");
            System.out.println(" 5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int response = getUserIntInput();
            switch (response) {
                case 1:
                    runLightMenu();
                    break;
                case 2:
                    runCameraMenu();
                    break;
                case 3:
                    runThermostatMenu();
                    break;
                case 4:
                    removeDevice();
                    break;
                case 5:
                    inControllerMenu = false;
                    break;
                default:
                    System.out.println("Invalid response. Please try again.");
            }
        }
    }

    // Method to handle device removal
    private static void removeDevice() {
        System.out.print("Enter the device ID to remove: ");
        String deviceId = input.nextLine();

        if (controller.removeDevice(deviceId)) {
            System.out.println("Device [" + deviceId + "] removed successfully.");
        } else {
            System.out.println("Device with ID [" + deviceId + "] not found.");
        }
    }

    // Light Menu loop
    private static void runLightMenu() {
        boolean inLightMenu = true;
        while (inLightMenu) {
            System.out.println("\n--- Light Menu ---");
            System.out.println(" 1. List all Light systems");
            System.out.println(" 2. Add new Light system");
            System.out.println(" 3. Control existing Light system");
            System.out.println(" 4. Back to Controller Menu");
            System.out.print("Enter your choice: ");
            int res = getUserIntInput();
            switch (res) {
                case 1:
                    System.out.println(controller.listAllLightsStatuses());
                    break;
                case 2:
                    System.out.print("Enter room name: ");
                    String room = input.nextLine();
                    Light light = new Light(room + "_light", room);
                    controller.addDevice(light);
                    System.out.println("Light '" + light.id + "' created. Now controlling it:");
                    controlLight(light.id);
                    break;
                case 3:
                    System.out.print("Enter Light System ID: ");
                    String id = input.nextLine();
                    Device lightDevice = controller.getDeviceByID(id);
                    if (lightDevice == null) {
                        System.out.println("No system found with ID " + id);
                    } else if (!(lightDevice instanceof Light)) {
                        System.out.println("Device with ID " + id + " is not a Light.");
                    } else {
                        controlLight(lightDevice.id);
                    }
                    break;
                case 4:
                    inLightMenu = false;
                    break;
                default:
                    System.out.println("Invalid response. Try again.");
            }
        }
    }

    // Helper method to control a specific light device
    private static void controlLight(String lightId) {
        boolean controllingLight = true;
        while (controllingLight) {
            Device lightDevice = controller.getDeviceByID(lightId);
            if (lightDevice == null) {
                System.out.println("Error: Light device with ID " + lightId + " no longer exists. Returning to Light Menu.");
                return;
            }
            System.out.println("\n--- Controlling Light: " + lightId + " ---");
            System.out.println(" 1. Turn On\n 2. Adjust Brightness\n 3. Turn Off\n 4. Back to Light Menu");
            System.out.print("Enter your choice: ");
            int choice = getUserIntInput();
            switch (choice) {
                case 1:
                    controller.turnOn(lightId);
                    System.out.println("Light " + lightId + " turned on.");
                    break;
                case 2:
                    System.out.print("Enter brightness [0–100]: ");
                    int value = getUserIntInput();
                    if (value >= 0 && value <= 100) {
                        controller.adjust(lightId, value);
                        System.out.println("Brightness for " + lightId + " set to " + value + ".");
                    } else {
                        System.out.println("Invalid brightness value. Must be between 0 and 100.");
                    }
                    break;
                case 3:
                    controller.turnOff(lightId);
                    System.out.println("Light " + lightId + " turned off.");
                    break;
                case 4:
                    controllingLight = false;
                    break;
                default:
                    System.out.println("Invalid response. Try again.");
            }
        }
    }

    // Camera Menu loop
    private static void runCameraMenu() {
        boolean inCameraMenu = true;
        while (inCameraMenu) {
            System.out.println("\n--- Camera Menu ---");
            System.out.println(" 1. List all Camera systems\n 2. Add new Camera system\n 3. Control existing Camera system\n 4. Back to Controller Menu");
            System.out.print("Enter your choice: ");
            int res = getUserIntInput();
            switch (res) {
                case 1:
                    System.out.println(controller.listAllCameraStatuses());
                    break;
                case 2:
                    System.out.print("Enter room name: ");
                    String room = input.nextLine();
                    Camera cam = new Camera(room + "_cam", room);
                    controller.addDevice(cam);
                    System.out.println("Camera '" + cam.id + "' created. Now controlling it:");
                    controlCamera(cam.id);
                    break;
                case 3:
                    System.out.print("Enter Camera System ID: ");
                    String id = input.nextLine();
                    Device camDevice = controller.getDeviceByID(id);
                    if (camDevice == null) {
                        System.out.println("No system found with ID " + id);
                    } else if (!(camDevice instanceof Camera)) {
                        System.out.println("Device with ID " + id + " is not a Camera.");
                    } else {
                        controlCamera(camDevice.id);
                    }
                    break;
                case 4:
                    inCameraMenu = false;
                    break;
                default:
                    System.out.println("Invalid response. Try again.");
            }
        }
    }

    // Helper method to control a specific camera device
    private static void controlCamera(String camId) {
        // Implementation would be similar to controlLight, with camera-specific actions
        System.out.println("Camera control for '" + camId + "' is not yet fully implemented.");
    }

    // Thermostat Menu loop
    private static void runThermostatMenu() {
        // Implementation would be similar to runLightMenu
        System.out.println("Thermostat management is not yet fully implemented.");
    }

    /**
     * Reads a line of input from the user and safely parses it into an integer.
     * Handles NumberFormatException and prompts the user to retry.
     * This avoids common Scanner issues with nextInt() followed by nextLine().
     * @return The integer value entered by the user.
     */
    private static int getUserIntInput() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a whole number: ");
            }
        }
    }
}