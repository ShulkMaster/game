package systems;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.InputStream;

public class Fontloader {

    private Font custonfont = null;

    public Fontloader(String fontpath) {
        try {
            //Se carga la fuente
            InputStream is = Toolkit.getDefaultToolkit().getClass().getResourceAsStream(fontpath);
            //InputStream is =  getClass().getResourceAsStream(fontpath);
            custonfont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception ex) {
            //Si existe un error se carga fuente por defecto ARIAL
            System.err.println(fontpath + " No se cargo la fuente");
            custonfont = new Font("Arial", Font.PLAIN, 14);            
        }
  }

    /* Font.PLAIN = 0 , Font.BOLD = 1 , Font.ITALIC = 2
 * tamanio = float
 */
    public Font MyFont( int estilo, float tamanio)
    {
        Font tfont = custonfont.deriveFont(estilo, tamanio);
        return tfont;
    }

}