import sas.*;
import java.awt.Color;
/**
 * Die Verwaltungsklasse soll die folgenden Objekte verwalten:
 * linkerSchlaeger
 * rechterSchlaeger
 * spielball
 */
public class Pong
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    View view;
    double direction;
    Rectangle hintergrund;
    Rectangle schlaeger1;
    Rectangle schlaeger2;
    Rectangle boden;
    Rectangle decke;
    Rectangle wandL;
    Rectangle wandR;
    Circle ball;
    Sprite bande;
    Sprite schlaeger;

    /**
     * Konstruktor für Objekte der Klasse Verwaltung
     */
    public Pong()
    {
        // Instanzvariable initialisieren
        view = new View ("Bierpong");
        hintergrund = new Rectangle (-1.0,0.0,600.0,400.0,Color.white);
        schlaeger1 = new Rectangle (10.0,175.0,5.0,50.0,Color.red);
        schlaeger2 = new Rectangle (580.0,175.0,5.0,50.0,Color.red);
        decke = new Rectangle (1.0,0.0,599.0,1.0,Color.black);
        boden = new Rectangle (1.0,398.0,599.0,1.0,Color.black);
        wandL = new Rectangle (0.0,0.0,1.0,399.0,Color.yellow);
        wandR = new Rectangle (598.0,0.0,1.0,399.0,Color.yellow);
        ball =  new Circle (300.0,200.0,10.0,Color.yellow);

        schlaeger = new Sprite();
        schlaeger.add(schlaeger1);
        schlaeger.add(schlaeger2);

        bande = new Sprite();
        bande.add(boden);
        bande.add(decke);
    }

    public void schlaeger1Bewegung1(){
        if (view.keyPressed ('w')&&!schlaeger2.intersects(bande)){
            schlaeger1.move(0.0,-10.0);
        }
    }

    public void schlaeger1Bewegung2(){
        if (view.keyPressed ('s')&&!schlaeger2.intersects(bande)){
            schlaeger1.move(0.0,10.0);
        }
    }

    public void schlaeger2Bewegung1(){
        if (view.keyPressed ('o')&&!schlaeger2.intersects(bande)){
            schlaeger2.move(0.0,-10.0);
        }
    }

    public void schlaeger2Bewegung2(){
        if (view.keyPressed ('l')&&!schlaeger2.intersects(bande)){
            schlaeger2.move(0.0,10.0);
        }
    }

    public void schlaeger1bewegen(){
        schlaeger1Bewegung1();
        schlaeger1Bewegung2();
    }

    public void schlaeger2bewegen(){
        schlaeger2Bewegung1();
        schlaeger2Bewegung2();
    }

    public void richtungSetzen(){
        direction = Tools.randomNumber(10,45)*(-1)^(Tools.randomNumber(0,10));
        ball.setDirection(direction);
    }

    public static void main(String [] args)
    {Pong neuesSpiel = new Pong();
        neuesSpiel.starteSpiel();
    }

    public void abprallen(){
        if (ball.intersects(bande)){
            double richtung = ball.getDirection();
            double neueRichtung = 180 - richtung;
            ball.setDirection(neueRichtung);

        }
        if (ball.intersects(schlaeger1) ||  ball.intersects(schlaeger2)){
            double richtung = ball.getDirection();
            double neueRichtung = 360 - richtung;
            ball.setDirection(neueRichtung);
        }
        if(ball.intersects(wandL) || ball.intersects (wandR)){
            ball.moveTo (300.0,200.0);
            view.wait(200);
        }
    }


    /**
     * Hier soll eine Methode programmiert werden, die das Spiel startet.
     * Der Multiplayer soll über die Tastatur gesteuert werden.
     */

    public void starteSpiel()
    {
        richtungSetzen();
        while (!view.keyEnterPressed()){
            //schlaeger1bewegen();
            //schlaeger2bewegen();
            if (view.keyPressed ('l')&&!schlaeger2.intersects(bande)){
                schlaeger2.move(0.0,10.0);
            }
            if (view.keyPressed ('o')&&!schlaeger2.intersects(bande)){
                schlaeger2.move(0.0,-10.0);
            }
            if (view.keyPressed ('s')&&!schlaeger1.intersects(bande)){
                schlaeger1.move(0.0,10.0);
            }
            if (view.keyPressed ('w')&&!schlaeger1.intersects(bande)){
                schlaeger1.move(0.0,-10.0);
            }
            if (schlaeger1.intersects(decke)){
                schlaeger1.moveTo(10.0,10.0);
            }
            if (schlaeger1.intersects(boden)){
                schlaeger1.moveTo(10.0,340);
            }
            if (schlaeger2.intersects(decke)){
                schlaeger2.moveTo(580.0,10.0);
            }
            if (schlaeger2.intersects(boden)){
                schlaeger2.moveTo(580.0,340);
            }
            abprallen();
            ball.move(5);
            view.wait(15);
        }

    }
}
