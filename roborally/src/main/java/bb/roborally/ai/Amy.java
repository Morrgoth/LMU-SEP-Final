package bb.roborally.ai;

import bb.roborally.protocol.Position;

import java.util.*;

/**
 * Model-based AI Agent
 *
 * @author Bence Ament
 */
public class Amy extends Agent {

    private final ArrayList<Program> candidates = new ArrayList<>();
    public Amy(String ip, int port) {
        super(ip, port);
    }

    @Override
    protected Program createProgram(CardModel[] availableCards) {
        buildCandidates();
        Set<Program> reduced = new LinkedHashSet<>(candidates);
        candidates.clear();
        candidates.addAll(reduced);
        candidates.removeIf(program -> isIllegal(program, getYourCards()));
        candidates.removeIf(program -> !getBoardModel().isProgramValid(program));
        candidates.removeIf(program -> !getBoardModel().calculateNextState(program, getPosition(), getOrientation()).getKey().isValid());

        Position nextCheckPoint = getBoardModel().findNextCheckpoint(getCheckpoints());
        Program bestCandidate = candidates.get(0);
        Position bestCandidatePosition = getBoardModel().calculateNextState(bestCandidate, getPosition(), getOrientation()).getKey();
        double bestDistance = Position.calculateDistance(bestCandidatePosition, nextCheckPoint);
        for (Program program: candidates) {
            Position position = getBoardModel().calculateNextState(program, getPosition(), getOrientation()).getKey();
            if (Position.calculateDistance(position, nextCheckPoint) < bestDistance) {
                bestCandidate = program;
                bestDistance = Position.calculateDistance(position, nextCheckPoint);
                bestCandidatePosition = position;
            }
        }
        return bestCandidate;
    }

    @Override
    protected String getName() {
        return "Amy";
    }

    public static void main(String[] args) {
        if (args.length >= 3) {
            Amy amy = new Amy(args[1], Integer.parseInt(args[2]));
            amy.start();
        } else {
            Amy amy = new Amy("localhost", 6868);
            amy.start();
        }
    }

    /**
     * Constructs a set of candidate programs
     */
    private void buildCandidates() {
        optimiseYourCards();
        candidates.clear();
        generateRandomCandidates(30000);
    }

    /**
     * @param count the number of candidates to generate
     */
    private void generateRandomCandidates(int count) {
        final ArrayList<CardModel> all = new ArrayList<>(List.of(getYourCards()));
        for (int i = 0; i < count; i++) {
            Collections.shuffle(all);
            CardModel[] program = new CardModel[5];
            int ind = 0;
            for (CardModel cardModel: all.subList(0, 5)) {
                program[ind++] = cardModel;
            }
            Program program1 = new Program(program);
            candidates.add(program1);
        }
    }

    private CardModel[] concat(CardModel[] cardModels, CardModel cardModel) {
        CardModel[] result = new CardModel[cardModels.length + 1];
        for (int i = 0; i < cardModels.length; i++) {
            result[i] = cardModels[i];
        }
        result[cardModels.length] = cardModel;
        return result;
    }

    private CardModel[] concat(CardModel[] cardModels1, CardModel[] cardModels2) {
        CardModel[] result = new CardModel[cardModels1.length + cardModels2.length];
        for (int i = 0; i < cardModels1.length; i++) {
            result[i] = cardModels1[i];
        }
        for (int i = 0; i < cardModels2.length; i++) {
            result[cardModels1.length + i] = cardModels2[i];
        }
        return result;
    }

    private CardModel[] subarray(CardModel[] array, int beginIndex, int endIndex) {
        return Arrays.copyOfRange(array, beginIndex, endIndex);
    }

    private void optimiseYourCards() {
        //minimizeDamageCards();
    }

    private void minimizeDamageCards() {
        if (getYourCards().length - damageCardCount() >= 5) {
            CardModel[] yourCards = new CardModel[getYourCards().length - damageCardCount()];
            int index = 0;
            for (int i = 0; i < getYourCards().length; i++) {
                if (!isDamageCard(getYourCards()[i])) {
                    yourCards[index++] = getYourCards()[i];
                }
            }
            setYourCards(yourCards);
        } else {
            // Nothing for now
        }
    }

    /**
     * @return the number of damage cards in the Bot's hand
     */
    private int damageCardCount() {
        int cnt = 0;
        for (CardModel cardModel: getYourCards()) {
            if (isDamageCard(cardModel)) {
                cnt += 1;
            }
        }
        return cnt;
    }

    private static boolean isDamageCard(CardModel cardModel) {
        return cardModel.type() == CardModel.CardType.SPAM || cardModel.type() == CardModel.CardType.VIRUS
                || cardModel.type() == CardModel.CardType.WORM || cardModel.type() == CardModel.CardType.TROJAN;
    }

    /**
     * @param program program to check
     * @param cards available cards
     * @return returns whether the program can be constructed from the given cards
     */
    private boolean isIllegal(Program program, CardModel[] cards) {
        for (CardModel cardModel: program.getProgram()) {
            if (count(program.getProgram(), cardModel.type()) > count(cards, cardModel.type())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param cards the list of cards
     * @param type the type of the card we want to count
     * @return the number of cards of the given type in the list
     */
    private int count(CardModel[] cards, CardModel.CardType type) {
        int cnt = 0;
        for (CardModel cardModel: cards) {
            if (cardModel.type() == type) {
                cnt += 1;
            }
        }
        return 0;
    }
}
