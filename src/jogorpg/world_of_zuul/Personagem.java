/**
 * Class Personagem - ...
 *
 * ...
 * ...
 * ...
 * 
 * @author  Frederico Peixoto Antunes e José Pauletti
 * @version 2019.07.07
 */

package jogorpg.world_of_zuul;

import java.util.Random;

public abstract class Personagem {
    
    private String nome;
    private int energia;
    private Random random = new Random();
    private Item ataque;
    private Item defesa;
    
    public Personagem(String nome, int energia) {
        this.nome = nome;
        this.energia = energia;
    }

    public String getNome() {
        return nome;
    }

    public int getEnergia() {
        return energia;
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
   
    
    public int incremento(int qtd){             // Modifiquei para receber qunatidade de icremento de vida
        energia += qtd;
        if(energia > 10)
            energia = 10;

        return energia;
    }
    
    public int decremento(int ataque){
        if(energia > 0)
            energia--;

        if(energia == 0)
            System.out.println("Morto");

        return energia;
    }

    public int sorte(){
        return random.nextInt(6);
    }
    
    public void imprimir(){
        System.out.println("#####################\n" + "#Nome: " + nome + "\n" + "#Energia: " + energia + "\n" + "#####################" );
    }
    
}
