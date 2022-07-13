package bb.roborally.ai;

public abstract class Agent {
    public void start() {
        connect();
        register();
        program();
    }

    private void connect() {
        // TODO: establish connection
    }

    private void register() {
        // TODO: register as AI Bot and signal readyness
    }

    private void program() {
        createProgram();
    }

    protected abstract void createProgram();
}
