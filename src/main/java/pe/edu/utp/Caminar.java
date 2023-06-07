package pe.edu.utp;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Caminar implements Runnable {
    private PanelSnake panel;
    private boolean estado = true;

    public Caminar(PanelSnake panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (estado) {
            panel.avanzar();
            panel.repaint();
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Caminar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void parar() {
        this.estado = false;
    }
}
