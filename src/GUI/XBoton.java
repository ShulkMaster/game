
package GUI;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
public class XBoton extends JButton{
        ImageIcon iconox;
        ImageIcon iconoy;
        Color efecto1;
        Color efecto2;
     public XBoton(Color entrada,Color salida ){
        creadorXD();
        efecto1 = entrada;
        efecto2= salida;
                       

}
    public XBoton(ImageIcon icono1, ImageIcon icono2){
        iconox = icono1;
        iconoy = icono2;
        creadorXD2();


}
        public XBoton(ImageIcon icono1, Color salida){
        iconox = icono1;
        efecto2 = salida;
        creadorXD3();


}
    private void creadorXD(){
        //agregando listener para evento de mouse entrando y saliendo al boton con copy paste
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                //esta funcion contiene el codigo ejecutable al activarse el listener
                efectoentrada(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                //esta funcion contiene el codigo ejecutable al activarse el listener
                efectosalida(evt);
            }
        });


    
    
    }
    private void creadorXD2(){
        //agregando listener para evento de mouse entrando y saliendo al boton con copy paste
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                //esta funcion contiene el codigo ejecutable al activarse el listener
                iconoentrada(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                //esta funcion contiene el codigo ejecutable al activarse el listener
                iconosalida(evt);
            }
        });
    }
        private void creadorXD3(){
        //agregando listener para evento de mouse entrando y saliendo al boton con copy paste
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                //esta funcion contiene el codigo ejecutable al activarse el listener
                iconoentrada(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                //esta funcion contiene el codigo ejecutable al activarse el listener
                efectosalida2(evt);
            }
        });
    }
    private void efectoentrada(MouseEvent evt){
        this.setBackground(efecto1);
    }
    private void efectosalida(MouseEvent evt){
        this.setBackground(efecto2);
    }
    private void efectosalida2(MouseEvent evt){
        this.setBackground(efecto2);
        this.setText(this.getText());
        this.setIcon(null);
    }
    private void iconoentrada(MouseEvent evt){
        this.setIcon(iconox);
    }
    private void iconosalida(MouseEvent evt){
        this.setIcon(iconoy);
    }
    
}
