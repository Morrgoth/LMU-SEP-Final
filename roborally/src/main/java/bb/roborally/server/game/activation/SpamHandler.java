package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.PlayingCard;

import java.io.IOException;

public class SpamHandler {
    Server server;
    Game game;
    User user;
    private int register;

    public SpamHandler(Server server, Game game, User user, int register) {
        this.server = server;
        this.game = game;
        this.user = user;
        this.register = register;
    }
    public void handle() throws IOException {
        PlayingCard playedCardisSpam = user.getProgram().getCardInRegister(register);
        game.getSpamDeck().getSpamDeck().add(playedCardisSpam);
        user.getProgram().resetOneRegister(register);
        game.getSpamDeck().getSpamDeck().add(playedCardisSpam);

        user.getProgram().add(user.getProgrammingDeck().draw(),register);
        if (user.getProgram().getCardInRegister(register).getName().equals("Move1")){
            Move1Handler move1Handler = new Move1Handler(server,game, user);
            move1Handler.handle();
        } else if (user.getProgram().getCardInRegister(register).getName().equals("Move2")) {
            Move2Handler move2Handler = new Move2Handler(server,game,user);
            move2Handler.handle(user);
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Move3")) {
            Move3Handler move3Handler = new Move3Handler(server,game,user);
            move3Handler.handle(user);
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Again")) {
            AgainHandler againHandler = new AgainHandler(server,game,user,register);
            againHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("BackUp")) {
            BackUpHandler backUpHandler = new BackUpHandler(server,game, user);
            backUpHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("PowerUp")) {
            PowerUpHandler powerUpHandler = new PowerUpHandler(server,game,user);
            powerUpHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("TurnLeft")) {
            TurnLeftHandler turnLeftHandler = new TurnLeftHandler(server,game,user);
            turnLeftHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("TurnRight")) {
            TurnRightHandler turnRightHandler = new TurnRightHandler(server,game,user);
            turnRightHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("UTurn")) {
            UTurnHandler uTurnHandler = new UTurnHandler(server,game,user);
            uTurnHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Spam")) {
            SpamHandler spamHandler = new SpamHandler(server,game, user, register);
            spamHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("TrojanHorse")) {
            TrojanHandler trojanHandler = new TrojanHandler(server, game, user, register);
            trojanHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Worm")) {
            WormHandler wormHandler = new WormHandler(server,game,user,register);
            wormHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Virus")) {
            VirusHandler virusCardHandler = new VirusHandler(server,game,user,register);
            virusCardHandler.handle();
        }

        //broadcast ReplaceCard(only), CardPlayed(except), PlayCard(only)
    }


}
