package com.example;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import org.w3c.dom.events.Event;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

public class MotorControllerPanel extends JPanel {
    // ATTRIBUTES
    private final int WIDTH = 1200;
    private final int HEIGHT = 800;

    private final GpioController gpio = GpioFactory.getInstance();

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
    }

    // EVENT HANDLERS

    // forwardButton listener

    // backwardButton listener

    // leftButton listener

    // rightbutton listener
}
