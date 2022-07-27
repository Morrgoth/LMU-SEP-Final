package bb.roborally.ai;

public class Amy extends Agent {
    public Amy(String ip, int port) {
        super(ip, port);
    }

    @Override
    protected String[] createProgram(String[] availableCards) {
        return new String[0];
    }

    @Override
    protected String getName() {
        return "Emmy";
    }

    public static void main(String[] args) {
        Amy amy = new Amy("localhost", 6868);
        amy.start();
    }
}
