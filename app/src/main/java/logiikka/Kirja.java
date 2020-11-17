
package logiikka;

public class Kirja {
    
    private String otsikko;
    private String kirjailija;
    private int sivumaara;

    public Kirja(){

    }
    
    public Kirja(String otsikko, String kirjailija, int sivut) {
        this.otsikko = otsikko;
        this.kirjailija = kirjailija;
        this.sivumaara = sivut;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public String getKirjailija() {
        return kirjailija;
    }
    
    public int getSivut(){
        return sivumaara;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setKirjailija(String kirjailija) {
        this.kirjailija = kirjailija;
    }
    

    public void setSivut(int sivut){
        this.sivumaara = sivut;
    }

    @Override
    public String toString() {
        return otsikko + ", " + kirjailija + ", " + sivumaara;
    }
    
}
