package designPattern.factoryMethod;

import designPattern.simpleFactory.Operation;
import designPattern.simpleFactory.OperationAdd;

public class AddFactory implements IFactory {
    @Override
    public Operation createOperation() {
        return new OperationAdd();
    }
}
