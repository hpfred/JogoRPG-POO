/**
 * Class Heroi - ...
 *
 * ...
 * ...
 * ...
 * 
 * @author  Ari e José Pauletti
 * @version 2019.06.30
 */

package jogorpg.world_of_zuul;

import java.util.HashMap;
import java.util.Map;


public class Heroi extends Personagem {
    
    private int LimitedePeso;
    private int PesoAtual;
    private int QntdItens;
    private int moedas;
    private int EnerMax;
    
    private Map<String, Item> Inventario = new HashMap<String, Item>(); 
            
    public Heroi(String Nome, int Energia, char tipo) {
        super(Nome, Energia, tipo);
    }

    public Item getMoedas(){
        
        return Inventario.get("MOEDAS");
        
    }
    public void ImprimirInventario(){
    
        for(Map.Entry<String, Item> i: Inventario.entrySet()){
            System.out.println(i.getKey());
        }
    
    }
    public void Alimentar(String comida){            // Passar como parametro um int de quanto incrementar; Facilitaria para comida/poção;
       Item aux = null;
        if(Inventario.get(comida)!= null){
        aux = Inventario.get(comida);
    
       Incremento(aux.getAtributo());
    }
        System.out.println("Item inexistente");
    }
    
    public void Lutar(Personagem Inimigo){            // Método de luta deveria mesmo ser dentro da classe Heroi?
        Item aux = null;
        Item auxI = null;
        
        int sorte_heroi = 0;
        int sorte_vilao = 0;
        
        int auxDano = 0;
//Vitoria Heroi
        sorte_heroi = Sorte();
        sorte_vilao = Inimigo.Sorte();
        
        if(sorte_heroi > sorte_vilao){
            System.out.println("Heroi");
            Incremento(1);
            
                aux = getAtaque();
                auxI = Inimigo.getDefesa();
                
            if(Inimigo.getDefesa() != null){
                
                auxDano = aux.getAtributo() - auxI.getAtributo();
                
                Inimigo.Decremento(auxDano);
         
            }else if(aux == null){
               Inimigo.Decremento(1);
           }else
                Inimigo.Decremento(aux.getAtributo());
        }
        
        //Vitoria inimiga
        
        if(sorte_heroi < sorte_vilao){
            System.out.println("Inimigo");
            
               aux = getDefesa();
               auxI = Inimigo.getAtaque();
               
            if(aux !=  null){
           
               int j = aux.getAtributo();
               int k;
               if(auxI == null)
                k = 1;
               else k = auxI.getAtributo();
               
               if(j > k)
                   auxDano = j-k;
               else auxDano = k - j;
               
               Decremento(auxDano);
           }else if(auxI != null){
            Decremento(auxI.getAtributo());
            }else {
               Decremento(1);
           }
           
            Inimigo.Incremento(1);
        }
        
        // Empate na Sorte
        
        if(sorte_heroi == sorte_vilao){
            
            System.out.println("Empate");
           
            if(getDefesa() != null){
           
               aux = getDefesa();
               auxI = Inimigo.getAtaque();
               
               int j = aux.getAtributo();
               int k;
               if(auxI == null)
                k = 1;
               else k = auxI.getAtributo();
               
               if(j > k)
                   auxDano = j-k;
               else auxDano = k - j;
               
               
               Decremento(auxDano);
           } else{
                if(Inimigo.getAtaque() != null){
               auxI = Inimigo.getAtaque();
               
               Decremento(auxI.getAtributo()); 
                       }else Decremento(1);
            }
           
           
           if(Inimigo.getDefesa() != null){
                
                aux = getAtaque();
                auxI = Inimigo.getDefesa();
                
                auxDano = aux.getAtributo() - auxI.getAtributo();
                
                Inimigo.Decremento(auxDano);
         
            }
//            else{   
//                aux = getAtaque();
//                Inimigo.Decremento(aux.getAtributo());
//            }
            //
            else{
                if(getAtaque()!= null){
                    aux = getAtaque();
                    Inimigo.Decremento(aux.getAtributo());
                }else Inimigo.Decremento(1);
            }
           //
        } 
    }
   
