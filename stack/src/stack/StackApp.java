
package stack;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

public class StackApp extends SingleFrameApplication {

    @Override protected void startup() {
        show(new StackView(this));
    }

    @Override protected void configureWindow(java.awt.Window root) {
    }

    public static StackApp getApplication() {
        return Application.getInstance(StackApp.class);
    }

    public static void main(String[] args) {
        launch(StackApp.class, args);
    }
}
