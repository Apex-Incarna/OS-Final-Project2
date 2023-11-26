package com.example;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

import org.w3c.dom.events.Event;

import com.example.HBridgeMotorControllerPanel.KeyboardListener;
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

public class HBridgeMotorControllerPanel extends JPanel {
    // ATTRIBUTES
    private final int WIDTH = 800;
    private final int HEIGHT = 400;

    private final GpioController gpio = GpioFactory.getInstance();
    private GpioPinDigitalOutput RightIn1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "RightIn1", PinState.LOW);
    private GpioPinDigitalOutput RightIn2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "RightIn2", PinState.LOW);
    private GpioPinDigitalOutput LeftIn1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "LefIn1", PinState.LOW);
    private GpioPinDigitalOutput LeftIn2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "LeftIn2", PinState.LOW);

    private JButton fowardButton;
    private JButton backwardButton;
    private JButton leftButton;
    private JButton rightButton;

    // CONSTRUCTORS

    public HBridgeMotorControllerPanel() {
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

    }

    public void beginShutdown() {

        RightIn1.low();
        RightIn2.low();

        LeftIn1.low();
        LeftIn2.low();

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

                RightIn2.low();
                RightIn1.high();

                LeftIn1.low();
                LeftIn2.high();

            } else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {

                RightIn1.low();
                RightIn2.high();

                LeftIn1.high();
                LeftIn2.low();

            } else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {

                LeftIn2.low();

                RightIn2.low();
                RightIn1.high();

            } else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {

                RightIn1.low();

                LeftIn1.low();
                LeftIn2.high();

            }

        }

        @Override
        public void keyReleased(KeyEvent arg0) {
            System.out.println(arg0.getKeyCode());

            if (arg0.getKeyCode() == KeyEvent.VK_UP) {

                RightIn1.low();
                LeftIn2.low();

            } else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {

                RightIn2.low();
                LeftIn1.low();

            } else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {

                RightIn1.low();

            } else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {

                LeftIn2.low();

            }
        }

        @Override
        public void keyTyped(KeyEvent arg0) {

        }

    }
}
