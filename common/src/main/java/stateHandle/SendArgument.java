package stateHandle;

public class SendArgument extends State {

    public SendArgument(Object object) {
        super(object);
    }

    @Override
    public boolean handle() {

        return true;
    }
}
