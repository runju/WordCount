package worthwest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;



import javax.swing.JFrame;

public class Test extends JFrame  {
     public static String[] str;
     public static TreeMap<String,Integer> map;
     public static void main(String[] args) throws IOException  {
		 System.out.println("******词频统计*********\n"
		 		+ "1.指定单词的统计和柱形图：\n"
		 		+ "2.高频单词的统计(输入整数k):\n"
		 		+ "3.统计所有单词并将结果存放在result.txt中:\n"
		 		+ "请输入：");
		    Scanner sc = new Scanner(System.in);
		    String ss=sc.next();
		    int s=Integer.parseInt(ss);		 
		    map = new TreeMap<String,Integer>();
			 //读入文件Word.txt
			    String line = "Word.txt";
				File file = new File(line);
				InputStreamReader f = null;						
				try {				 
					f = new InputStreamReader(new FileInputStream(file), "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				BufferedReader reader = new BufferedReader(f);//读入缓冲流													
					//一次读入一行，读入不为空时 进行单词统计				 				  
				        try {
							while((line=reader.readLine())!=null){
								line = line.toLowerCase();//忽略大小写
								String[] str = line.split("[^a-zA-Z]");//去掉特殊字符
								
								for(int i = 0; i<str.length; i++){
									String word = str[i].trim();
									if(map.containsKey(word) &&  word.length() != 0){
										map.put(word, map.get(word)+1);
									}else{
										map.put(word, 1);
									}			        		
							    }
	                        }
						} catch (IOException e) {
							e.printStackTrace();
						}		   
			    // 统计了所有的单词的个数   
//				Iterator iterator=map.keySet().iterator();
//				while(iterator.hasNext()){
//					System.out.println(iterator.next()
//							);
//				       }				
//				    	System.out.println(entry.getKey()+"=="+entry.getValue());					    					    }										
		      //具体功能的实现			
				if(s==1){
				System.out.println("请输入要查找的单词，用,号隔开：");
				String ss1=sc.next();
				WordCount wc=new WordCount();
				str=wc.one(ss1, map);
				Test demo = new Test();
				demo.setVisible(true);
		         }else if(s==2){
				//统计高评率的词
				WordCount wc=new WordCount();
				System.out.println("请输入整数k：");
				int ss3=sc.nextInt();
				wc.two(map,ss3);	
				}else if(s==3){
				//存放result.txt
	             WordCount wc=new WordCount();
				 wc.three(map);		
				  }
				}
          
	    //柱形图
	    public Test(){
			super();
			setTitle("柱形图");
			setBounds(3, 200, 450, 450);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		 @Override
		  public void paint(Graphics g){
			int Width = getWidth();
			int Height = getHeight();
			int leftMargin = 50;//柱形图左边界
			int topMargin = 50;//柱形图上边界
			Graphics2D g2 = (Graphics2D) g;
			int ruler = Height-topMargin;
			int rulerStep = ruler/20;//将当前的高度评分为20个单位
			g2.setColor(Color.WHITE);//绘制白色背景
			g2.fillRect(0, 0, Width, Height);//绘制矩形图
			g2.setColor(Color.LIGHT_GRAY);
			for(int i=0;i<rulerStep;i++){
				g2.drawString((400-20*i)+"个", 8, topMargin+rulerStep*i);//绘制Y轴上的数据
			}
			g2.setColor(Color.pink);
			int m=0;
			for(int i = 0;i<str.length;i++){
				int value = map.get(str[i]);
				int step = (m+1)*40;//设置每隔柱形图的水平间隔为40
				g2.fillRoundRect(leftMargin+step*2,Height-value, 40, value, 40, 10);//绘制每个柱状条
				g2.drawString(str[i], leftMargin+step*2, Height-value-5);	//标识每个柱状条		
				m++;
			}
		  }     
	    }  

