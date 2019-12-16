package designPattern.factoryMethod;

import designPattern.simpleFactory.Operation;
import designPattern.simpleFactory.OperationAdd;
import designPattern.simpleFactory.OperationSub;

public class SubFactory implements IFactory {
    @Override
    public Operation createOperation() {
        return new OperationSub();
    }
}
