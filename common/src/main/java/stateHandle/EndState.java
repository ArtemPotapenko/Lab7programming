package stateHandle;

public class EndState extends State {
    public EndState(Object object) {
        super(object);
    }

    @Override
    public boolean handle() {
        return false;
    }
}
