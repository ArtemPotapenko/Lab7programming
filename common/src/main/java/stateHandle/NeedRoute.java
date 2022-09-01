package stateHandle;

public class NeedRoute extends State {
    public NeedRoute(Object object) {
        super(object);
    }

    @Override
    public boolean handle() {
        return true;
    }
}
