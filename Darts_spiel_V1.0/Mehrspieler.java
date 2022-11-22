
import sum.kern.*;
import sum.werkzeuge.*;
/**
 * @author 
 * @version 
 */
public class Mehrspieler{
    // Objekte
    Bildschirm derBildschirm;
    Buntstift meinStift;
    Uhr dieUhr;
    Maus dieMaus;
    int scoreSpieler1;
    int scoreSpieler2;
    int oldScoreSpieler1;
    int oldScoreSpieler2;
    double stiftx;
    double stifty;
    int Yhochrunter;
    int VersucheSpieler1;
    int VersucheSpieler2;
    //public class gameOver extends Actor{};
    // Konstruktor
    public Mehrspieler(Bildschirm bildschirm)
    {
        derBildschirm = bildschirm;
        meinStift = new Buntstift();
        dieUhr = new Uhr();
        dieMaus = new Maus();
        
        // Anfangswert des Pfeils
        oldScoreSpieler1 = 0;
        oldScoreSpieler2 = 0;
        scoreSpieler1 = 0;
        scoreSpieler2 = 0;
        Yhochrunter = 3;
        VersucheSpieler1 = 3;
        VersucheSpieler2 = 3;
        meinStift.setzeSchriftgroesse(15);
        
        //Anfangswert der Scheibe
        meinStift.bewegeBis(derBildschirm.breite()-60,derBildschirm.hoehe()/2);//alternativ 580,240
        zeichneScheibe();
        
        starteMehrSpieler();
        
    }
    
    //Startet den Einzelspieler
    public void starteMehrSpieler(){
        meinStift.bewegeBis(derBildschirm.breite()-130, derBildschirm.hoehe()-30);
        meinStift.schreibeText("Spieler 1 Punkte: "+oldScoreSpieler1);
        meinStift.bewegeBis(derBildschirm.breite()-610, derBildschirm.hoehe()-30);
        meinStift.schreibeText("Spieler 1 Versuche: "+VersucheSpieler1);
        meinStift.setzeFarbe(7);
        meinStift.bewegeBis(derBildschirm.breite()-130, derBildschirm.hoehe()-10);
        meinStift.schreibeText("Spieler 2 Punkte: "+oldScoreSpieler2);
        meinStift.bewegeBis(derBildschirm.breite()-610, derBildschirm.hoehe()-10);
        meinStift.schreibeText("Spieler 2 Versuche: "+VersucheSpieler2);
        meinStift.setzeFarbe(3);
        meinStift.bewegeBis(60,1);
        zeichnePfeil();
        Pfeilfall();
    }
    
    //Main als Methode
    public void neustart(){
        //Anfangswert der Scheibe
        meinStift.hoch();
        meinStift.bewegeBis(derBildschirm.breite()-60,derBildschirm.hoehe()/2);//alternativ 580,240
        meinStift.runter();
        zeichneScheibe();
        // Anfangswert des Pfeils
        oldScoreSpieler1 = 0;
        oldScoreSpieler2 = 0;
        scoreSpieler1 = 0;
        scoreSpieler2 = 0;
        Yhochrunter = 3;
        VersucheSpieler1 = 3;
        VersucheSpieler2 = 3;
        meinStift.hoch();
        meinStift.bewegeBis(derBildschirm.breite()-130, derBildschirm.hoehe()-30);
        meinStift.runter();
        meinStift.schreibeText("Spieler 1 Punkte: "+oldScoreSpieler1);
        meinStift.hoch();
        meinStift.bewegeBis(derBildschirm.breite()-610, derBildschirm.hoehe()-30);
        meinStift.runter();
        meinStift.schreibeText("Spieler 1Versuche: "+VersucheSpieler1);
        meinStift.hoch();
        meinStift.setzeFarbe(7);
        meinStift.bewegeBis(derBildschirm.breite()-130, derBildschirm.hoehe()-10);
        meinStift.runter();
        meinStift.schreibeText("Spieler 2 Punkte: "+oldScoreSpieler2);
        meinStift.hoch();
        meinStift.bewegeBis(derBildschirm.breite()-610, derBildschirm.hoehe()-10);
        meinStift.runter();
        meinStift.schreibeText("Spieler 2 Versuche: "+VersucheSpieler2);
        meinStift.hoch();
        meinStift.setzeFarbe(3);
        meinStift.bewegeBis(60,1);
        meinStift.runter();
        zeichnePfeil();
        Pfeilfall();
    }
    
