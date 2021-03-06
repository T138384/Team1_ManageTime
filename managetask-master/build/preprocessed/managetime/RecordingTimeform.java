/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managetime;

import data.BusinessTag;
import java.util.Vector;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.*;
import pojo.Tag;

/**
 *
 * @author BaoNguyen
 */
public final class RecordingTimeform extends Form implements CommandListener {

    Ticker newsTicker = new Ticker("Java J2ME");
    private Display display;
    Command Logout;
    Command Add;
    Command ManageTag;
    Command ManageTime;
    Command Statistic;
    Displayable main;
    BusinessTag businessAccess = new BusinessTag();

    public RecordingTimeform(String title) {
        super(title);
        //draw 
        this.drawGUI();
        // a menu with items
        this.showMenu();
        //load data
    }

    public RecordingTimeform(String title, Displayable Main) {
        super(title);
        //draw 
        this.drawGUI();
        // a menu with items
        this.showMenu();
        //load data
        main = Main;
    }

    protected void drawGUI() {
        TextField content = new TextField("Content:", "", 30, TextField.ANY);
        this.append(content);
        //DateField
        DateField date = new DateField("Date", DateField.DATE);
        date.setDate(new java.util.Date());
        this.append(date);
        //beginTime
        DateField beginTime = new DateField("Begin Time", DateField.TIME);
        date.setDate(new java.util.Date());
        this.append(beginTime);
        //endTime
        DateField endTime = new DateField("End Time", DateField.TIME);
        date.setDate(new java.util.Date());
        this.append(endTime);
        //list
        ChoiceGroup list = new ChoiceGroup("Tag:", Choice.EXCLUSIVE);
        Vector listTag = businessAccess.getAllTag();
        for (int i = 0; i < listTag.size(); i++) {
            Tag tag = (Tag) listTag.elementAt(i);
            list.append(tag.getName(), null);
            System.out.println(tag.getId() + " " + tag.getName());
        }
        this.append(list);
    }

    public void showMenu() {

        Add = new Command("Add", Command.OK, 2);
        ManageTag = new Command("ManageTag", Command.OK, 2);
        ManageTime = new Command("ManageTime", Command.OK, 2);
        Statistic = new Command("Statistic", Command.OK, 2);

        Logout = new Command("Logout", Command.EXIT, 2);
        this.addCommand(Logout);
        this.addCommand(Add);
        this.addCommand(ManageTag);
        this.addCommand(ManageTime);
        this.addCommand(Statistic);

        this.setCommandListener(this);
    }
    //Action

    public void commandAction(Command c, Displayable d) {
        String label = c.getLabel();
        if (label.equals("Logout")) {
            this.display.setCurrent(main);
        } else if (label.equals("ManageTag")) {
            ManageTagform f1 = new ManageTagform("Manage Tag");
            f1.setDisplay(this.display);
            f1.setTicker(newsTicker);
            this.display.setCurrent(f1);
        } else if (label.equals("ManageTime")) {
            //ManageTimeForm f1 = new ManageTimeForm("Edit Time");
            //f1.setDisplay(this.display);
            //this.display.setCurrent(f1);
        } else if (label.equals("Statistic")) {
            StatisticForm f1 = new StatisticForm("Statistic");
            f1.setDisplay(this.display);
            f1.setTicker(newsTicker);
            this.display.setCurrent(f1);
        }
    }

    void setDisplay(Display display) {
        this.display = display;
    }
}
