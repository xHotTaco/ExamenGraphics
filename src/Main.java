import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class Main extends JFrame {
    // COLORES QUE UTILIZARE
    Color azul = new Color(0, 66, 255);
    Color blanco = new Color(255, 255, 255);
    Color rojo = new Color(255, 0, 0);
    Color verde = new Color(58, 255, 0);
    Color gris = new Color(99, 74, 27);
    // FUNCIONES
    private LineaBresenham lineaBresenham;
    private BufferedImage buf;
    private int widthPantalla = 600;
    private int heightPantalla = 600;

    // CONSTRUCTOR
    Main() {
        setLayout(null);
        setTitle("Examen");
        setSize(widthPantalla, heightPantalla);
        this.getContentPane().setBackground(Color.gray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // INICIALIZACION DE VARIABLES
        lineaBresenham = new LineaBresenham();
        // METODOS DE PAINT
        buf = new BufferedImage(widthPantalla, heightPantalla, BufferedImage.TYPE_INT_RGB);
    }
    // METODO PIXELES
    public void putPixel(int x, int y, Color c) {
        buf.setRGB(x, y, c.getRGB());
    }
    // MAIN
    public static void main(String[] args) {
        new Main();
    }
    // PAINT
    @Override
    public void paint(Graphics h) {
        super.paint(h);

        int cantidad_punots = 100000;

        int puntoInicialX = 350;
        int puntoInicialY = 150;

        int vectorPosicionX = 100;
        int vectorPosicionY = 175;
        int vectorPosicionZ = 10;

        double Ut = 0.0;

        int[] puntoX = new int[cantidad_punots];
        int[] puntoY = new int[cantidad_punots];

        double v, U;
        double x, y, z;
        int cont = 0;

        for (v = 0; v <= 2 * Math.PI; v += 0.1) { // Incremento de v
            for (U = 0; U <= 2.5; U += 0.1) { // Incremento de U
                x = U * Math.sin(v);
                y = Math.pow(U, 2);
                z = U * Math.cos(v);
                System.out.println("X: "+x);
                System.out.println("Y: "+y);
                System.out.println("z: "+z);

                Ut = -(z / vectorPosicionZ);

                puntoX[cont] = (int) Math.round((x * 50 + (vectorPosicionX * Ut)) + puntoInicialX);
                puntoY[cont] = (int) Math.round((y * 50 + (vectorPosicionY * Ut)) + puntoInicialY);
                System.out.println("X parametricas: "+ puntoX[cont]);
                System.out.println("Y parametricas: "+ puntoY[cont]);
                cont++;
            }
        }

        int j = 0;

        while (j < (cantidad_punots - 1)) {
            List<Point> verticalLine = lineaBresenham.calcularLineaBresenham(puntoX[j], puntoY[j], puntoX[j + 1], puntoY[j + 1]);   // RELLENO IMPROVISADO
            for (Point punto : verticalLine) {
                putPixel(punto.x, punto.y, rojo);
            }
            j++;
        }
        // -----------------------------------------------------------------------------------------------------------------
        h.drawImage(buf, 0, 0, this);
    }
}