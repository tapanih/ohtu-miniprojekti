
package logiikka;

public class Kirja {
    
    private String otsikko;
    private String kirjailija;


    public Kirja(){
        
    }
    
    public Kirja(String otsikko, String kirjailija) {
        this.otsikko = otsikko;
        this.kirjailija = kirjailija;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public String getKirjailija() {
        return kirjailija;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setKirjailija(String kirjailija) {
        this.kirjailija = kirjailija;
    }

    @Override
    public String toString() {
        return otsikko + ", " + kirjailija;
    }
    
}
