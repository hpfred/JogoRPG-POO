/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogorpg.world_of_zuul;

/**
 *
 * @author José
 */
public class SimuladordeBatalhas extends Game{
    
    public static void main(String[] args) {
        
        Heroi h = new Heroi("Amelia", 10, 'H');
        Vilao v = new Vilao("Kevin", 10, 'V');
        
        while(h.getEnergia() != 0 || v.getEnergia() != 0){
        
            h.Imprimir();
            v.Imprimir();
            h.Lutar(v);
            
        }
        
        if(h.getEnergia() == 0)
        System.out.println("Vilao Venceu");
    
        if(v.getEnergia() == 0)
            System.out.println("Heroi Venceu");
    }
    
    public void newBattle(){
        
        return;
    }
/*
    public SimuladordeBatalhas(String description, ) {
        super(description);
    }
*/  
}
