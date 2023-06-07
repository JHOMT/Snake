package pe.edu.utp;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Vista extends JFrame {
    private PanelSnake panel;

    public Vista() {
        frameInit();
        panel = new PanelSnake(800, 30);
        this.add(panel);
        panel.setBounds(10, 10, 800, 800);
        panel.setOpaque(false);

        PanelFondo fondo = new PanelFondo(800, 30);
        this.add(fondo);
        fondo.setBounds(10, 10, 800, 800);

        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Vista vista = new Vista();
            vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            vista.setSize(800, 800);
            vista.setVisible(true);
        });
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        switch (evt.getExtendedKeyCode()) {
            case KeyEvent.VK_LEFT:
                panel.cambiarDireccion("iz");
                break;
            case KeyEvent.VK_RIGHT:
                panel.cambiarDireccion("de");
                break;
            case KeyEvent.VK_UP:
                panel.cambiarDireccion("ar");
                break;
            case KeyEvent.VK_DOWN:
                panel.cambiarDireccion("ab");
                break;
            default:
                break;
        }
    }
}