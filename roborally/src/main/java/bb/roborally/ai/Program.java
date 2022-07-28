package bb.roborally.ai;

import java.util.ArrayList;

public class Program {
    public final int LENGTH = 5;
    private final CardModel[] program = new CardModel[LENGTH];

    public Program(CardModel[] cardModels) {
        for (int i = 0; i < LENGTH; i++) {
            program[i] = cardModels[i];
        }
    }

    public Program(ArrayList<CardModel> cardModels) {
        for (int i = 0; i < LENGTH; i++) {
            program[i] = cardModels.get(i);
        }
    }

    public Program() {

    }

    public boolean set(int register, CardModel cardModel) {
        if (1 <= register && register <= 5) {
            program[register - 1] = cardModel;
            return true;
        }
        return false;
    }

    public CardModel get(int register) {
        if (1 <= register && register <= 5) {
            return program[register - 1];
        }
        return null;
    }

    public int getNextEmptyRegister() {
        for (int i = 0; i < LENGTH; i++) {
            if (program[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public CardModel[] getProgram() {
        return program;
    }

    public boolean isReady() {
        for (CardModel cardModel: program) {
            if (cardModel == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Program other)) return false;
        for (int i = 1; i <= LENGTH; i++) {
            if (get(i).type() != other.get(i).type()) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < LENGTH; i++) {
            stringBuilder.append(program[i].type());
            if (i != LENGTH - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
