import java.util.ArrayList;

abstract public class Rute {
    char tegn;
    int row;
    int column;
    Labyrint labyrint;
    Rute naboer[];

    public void gaa(ArrayList<Tuppel> rute) {
    }   

    public char tilTegn(){
        return tegn;
    }

    public void finnUtvei(){
        gaa(new ArrayList<Tuppel>());
    }
}
