
import sum.kern.*;
import sum.werkzeuge.*;
/**
 * @author Luca & Anton
 * @version 1.0
 */
public class Einzelspieler{
    // Objekte
    Bildschirm derBildschirm;
    Buntstift meinStift;
    Uhr dieUhr;
    Maus dieMaus;
    int score;
    int oldscore;
    double stiftx;
    double stifty;
    int Yhochrunter;
    int Versuche;
    //public class gameOver extends Actor{};
    // Konstruktor
    public Einzelspieler(Bildschirm bildschirm)
    {
        derBildschirm = bildschirm;
        meinStift = new Buntstift();
        dieUhr = new Uhr();
        dieMaus = new Maus();
        
        // Anfangswert des Pfeils
        oldscore = 0;
        score = 0;
        Yhochrunter = 3;
        Versuche = 3;
        meinStift.setzeSchriftgroesse(15);
        
        //Anfangswert der Scheibe
        meinStift.bewegeBis(derBildschirm.breite()-60,derBildschirm.hoehe()/2);//alternativ 580,240
        zeichneScheibe();
        
        starteEinzelSpieler();
        
    }
    
    //Startet den Einzelspieler
    public void starteEinzelSpieler(){
        meinStift.bewegeBis(derBildschirm.breite()-100, derBildschirm.hoehe()-20);
        meinStift.schreibeText("Punkte: "+oldscore);
        meinStift.bewegeBis(derBildschirm.breite()-620, derBildschirm.hoehe()-20);
        meinStift.schreibeText("Versuche: "+Versuche);
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
        oldscore = 0;
        score = 0;
        Yhochrunter = 3;
        Versuche = 3;
        meinStift.hoch();
        meinStift.bewegeBis(derBildschirm.breite()-100, derBildschirm.hoehe()-20);
        meinStift.runter();
        meinStift.schreibeText("Punkte: "+oldscore);
        meinStift.hoch();
        meinStift.bewegeBis(derBildschirm.breite()-620, derBildschirm.hoehe()-20);
        meinStift.runter();
        meinStift.schreibeText("Versuche: "+Versuche);
        meinStift.hoch();
        meinStift.bewegeBis(60,1);
        meinStift.runter();
        zeichnePfeil();
        Pfeilfall();
    }
    
    // Läuft das Programm während der Pfeil geworgen wird (Luca) / Flugbahn Modifikation (Anton)
    public void run(){
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
        meinStift.bewegeBis(derBildschirm.breite()-620, derBildschirm.hoehe()-20);
        meinStift.schreibeText("Versuche: "+Versuche);
        meinStift.normal();
        Versuche = Versuche - 1;
        meinStift.bewegeBis(derBildschirm.breite()-620, derBildschirm.hoehe()-20);
        meinStift.schreibeText("Versuche: "+Versuche);
        
        // Checkt ob der Pfeil die Scheibe trifft & gibt gegebenenfalls einen Punkt (Luca)
        meinStift.dreheBis(0);
        
        if(stifty<=derBildschirm.hoehe()/2+190 && stifty >= derBildschirm.hoehe()/2-120){
                //Ich wollte eigentlich einen switch benutzen, es ist aber nicht möglich, da stifty ein double und kein int ist (Luca)
                if(stifty<=derBildschirm.hoehe()/2+80 && stifty >= derBildschirm.hoehe()/2-100){
                    if(stifty<=derBildschirm.hoehe()/2+60 && stifty >= derBildschirm.hoehe()/2-80){
                        if(stifty<=derBildschirm.hoehe()/2+40 && stifty >= derBildschirm.hoehe()/2-60){
                            if(stifty<=derBildschirm.hoehe()/2+20 && stifty >= derBildschirm.hoehe()/2-40){
                                if(stifty<=derBildschirm.hoehe()/2 && stifty >= derBildschirm.hoehe()/2-20){
                                    score += 5;
                                }
                                score += 1;
                            }
                            score += 1;
                        }
                        score += 1;
                    }
                    score += 1;
                }
                score += 1;
                // Rechnet die Punkte hoch (Luca)
                // Aufploppen des textes "Nicht Schlecht" wenn der Pfeil Die scheibe Trifft ( Anton)
                meinStift.bewegeBis(150,150);
                meinStift.setzeSchriftGroesse(25);
                meinStift.schreibeText("Nicht Schlecht");
                meinStift.radiere();
                dieUhr.warte(2000);
                meinStift.radiere();
                meinStift.bewegeBis(150,150);
                meinStift.schreibeText("Nicht Schlecht");
                meinStift.setzeSchriftGroesse(15);
                
                
                meinStift.hoch();
                meinStift.bewegeBis(stiftx, stifty);
                meinStift.runter();
                zeichnePfeil();
                meinStift.hoch();
                meinStift.bewegeBis(derBildschirm.breite()-100, derBildschirm.hoehe()-20);
                meinStift.runter();
                meinStift.schreibeText("Punkte: "+oldscore);
                meinStift.hoch();
                meinStift.bewegeBis(derBildschirm.breite()-100, derBildschirm.hoehe()-20);
                meinStift.runter();
                int temp = score - oldscore;
                oldscore += temp; 
                meinStift.normal();
                meinStift.schreibeText("Punkte: "+oldscore);
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
        if(Versuche > 0){
            while(!dieMaus.doppelKlick()&& Versuche > 0){   
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
            
            run();
        
        }
        // Neustart des Spiels (Luca)
        
        else{
            meinStift.hoch();
            meinStift.bewegeBis(300,300);
            meinStift.runter();
            meinStift.normal();
            meinStift.schreibeText("Doppelclick zum neustart");
            meinStift.hoch();
            while(!dieMaus.doppelKlick()){
                
            }
            Versuche = 3;
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