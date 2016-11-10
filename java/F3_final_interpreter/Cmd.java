
package main;

public class Cmd {
    String commandName;
    int commandID;
    
    public Cmd(String name, int id){
        commandName = name;
        commandID = id;
    }
    
    public void setName(String cmdName){
        commandName = cmdName;
    }
    public void setID(int id){
        commandID = id;
    }
    
}
