package com.example;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

import org.w3c.dom.events.Event;

import com.example.MotorControllerPanel.KeyboardListener;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MotorControllerPanel extends JPanel {
    // ATTRIBUTES
    private final int WIDTH = 1200;
    private final int HEIGHT = 800;

    private final GpioController gpio = GpioFactory.getInstance();
    private GpioPinDigitalOutput pinLeft1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "Lefty1", PinState.LOW);
    private GpioPinDigitalOutput pinLeft2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "Lefty2", PinState.LOW);
    private GpioPinDigitalOutput pinRight1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "Righty1", PinState.LOW);
    private GpioPinDigitalOutput pinRight2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_31, "Righty2", PinState.LOW);
    private int leftState = 0;
    private int rightState = 0;

    private JButton fowardButton;
    private JButton backwardButton;
    private JButton leftButton;
    private JButton rightButton;

    // CONSTRUCTORS

    public MotorControllerPanel() {
        super();
        initPanel();
    }

    // METHODS

    public void initPanel() {
        setPreferredSize(new DimensionUIResource(WIDTH, HEIGHT));

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

    }

    public void motion(int left, int right) {
        leftState += left;
        rightState += right;

        if (leftState > 1) {
            leftState = 1;
        }
        if (leftState < -1) {
            leftState = -1;
        }

        if (rightState > 1) {
            rightState = 1;
        }
        if (rightState < -1) {
            rightState = -1;
        }

        if (leftState == 1) {
            pinLeft1.high();
            pinLeft2.low();
        } else if (leftState == -1) {
            pinLeft1.low();
            pinLeft2.high();
        } else {
            pinLeft1.low();
            pinRight2.low();
        }

        if (leftState == 1) {
            pinRight1.high();
            pinRight2.low();
        } else if (leftState == -1) {
            pinRight1.low();
            pinRight2.high();
        } else {
            pinRight1.low();
            pinRight2.low();
        }

        System.out.println(leftState);
        System.out.println(rightState);
    }

    public void beginShutdown() {
        pinLeft1.low();
        pinRight2.low();

        pinRight1.low();
        pinRight2.low();

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
            if (arg0.getKeyCode() == KeyEvent.VK_KP_UP) {
                motion(-1, -1);
            } else if (arg0.getKeyCode() == KeyEvent.VK_KP_DOWN) {
                motion(1, 1);
            } else if (arg0.getKeyCode() == KeyEvent.VK_KP_LEFT) {
                motion(0, -1);
            } else if (arg0.getKeyCode() == KeyEvent.VK_KP_RIGHT) {
                motion(-1, 0);
            }
        }

        @Override
        public void keyTyped(KeyEvent arg0) {

        }

    }
}
