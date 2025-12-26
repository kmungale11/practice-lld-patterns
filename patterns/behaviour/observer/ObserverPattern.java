package patterns.behaviour.observer;

import java.util.ArrayList;
import java.util.List;

interface BasePublisher {
    void subscribe(BaseSubscriber subscriber);
    void unsubscribe(BaseSubscriber subscriber);
    void publish(String message);
}

class Notifications implements BasePublisher {
    List<BaseSubscriber> subscribers = new ArrayList<>();

    @Override
    public void subscribe(BaseSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(BaseSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void publish(String message) {
        for(BaseSubscriber subscriber: subscribers) {
            subscriber.broadcast(message);
        }
    }
}

interface BaseSubscriber {
    void broadcast(String message);
}

class SMS implements BaseSubscriber {
    @Override
    public void broadcast(String message) {
        System.out.println("Broadcasting message: " + message + " on SMS channel: ");
    }
}

class Email implements BaseSubscriber {
    @Override
    public void broadcast(String message) {
        System.out.println("Broadcasting message: " + message + " on Email channel: ");
    }
}

public class ObserverPattern {

    public static void main(String[] args) {
        BaseSubscriber smsSubscription = new SMS();
        BaseSubscriber emailSubscription = new Email();

        Notifications notifications = new Notifications();
        notifications.subscribe(smsSubscription);
        notifications.subscribe(emailSubscription);
        notifications.publish("Alert message");

        notifications.unsubscribe(smsSubscription);
        notifications.publish("New alert");
    }
}
