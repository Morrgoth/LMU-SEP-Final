package bb.roborally.ai;

public class Noether extends Agent {
    public Noether(String ip, int port) {
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
        Noether noether = new Noether("localhost", 6868);
        noether.start();
    }
}
