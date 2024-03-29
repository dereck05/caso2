
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;





public class Ball extends JPanel implements Runnable,IPrototype<Ball>{
        Color color;
        String ballcol;
        int diameter = 15;
        long delay = 100;
        private int orientacion;
        private int velocidad;
       
        

        public Ball(String ballcolor,int vel,int orient) {
            setLayout(null);
            setBallColor(ballcolor);
            orientacion = orient;
            velocidad= vel;
            ballcol= ballcolor;
            new Thread(this).start();

        }
        public Ball(){
            setLayout(null);

            new Thread(this).start();

        }
        
        public String getBallColor() {
            return ballcol;
        }
        
        public int getVelocidad() {
            return velocidad;
        }

       
        public int getOrientation() {
            return orientacion;
        }
    
        public void setBallColor(String ballcolor) {
            this.ballcol = ballcolor;
            if (ballcolor == "red") {
                color = Color.red;
            } else if (ballcolor == "blue") {
                color = Color.blue;
            } else if (ballcolor == "black") {
                color = Color.black;
            } else if (ballcolor == "cyan") {
                color = Color.cyan;
            
            } else if (ballcolor == "green") {
                color = Color.green;
            } else if (ballcolor == "yellow") {
                color = Color.yellow;
            
            } else if (ballcolor == "magenta") {
                color = Color.magenta;
            } else if (ballcolor == "orange") {
                color = Color.orange;
            } else if (ballcolor == "pink") {
                color = Color.pink;
            }
        }
        
        public void setVelocidad(int v) {
            this.velocidad = v;
        }

        
        public void setOrientation(int orientacion) {
            this.orientacion = orientacion;
        }
        
        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            int x = getX();
            int y = getY();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(color);
            g.fillOval(0, 0, 10, 10); //agrega color al circulo
            g.setColor(Color.black);
            g2.drawOval(0, 0, 10, 10); //dibuja circulo
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(11, 11);
        }
        
        //public void run() {}
        
        public void run() {
            
            try {
              
                SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        
                        int x = (int)(Math.round(Math.random() * 800));
                        int y = (int)(Math.round(Math.random() * 800));
                        
                        setLocation(x, y);
                        
                    }
                });
            } catch (InterruptedException exp) {
              
                exp.printStackTrace();
            } catch (InvocationTargetException exp) {
              
                exp.printStackTrace();
            }

            while (isVisible()) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }

                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        @Override
                        public void run() {
                            if(orientacion == 90){
                                move90();
                                repaint();
                            }
                            if(orientacion == 180){
                                move180();
                                repaint();
                            }
                            if(orientacion == 45){
                                move45();
                                repaint();
                            }
                            if(orientacion ==135){
                                move135();
                                repaint();
                            }
                        }
                    });
                } catch (InterruptedException exp) {
                    exp.printStackTrace();
                } catch (InvocationTargetException exp) {
                    exp.printStackTrace();
                }
            }
        } 

        public void move180() {

            int x = getX();
            int y = getY();

            if (x + velocidad < 0 || x + diameter + velocidad > getParent().getWidth()) {
                velocidad *= -1;
            } 
            x += velocidad;

            setSize(getPreferredSize());
            setLocation(x, y);
        }
        public void move90() {

            int x = getX();
            int y = getY();

            
            if (y + velocidad < 0 || y + diameter + velocidad > getParent().getHeight()) {
                velocidad *= -1;
            }
            y += velocidad;
            setSize(getPreferredSize());
            setLocation(x, y);

        }
        public void move135() {

            int x = getX();
            int y = getY();
            
            if (y + velocidad < 0 || y + diameter + velocidad > getParent().getHeight()) {
                velocidad *= -1;
            }
            if (x + velocidad < 0 || x + diameter + velocidad > getParent().getWidth()) {
                velocidad *= -1;
            }
            x += velocidad;
            y += velocidad;
            setSize(getPreferredSize());
            setLocation(x, y);

        }  
        public void move45() {

            int x = getX();
            int y = getY();

            
            if (y + velocidad < 0 || y + diameter + velocidad  > getParent().getHeight()) {
                velocidad *= -1;
            }
            if (x + velocidad*-1 < 0 || x + diameter + velocidad*-1 > getParent().getWidth()) {
                velocidad *= -1;
            }
            x -= velocidad;
            y += velocidad;
            setSize(getPreferredSize());
            
            setLocation(x, y);

        } 

        @Override
        public Ball clone() {
            return new Ball(this.ballcol,this.velocidad,this.orientacion);
        }

        @Override
        public Ball deepClone() {
            return clone();
        }

        @Override
        public String toString() {
            String msj = "Ball = " + "color:" +  ballcol+ ", velocidad=" + velocidad + ",orientacion:" + orientacion; 
            return msj.toString();
        }
        
        public static class BallBuilder implements IBuilder<Ball>{
            private String ballcolor;
            private int orientacion;
            private int velocidad;
            public BallBuilder(){
                
            }
            public BallBuilder setColor(String color){
                this.ballcolor=color;
                return this;
            }
            public BallBuilder setOrientacion(int ori){
                this.orientacion=ori;
                return this;
            }
            public BallBuilder setVelocidad(int vel){
                this.velocidad=vel;
                return this;
            }
            @Override
            public Ball build(){
                return new Ball(ballcolor,velocidad,orientacion);
           }
        }

}
