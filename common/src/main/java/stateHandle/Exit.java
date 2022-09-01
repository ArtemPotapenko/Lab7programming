package stateHandle;

public class Exit extends State {
    public Exit(Object object) {
        super(object);
    }

    @Override
    public boolean handle() {
        System.exit(0);
        return false;
    }
}
