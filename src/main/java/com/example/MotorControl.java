package com.example;

import javax.swing.JFrame;

import com.pi4j.io.gpio.*;

public class MotorControl {
    public static void main(String[] args) throws InterruptedException {
        JFrame myFrame = new JFrame("Remote Control");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MotorControllerPanel myPanel = new MotorControllerPanel();

        myFrame.getContentPane().add(myPanel);

        myFrame.pack();
        myFrame.setVisible(true);

        // Create a GPIO controller
        final GpioController gpio = GpioFactory.getInstance();

        // Provision GPIO pin as an Output pin
        GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "MyLED", PinState.HIGH);

        // Set pin to high (or low) state
        pin.high(); // or pin.low();

        System.out.println("Did we get this far?");

        // Sleep for a while
        Thread.sleep(10000);

        // Toggle pin state
        pin.toggle();

        System.out.println("Is the pin on?");
        // Sleep again
        Thread.sleep(1000);

        // Release GPIO resources
        gpio.shutdown();
    }
}
