package designPattern.factoryMethod;

import designPattern.simpleFactory.Operation;
import designPattern.simpleFactory.OperationAdd;
import designPattern.simpleFactory.OperationDiv;

public class DivFactory implements IFactory {
    @Override
    public Operation createOperation() {
        return new OperationDiv();
    }
}
