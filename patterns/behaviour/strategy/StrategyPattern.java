package patterns.behaviour.strategy;


interface BaseStrategy {

    void connect();
}

class MySQLService implements BaseStrategy {

    @Override
    public void connect() {
        System.out.println("Connected to MySQL");
    }
}

class PostgresSQLService implements BaseStrategy {
    @Override
    public void connect() {
        System.out.println("Connected to PostgresSQL");
    }
}


class MainApplication {
    BaseStrategy strategy;

    public BaseStrategy getStrategy(String name) {
        if(name.equals("mysql")) {
            strategy = new MySQLService();
        } else {
            strategy = new PostgresSQLService();
        }
        return strategy;
    }
    public void connectToDB(String name) {
        BaseStrategy baseStrategy = getStrategy(name);
        baseStrategy.connect();
    }
}

public class StrategyPattern {
    public static void main(String[] args ){
        MainApplication application = new MainApplication();
        application.connectToDB("mysql");
    }

}
