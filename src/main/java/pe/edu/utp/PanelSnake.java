package pe.edu.utp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelSnake extends JPanel {
    private Color colorSnake = Color.blue;
    private Color colorComida = Color.red;
    private int tamMax, tam, can, res;
    private List<int[]> snake = new ArrayList<>();
    private String direccion = "de";
    private String direccionProxima = "de";

    private Thread hilo;
    private Caminar caminar;

    private int[] comida = new int[2];

    public PanelSnake(int tamMax, int can) {
        this.tamMax = tamMax;
        this.can = can;
        this.tam = tamMax / can;
        this.res = tamMax % can;
        int[] a = {can / 2 - 1, can / 2 - 1};
        int[] b = {can / 2, can / 2 - 1};
        snake.add(a);
        snake.add(b);
        generarComida();

        caminar = new Caminar(this);
        hilo = new Thread(caminar);
        hilo.start();
    }

    @Override
    public void paintComponent(Graphics pintor) {
        super.paintComponent(pintor);
        pintor.setColor(colorSnake);
        for (int[] par : snake) {
            pintor.fillRect(par[0] * tam, par[1] * tam, tam, tam);
        }
        // pintando comida
        pintor.setColor(colorComida);
        pintor.fillRect(comida[0] * tam, comida[1] * tam, tam, tam);
    }

    public void avanzar() {
        igualarDireccion();
        int[] ultimo = snake.get(snake.size() - 1);
        int agregarX = 0, agregarY = 0;
        switch (direccion) {
            case "de":
                agregarX = 1;
                break;
            case "iz":
                agregarX = -1;
                break;
            case "ar":
                agregarY = -1;
                break;
            case "ab":
                agregarY = 1;
                break;
        }
        int[] nuevo = {Math.floorMod(ultimo[0] + agregarX, can),
                Math.floorMod(ultimo[1] + agregarY, can)};
        boolean existe = false;
        for (int i = 0; i < snake.size(); i++) {
            if (nuevo[0] == snake.get(i)[0] && nuevo[1] == snake.get(i)[1]) {
                existe = true;
                break;
            }
        }
        if (existe) {
            JOptionPane.showMessageDialog(null, "Has perdido");
            reiniciarJuego(); // Agregamos esta línea para reiniciar el juego
        } else {
            if (nuevo[0] == comida[0] && nuevo[1] == comida[1]) {
                snake.add(nuevo);
                generarComida();
            } else {
                snake.add(nuevo);
                snake.remove(0);
            }
        }
    }
    public void reiniciarJuego() {
        snake.clear();
        int[] a = {can / 2 - 1, can / 2 - 1};
        int[] b = {can / 2, can / 2 - 1};
        snake.add(a);
        snake.add(b);
        generarComida();
    }

    public void generarComida() {
        List<int[]> posicionesDisponibles = new ArrayList<>();
        for (int i = 0; i < can; i++) {
            for (int j = 0; j < can; j++) {
                boolean ocupado = false;
                for (int[] par : snake) {
                    if (par[0] == i && par[1] == j) {
                        ocupado = true;
                        break;
                    }
                }
                if (!ocupado) {
                    posicionesDisponibles.add(new int[]{i, j});
                }
            }
        }

        if (!posicionesDisponibles.isEmpty()) {
            int indice = (int) (Math.random() * posicionesDisponibles.size());
            int[] nuevaComida = posicionesDisponibles.get(indice);
            comida[0] = nuevaComida[0];
            comida[1] = nuevaComida[1];
        }
    }


    public void cambiarDireccion(String dir) {
        if ((this.direccion.equalsIgnoreCase("de") || this.direccion.equalsIgnoreCase("iz")) && (dir.equalsIgnoreCase("ar") || dir.equalsIgnoreCase("ab"))) {
            this.direccionProxima = dir;
        }
        if ((this.direccion.equalsIgnoreCase("ar") || this.direccion.equalsIgnoreCase("ab")) && (dir.equalsIgnoreCase("iz") || dir.equalsIgnoreCase("de"))) {
            this.direccionProxima = dir;
        }
    }

    public void igualarDireccion() {
        this.direccion = this.direccionProxima;
    }
}