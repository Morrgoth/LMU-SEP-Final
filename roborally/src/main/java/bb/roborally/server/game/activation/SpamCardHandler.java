package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.Move1;
import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.deck.ProgrammingDeck;

public class SpamCardHandler {
    Server server;
    Game game;
    User user;
    private int register;

    public SpamCardHandler(Server server, Game game, User user, int register) {
        this.server = server;
        this.game = game;
        this.user = user;
        this.register = register;
    }
    public void handle(){
        PlayingCard playedCardisSpam = user.getProgram().getCardInRegister(register);
        playedCardisSpam.setMarked(true);
        for (int i = 0; i< user.getProgram().getProgram().length; i++){
            if (user.getProgram().getProgram()[i] == playedCardisSpam){
                user.getProgram().resetOneRegister(playedCardisSpam);
            }
        }
        game.getSpamDeck().getSpamDeck().add(playedCardisSpam);
        playedCardisSpam.setMarked(false);
        user.getProgram().add(user.getProgrammingDeck().draw(),register);
        if (user.getProgram().getCardInRegister(register).getName().equals("Move1")){
            Move1Handler move1Handler = new Move1Handler(server,game, user);
            move1Handler.handle();
        } else if (user.getProgram().getCardInRegister(register).getName().equals("Move2")) {

        }else if (user.getProgram().getCardInRegister(register).getName().equals("Move3")) {

        }else if (user.getProgram().getCardInRegister(register).getName().equals("Again")) {
            AgainHandler againHandler = new AgainHandler(server,game,user,register);
            againHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("BackUp")) {
            BackUpHandler backUpHandler = new BackUpHandler(server,game, user);
            backUpHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("PowerUp")) {

        }else if (user.getProgram().getCardInRegister(register).getName().equals("TurnLeft")) {

        }else if (user.getProgram().getCardInRegister(register).getName().equals("TurnRight")) {

        }else if (user.getProgram().getCardInRegister(register).getName().equals("UTurn")) {

        }else if (user.getProgram().getCardInRegister(register).getName().equals("Spam")) {
            SpamCardHandler spamCardHandler = new SpamCardHandler(server,game, user, register);
            spamCardHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("TrojanHorse")) {
            TrojanCardHandler trojanCardHandler = new TrojanCardHandler(server, game, user, register);
            trojanCardHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Worm")) {

        }else if (user.getProgram().getCardInRegister(register).getName().equals("Virus")) {

        }

        //broadcast ReplaceCard(only), CardPlayed(except), PlayCard(only)
    }


}
