
import sum.kern.*;
/**
 * @author Luca & Anton
 * @version 0.7
 */
public class Main{
    // Objekte
    Bildschirm derBildschirm;
    Buntstift meinStift;
    Maus dieMaus;
    // Konstruktor
    public Main()
    {
        derBildschirm = new Bildschirm(640,480);        
        meinStift = new Buntstift();
        dieMaus = new Maus();
        
        starteStartBildschirm(1,10);
        
        while(true){
            if(dieMaus.istGedrueckt()){
                if(dieMaus.hPosition() >= derBildschirm.breite()-380 && dieMaus.hPosition() <= derBildschirm.breite()-280){
                    if(dieMaus.vPosition() >= derBildschirm.hoehe()-320 && dieMaus.vPosition() <= derBildschirm.hoehe()-270){
                        löscheStartBildschirm();
                        Einzelspieler einzelSpieler = new Einzelspieler(derBildschirm);
                        break;
                    }
                }
                if(dieMaus.hPosition() >= derBildschirm.breite()-380 && dieMaus.hPosition() <= derBildschirm.breite()-280){
                    if(dieMaus.vPosition() >= derBildschirm.hoehe()-260 && dieMaus.vPosition() <= derBildschirm.hoehe()-210){
                        löscheStartBildschirm();
                        Mehrspieler mehrSpieler = new Mehrspieler(derBildschirm);
                        break;
                    }
                }
            }
        }
    }
    
    public void löscheStartBildschirm(){
        starteStartBildschirm(11,11);
        meinStift.normal();
        meinStift.setzeFarbe(3);
        meinStift.setzeFuellMuster(0);
    }
    
    public void starteStartBildschirm(int farbe1, int farbe2){
        meinStift.bewegeBis(derBildschirm.breite()-380, derBildschirm.hoehe()-320);
        meinStift.setzeFarbe(farbe1);
        meinStift.setzeFuellMuster(1);
        meinStift.zeichneRechteck(100,50);
        meinStift.bewegeBis(meinStift.hPosition()+10, meinStift.vPosition()+30);
        meinStift.setzeSchriftgroesse(15);
        meinStift.setzeFarbe(11);
        meinStift.schreibeText("Einzelspieler");
        
        meinStift.bewegeBis(derBildschirm.breite()-380, derBildschirm.hoehe()-260);
        meinStift.setzeFarbe(farbe2);
        meinStift.zeichneRechteck(100,50);
        meinStift.bewegeBis(meinStift.hPosition()+10, meinStift.vPosition()+30);
        meinStift.setzeSchriftgroesse(15);
        meinStift.setzeFarbe(11);
        meinStift.schreibeText("Mehrspieler");
    }
    
}