
import java.awt.BorderLayout;

import java.awt.EventQueue;

import java.util.ArrayList;
import javax.swing.JFrame;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AnimatedBalls {
    ArrayList<Ball> arr = new ArrayList();      //arraylist que proviene de algun patron
    public static void main(String[] args) {      
        ArrayList<Ball> array = new ArrayList();
        for(int i=0;i<500;i++){
            array.add(new Ball("blue",30,90));           //color,velocid X, velocid Y, angulo
            array.add(new Ball("green",10,180));
        }
        
        
        new AnimatedBalls(array);
    }

    public AnimatedBalls(ArrayList<Ball> array) {
        this.arr = array;
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {        
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                for (Ball i : arr){                     //agrega las bolitas al frame de la ventana
                    frame.add(i);
                }
                
                frame.setSize(800, 800);
                frame.setVisible(true);
            }
        }); 
    } 

}