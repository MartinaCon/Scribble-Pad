import java.awt.EventQueue;
import java.awt.MouseInfo;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Font;

public class SecurePad implements ActionListener 
{
	JFrame frame;
	static int loading=4;
	static int loadCount=0;
	
	public static void main(String[] args) throws Exception
	{	
		
        EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					SecurePad window = new SecurePad();
					window.frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public static final String pubKey = "C:\\Users\\marti\\Desktop\\Final Year Project\\VSProj\\miracl\\Debug\\public.key";
	public static final String priKey = "C:\\Users\\marti\\Desktop\\Final Year Project\\VSProj\\miracl\\Debug\\private.key";
	

	public SecurePad() throws Exception 
	{
		initialize();
	}

	
	@SuppressWarnings("resource")
	public void initialize() throws IOException, Exception
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(176, 224, 230));
		frame.getContentPane().setLayout(null);
		
		TextArea textArea = new TextArea();
		textArea.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 14));
		textArea.setBounds(0, 0, 684, 84);
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setText("Please move your mouse around and allow the system to gather data.");
		textArea.setEditable(false);
		textArea.setBackground(new Color(240, 128, 128));
		frame.getContentPane().add(textArea);
		
		
		TextArea mousepos = new TextArea();
		mousepos.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 12));
		mousepos.setBounds(0, 90, 684, 310);
		mousepos.setColumns(2);
		mousepos.setForeground(new Color(0, 0, 0));
		mousepos.setBackground(new Color(175, 238, 238));
		mousepos.setText("Your mouse positions will appear here \n");
		mousepos.setEditable(false);
		frame.getContentPane().add(mousepos);
		
		JTextArea Keys = new JTextArea();
		Keys.setBackground(Color.PINK);
		Keys.setBounds(0, 406, 662, 244);
		Keys.setText("Keys will appear here");
		BufferedReader br = null;
		FileReader fr = null;
		fr = new FileReader(pubKey);
		br = new BufferedReader(fr);
		String line;
		br = new BufferedReader(new FileReader(pubKey));
		while ((line = br.readLine()) != null) 
		{
			Keys.append(line + "\n");
		}
		BufferedReader br2 = null;
		br2 = new BufferedReader(fr);
		String line2;
		br2 = new BufferedReader(new FileReader(priKey));
		while ((line2 = br2.readLine()) != null) 
		{
			Keys.append(line2 + "\n");
		}
		
		frame.getContentPane().add(Keys);
		
		frame.setBounds(500, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Timer t = new Timer();
	    t.scheduleAtFixedRate(new TimerTask() 
	    {
	        public void run()
	        {
	        	if(loadCount<loading)
	        	{
	        	    java.awt.Point p = MouseInfo.getPointerInfo().getLocation();
	        	    StringBuilder sb = new StringBuilder();
	        	    Random rand = new Random();
	        	    int random = rand.nextInt(999);
	        	  	mousepos.append("" + "X: " + p.getX() + " Y: " + p.getY() + "\n" + "Random Number: " + random + "\n");
	        	  	
	        		loadCount=loadCount+1;
	        	
		        	for(int i = 0; i < 4; i++)
	        		{
		        		try(PrintWriter out = new PrintWriter("C:\\Users\\marti\\Desktop\\Final Year Project\\VSProj\\miracl\\Debug\\inputfile.txt"))
		        		{
		        			sb.append(p.x + "" + p.y + "" + random + "\r\n");
		        			out.println(sb.toString());
		        		}
		        		 catch (FileNotFoundException e) 
		        		{
							e.printStackTrace();
						}
	        		}
		        	
	        	}
	        }
	        
	    }, 500, 1000); 
	    frame.setSize(700, 700);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	  }
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}
}


