package jogorpg.world_of_zuul;

/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author  Frederico Peixoto Antunes e José Pauletti
 * @version 2019.06.30
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("ir"), QUIT("sair"), HELP("ajuda"), UNKNOWN("?"), ATTACK("atacar"), ARMA("arma"), EQUIP("def"), UTITENS("item"), BAU("bau"), COMPRAR("comprar"), REMOVER("remover");       // Adicionado ATTACK - Como pedido em aula
    
    // The command string.
    private String commandString;
    
    /**
    * Initialise with the corresponding command word.
    * @param commandWord The command string.
    */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
