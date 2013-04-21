package file_reader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GUI implements ActionListener {

    JTextArea txtArea;
    JTextField textField;

    public void display() {
    	
        JFrame jfrm = new JFrame("File Reader");
        
        //Obtaining resolution to get better frame :)
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        
        jfrm.setSize(screenWidth/2, screenHeight/2);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setVisible(true);

        txtArea = new JTextArea(screenHeight/40,screenWidth/23);//values obtained from hit and try
        txtArea.setLineWrap(true);
        txtArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(txtArea);
        
        JPanel jpanel = new JPanel();
        jpanel.add(scrollPane);
        
        textField = new HintTextField("Enter directory eg. C:\\file.txt or /home/user/file.txt", 20);
        
        JButton jbtn = new JButton("Start");
        jbtn.addActionListener(this);

        jfrm.getContentPane().add(BorderLayout.CENTER, jpanel);
        jfrm.getContentPane().add(BorderLayout.PAGE_END, textField);
        jfrm.getContentPane().add(BorderLayout.PAGE_START, jbtn);

    }

    public static void main(String j[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI GUI = new GUI();
                GUI.display();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	String directory = textField.getText();
        File_reader fr = new File_reader(directory, txtArea);
        fr.execute();
    }
}
//Extends JTextField with object HintTextField in JTextField - differ from JTextField: have gray hint in it ;)
@SuppressWarnings("serial")
class HintTextField extends JTextField {

    public HintTextField(final String hintText,int column) {

        final Font f1 = this.getFont();

        final Font f2 = f1.deriveFont(Font.ITALIC);

        setText(hintText);

        setColumns(column);

        setFont(f2);

        setForeground(Color.GRAY);

        addFocusListener(new FocusListener() {

            @Override

            public void focusGained(FocusEvent e) {

                if (getText().equals(hintText)) {

                    setText("");

                } else {

                    setText(getText());

                }

                setFont(f1);

                setForeground(Color.BLACK);

            }

            @Override

            public void focusLost(FocusEvent e) {

                if(getText().equals("")){

                    setText(hintText);

                    setFont(f2);

                    setForeground(Color.GRAY);

                }else{

                    if (getText().equals(hintText)) {

                    setText(hintText);

                    setFont(f2);

                    setForeground(Color.GRAY);

                } else {

                    setText(getText());

                    setFont(f1);

                    setForeground(Color.BLACK);

                }

                }

            }

        });

    }

}