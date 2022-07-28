package bb.roborally.client.programming_interface;

import bb.roborally.client.card.Card;

public class ProgramModel {

    private final Card register1 = new Card();
    private final Card register2 = new Card();
    private final Card register3 = new Card();
    private final Card register4 = new Card();
    private final Card register5 = new Card();

    public Card getRegister1(){
        return register1;
    }
    public Card getRegister2(){
        return register2;
    }
    public Card getRegister3(){
        return register3;
    }
    public Card getRegister4(){
        return register4;
    }
    public Card getRegister5(){
        return register5;
    }

    public void setRegister1(Card register1) {
        this.register1.setType(register1.getType());
    }
    public void setRegister2(Card register2) {
        this.register2.setType(register2.getType());
    }
    public void setRegister3(Card register3) {
        this.register3.setType(register3.getType());
    }
    public void setRegister4(Card register4) {
        this.register4.setType(register4.getType());
    }
    public void setRegister5(Card register5) {
        this.register5.setType(register5.getType());
    }

    public void setRegister(int register, Card card) {
        if (card == null) {
            getCard(register).reset();
        } else {
            getCard(register).set(card);
        }
    }

    public void resetRegister(int register) {
        getCard(register).setMarked(false);
        getCard(register).setType("");
    }

    public void reset() {
        for (int i = 1; i <= 5; i++) {
            resetRegister(i);
        }
    }

    //1
    public Card getCard(int registerID){
        return switch (registerID) {
            case 1 -> getRegister1();
            case 2 -> getRegister2();
            case 3 -> getRegister3();
            case 4 -> getRegister4();
            case 5 -> getRegister5();
            default -> null;
        };
    }


    public boolean isEmpty(int registerID) {
        return getCard(registerID).isEmpty();
    }

    public boolean isReady(){
        for (int i = 1; i <= 5; i++) {
            if (getCard(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }



}
