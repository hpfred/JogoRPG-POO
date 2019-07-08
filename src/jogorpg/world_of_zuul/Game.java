package jogorpg.world_of_zuul;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Frederico Peixoto Antunes e José Pauletti
 * @version 2019.07.06
 */

public class Game
{
    private Parser parser;
    private Room currentRoom;
    private Heroi heroi;           // Atributo de herói - Como pedido em aula
/**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        heroi = new Heroi("Anamelia", 10, 'H');
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, recepcao, pub, lab, portal, huecomundo, espadas, soulsociety, folha, vento, agua, raio, boss;
      
        // create the rooms
        entrada = new Room("Você está na Porta da Faculdade");
        recepcao = new Room("Você está na Recepção, onde os alunos se reunem");
        pub = new Room("Você está na Cantina");
        lab = new Room("Você está no Laboratorio sombrio, dizem que tem um grande inimoa sua espera");
        portal = new Room("A sala que te separa de um mundo diferente");
        huecomundo = new Room("Demonio e Hollows são o menor de nosso problemas aqui");
        espadas = new Room("CUIDADO! A partir daqui não tem volta");
        soulsociety = new Room("no primeiro Super Monstro Aizen");
        folha = new Room("Respeita o Narutinho");
        vento = new Room("Respeita o Gaara");
        agua = new Room("Respeita a Mei");
        raio = new Room("Respeita o Bee");
        boss = new Room("Tudo ou nada");
        
        // Entrada
        entrada.setExit("leste", recepcao);
        
        Personagem kevin = new Vilao("KEVIN", 10,'V');
        Personagem frederico = new Vilao("FRED", 10, 'V');
        entrada.setPersonagem("FRED", frederico );
        entrada.setPersonagem("KEVIN", kevin);
        
        // Recepção
        Personagem joaz = new Vilao("JOAZ", 10, 'V');
        Personagem bruno = new Vilao("BRUNO", 10, 'V');
        recepcao.setPersonagem("JOAZ", joaz);
        recepcao.setPersonagem("BRUNO", bruno);
                
        
        recepcao.setExit("oeste", entrada);
        recepcao.setExit("norte", lab);
        recepcao.setExit("sul", pub);
        recepcao.setExit("leste", folha);
        //Pub
        pub.setExit("norte", recepcao);
        Personagem Boss1 = new Vilao("DESTRUIDOR", 15, 'B');
        pub.setPersonagem("DESTRUIDOR", Boss1);
        Item arma_latao = new Item("ALATAO", 1, 3, "A");
        Boss1.setAtaque(arma_latao);
        
        //Usado como corredor
        
        lab.setExit("sul", recepcao);
        lab.setExit("norte", portal);
        
        //Portal para Hueco Mundo
        
        portal.setExit("WEST", huecomundo);
        Personagem inimigo1 = new Vilao("SHINIGAMI1", 10, 'V');
        Personagem inimigo2 = new Vilao("SHINIGAMI2", 10, 'V');
        
        portal.setPersonagem("SHINIGAMI1", inimigo1);
        portal.setPersonagem("SHINIGAMI2", inimigo2);
        
        //Terra dos HOLLOWS
        
        huecomundo.setExit("leste", soulsociety);
        huecomundo.setExit("sul", espadas);
        
        Personagem inimigo5 = new Vilao("KENPACHI", 10, 'V'); // boss do tesouro
        Item a = new Item("KEN", 2, 3, "A");
        huecomundo.setPersonagem("KEN", inimigo5);
        inimigo5.setAtaque(a);
        
        //Espadas
        
        espadas.setExit("norte", huecomundo);
        Personagem inimigo3 = new Vilao("N5", 10, 'V');
        Personagem inimigo4 = new Vilao("N6", 10, 'V');
        espadas.setPersonagem("N5", inimigo3);
        espadas.setPersonagem("N6", inimigo4);
        
        //Soul Society
        soulsociety.setExit("sul", recepcao);
        
        Item BankaiIn = new Item("BANKAI", 2, 4, "A");
        soulsociety.setItem("BANKAI", BankaiIn);
        
        //Folha
        
        folha.setExit("sul", vento);
        folha.setExit("norte", raio);
        folha.setExit("leste", agua);
        
        Personagem Sasuke = new Vilao("SASUKE", 10, 'V');
        folha.setPersonagem("SASUKE", Sasuke);
        
        // Vento
        
        vento.setExit("sul", folha);

        Personagem Gaa = new Vilao("GAARA", 10, 'V');
        vento.setPersonagem("GAARA", Gaa);
        
        //Raio
        
        raio.setExit("sul", folha);
        Item esp = new Item("Aum Vd", 1, 5, "E");
        raio.setItem("VidaMAX", esp);
        
        //Agua
        
        agua.setExit("oeste", folha);
        Personagem Mi = new Vilao("MI", 10, 'V');
        agua.setPersonagem("MI", Mi);
        
        //BOSS FINAL
        
        Item armaS = new Item("ZANP", 1, 8, "A");
        Personagem BOSS = new Vilao("BOSS", 20, 'B');
        BOSS.setAtaque(armaS);
        boss.setPersonagem("BOSS", BOSS);
        
        //
        currentRoom = entrada;  // start game outside
        currentRoom.setPersonagem("Amelia", heroi);

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bem vindo ao World of Zuul!");
        System.out.println("World of Zuul é um jogo, desenvolvido por Ari e José");
        System.out.println("Digite '" + CommandWord.HELP + "' caso necessite de ajuda");
        System.out.println("Sempre que querer algum comando sempre digite comando + equipamento");
        System.out.println("Exemplo: arma Basica ou def Basica");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println(newStringBlock() + "I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.ATTACK) {        // Adiciona comando de ataque - Como pedido em aula
            // função de ataque
            goAttack(command);
        }
        else if(commandWord == CommandWord.ARMA){
            goEscolhaArma(command);
        }
        else if(commandWord == commandWord.EQUIP){
            goEquipDef(command);
        }
        else if(commandWord == commandWord.UTITENS){
            goItensUtil(command);
        }
        else if(commandWord == commandWord.BAU){
            goItensBau(command);
        }
        else if(commandWord == commandWord.COMPRAR){
            goSellItens(command);
        } else if(commandWord == commandWord.REMOVER){
            goRemoveItens(command);
        }
// else command not recognised.
        return wantToQuit;
    }
    
    public void goRemoveItens(Command command){
    
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know who to attack...
            System.out.println(newStringBlock() + "Qual item desejas?");
            return;
        }
        
        heroi.Remover(command.getSecondWord());
    }
    public void goSellItens(Command command){
        Item m = new Item("MOEDAS", 1, 1000,"M");
        heroi.InserirItem(m);
        Item i;
            i = heroi.getMoedas();
        System.out.println(i.getAtributo());
        Item Vida_1 = new Item("Vida1", 1, 3, "C");
        
        Item Vida_2 = new Item("Vida2", 2, 6, "C");
        
        Item Vida_3 = new Item("Vida3", 4, 10, "C");
        
        Item Aum_vd = new Item("VMax", 2, 5, "E");
        
        Item Aum_ps = new Item("PMax", 2, 5, "P");
        
        System.out.println(Vida_1.getNomeDoItem() + "  valor: 150");
        System.out.println(Vida_2.getNomeDoItem() + "  valor: 250");
        System.out.println(Vida_3.getNomeDoItem() + "  valor: 500");
        System.out.println(Aum_vd.getNomeDoItem() + " valor: 1000");
        System.out.println(Aum_ps.getNomeDoItem() + " valor: 1000");
        
      
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know who to attack...
            System.out.println(newStringBlock() + "Qual item desejas?");
            return;
        }
           
        if(command.getSecondWord().equals("Vida1")){
            if(i.getAtributo() >= 150){
               i.setAtributo( i.getAtributo() - 150);
               heroi.InserirItem(Vida_1);
            }
        }
    
        if(command.getSecondWord().equals("Vida2")){
            if(i.getAtributo() >= 250){
               i.setAtributo( i.getAtributo() - 250);
               heroi.InserirItem(Vida_2);
            }
        }
        
        
        if(command.getSecondWord().equals("Vida3")){
            if(i.getAtributo() >= 500){
               i.setAtributo( i.getAtributo() - 500);
               heroi.InserirItem(Vida_3);
            }
        }
    
        
        if(command.getSecondWord().equals("VMax")){
            if(i.getAtributo() >= 1000){
               i.setAtributo( i.getAtributo() - 1000);
               heroi.InserirItem(Aum_vd);
           }
        }
        
        if(command.getSecondWord().equals("PMax")){
            if(i.getAtributo() >= 1000){
               i.setAtributo( i.getAtributo() - 1000);
               heroi.InserirItem(Aum_ps);
           }
        }System.out.println(i.getAtributo());
        
    }
    
    public void goItensBau(Command command){ 
        Item i;
        /*
        System.out.println("Inventario");
        heroi.ImprimirInventario();
        */
        
