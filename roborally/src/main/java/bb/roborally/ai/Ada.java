package bb.roborally.ai;

/**
 * Random Ai Agent
 *
 * @author Bence Ament
 */
public class Ada extends Agent {

    public Ada(String ip, int port) {
        super(ip, port);
    }

    @Override
    protected Program createProgram(CardModel[] availableCards) {
        Program program = new Program();
        for (int i = 1; i <= 5; i++) {
            program.set(i, availableCards[i]);
        }
        return program;
    }

    @Override
    protected String getName() {
        return "Ada";
    }

    public static void main(String[] args) {
        Ada ada = new Ada("localhost", 6868);
        ada.start();
    }


}
