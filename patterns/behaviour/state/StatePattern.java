package patterns.behaviour.state;


/**
 * Why State is necessary
 * Consider Java example
 * class Test {
 *     public void publish(String state) {
 *         switch (state)
 *             "draft":
 *                 state = "moderation"
 *                 break
 *             "moderation":
 *                 if (currentUser.role == "admin")
 *                     state = "published"
 *                 break
 *             "published":
 *                 // Do nothing.
 *                 break
 *     }
 * }
 * This will bloat the code and hence it is necessary to create separate class for each
 * To switch the state of the context, create an instance of one of the state classes and pass it to the context.
 * You can do this within the context itself, or in various states, or in the client.
 * Wherever this is done, the class becomes dependent on the concrete state class that it instantiates.
 * Components
 * Components in State pattern
 * 1. Context - Document Context
 * 2.  - Context will have DocumentState
 * 3.  - Provide setter for State
 * 4.  - provide initial state in Constructor
 * 5. Declare abstract State
 * 6. Declare Concrete states
 */

interface DocumentState {

    void edit(DocumentContext state);
    void review(DocumentContext state);
    void publish(DocumentContext state);
}

class DraftState implements DocumentState{

    @Override
    public void edit(DocumentContext context) {
        System.out.println("Document already in editing state");
    }

    @Override
    public void review(DocumentContext context ) {
        System.out.println("Document moved to moderation state");
        context.setState(new ModerationState());
    }

    @Override
    public void publish(DocumentContext context ) {
        System.out.println("Document cannot be published from edit");
    }
}

class ModerationState implements DocumentState {

    @Override
    public void edit(DocumentContext context) {
        System.out.println("Document moved back to edit state");
        context.setState(new DraftState());
    }

    @Override
    public void review(DocumentContext context ) {
        System.out.println("Document already in moderation state");
    }

    @Override
    public void publish(DocumentContext context ) {
        System.out.println("Document published");
        context.setState(new PublishedState());

    }
}

class PublishedState implements DocumentState {

    @Override
    public void edit(DocumentContext context ) {
        System.out.println("Document cannot be moved back to edit");
    }

    @Override
    public void review(DocumentContext context ) {
        System.out.println("Document cannot be moved back to moderation");
    }

    @Override
    public void publish(DocumentContext context ) {
        System.out.println("Document already published");
    }
}

class DocumentContext {
    DocumentState state;

    public DocumentContext() {
        this.state = new DraftState();
    }

    public void setState(DocumentState state) {
        this.state = state;
    }

    /*
     * This is where the initial switch case will be existing.
     * Replace it with call to the state
     */

    public void edit() {
        state.edit(this);
    }

    public void review() {
        state.review(this);
    }

    public void publish() {
        state.publish(this);
    }
}


public class StatePattern {

    public static void main(String[] args) {
        DocumentContext context = new DocumentContext();
        context.review();
        context.edit();
        context.publish();
        context.review();
        context.publish();
    }
}
