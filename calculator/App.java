import java.awt.*
import java.awt.event.*;
class MyFrame extends Frame implements ActionListener {
    Label l1,l2;
    Button b1,b2,b3,b4,b5;
    TextField t1,t2;
    int times = 0;
    int result = 0;
    int a,b;
    public MyFrame() {
        super("My Calculator APP");    // Title
        l1 = new Label("       ");
        l1.setFont(new Font("Verdana", Font.PLAIN, 24));
        l2 = new Label(" _ ");
        l2.setFont(new Font("Verdana", Font.PLAIN, 24));
        FlowLayout fl = new FlowLayout();
        setLayout(fl);
        t1 = new TextField(8);
        t1.setFont(new Font("Verdana", Font.PLAIN, 24));
        t2 = new TextField(8);
        t2.setFont(new Font("Verdana", Font.PLAIN, 24));
        add(t1);

        add(l2);

        add(t2);
        b1 = new Button("+");
        b1.setFont(new Font("Verdana", Font.PLAIN, 24));
        add(b1);
        b1.addActionListener(this);
        b2 = new Button("-");
        b2.setFont(new Font("Verdana", Font.PLAIN, 24));
        add(b2);
        b2.addActionListener(this);
        b3 = new Button("*");
        b3.setFont(new Font("Verdana", Font.PLAIN, 24));
        add(b3);
        b3.addActionListener(this);
        b4 = new Button("/");
        b4.setFont(new Font("Verdana", Font.PLAIN, 24));
        add(b4);
        b4.addActionListener(this);
        b5 = new Button("=");
        b5.setFont(new Font("Verdana", Font.PLAIN, 24));
        add(b5);
        b5.addActionListener(this);
        add(l1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        try {
            a = Integer.parseInt(t1.getText());
            b = Integer.parseInt(t2.getText());
        }
        catch (Exception er) {
            l1.setText("Error");
            repaint();
            return;
        }
        System.out.println(a+" "+b);
        switch(btn) {
            case "+":
                l2.setText(" + ");
                repaint();
                result = a + b;
                break;
            case "-":
                l2.setText(" - ");
                repaint();
                result = a - b;
                break;
            case "*":
                l2.setText(" * ");
                repaint();
                result = a * b;
                break;
            case "/":
                l2.setText(" / ");
                repaint();
                result = a / b;
                break;
            case "=":
                l1.setText(""+result);
                repaint();
                break;
        }
    }
}
public class App {
    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
        myFrame.setSize(800,500);
        myFrame.setVisible(true);
    }
}
