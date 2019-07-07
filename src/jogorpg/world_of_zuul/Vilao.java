/**
 * Class Vilao - ...
 *
 * ...
 * ...
 * ...
 * 
 * @author  Frederico Peixoto Antunes e José Pauletti
 * @version 2019.06.30
 */

package jogorpg.world_of_zuul;


public class Vilao extends Personagem{
    
    private int qtdMoedas;

    public Vilao(String Nome, int Energia,int qtdMoedas) {
        super(Nome, Energia);
        this.qtdMoedas = qtdMoedas;
    }

    public int getQtdMoedas() {
        return qtdMoedas;
    }
    
    
}
