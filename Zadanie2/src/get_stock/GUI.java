package get_stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI implements ActionListener {

    JTextArea txtArea;
    JTextField textField;

    public void display() {
    	
        JFrame jfrm = new JFrame("Notowania");
        jfrm.setSize(230,200);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setVisible(true);

        txtArea = new JTextArea(6,15);//values obtained from hit and try
        txtArea.setLineWrap(false);
        txtArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(txtArea);
        
        JPanel jpanel = new JPanel();
        jpanel.add(scrollPane);
        
        textField = new HintTextField("ABK", 6);
        
        JButton jbtn = new JButton("Pobierz");
        jbtn.addActionListener(this);

        jfrm.getContentPane().add(BorderLayout.CENTER, jpanel);
        jfrm.getContentPane().add(BorderLayout.PAGE_END, textField);
        jfrm.getContentPane().add(BorderLayout.PAGE_START, jbtn);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI GUI = new GUI();
                GUI.display();
            }
        });
    }

    @SuppressWarnings("static-access")
	@Override
    public void actionPerformed(ActionEvent e) {
    	String company = textField.getText();//pobiera nazwe spolki
    	Get_Stock stock = new Get_Stock();//daje nazwe spolki klasie Get_Stock
    	stock.company(company);
    	try {
			stock.callMain();//calluje maina zeby zdobyc informacje
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
    	//sprawdza czy zdobyl informacje
    	if((stock.kurs.equals(stock.zmiana)) && (stock.wartosc_obrotu).equals(stock.kurs))
    		txtArea.append("B³¹d!\n");
    	else//wypisuje informacje
    	txtArea.append("Spó³ka:" + company + "\nKurs: " + stock.kurs + "\nZmiana: " + stock.zmiana + "\nWartoœæ obrotu: " + stock.wartosc_obrotu + "\n");
    	//przypisujemy defaultowe wartosci aby moc sprawdzac poprawnosc nazwy spolki
    	stock.kurs="";
    	stock.zmiana="";
    	stock.wartosc_obrotu="";
    	   	
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