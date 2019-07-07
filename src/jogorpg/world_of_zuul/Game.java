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
 * @version 2019.07.07
 */

public class Game
{
    private Parser parser;
    private Room currentRoom;
    //private Personagem heroi;           // Atributo de herói - Como pedido em aula
    private Heroi heroi;
    private Vilao inimigo; 
    private Boss chefe;
    private Item permanente;
    private Item instantaneo;
    
    private String nomeDoHeroi = "Link";
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        //Room outside, theatre, pub, lab, office;
      
        // create the rooms
        Room entrance_hall = new Room("in the entrance hall of the castle");
        Room office = new Room("in the castle's office");
        Room lab = new Room("in a lab");        //It has a passage being blocked by an Orc
        Room aux_corridor = new Room("in an empty corridor");
        Room main_hall = new Room("in the giant center hall of the castle");
        Room dinning_room = new Room("in a dinning room, full of food over a big table");
        Room west_corridor = new Room("in an empty corridor");
        Room east_corridor = new Room("in an empty corridor");
        Room east_tower = new Room("in a watch tower with a stair leading to the underground");
        Room dungeon = new Room("in a dungeon full of creatures");
        Room north_corridor = new Room("in an empty corridor");
        Room north_tower = new Room("in the boss room");
        //Room outside = new Room("in the outside. You're not supposed to go outside.");
        
        // initialise room exits
        // Inicializa personagens da sala
        // Inicializa itens da sala
        
        //entrance_hall.setExit("south", askQuit);
        //If in entrance_hall, going south would make you go outside, which quits the game, it should offer the option to the person to either keep playing or quit
        entrance_hall.setExit("west", office);
        entrance_hall.setExit("east", lab);
        entrance_hall.setExit("north", main_hall);

        office.setExit("east", entrance_hall);
        office.setExit("west", west_corridor);
        
        lab.setExit("west", entrance_hall);
        lab.setPersonagem("Orc", inimigo);
        lab.setItem("Potion", instantaneo);
        //If deafeated Orc, secret passage to east_tower appears  >>  adds east exit to lab, and adds south exit to east_tower
        
        aux_corridor.setExit("east", office);
        aux_corridor.setExit("north", dinning_room);
        
        dinning_room.setExit("south", aux_corridor);
        dinning_room.setExit("east", west_corridor);
        dinning_room.setItem("Pernil", instantaneo);
        dinning_room.setItem("Peru", instantaneo);
        
        west_corridor.setExit("west", dinning_room);
        west_corridor.setExit("east", main_hall);
        
        main_hall.setExit("south", entrance_hall);
        main_hall.setExit("west", west_corridor);
        main_hall.setExit("east", east_corridor);
        main_hall.setExit("north", north_corridor);
        
        east_corridor.setExit("west", main_hall);
        east_corridor.setExit("east", east_tower);
        
        east_tower.setExit("west", east_corridor);
        east_tower.setExit("north", dungeon);
        east_tower.setItem("Axe", permanente);
        
        dungeon.setExit("south", east_tower);
        dungeon.setPersonagem("Esqueleto", inimigo);
        dungeon.setPersonagem("Urgal", heroi);
        dungeon.setPersonagem("Gnome", inimigo);
        
        north_corridor.setExit("south", main_hall);
        north_corridor.setExit("north", north_tower);   //Talvez fazer pedido de chave q se encontra na dungeon
        
        north_tower.setExit("south", north_corridor);
        north_tower.setPersonagem("Ganon", chefe);
        

        currentRoom = entrance_hall;  // start game outside
        currentRoom.setPersonagem(nomeDoHeroi, heroi);
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
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
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
        else if(commandWord == CommandWord.PICK){
            pickItem(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println(newStringBlock() + "You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
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
        
//        if(nextRoom == outside){
//            System.out.println("You're going outside. That will quit the game.");
//            System.out.println("Do you want to return to the castle? (Y/N)");
//            
//        }
        if (nextRoom == null) {
            System.out.println(newStringBlock() + "There is no door!");
        }
        else {
            currentRoom.removePersonagem(nomeDoHeroi);
            currentRoom = nextRoom;
            currentRoom.setPersonagem(nomeDoHeroi, heroi);
            System.out.println(currentRoom.getLongDescription());
        }
    }
 
    private void goAttack(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know who to attack...
            System.out.println(newStringBlock() + "Attack who?");
            return;
        }

        String enemy = command.getSecondWord();
        
        //heroi.lutar(inimigo);
        currentRoom.battle(heroi, enemy);

        // Try to leave current room.
        ///Room nextRoom = currentRoom.getExit("east");
        /*
        Battle nextBattle() = new Battle();
        nextBattle() = NULL;
        nextBattle.newBattle();
        
                //currentRoom.SimuladordeBatalhas();
        
        if (nextRoom == null) {
            System.out.println(newStringBlock() + "There is no door!");
        }
        else {
            currentRoom.removePersonagem("Link");
            currentRoom = nextRoom;
            currentRoom.setPersonagem("Link", heroi);
            System.out.println(currentRoom.getLongDescription());
        
        //Matar inimigo: +100 moedas
        //Matar boss: +500 ou 1000 moedas
        }
        */
    }
    
    private void pickItem(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println(newStringBlock() + "Pick what?");
            return;
        }

        String item = command.getSecondWord();

        Item tipoItem = currentRoom.getItem(item);
        // Quando pegar item, deve adicionar ao personagem(se couber na bolsa) e remover do cenário
        if(tipoItem == instantaneo)
            heroi.incremento(1);
        else
            heroi.inserirItem(tipoItem);
        //Talvez seja necessário testar se instantaneo ou permanente para decidir se precisa guardar ou se tem efeito instantaneo
        
        return;
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