    public void InserirItem(Item item){   // Modificar para tipo Boolean para poder retornar se 
        
        if(LimitedePeso == 0) LimitedePeso = 9;
        
        if(PesoAtual < LimitedePeso){
            if(item.getNomeDoItem() != "MOEDAS"){
            Inventario.put(item.getNomeDoItem(), item);
            PesoAtual = PesoAtual + item.getPesoDoItem();
            QntdItens++;
                System.out.println(item.getNomeDoItem() + " adicionado no inventario");
            }else if(item.getNomeDoItem().equals("MOEDAS")){
                    adionarMoedas(item.getAtributo());
                    System.out.println("Moedas Adicionadas no iinventario");
            }
        else    
            System.out.println("Inventário cheio!");
          
    }
    }
    
    public void Remover(String nome){           // Modifiquei/Modificar nome do método de "Remover" para "RemoverItem"; Tbm modificar para tipo Item para pdoer retornar
     
        if(Inventario.get(nome) != null){
        
            Inventario.remove(nome);
            QntdItens--;
            System.out.println("Item removido");
        }
        else System.out.println("Item não existe no seu inventário");
            
    }
 
    public void equiparItem(String eqp){
        
        Item aux = null;
        
        aux = Inventario.get(eqp);
        
        if(aux.getTipoIP().equals("A")){
             setAtaque(aux);
             System.out.println(aux.getNomeDoItem());
             System.out.println("Equipado arma");
             
        }
        if(aux.getTipoIP().equals("D")){
            setDefesa(aux);
            System.out.println(aux.getAtributo());
            System.out.println("Equipado Escudo");
        }
        
    }
    
    public int getPesoAtual() {
        return PesoAtual;
    }
    
    public void usarItem(String nome){
    
        Item i;
        i = Inventario.get(nome);
        
        if(i.getTipoIP().equals("C")){
            Alimentar(i.getNomeDoItem());
            Imprimir();
        }
        if(i.getTipoIP().equals("E")){
            if(EnerMax == 0){ 
                EnerMax = getEnergia() + i.getAtributo();
                setEnergia(EnerMax);
            }else EnerMax += i.getAtributo();
            
            System.out.println("Energia Max aumentada! Agora é " + getEnergia() );
        }
        
        if(i.getTipoIP().equals("P")){
            if(LimitedePeso == 0)
            LimitedePeso += i.getAtributo();
            System.out.println("Limite de Peso aumentado! Agora é " + LimitedePeso);
        }   
        
    }
    
    public void adionarMoedas(int qtd){
            
        int p = 1;
        
        moedas += qtd;
        
        Item m;
        m = Inventario.get("MOEDAS");
        
        if(m == null){
            
            Item mds = new Item("MOEDAS", 1, moedas, "M");
            Inventario.put("MOEDAS", mds);
        }
        
        if((moedas/1000) > 0){
            Item auxM;
           
            p =+ 1 ;
            
            auxM = Inventario.get("MOEDAS");
            
            auxM.setPesoDoItem(p);
            Inventario.remove("MOEDAS");
            
            auxM.setAtributo(moedas);
            
            Inventario.put("MOEDAS", auxM);
        }
        if((moedas%1000) != 0 &&((moedas/1000) > 1)){
        
            Item auxM;
           
            p =+ 1 ;
            
            auxM = Inventario.get("MOEDAS");
          
            auxM.setPesoDoItem(p);
            
            Inventario.remove("MOEDAS");
            
            auxM.setAtributo(moedas);
            
            Inventario.put("MOEDAS", auxM);
            System.out.println(auxM.getAtributo());
         
        }else{
            Item auxM;
            
            auxM = Inventario.get("MOEDAS");
          
            auxM.setPesoDoItem(p);
            
            auxM.setAtributo(moedas);
            
            Inventario.put("MOEDAS", auxM);
            System.out.println(auxM.getAtributo());
        
        }
    }
    	public void imprimir() {
		System.out.println("#####################");
		System.out.println("# Dados do Heroi");
		super.Imprimir();
	}

        
}

