package managetime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BaoNguyen
 */
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;

public class Midlet extends MIDlet implements CommandListener {
    
    Ticker newsTicker = new Ticker("Java J2ME");
    private Display display;
    private final TextField userName;
    private final TextField password;
    private final Form form;
    private final Command cancel;
    private final Command login;

    public Midlet() {
        userName = new TextField("LoginID :         ", "", 10, TextField.ANY);
        password = new TextField("Password:", "", 10, TextField.PASSWORD);
        form = new Form("Log in");
        cancel = new Command("Cancel", Command.CANCEL, 2);
        login = new Command("Login", Command.OK, 2);
    }

    public void startApp() {
        display = Display.getDisplay(this);
        form.setTicker(newsTicker);
        form.append(userName);
        form.append(password);
        form.addCommand(cancel);
        form.addCommand(login);
        form.setCommandListener(this);
        display.setCurrent(form);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }

    public void validateUser(String name, String password) {
        if (name.equals("n") && password.equals("1")) {
            menu();
        } else {
            tryAgain();
        }
    }

    public void menu() {
        RecordingTimeform f1 = new RecordingTimeform("Recording Time",Display.getDisplay(this).getCurrent());
        //f1.setTitle(newsTicker);
        display = Display.getDisplay(this);
        f1.setDisplay(display);
        display.setCurrent(f1);
    }

    public void tryAgain() {
        Alert error = new Alert("Login Incorrect", "Please try again", null, AlertType.ERROR);
        error.setTimeout(Alert.FOREVER);
        userName.setString("");
        password.setString("");
        display.setCurrent(error, form);
    }

    public void commandAction(Command c, Displayable d) {
        String label = c.getLabel();
        if (label.equals("Cancel")) {
            destroyApp(true);
        } else if (label.equals("Login")) {
            validateUser(userName.getString(), password.getString());
        }
    }
}
