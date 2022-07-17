package bb.roborally.ai;

public class Ada extends Agent {

    public Ada(String ip, int port) {
        super(ip, port);
    }

    @Override
    protected String[] createProgram(String[] availableCards) {
        String[] program = new String[5];
        for (int i = 0; i < 5; i++) {
            program[i] = availableCards[0];
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
