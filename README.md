# 🏠 Simple Smart Home System

A simple **Object-Oriented Programming (OOP)** project written in **Java** that simulates a smart home system.  
The system allows users to **control devices** like **Lights**, **Cameras**, and **Thermostats** using a **console-based controller**.

---

## 🚀 Features

- 🕹️ Interactive text-based controller system
- 💡 Manage different types of smart devices:
    - **Light** — can be turned on/off and brightness adjusted
    - **Camera** — can be turned on/off and start/stop recording
    - **Thermostat** — can be turned on/off and temperature adjusted
- 🧠 Fully OOP-based:
    - Inheritance (`Device` base class)
    - Interfaces (`Adjustable`, `Recordable`)
    - Encapsulation and abstraction
- 📋 Device status reporting through the controller

---

## 🧩 Project Structure

```
SmartHome/
│
├── src/
│   └── simplesmarthome/
│       ├── Devices/
│       │   ├── Device.java
│       │   ├── Light.java
│       │   ├── Camera.java
│       │   └── Thermostat.java
│       │
│       ├── Interface/
│       │   ├── Adjustable.java
│       │   └── Recordable.java
│       │
│       ├── Services/
│       │   └── HomeController.java
│       │
│       └── Main.java
│
└── README.md
```

---

## 🧠 How It Works

Each smart device inherits from the base class `Device` and provides specific functionalities.

- `Light` implements the `Adjustable` interface
- `Camera` implements the `Recordable` interface
- `Thermostat` implements the `Adjustable` interface
- `HomeController` acts as the central manager to control all devices

The `Main` class allows the user to interact through a console menu to:
- Add devices
- Turn them on/off
- Adjust settings or start/stop recording

---

💡 **Contributing**

Feel free to open an issue or submit a pull request with new features, improvements, or bug fixes — contributions are always welcome!
