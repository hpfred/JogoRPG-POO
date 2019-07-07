/**
 * Class Heroi - ...
 *
 * ...
 * ...
 * ...
 * 
 * @author  Frederico Peixoto Antunes e José Pauletti
 * @version 2019.07.07
 */

package jogorpg.world_of_zuul;

import java.util.Map;


public class Heroi extends Personagem {
    private int limiteDePeso;
    private int pesoAtual;
    private int qntdItens;
    private int moedas;
    
    private Map<String, Item> inventario; 
            
    public Heroi(String nome, int energia) {
        super(nome, energia);
    }
    
    public void alimentar(String comida){            // Passar como parametro um int de quanto incrementar; Facilitaria para comida/poção;
        Item aux = null;
        if(inventario.get(comida)!= null){
            aux = inventario.get(comida);
    
        incremento(aux.getAtributo());
    }
        System.out.println("Item inexistente");
    }
    
    public void lutar(Personagem inimigo){            // Método de luta deveria mesmo ser dentro da classe Heroi?
        Item aux;
        Item auxI;
        
        int auxDano = 0;
        
        //Vitoria Heroi
        if(sorte() > inimigo.sorte()){
            incremento(1);
            if(inimigo.getDefesa() != null){
                aux = getAtaque();
                auxI = inimigo.getDefesa();
                
                auxDano = aux.getAtributo() - auxI.getAtributo();
                
                inimigo.decremento(auxDano);
            }       
        }
        
        //Vitoria inimiga
        if(sorte() < inimigo.sorte()){
            if(getDefesa() != null){
                aux = getDefesa();
                auxI = inimigo.getAtaque();
               
                auxDano = auxI.getAtributo() - aux.getAtributo();
               
                decremento(auxDano);
            }
            inimigo.incremento(1);
        }
        
        // Empate na Sorte
        if(sorte() == inimigo.sorte()){
            if(getDefesa() != null){
           
                aux = getDefesa();
                auxI = inimigo.getAtaque();
               
                auxDano = auxI.getAtributo() - aux.getAtributo();
                
                decremento(auxDano);
            } 
            else{
                auxI = inimigo.getAtaque();
               
                decremento(auxI.getAtributo()); 
            }
           
            if(inimigo.getDefesa() != null){
                aux = getAtaque();
                auxI = inimigo.getDefesa();
                
                auxDano = aux.getAtributo() - auxI.getAtributo();
                
                inimigo.decremento(auxDano);
            }
            else{
                aux = getAtaque();
                inimigo.decremento(aux.getAtributo());
           }
        }
    }
   
    public void inserirItem(Item item){   // Modificar para tipo Boolean para poder retornar se 
        if(pesoAtual < limiteDePeso){
            inventario.put(item.getNomeDoItem(), item);
            pesoAtual = pesoAtual + item.getPesoDoItem();
            qntdItens++;
        }
        else    
            System.out.println("Inventário cheio!");
    }
    
    public void remover(String nome){           // Modifiquei/Modificar nome do método de "Remover" para "RemoverItem"; Tbm modificar para tipo Item para pdoer retornar
        if(inventario.get(nome) != null){
            inventario.remove(nome);
            qntdItens--;
            System.out.println("Item removido");
        }
        else 
            System.out.println("Item não existe no seu inventário");
    }
 
    public void equiparItem(String eqp){
        Item aux = null;
        
        aux = inventario.get(eqp);
        
        if(aux.getTipoIP().equals("A")){
            setAtaque(aux);
            System.out.println("Equipado arma");             
        }
        
        if(aux.getTipoIP().equals("D")){
            setDefesa(aux);
            System.out.println("Equipado Escudo");
        }
    }
    
    public int getPesoAtual() {
        return pesoAtual;
    }
    
    public void adionarMoedas(int qtd){
        int p = 1;
        
        moedas += qtd;
        
        if(inventario.get("Moedas") == null){
            Item m = new Item("Moedas", p, moedas, "M");
            inserirItem(m);
        }
        else{
            p = moedas/1000;
            
            Item auxM = null;
            
            auxM = inventario.get("Moedas");
            
            auxM.setPesoDoItem(p);
            auxM.setAtributo(moedas);
        }
        
        if((moedas%1000) != 0){
            Item auxM = null;
           
            p =+ 1 ;
            
            auxM = inventario.get("Moedas");
          
            auxM.setPesoDoItem(p);
        }
    }
    
}

