package designPattern.factoryMethod;

import designPattern.simpleFactory.Operation;
import designPattern.simpleFactory.OperationAdd;
import designPattern.simpleFactory.OperationMul;

public class MulFactory implements IFactory {
    @Override
    public Operation createOperation() {
        return new OperationMul();
    }
}
