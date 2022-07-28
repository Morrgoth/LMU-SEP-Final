package bb.roborally.ai;

import bb.roborally.protocol.Position;

import java.util.*;

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
        //System.out.println(bestCandidatePosition);
        //System.out.println(bestDistance);
        //System.out.println(bestCandidate);
        return bestCandidate;
    }

    @Override
    protected String getName() {
        return "Amy";
    }

    public static void main(String[] args) {
        boolean local = true;
        if (local) {
            Amy amy = new Amy("localhost", 6868);
            amy.start();
        } else {
            Amy amy = new Amy("sep21.dbs.ifi.lmu.de", 52018);
            amy.start();
        }

    }

    private void buildCandidates() {
        optimiseYourCards();
        candidates.clear();
        generateRandomCandidates(1000);
    }

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

    private void permutation(CardModel[] prefix, CardModel[] rest) {
        if (prefix.length == 5) candidates.add(new Program(prefix));
        else {
            for (int i = 0; i < rest.length; i++)
                permutation(concat(prefix, rest[i]), concat(subarray(rest, 0, i), subarray(rest, i + 1, rest.length)));
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

    private boolean isIllegal(Program program, CardModel[] cards) {
        for (CardModel cardModel: program.getProgram()) {
            if (count(program.getProgram(), cardModel.type()) > count(cards, cardModel.type())) {
                return true;
            }
        }
        return false;
    }

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
