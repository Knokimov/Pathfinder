import java.util.ArrayList;

public class Aapning extends HvitRute{
    
    @Override
    public char tilTegn(){
        return this.tegn;
    }
    
    @Override
    public void gaa(ArrayList<Tuppel> rute){
        ArrayList<Tuppel> nySti = new ArrayList<>(rute);
        nySti.add(new Tuppel(this.row, this.column));
        
        if(nySti.size() == 1){   // Hvis ruten starter her
            labyrint.leggTilRute(nySti);
            for (Rute x: this.naboer){
                if(super.besoekt(x.row, x.column, nySti)){
                    continue;
                }
                else{
                    x.gaa(nySti);
                }
            }    
        }
        else{
            labyrint.leggTilRute(nySti);
        }
    }
    
    public Aapning(int row, int column, Labyrint labyrint){
        super(row, column, labyrint);
        this.tegn = '.';
        this.naboer = new Rute[4];
    }

    @Override
    public String toString() {
        return ".";
    }
}