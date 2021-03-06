package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();

    //增加观察者
    public void attach(Observer observer) {
        observers.add(observer);
    }

    //移除观察者
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    //通知
    public void Notify() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
