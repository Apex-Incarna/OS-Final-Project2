package com.example;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import com.pi4j.io.gpio.*;

public class MotorControl {
    public static void main(String[] args) throws InterruptedException {

        JFrame myFrame = new JFrame("Remote Control");
        myFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // This close operation has been modified to work with the stolen code below

        final MotorControllerPanel myPanel = new MotorControllerPanel();

        myFrame.getContentPane().add(myPanel);

        myFrame.pack();
        myFrame.setVisible(true);

        // The following code was stolen and then modified from https://stackoverflow.com/questions/5824049/running-a-method-when-closing-the-program
            myFrame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentHidden(ComponentEvent e) {
                    System.out.println("Beginning shutdown");
                    myPanel.beginShutdown();
                    ((JFrame)(e.getComponent())).dispose();
                }
            });
        }

    }

