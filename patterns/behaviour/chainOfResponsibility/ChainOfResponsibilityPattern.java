package patterns.behaviour.chainOfResponsibility;

abstract class BaseHandler {

    protected BaseHandler next;

    public BaseHandler setLink(BaseHandler next) {
        this.next = next;
        return next;
    }

    abstract boolean handle(String username, String password);

    public boolean checkNext(String username, String password) {
        if(this.next == null) {
            return true;
        }
        return next.handle(username, password);
    }
}

class Authentication extends BaseHandler{

    @Override
    boolean handle(String username, String password) {
        if(!username.equals("kaustubh")) {
            System.out.println("Authentication failed");
            return false;
        }
        System.out.println("Authentication passed. Proceeding with authorization");

        return this.checkNext(username, password);
    }
}

class Authorization extends BaseHandler {

    @Override
    boolean handle(String username, String password) {
        if(!password.equals("mungale")) {
            System.out.println("Authorization failed");
            return false;
        }
        System.out.println("Authorization passed");
        return this.checkNext(username, password);
    }
}

class UserAccess extends BaseHandler {

    @Override
    boolean handle(String username, String password) {
        if(!username.equals("kaustubh")) {
            System.out.println("User does not exists");
            return false;
        }
        System.out.println("User exists. Proceeding with authentication ... ");
        return this.checkNext(username, password);
    }
}

public class ChainOfResponsibilityPattern {

    public static void main(String[] args) {
        BaseHandler handler = new UserAccess();
        BaseHandler authentication = new Authentication();
        BaseHandler authorization = new Authorization();
        handler.setLink(authentication).setLink(authorization);

        System.out.println(handler.handle("kaustubh", "mungale"));
    }
}
