package com.example;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

import org.w3c.dom.events.Event;

import com.example.ServoControllerPanel.KeyboardListener;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class ServoControllerPanel extends JPanel {
    // ATTRIBUTES
    private final int WIDTH = 800;
    private final int HEIGHT = 400;

    private final GpioController gpio = GpioFactory.getInstance();
    private GpioPinDigitalOutput servo1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Servo1", PinState.LOW);
    private Runtime runTime = Runtime.getRuntime();

    private JButton fowardButton;
    private JButton backwardButton;
    private JButton leftButton;
    private JButton rightButton;

    // CONSTRUCTORS

    public ServoControllerPanel() {
        super();
        initPanel();
    }

    // METHODS

    public void initPanel() {
        setPreferredSize(new DimensionUIResource(WIDTH, HEIGHT));
        this.setBackground(Color.RED); // Change the color to distinguish it from MotorControllerPanel

        // Instantiate and add the buttons
        JButton fowardButton = new JButton("/\\");
        JButton backwardButton = new JButton("\\/");
        JButton leftButton = new JButton("<");
        JButton rightButton = new JButton(">");

        add(fowardButton);
        add(backwardButton);
        add(leftButton);
        add(rightButton);

        // Add an event listener to each button

        // Add a keyboard listener to the panel
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new KeyboardListener());

        try {
            runTime.exec("gpio mode 1 pwm");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void motion(int left, int right) {

    }

    public void beginShutdown() {

        servo1.low();

        gpio.shutdown();

        System.out.println("Shutdown complete. Have a nice day!");
    }

    // EVENT HANDLERS

    // forwardButton listener

    // backwardButton listener

    // leftButton listener

    // rightbutton listener

    // keyboard listener
    public class KeyboardListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent arg0) {
            System.out.println(arg0.getKeyCode());

            if (arg0.getKeyCode() == KeyEvent.VK_UP) {
                motion(1, 1);
            } else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
                motion(-1, -1);
            } else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
                motion(0, 1);
            } else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
                motion(1, 0);
            }

        }

        @Override
        public void keyReleased(KeyEvent arg0) {
            System.out.println(arg0.getKeyCode());

            if (arg0.getKeyCode() == KeyEvent.VK_UP) {
                motion(-1, -1);
            } else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
                motion(1, 1);
            } else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
                motion(0, -1);
            } else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
                motion(-1, 0);
            }
        }

        @Override
        public void keyTyped(KeyEvent arg0) {

        }

    }
}
