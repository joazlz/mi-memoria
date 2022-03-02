/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gt.joaquin.memoria;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author joaquinlinares
 */
public class main extends JFrame {

    public String[][] cartas = {{"a","0"},{"b","0"},{"c","0"},{"d","0"},{"f","0"},{"g","0"},{"h","0"},{"i","0"},{"j","0"},{"k","0"},{"l","0"},{"m","0"},{"n","0"},{"ñ","0"},{"o","0"},{"p","0"},{"q","0"},{"r","0"},{"s","0"},{"t","0"},{"u","0"},{"v","0"},{"w","0"},{"x","0"},{"y","0"},{"z","0"},{"A","0"},{"B","0"},{"C","0"},{"D","0"},{"F","0"},{"G","0"},{"H","0"},{"I","0"},{"J","0"},{"K","0"},{"L","0"},{"M","0"},{"N","0"},{"Ñ","0"},{"O","0"},{"P","0"},{"Q","0"},{"R","0"},{"S","0"},{"T","0"},{"U","0"},{"V","0"},{"W","0"},{"X","0"},{"Y","0"},{"Z","0"},{"aa","0"},{"bb","0"},{"cc","0"},{"dd","0"},{"ff","0"},{"gg","0"},{"hh","0"},{"ii","0"},{"jj","0"},{"kk","0"},{"ll","0"},{"mm","0"},{"nn","0"},{"ññ","0"},{"oo","0"},{"pp","0"},{"qq","0"},{"rr","0"},{"ss","0"},{"tt","0"},{"uu","0"},{"vv","0"},{"ww","0"},{"xx","0"},{"yy","0"},{"zz","0"},{"AA","0"},{"BB","0"},{"CC","0"},{"DD","0"},{"FF","0"},{"GG","0"},{"HH","0"},{"II","0"},{"JJ","0"},{"KK","0"},{"LL","0"},{"MM","0"},{"NN","0"},{"ÑÑ","0"},{"OO","0"},{"PP","0"},{"QQ","0"},{"RR","0"},{"SS","0"},{"TT","0"},{"UU","0"},{"VV","0"},{"WW","0"},{"XX","0"},{"YY","0"},{"ZZ","0"}};
    Map<JButton, String> mesa = new HashMap<JButton, String>();
    List<JButton> seleccionadas = new ArrayList<>();
    public main() {
        setTitle("Mi Memoria");
        setSize(700, 500);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        //menu panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS)); http://chuwiki.chuidiang.org/index.php?title=Uso_de_Layouts
        panel.setLayout(new GridLayout(3, 1));
        Icon icono = (new javax.swing.ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/gt/joaquin/memoria/img/posterior.png")).getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT))); // NOI18N
        List<JButton> buttons = new ArrayList<>();
        int i = 0;
        int cartas_cantidad=10;
        var cartasJugadas = new String[cartas_cantidad][2];
        
        for(int j=0;j<cartas_cantidad;j++){
            cartasJugadas[j]=cartas[j];
        }
        
        while (i < cartas_cantidad*2) {
            JButton button = new JButton();
            Random r = new Random();
            int valorDado = r.nextInt(cartas_cantidad);
            if (Integer.parseInt(cartasJugadas[valorDado][1]) != 2) {
                if (mesa.get(cartasJugadas[valorDado]) == null) {
                    mesa.put(button, cartasJugadas[valorDado][0]);
                    cartasJugadas[valorDado][1] = Integer.toString(Integer.parseInt(cartas[valorDado][1]) + 1);
                } else {
                    mesa.put(button, cartasJugadas[valorDado][0]);
                    cartasJugadas[valorDado][1] = Integer.toString(Integer.parseInt(cartas[valorDado][1] + 1));
                }

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (button.getIcon() == null) {
                            if(button.getText()!="Encontrada"){
                                button.setText("");
                                button.setIcon(icono);
                            }
                        } else {
                            button.setIcon(null);
                            button.setText(mesa.get(button).toString());
                            if(!seleccionadas.contains(button)){
                                seleccionadas.add(button);
                            }
                        }
                        if (seleccionadas.size() == 2) {
                            
                            String carta1=seleccionadas.get(0).getText();
                            String carta2=seleccionadas.get(1).getText();
                            if(carta1==carta2){
                                seleccionadas.get(0).setText("Encontrada");
                                seleccionadas.get(1).setText("Encontrada");
                                seleccionadas.removeAll(seleccionadas);
                            }
                        }

                        if (seleccionadas.size() == 3) {
                            seleccionadas.forEach(b -> {
                                b.setText("");
                                b.setIcon(icono);
                            });
                            seleccionadas.removeAll(seleccionadas);
                            if (!seleccionadas.contains(button)) {
                                seleccionadas.add(button);
                                button.setIcon(null);
                                button.setText(mesa.get(button).toString());
                            }
                        }
                    }
                });
                button.setIcon(icono);
                buttons.add(button);
                i++;
            }
        }

        buttons.forEach(e -> {
            panel.add(e);
        });

        this.getContentPane().add(panel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        main a = new main();
    }

}
