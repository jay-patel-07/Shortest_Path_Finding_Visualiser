import java.util.*;
import java.applet.*;
import java.awt.*;
import java.util.List;
import javax.swing.*;	
import java.awt.event.*;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class path{
	static int arr[]=new int[1000];
	static int value[]=new int[1000];
	
	public static void main(String arg[])
	{	
		for(int i=0; i<1000; i++){
			arr[i]=0;
			value[i]=0;
		}
		JFrame f=new JFrame();
		JPanel p=new JPanel();
		JButton[] buttons = new JButton[98];
		f.setSize(800,400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.setLayout(new GridLayout(7,5));
		for(int i=0;i<98;i++)
		{
			buttons[i]=new JButton();
			int pk=value[i];
			if(i!=97)
				buttons[i].setBackground(Color.CYAN);
			else
			{
				buttons[i].setBackground(Color.BLUE);
				buttons[i].setForeground(Color.WHITE);
				buttons[i].setText("GO");
			}
			buttons[i].addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{			
				for(int ll=0; ll<98; ll++)
				{
					if(e.getSource()==buttons[ll] && ll==97)
					{
						//main code
						value[97]=1;
						int b[][]=new int[8][15];
						for(int k=0; k<98; k++)
						{
							int x=k/14+1, y=k%14+1;
							b[x][y]=value[k];
						}
						for(int row=1; row<8; row++)
						{
							for(int col=1; col<15; col++)
							{
								System.out.print(b[row][col] + " ");
							}
							System.out.println();
						}	
						int [][]m=new int[100][100];
						int k=1, start=-1, end=-1;
						for(int i=1; i<8; i++)
						{
							for(int j=1; j<15; j++)
							{
								m[i][j]=k++;
								if(b[i][j]==2)
									start=m[i][j];
								if(b[i][j]==3)
									end=m[i][j];
							}
						}						
						Vector<Vector<Integer> > v = new Vector<Vector<Integer> >(); 
						for(int i=0; i<101; i++)
							v.add(new Vector<Integer>()); 
						for(int i=1; i<8; i++)
						{
							for(int j=1; j<15; j++)
							{
								if(b[i][j]!=1)
								{
									if(i-1>0 && b[i-1][j]!=1)
										v.get(m[i][j]).add(m[i-1][j]);
									if(j-1>0 && b[i][j-1]!=1)
										v.get(m[i][j]).add(m[i][j-1]);
									if(i<7 && b[i+1][j]!=1)
										v.get(m[i][j]).add(m[i+1][j]);
									if(j<14 && b[i][j+1]!=1)
										v.get(m[i][j]).add(m[i][j+1]);
								}
							}
						}	
						Vector<Integer> s=new Vector<Integer>();
						s.add(start);
						
						int taken[]=new int[105];
						int ans[]=new int[105];
						for(int i=0; i<105; i++){
							taken[i]=0;
							ans[i]=-1;
						}
						taken[start]=1;
						int count=0;
						while(taken[end]==0)
						{
							int vertex=s.get(0);
							taken[vertex]=1;
							buttons[vertex].setBackground(Color.YELLOW);
							for(int i=0; i<v.get(vertex).size(); i++)
							{
								if(taken[v.get(vertex).get(i)]==0)
								{
									ans[v.get(vertex).get(i)]=vertex;
									taken[v.get(vertex).get(i)]=1;
									s.add(v.get(vertex).get(i));
									buttons[v.get(vertex).get(i)].setBackground(Color.YELLOW);
								}
							}
							count++;
							if(count>50000)
								break;
							s.remove(0);	
						}
						Vector<Integer> fin=new Vector<Integer>();
						fin.add(end);
						int xx=ans[end];
						while(true)
						{
							fin.add(xx);
							if(xx==start)
								break;
							xx=ans[xx];
						}
						for(int i=0; i<fin.size(); i++)
						{
							buttons[fin.get(i)-1].setBackground(Color.BLUE);
						}					
					}
					if(e.getSource()==buttons[ll] && ll!=97)
					{	
						value[ll]++;
						value[ll]%=4;
						ImageIcon X,S,F,R;
						X=new ImageIcon("WALL.png");
						S=new ImageIcon("FIN.png");
						F=new ImageIcon("FINAL.png");
						R=new ImageIcon("R.png");
						buttons[ll].setBackground(Color.CYAN);
						switch(value[ll])
						{
							case 0:
								buttons[ll].setIcon(null);
								break;
							case 1:
								buttons[ll].setIcon(X);
								break;
							case 2:
								buttons[ll].setIcon(S);
								break;
							case 3:
								buttons[ll].setIcon(F);
								break;
							case 4: 
								buttons[ll].setIcon(R);
								break;
						}
					}
				}
			}});
			p.add(buttons[i]);
		}
		f.add(p);	
	
		f.setVisible(true);	
	}	
}
