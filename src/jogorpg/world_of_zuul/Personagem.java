/**
 * Class Personagem - ...
 *
 * ...
 * ...
 * ...
 * 
 * @author  Frederico Peixoto Antunes e José Pauletti
 * @version 2019.06.30
 */

package jogorpg.world_of_zuul;

import java.util.Random;

public abstract class Personagem {
    
    private String Nome;
    private int Energia;
    private Random random = new Random();
    private Item ataque;
    private Item defesa;
    private char tipo;
    
    public Personagem(String Nome, int Energia, char tipo) {
        this.Nome = Nome;
        this.Energia = Energia;
        this.tipo = tipo;
    }

    public char getTipo() {
        return tipo;
    }

    
    public String getNome() {
        return Nome;
    }

    public int getEnergia() {
        return Energia;
    }

    public void setEnergia(int Energia) {
       this.Energia = Energia;
    }

    
    public void setAtaque(Item ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(Item defesa) {
        this.defesa = defesa;
    }

    public Item getAtaque() {
        return ataque;
    }

    public Item getDefesa() {
        return defesa;
    }
   
    
    public int Incremento(int qtd){             // Modifiquei para receber qunatidade de icremento de vida
        Energia += qtd;
        if(Energia > 10)
            Energia = 10;

        return Energia;
    }
    
    public int Decremento(int ataque){
        if(Energia > 0)
            Energia -= ataque;
        
        if(Energia < 0)
            Energia = 0;
        
        return Energia;
    }

    public int Sorte(){
        return random.nextInt(6);
    }
    
    public void Imprimir(){
        System.out.println("#####################\n" + "#Nome: " + Nome + "\n" + "#Energia: " + Energia + "\n" + "#####################" );
    }
    
}