    // Läuft das Programm während der Pfeil geworgen wird (Luca) / Flugbahn Modifikation (Anton)
    public void runSpieler1(){
        meinStift.radiere();
        zeichnePfeil();
        meinStift.dreheBis(20);
       
       
        for(int i=0;i<derBildschirm.breite()-160;i++)
        //while(meinStift.hPosition() != derBildschirm.breite()-110)
        {
            meinStift.dreheUm(-0.07);
            //radiere Pfeil
            meinStift.radiere();
            zeichnePfeil();
            
            //bewege Stift
            meinStift.bewegeUm(1);
            
            //zeichne Pfeil
            meinStift.normal();
            zeichnePfeil();
            dieUhr.warte(1);//warten
        }
        
        stiftx = meinStift.hPosition();
        stifty = meinStift.vPosition();
        meinStift.radiere();
        meinStift.bewegeBis(derBildschirm.breite()-610, derBildschirm.hoehe()-30);
        meinStift.schreibeText("Spieler 1 Versuche: "+VersucheSpieler1);
        meinStift.normal();
        VersucheSpieler1 -= 1;
        meinStift.bewegeBis(derBildschirm.breite()-610, derBildschirm.hoehe()-30);
        meinStift.schreibeText("Spieler 1 Versuche: "+VersucheSpieler1);
        
        // Checkt ob der Pfeil die Scheibe trifft & gibt gegebenenfalls einen Punkt (Luca)
        meinStift.dreheBis(0);
        
        if(stifty<=derBildschirm.hoehe()/2+190 && stifty >= derBildschirm.hoehe()/2-120){
                //Ich wollte eigentlich einen switch benutzen, es ist aber nicht möglich, da stifty ein double und kein int ist (Luca)
                if(stifty<=derBildschirm.hoehe()/2+80 && stifty >= derBildschirm.hoehe()/2-100){
                    if(stifty<=derBildschirm.hoehe()/2+60 && stifty >= derBildschirm.hoehe()/2-80){
                        if(stifty<=derBildschirm.hoehe()/2+40 && stifty >= derBildschirm.hoehe()/2-60){
                            if(stifty<=derBildschirm.hoehe()/2+20 && stifty >= derBildschirm.hoehe()/2-40){
                                if(stifty<=derBildschirm.hoehe()/2 && stifty >= derBildschirm.hoehe()/2-20){
                                    scoreSpieler1 += 5;
                                }
                                scoreSpieler1 += 1;
                            }
                            scoreSpieler1 += 1;
                        }
                        scoreSpieler1 += 1;
                    }
                    scoreSpieler1 += 1;
                }
                scoreSpieler1 += 1;
                // Rechnet die Punkte hoch (Luca)
                // Aufploppen des textes "Nicht Schlecht" wenn der Pfeil Die scheibe Trifft ( Anton)
                meinStift.bewegeBis(150,150);
                meinStift.setzeSchriftGroesse(25);
                meinStift.schreibeText("Nicht Schlecht");
                meinStift.radiere();
                dieUhr.warte(2000);
                meinStift.bewegeBis(150,150);
                meinStift.schreibeText("Nicht Schlecht");
                meinStift.setzeSchriftGroesse(15);
                
                meinStift.hoch();
                meinStift.bewegeBis(stiftx, stifty);
                meinStift.runter();
                zeichnePfeil();
                meinStift.hoch();
                meinStift.bewegeBis(derBildschirm.breite()-130, derBildschirm.hoehe()-30);
                meinStift.runter();
                meinStift.schreibeText("Spieler 1 Punkte: "+oldScoreSpieler1);
                meinStift.hoch();
                meinStift.bewegeBis(derBildschirm.breite()-130, derBildschirm.hoehe()-30);
                meinStift.runter();
                int temp = scoreSpieler1 - oldScoreSpieler1;
                oldScoreSpieler1 += temp; 
                meinStift.normal();
                meinStift.schreibeText("Spieler 1 Punkte: "+oldScoreSpieler1);
                meinStift.hoch();
                meinStift.bewegeBis(60, 1);
                zeichnePfeil();
                Pfeilfall();
            
                
        }
        else{
            meinStift.radiere();
            meinStift.hoch();
            meinStift.bewegeBis(stiftx, stifty);
            meinStift.runter();
            zeichnePfeil();
            meinStift.hoch();
            meinStift.bewegeBis(60, 1);
            meinStift.runter();
            zeichnePfeil();
            Pfeilfall();
        }
        
    }
    public void runSpieler2(){
        meinStift.radiere();
        zeichnePfeil();
        meinStift.dreheBis(20);
       
       
        for(int i=0;i<derBildschirm.breite()-160;i++)
        //while(meinStift.hPosition() != derBildschirm.breite()-110)
        {
            meinStift.dreheUm(-0.07);
            //radiere Pfeil
            meinStift.radiere();
            zeichnePfeil();
            
            //bewege Stift
            meinStift.bewegeUm(1);
            
            //zeichne Pfeil
            meinStift.normal();
            zeichnePfeil();
            dieUhr.warte(1);//warten
        }
        
        stiftx = meinStift.hPosition();
        stifty = meinStift.vPosition();
        meinStift.radiere();
        meinStift.bewegeBis(derBildschirm.breite()-610, derBildschirm.hoehe()-10);
        meinStift.schreibeText("Spieler 2 Versuche: "+VersucheSpieler2);
        meinStift.normal();
        VersucheSpieler2 -= 1;
        meinStift.bewegeBis(derBildschirm.breite()-610, derBildschirm.hoehe()-10);
        meinStift.schreibeText("Spieler 2 Versuche: "+VersucheSpieler2);
        
        // Checkt ob der Pfeil die Scheibe trifft & gibt gegebenenfalls einen Punkt (Luca)
        meinStift.dreheBis(0);
        
        if(stifty<=derBildschirm.hoehe()/2+190 && stifty >= derBildschirm.hoehe()/2-120){
                //Ich wollte eigentlich einen switch benutzen, es ist aber nicht möglich, da stifty ein double und kein int ist (Luca)
                if(stifty<=derBildschirm.hoehe()/2+80 && stifty >= derBildschirm.hoehe()/2-100){
                    if(stifty<=derBildschirm.hoehe()/2+60 && stifty >= derBildschirm.hoehe()/2-80){
                        if(stifty<=derBildschirm.hoehe()/2+40 && stifty >= derBildschirm.hoehe()/2-60){
                            if(stifty<=derBildschirm.hoehe()/2+20 && stifty >= derBildschirm.hoehe()/2-40){
                                if(stifty<=derBildschirm.hoehe()/2 && stifty >= derBildschirm.hoehe()/2-20){
                                    scoreSpieler2 += 5;
                                }
                                scoreSpieler2 += 1;
                            }
                            scoreSpieler2 += 1;
                        }
                        scoreSpieler2 += 1;
                    }
                    scoreSpieler2 += 1;
                }
                scoreSpieler2 += 1;
                // Rechnet die Punkte hoch (Luca)
                // Aufploppen des textes "Nicht Schlecht" wenn der Pfeil Die scheibe Trifft ( Anton)
                meinStift.bewegeBis(150,150);
                meinStift.setzeSchriftGroesse(25);
                meinStift.schreibeText("Nicht Schlecht");
                meinStift.radiere();
                dieUhr.warte(2000);
                meinStift.bewegeBis(150,150);
                meinStift.schreibeText("Nicht Schlecht");
                meinStift.setzeSchriftGroesse(15);
                
                meinStift.hoch();
                meinStift.bewegeBis(stiftx, stifty);
                meinStift.runter();
                zeichnePfeil();
                meinStift.hoch();
                meinStift.bewegeBis(derBildschirm.breite()-130, derBildschirm.hoehe()-10);
                meinStift.runter();
                meinStift.schreibeText("Spieler 2 Punkte: "+oldScoreSpieler2);
                meinStift.hoch();
                meinStift.bewegeBis(derBildschirm.breite()-130, derBildschirm.hoehe()-10);
                meinStift.runter();
                int temp = scoreSpieler2 - oldScoreSpieler2;
                oldScoreSpieler2 += temp; 
                meinStift.normal();
                meinStift.schreibeText("Spieler 2 Punkte: "+oldScoreSpieler2);
                meinStift.hoch();
                meinStift.bewegeBis(60, 1);
                zeichnePfeil();
                Pfeilfall();
            
                
        }
        else{
            meinStift.radiere();
            meinStift.hoch();
            meinStift.bewegeBis(stiftx, stifty);
            meinStift.runter();
            zeichnePfeil();
            meinStift.hoch();
            meinStift.bewegeBis(60, 1);
            meinStift.runter();
            zeichnePfeil();
            Pfeilfall();
        }
        
    }
    public void zeichnePfeil()    
    {  
        meinStift.runter();
        meinStift.bewegeUm(50);
        meinStift.bewegeUm(-50);
        meinStift.hoch();
    }
    public void zeichneScheibe()
    {
        meinStift.zeichneKreis(50);
        meinStift.zeichneKreis(70);
        meinStift.zeichneKreis(90);
        meinStift.zeichneKreis(110);
        meinStift.zeichneKreis(30);
        meinStift.zeichneKreis(10);
    }
    // Von Anton hab bei mir (Luca) zu Fehlern geführt, deswegen ist es auskommentiert
    /*public void GameOver()
    {

      setImage(new GreenfootImage("Game Over",44,Color.RED,Color.BLACK));
    }*/
    
