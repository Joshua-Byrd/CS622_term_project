package edu.bu.view;

/**
 * Facade for the view package, providing a singleton access point to the view functionalities.
 */
public class FacadeView {

    private static FacadeView instance;
    private TextView textView;

    // Private constructor to prevent instantiation
    private FacadeView() {
        textView = new TextView();
    }

    /**
     * INTENT: To provide a global point of access to the FacadeView singleton instance.
     * PRECONDITION: None.
     * POSTCONDITION: Returns the singleton instance of FacadeView.
     *
     * @return The singleton instance of FacadeView.
     */
    public static synchronized FacadeView getTheInstance() {
        if (instance == null) {
            instance = new FacadeView();
        }
        return instance;
    }

    // TextView Methods
    public TextView createTextView() {
        return new TextView();
    }

    public void displayMessage(String message) {
        textView.displayMessage(message);
    }

    public void printGreeting() {
        textView.printGreeting();
    }
}
