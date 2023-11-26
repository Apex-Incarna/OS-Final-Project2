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
    private GpioPinDigitalOutput in1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "In1", PinState.LOW);
    private GpioPinDigitalOutput in2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "In2", PinState.LOW);

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

        in1.low();
        in2.low();

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
                
                in2.low();
                in1.high();

            } else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
                
                in1.low();
                in2.high();

            } else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
                
            } else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
                
            }

        }

        @Override
        public void keyReleased(KeyEvent arg0) {
            System.out.println(arg0.getKeyCode());

            if (arg0.getKeyCode() == KeyEvent.VK_UP) {
                
                in1.low();

            } else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
                
                in2.low();

            } else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
                
            } else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
                
            }
        }

        @Override
        public void keyTyped(KeyEvent arg0) {

        }

    }
}
