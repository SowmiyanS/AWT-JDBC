// Java AWT Program for Hello World
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.*;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;

// Driver Class
public class AWTApp {
    // main function
    public static void main(String[] args)
    {
        // Declaring a Frame and Label
        Frame frame = new Frame("Student Registration Form");
        Label label = new Label("Student Details");
        label.setBounds(20,30,100,30);
        frame.add(label);

        // Aligning the label to CENTER

        Label namel = new Label("Name:");
        namel.setBounds(20, 70, 100, 30);
        frame.add(namel);
        TextField name = new TextField();
        name.setBounds(120, 70, 350, 30);
        frame.add(name);
        Label emaill = new Label("Email:");
        emaill.setBounds(20, 100, 100, 30);
        frame.add(emaill);
        TextField email = new TextField();
        email.setBounds(120, 100, 350, 30);
        frame.add(email);
        Label agel = new Label("Age:");
        agel.setBounds(20, 130, 100, 30);
        frame.add(agel);
        TextField age = new TextField();
        age.setBounds(120, 130, 350, 30);
        frame.add(age);

        Label error = new Label();
        error.setBounds(20, 210, 350, 30);
        frame.add(error);

        Button button = new Button("submit");
        button.setBounds(20,170,350,30);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //text_field.setText("Wow it Works!");
                boolean isValid = true;
                boolean isNameEmpty = false;
                boolean isEmailEmpty = false;
                boolean isAgeEmpty = false;
                boolean errored = false;
                String nameValue = name.getText().trim();
                String emailValue = email.getText();
                String ageValue = age.getText();
                if(nameValue.isEmpty()) {
                    isValid = false;
                    isNameEmpty = true;
                    if(!errored) {
                        error.setText("Name cannot be empty");
                        errored = true;
                    }
                } if(nameValue.length() < 2) {
                    isValid = false;
                    if(!errored) {
                        error.setText("Name must be longer than 2 Characters");
                        errored = true;
                    }
                } if(emailValue.isEmpty()) {
                    isValid = false;
                    isEmailEmpty = true;
                    if(!errored) {
                        error.setText("Email cannot be empty");
                        errored = true;
                    }
                } if (emailValue.length() < 2) {
                    isValid = false;
                    if(!errored) {
                        error.setText("Email must be longer than 2 Characters");
                        errored = true;
                    }
                } if(ageValue.isEmpty()) {
                    isValid = false;
                    isAgeEmpty = true;
                    if(!errored) {
                        error.setText("Age cannot be empty");
                        errored = true;
                    }
                }
                int ageIntValue = 0;
                try {
                    ageIntValue = Integer.parseInt(ageValue.trim());
                } catch(Exception ex) {
                    isValid = false;
                    if(!errored) {
                        error.setText("Age cannot be non numerical");
                        errored = true;
                    }
                }
                //System.out.println("na:"+isNameEmpty+"\nem:"+isEmailEmpty+"\nag:"+isAgeEmpty);
                if(!(isNameEmpty == false || (isEmailEmpty == false || isAgeEmpty == false))) {
                    // No need errored == false check as it is the special case
                    error.setText("You must fill all the fields!");
                    errored = true;
                }
                if(isValid) {
                    error.setText("");
                    System.out.println("Name: " + name.getText() + "\nEmail: " + email.getText() + "\nAge: " + age.getText());

                    Connection conn = null;
                    try {
                        conn = retConnection();
                    } catch(SQLException sqle) {
                        System.out.println(sqle);
                    }

                    
                    String query0 = "CREATE TABLE IF NOT EXISTS usr (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), email VARCHAR(255), age VARCHAR(255))";
                    try {
                        Statement stmt = conn.createStatement();
                        if(stmt.executeUpdate(query0) == 0) {
                            error.setText("Created Table!");
                        }
                    } catch(SQLException sqle2) {
                        System.out.println(sqle2);
                    }

                    String query = "INSERT INTO usr (name, email, age) VALUES ('"+nameValue+"','"+emailValue+"',"+ageIntValue+")";
                    try {
                        Statement stmt = conn.createStatement();
                        if(stmt.executeUpdate(query) == 1) {
                            error.setText("User added successfully!!!");
                        }
                    } catch(SQLException sqle3) {
                        System.out.println(sqle3);
                    }
                }
            }
        });
        frame.add(button);
        // Adding Label and Setting
        // the Size of the Frame
        frame.setSize(900, 700);

        // Making the Frame visible
        frame.setLayout(null);
        frame.setVisible(true);


        // Using WindowListener for closing the window
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }
    private static Connection retConnection() throws SQLException {
        Connection conn = null;
        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "root");

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", props);
        System.out.println("Connection Established!");
        return conn;
    }
}

