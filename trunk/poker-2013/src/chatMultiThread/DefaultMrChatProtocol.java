package chatMultiThread;

import java.util.*;
import java.util.regex.*;

public class DefaultMrChatProtocol extends ChatProtocol {

  private Map<String, Command> commands;

  {
    commands = new HashMap<String, Command>();
    commands.put("list", new List());
    commands.put("msg",  new Msg());
    commands.put("time", new Time());
    commands.put("user", new User());
    commands.put("quit", new Quit());
  }

  public DefaultMrChatProtocol(ChannelsManager manager) {
    super(manager);
  }

  public void parserMessage(ThreadChannel ch, String str) {
    if(str.charAt(0)!='/') {
      ch.send("Sintassi errata");
    } else {
      Pattern pattern = Pattern.compile("\\/([^\\s]+)\\s(.*)");
      Matcher match = pattern.matcher(str);
      match.find();
      String command = match.group(1);
      if (commands.containsKey(command)) {
        Command cmd = commands.get(command);
        cmd.execute(ch, match);
      } else {
        ch.send("Comando sconosciuto");
      }
    }
  }
  /* Invia la stringa msg a tutti i client, il flag mysend
  indica se inviarlo anche al mittente */
  protected void broadcast(String name, String msg, boolean mysend) {
    Set <String> set =  manager.getAllName();
    for(String str : set) {
      if(!str.equalsIgnoreCase(name) || mysend) {
        ThreadChannel channel = manager.getChannel(str);
        if(channel.isLogin()) channel.send(msg);
      }
    }
  }

  public void startMessage(ThreadChannel ch) {
    ch.send("Benvenuto nella MrChat - http://www.mrwebamster.it");
  }

  private class User implements Command {
     public void execute(ThreadChannel channel, Matcher match) {
       if(!channel.isLogin()) {
         String name= match.group(2);
         if (name.length()==0) {
           channel.send("Sintassi del comando errata");
         } else if(manager.addChannel(name.toLowerCase(), channel)) {
           broadcast(name, "L'utente "+name+" si e' collegato alla chat!!", false);
           channel.setName(name);
           channel.setLogin(true);
           channel.send("Ciao "+channel.getName()+" ti sei loggato correttamente!!");
         } else {
           channel.send("Nome gia' in uso da un altro utente");
         }
       } else {
          channel.send("Sei gia' loggato!!");
       }
     }
   }

   private class Msg implements Command {
     public void execute(ThreadChannel channel, Matcher match) {
        if (channel.isLogin()) {
           broadcast(channel.getName().toLowerCase(),
             "#"+channel.getName()+" "+match.group(2), true);
        } else {
           channel.send("Non ti sei ancora loggato");
        }
     }
   }

   private class Time implements Command {
      public void execute(ThreadChannel channel, Matcher match) {
        channel.send(new Date().toString());
      }
   }

   private class Quit implements Command {
     public void execute(ThreadChannel channel, Matcher match) {
       channel.send("By By");
       broadcast(channel.getName().toLowerCase(),
         "L'utente "+channel.getName()+ " si e' disconnesso!!", false);
       manager.removeChannel(channel.getName().toLowerCase());
       channel.closeChannel();
     }
   }

   private class List implements Command {
     public void execute(ThreadChannel channel, Matcher match) {
       Set <String> names = manager.getAllName();
       if (names.isEmpty()) {
         channel.send("Nessun utente collegato");
       } else {
         channel.send("********** Lista degli utenti connessi **********");
         for(String name:names)
           channel.send(name);
       }
     }
  }
}