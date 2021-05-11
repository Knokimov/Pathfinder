import java.util.ArrayList;

public class HvitRute extends Rute {

    @Override
    public void gaa(ArrayList<Tuppel> rute){
        ArrayList<Tuppel> nySti = new ArrayList<>(rute);
        nySti.add(new Tuppel(this.row, this.column));
        
        for (Rute x: this.naboer){
            if(this.besoekt(x.row, x.column, nySti)){
                continue;
            }
            else{
                x.gaa(nySti);
            }
        }
    }

    public Boolean besoekt(int row, int column, ArrayList<Tuppel> array){
        for (Tuppel x: array){
            if(x.row == row && x.column == column){
                return true;
            }
        }
        return false;
    }

    public HvitRute(int row, int column, Labyrint labyrint){
        this.row = row;
        this.column = column;
        this.labyrint = labyrint;
        this.tegn = '.';
        this.naboer = new Rute[4];
    }

    @Override
    public String toString() {
        return ".";
    }
}
