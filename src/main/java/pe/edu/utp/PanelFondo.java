package pe.edu.utp;

import javax.swing.*;
import java.awt.*;

public class PanelFondo extends JPanel {
    private Color colorFondo = Color.gray;
    private int tamMax, tam, can;

    public PanelFondo(int tamMax, int can) {
        this.tamMax = tamMax;
        this.can = can;
        this.tam = tamMax / can;
    }

    @Override
    public void paint(Graphics pintor) {
        super.paint(pintor);
        pintor.setColor(colorFondo);
        for (int i = 0; i < can; i++) {
            for (int j = 0; j < can; j++) {
                pintor.fillRect(i * tam, j * tam, tam - 1, tam - 1);
            }
        }
    }
}