        System.out.println("Bau");
        currentRoom.imprimirBau();
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know who to attack...
            System.out.println(newStringBlock() + "Qual item desejas?");
            return;
        }
        
        i = currentRoom.getItens(command.getSecondWord());
        
        heroi.InserirItem(i);
        
        currentRoom.removeItem(command.getSecondWord());
        System.out.println("Bau");
        currentRoom.imprimirBau();
    
        System.out.println("Inventario");
        heroi.ImprimirInventario();
    }
    
    public void goItensUtil(Command command){
        String nome;
        heroi.ImprimirInventario();
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know who to attack...
            System.out.println(newStringBlock() + "Qual Item Deseja Usar?");
            return;
        }
        
        nome = command.getSecondWord();
        
        heroi.usarItem(nome);   
    }
    
    public void goEscolhaArma(Command command){
       
       Item espada_b = new Item("BASICA", 1, 10, "A");    
        heroi.InserirItem(espada_b);
        heroi.ImprimirInventario();
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know who to attack...
            System.out.println(newStringBlock() + "Qual Arma Deseja Equipar?");
            return;
        }

        System.out.println(command.getSecondWord());
        
        heroi.equiparItem(command.getSecondWord());
    }
        
    public void goEquipDef(Command command){
        
        Item escudo_b = new Item("ESCUDOB", 1, 1, "D");
        heroi.InserirItem(escudo_b);
        heroi.ImprimirInventario();
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know who to attack...
            System.out.println(newStringBlock() + "Qual Escudo Deseja Equipar?");
            return;
        }

        System.out.println(command.getSecondWord());
        
        heroi.equiparItem(command.getSecondWord());
    }
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println(newStringBlock() + "Você está Perdido. Você está Perdido. Você está Perambulando.");
        System.out.println("aos arredores da Faculdade.");
        System.out.println();
        System.out.println("Suas palavras de comando são:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println(newStringBlock() + "Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println(newStringBlock() + "There is no door!");
        }
        else {
            currentRoom.removePersonagem("Amelia");
            currentRoom = nextRoom;
            currentRoom.setPersonagem("Amelia", heroi);
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    private void goAttack(Command command){
        Personagem inimigo;
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know who to attack...
            System.out.println(newStringBlock() + "Attack who?");
            return;
        }
        inimigo = currentRoom.getPesonagem(command.getSecondWord());
       
        if(inimigo != null){
            heroi.Lutar(inimigo);
            heroi.Imprimir();
            inimigo.Imprimir();
               
        }else System.out.println("The character '"+ command.getSecondWord() +"' is not in the current room.");
       
        if(inimigo.getEnergia() == 0)
            System.out.println("Inimigo morto");
       
        if(heroi.getEnergia() == 0){
            System.out.println("Heroi Morto");
            System.out.println("Você tem " + heroi.getMoedas() + "Moedas");
            System.out.println("Para reviver Digite: COMPRAR Vida1");
        }
        if(inimigo.getEnergia() <= 0){
            currentRoom.removePersonagem(command.getSecondWord());

            System.out.println(currentRoom.getLongDescription());

            if(inimigo.getTipo() == 'V'){
                if(currentRoom.getItens("MOEDAS") == null){
                    Item moedas = new Item("MOEDAS", 1,100, "M");
                    currentRoom.setItem("MOEDAS", moedas);
                }
                else{ 
                    Item i;
                    i = currentRoom.getItens("MOEDAS");
                    i.setAtributo(i.getAtributo() + 100);
                    currentRoom.setItem("MOEDAS", i);
                }
            }
            if(inimigo.getTipo() == 'B'){
                if(currentRoom.getItens("MOEDAS") == null){
                    Item moedas = new Item("MOEDAS", 1,500, "M");
                    currentRoom.setItem("MOEDAS", moedas);
                }
                else{ 
                    Item i;
                    i = currentRoom.getItens("MOEDAS");
                    i.setAtributo(i.getAtributo() + 500);
                    currentRoom.setItem("MOEDAS", i);
                }
                Item aux;
                aux = inimigo.getAtaque();
                currentRoom.setItem(aux.getNomeDoItem(), aux);

            }
            if(inimigo.getNome().equals("BOSS")){
                System.out.println("FIM DE JOGO");
                System.out.println("Você Concluiu o Jogo do World of ZUU");
                System.out.println("Feito por:");
                System.out.println("ARI VICTOR");
                System.out.println("FREDERICO, O GENIO");
                System.out.println("JOSÉ FELIPE MORÉ PAULETTI");
       
                System.out.println("AGRADECIMENTOS:");
                System.out.println("JACK DANIELS");
                System.out.println("NARUTO");
                System.out.println("BLEACH");
                System.out.println("E TUDO QUE HÁ DE BOM");
                System.out.println("PARA SAIR DIGITE: SAIR ");
            }
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println(newStringBlock() + "Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    private String newStringBlock(){
        return "\n------------\n";
    }
}
