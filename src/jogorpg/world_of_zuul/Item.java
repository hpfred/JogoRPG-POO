/**
 * Class Item - ...
 *
 * ...
 * ...
 * ...
 * 
 * @author  Frederico Peixoto Antunes e José Pauletti
 * @version 2019.06.30
 */

package jogorpg.world_of_zuul;

public class Item {
    
    private String NomeDoItem;
    private int PesoDoItem;
    private int atributo;
    private String tipoIP;     //C = consumivel A = ataque D = defesa E = energia P = peso M = moeda 
    
    public Item(String NomeDoItem, int PesoDoItem, int atributo, String tipoIP) {
        this.NomeDoItem = NomeDoItem;
        this.PesoDoItem = PesoDoItem;
        this.atributo = atributo;
        this.tipoIP = tipoIP;
    }

    public void setPesoDoItem(int PesoDoItem) {
        this.PesoDoItem = PesoDoItem;
    }

    public String getNomeDoItem() {
        return NomeDoItem;
    }

    public int getPesoDoItem() {
        return PesoDoItem;
    }  

    public int getAtributo() {
        if(atributo != 0)
        return atributo;
        
        else return 0;
    }

    public String getTipoIP() {
        return tipoIP;
    }

    public void setAtributo(int atributo) {
        this.atributo = atributo;
    }
    
    
    
}