    //Sorgt für den Fall des Pfeils (Luca)
    public void Pfeilfall(){
        if(VersucheSpieler1 > 0){
            while(!dieMaus.doppelKlick()&& VersucheSpieler1 > 0){   
                if(meinStift.vPosition()==derBildschirm.hoehe()-2){
                    meinStift.radiere();
                    zeichnePfeil();
                    meinStift.hoch();
                    meinStift.bewegeBis(60,1);
                    meinStift.runter();
                }
                //radiere Pfeil
                meinStift.radiere();
                zeichnePfeil();
                
                //bewege Stift
                meinStift.dreheUm(-90);
                meinStift.bewegeUm(Yhochrunter);
                meinStift.dreheUm(90);
                
                //zeichne Pfeil
                meinStift.normal();
                zeichnePfeil();
                dieUhr.warte(20);//warten
                
            }
            
            runSpieler1();
        
        }
        // Neustart des Spiels (Luca)
        
        else if(VersucheSpieler2 > 0){
            meinStift.setzeFarbe(7);
            while(!dieMaus.doppelKlick()&& VersucheSpieler2 > 0){   
                if(meinStift.vPosition()==derBildschirm.hoehe()-2){
                    meinStift.radiere();
                    zeichnePfeil();
                    meinStift.hoch();
                    meinStift.bewegeBis(60,1);
                    meinStift.runter();
                }
                //radiere Pfeil
                meinStift.radiere();
                zeichnePfeil();
                
                //bewege Stift
                meinStift.dreheUm(-90);
                meinStift.bewegeUm(Yhochrunter);
                meinStift.dreheUm(90);
                
                //zeichne Pfeil
                meinStift.normal();
                zeichnePfeil();
                dieUhr.warte(20);//warten
                
            }
            
            runSpieler2();
        }
        else{
            meinStift.setzeFarbe(3);
            meinStift.hoch();
            meinStift.bewegeBis(250,300);
            meinStift.runter();
            meinStift.normal();
            
            if(oldScoreSpieler1 == oldScoreSpieler2){
                meinStift.schreibeText("Keiner gewinnt");
            }
            else if(oldScoreSpieler1 > oldScoreSpieler2){
                meinStift.schreibeText("Spieler 1 gewinnt");
            }
            else if(oldScoreSpieler1 < oldScoreSpieler2){
                meinStift.schreibeText("Spieler 2 gewinnt");
            }
            meinStift.hoch();
            meinStift.bewegeBis(250,320);
            meinStift.schreibeText("Doppelclick zum neustart");
            
            while(!dieMaus.doppelKlick()){
                
            }
            VersucheSpieler1 = 3;
            VersucheSpieler2 = 3;
            derBildschirm.loescheAlles();
            neustart();
        }
    }
    
    // Von Anton hab bei mir (Luca) zu Fehlern geführt, deswegen ist es auskommentiert
    /*public void noArrows(){
    
       Actor Versuche = getOneIntersectingObject(Versuche.class);
       GameOver gameover = new GameOver();
       myWorld.addObject(gameover, myWorld.getWidth()/2, myWorld.getHeight()/2);
       myWorld.removeObject(this);
    }*/
}