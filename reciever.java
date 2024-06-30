import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.util.Calendar;
import javax.swing.border.*;
import java.text.*;
import java.net.*;
import java.io.*;



public class reciever  implements ActionListener{


    JTextField msg;
     static JPanel text;
     static Box vertical=Box.createVerticalBox();

      static DataOutputStream dout;
      static JFrame FR=new JFrame();
  reciever()
    {
FR.setLayout(null);

JPanel pl=new JPanel();
pl.setBackground(new Color(7,94,84));
pl.setBounds(0,0,450,70);
pl.setLayout(null);
FR.add(pl);


ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
Image i2=i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
ImageIcon i3=new ImageIcon(i2);
JLabel back= new JLabel(i3);
back.setBounds(5,20,25,25);
pl.add(back);

back.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent ae){
        System.exit(0);
    }
});

ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/userr.png"));
Image i5=i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
ImageIcon i6=new ImageIcon(i5);
JLabel profile= new JLabel(i6);
profile.setBounds(40,10,50,50);
pl.add(profile);

ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
Image i8=i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
ImageIcon i9=new ImageIcon(i8);
JLabel video= new JLabel(i9);
video.setBounds(360,20,30,30);
pl.add(video);

ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
Image i11=i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
ImageIcon i12=new ImageIcon(i11);
JLabel phone= new JLabel(i12);
phone.setBounds(310,20,30,30);
pl.add(phone);

ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
Image i14=i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
ImageIcon i15=new ImageIcon(i14);
JLabel dot= new JLabel(i15);
dot.setBounds(420,20,10,30);
pl.add(dot);


JLabel name=new JLabel("Harshita");
name.setBounds(110,15,100,18);
name.setForeground(Color.WHITE);
name.setFont(new Font("SAN_SERIF",Font.BOLD,20));
pl.add(name);

JLabel status=new JLabel("Online ");
status.setBounds(110,35,100,18);
status.setForeground(Color.WHITE);
status.setFont(new Font("SAN_SERIF",Font.BOLD,14));
pl.add(status);


text=new JPanel();//scree where msg should print
text.setBounds(5,75,425,570);
FR.add(text);


msg=new JTextField();//textfield
msg.setBounds(5,655,310,40);
msg.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
FR.add(msg);

JButton send=new JButton("Send");
send.setBounds(320,655,123,40);
send.setBackground(new Color(7,94,84));
send.setForeground(Color.WHITE);
send.addActionListener(this);
send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));

FR.add(send);


     FR.setSize(450,700);
     
    FR.setLocation(800,50);
    FR.setUndecorated(true);
    FR.getContentPane().setBackground(Color.WHITE);
    FR.setVisible(true);
    }



public void actionPerformed(ActionEvent ae) {
    
    try{
    String output=msg.getText();
   // System.out.println(output);


    JPanel p2=formatLabel(output);
  

    text.setLayout(new BorderLayout());

    JPanel right=new JPanel(new BorderLayout());
    right.add(p2,BorderLayout.LINE_END);
    vertical.add(right);
    vertical.add(Box.createVerticalStrut(15));//how much space should be there

    text.add(vertical,BorderLayout.PAGE_START);

    dout.writeUTF(output);
    msg.setText("");

    FR.repaint();
    FR.invalidate();
    FR.validate();
}
catch(Exception e)
{
  e.printStackTrace();
}
}



public static JPanel formatLabel(String output){
JPanel panel=new JPanel();

panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

JLabel out=new JLabel("<html><p style=\"width: 150px\">" + output +"</p </html>");
out.setFont(new Font("Tahoma",Font.PLAIN,16));
out.setBackground(new Color(37,211,102));
out.setOpaque(true);
out.setBorder(new EmptyBorder(15,15,15,50));
panel.add(out);


Calendar Ca=Calendar.getInstance();
SimpleDateFormat sd=new SimpleDateFormat("HH:mm");

JLabel time=new JLabel();
time.setText(sd.format(Ca.getTime()));
panel.add(time);
return panel;

}
     public static void main(String[] args) {
        new reciever();
        
        try{
  Socket s  =new Socket("127.0.0.1",6001);
  DataInputStream dn =new DataInputStream(s.getInputStream()); 
   dout= new DataOutputStream(s.getOutputStream());
    
   while(true){

    text.setLayout(new BorderLayout());
    String msg=dn.readUTF();
    JPanel panel=formatLabel(msg);
    JPanel left= new JPanel(new BorderLayout());
    left.add(panel,BorderLayout.LINE_START);
    vertical.add(left);

    vertical.add(Box.createVerticalStrut(15));
    text.add(vertical,BorderLayout.PAGE_START);
    FR.validate();
  }
        }catch(Exception e){
            e.printStackTrace();
        }
     }
    }