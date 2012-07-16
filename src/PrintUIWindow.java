
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
 
 class PrintUIWindow implements Printable, ActionListener {
 
    JFrame frameToPrint;
 
    public int print(Graphics g, PageFormat pf, int page) throws
                                                        PrinterException {
 
        if (page > 0) {
            return NO_SUCH_PAGE;
        }
 
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY()-55);
 
        frameToPrint.print(g);
 
        return PAGE_EXISTS;
    }
 
    public void actionPerformed(ActionEvent e) {
         PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         boolean ok = job.printDialog();
         if (ok) {
             try {
                  job.print();
             } catch (PrinterException ex) {
 
             }
         }
    }
 
    public PrintUIWindow(JFrame f) {
        frameToPrint = f;
    }
 
    /*
    public static void main(String args[]) {
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        JFrame f = new JFrame("Print UI Example");
        f.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent e) {System.exit(0);}
        });
 
        JLabel label1=new JLabel("Selling Bill",JLabel.CENTER);
        JLabel label2=new JLabel("Customer Name :Mahmoud Saleh       ",JLabel.LEFT);
        JLabel label3=new JLabel("Buying Date :29/8/2008             ",JLabel.LEFT);
        JLabel label4=new JLabel("Book Buyed :Java Printing          ",JLabel.LEFT);
        JLabel label5=new JLabel("Number : 6 Copies                  ",JLabel.LEFT);
        JLabel label6=new JLabel("Total Price :600 $                 ",JLabel.LEFT);
 
        label1.setFont(new Font("Courier New", Font.BOLD, 13));
        label2.setFont(new Font("Courier New", Font.BOLD, 13));
        label3.setFont(new Font("Courier New", Font.BOLD, 13));
        label4.setFont(new Font("Courier New", Font.BOLD, 13));
        label5.setFont(new Font("Courier New", Font.BOLD, 13));
        label6.setFont(new Font("Courier New", Font.BOLD, 13));
 
        JButton printButton = new JButton("Print This Window");
        printButton.addActionListener(new PrintUIWindow(f));
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(6,1));
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        f.setSize(300,300);
        f.setLocationRelativeTo(null);
        f.add(panel,BorderLayout.CENTER);
        f.add(printButton,BorderLayout.SOUTH);
        panel.setBackground(Color.WHITE);
        f.setResizable(false);
        f.setVisible(true);
    }
    */
}