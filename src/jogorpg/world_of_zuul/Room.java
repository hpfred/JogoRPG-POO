package jogorpg.world_of_zuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Frederico Peixoto Antunes e José Pauletti
 * @version 2019.07.07
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;                    // stores exits of this room.
    private HashMap<String, Personagem> personagens;        // Map de personagens - Como pedido em aula
    private HashMap<String, Item> itens;                    // Map de Itens - Guarda itens de uma sala

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        personagens = new HashMap<String, Personagem>();
        itens = new HashMap<String, Item>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {   
        return "\n------------\nYou are " + description + ".\n" + getExitString() + "\n" + getListaPersonagens() + "\n" + getListaItens();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    private String getListaPersonagens(){
        String descricao = "Personagens:";
        Iterator it = personagens.keySet().iterator();
        
        while (it.hasNext()) {
           Object chave = it.next();
           //Object valor = personagens.get(chave);
           descricao += " " + chave;
        }
        
        return descricao;
    }
    
    private String getListaItens(){
        String descricao;
        Iterator it = itens.keySet().iterator();
        
        if(!it.hasNext())
            return "";
        descricao = "Itens:";
        
        while (it.hasNext()) {
           Object chave = it.next();
           //Object valor = itens.get(chave);
           descricao += " " + chave;
        }
        
        return descricao;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Define os personagens presentes na sala.
     * @param nome Nome do personagem.
     * @param character Referência para o objeto que define o personagem.
     */
    public void setPersonagem(String nome, Personagem character)
    {
        personagens.put(nome, character);
    }
    
    public void removePersonagem(String nome){
        personagens.remove(nome);
    }
    
    public void setItem(String nome, Item item){
        itens.put(nome, item);
    }
    
    public Item getItem(String nome){
        return itens.get(nome);
    }
    
    public void removeItem(String nome){
        itens.remove(nome);
    }

//*    
    public void battle(Heroi hero, String enemy1){
        Item aux;
        Item auxI;
        int auxDano = 0;
        Personagem enemy = personagens.get(enemy1);
        
        //Vitoria Heroi
        if(hero.sorte() > enemy.sorte()){
            hero.incremento(1);
            if(enemy.getDefesa() != null){
                aux = hero.getAtaque();
                auxI = enemy.getDefesa();
                
                auxDano = aux.getAtributo() - auxI.getAtributo();
                enemy.decremento(auxDano);
         
            }
               
           }
        
        //Vitoria inimiga
        if(hero.sorte() < enemy.sorte()){
            if(hero.getDefesa() != null){
               aux = hero.getDefesa();
               auxI = enemy.getAtaque();
               
               auxDano = auxI.getAtributo() - aux.getAtributo();
               hero.decremento(auxDano);
           }
           enemy.incremento(1);
        }
        
        // Empate na Sorte
        if(hero.sorte() == enemy.sorte()){
            if(hero.getDefesa() != null){
                aux = hero.getDefesa();
                auxI = enemy.getAtaque();
               
                auxDano = auxI.getAtributo() - aux.getAtributo();
                
                hero.decremento(auxDano);
            } 
            else{
                auxI = enemy.getAtaque();
               
                hero.decremento(auxI.getAtributo()); 
            }
           
            if(enemy.getDefesa() != null){  
                aux = hero.getAtaque();
                auxI = enemy.getDefesa();
                
                auxDano = aux.getAtributo() - auxI.getAtributo();
                
                enemy.decremento(auxDano);
         
        }
        else{
                aux = hero.getAtaque();
                enemy.decremento(aux.getAtributo());
           }
        }
    }
//*/
    
}

