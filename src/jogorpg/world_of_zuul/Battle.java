/**
 * Class Battle - ...
 *
 * ...
 * ...
 * ...
 * 
 * @author  Frederico Peixoto Antunes e José Pauletti
 * @version 2019.07.07
 */
package jogorpg.world_of_zuul;

/**
 *
 * @author José
 */
public class Battle extends Game{
    public static void main(String[] args) {
        Heroi h = new Heroi("Amelia", 10);
        Vilao v = new Vilao("Kevin", 10, 100);
        
        while(h.getEnergia() != 0 || v.getEnergia() != 0){
            h.imprimir();
            v.imprimir();
            h.lutar(v);   
        }
        
        if(h.getEnergia() == 0)
            System.out.println("Vilao Venceu");
    
        if(v.getEnergia() == 0)
            System.out.println("Heroi Venceu");
            h.adionarMoedas(v.getQtdMoedas());
    }
/*    
    public void newBattle(){
        return;
    }
/*
    public SimuladordeBatalhas(String description, ) {
        super(description);
    }
*/  
}
