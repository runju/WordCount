package worthwest;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;



public class WordCount {
	
	//指定单词的查找
	 public String[] one(String s,TreeMap<String,Integer> map){	
		TreeMap<String,Integer> map1 = new TreeMap<String,Integer>();		
		String[] str=s.split(",");		
		for (int i = 0; i < str.length; i++) {
				for(Entry<String,Integer> entry: map.entrySet()) {					
				    if(str[i].equals(entry.getKey())){
				    	map1.put(entry.getKey(),entry.getValue());
				    	System.out.println(entry.getKey()+"-----"+entry.getValue());
				    	break;
				    }
			   }
		  }
		return str;
	 }	 
	 //高频词的统计 整数k
	 public void two(TreeMap<String,Integer> map,int k){
		//获取参数k
		        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
		        
		        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){  
		            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
		                return o2.getValue() - o1.getValue(); 
		            }  
		        }); 
		     //输出前k个数
		      for(int i = 0; i<k; i++){  
		            System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());  
		        }     
		    }
	  
	 //写入至result.txt中
	 public void three(TreeMap<String,Integer> map){
			BufferedWriter bw = null;
    		try {
    			File file = new File("result.txt");
    			if (!file.exists()) {
    				file.createNewFile();
    			}
    			FileWriter fw = new FileWriter(file.getAbsoluteFile());
    			bw = new BufferedWriter(fw);
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    		}			
            Iterator<String> it1 = map.keySet().iterator();
            while(it1.hasNext())
            {
            	String key = (String) it1.next();
	        	Integer value = map.get(key);	        	
	        	try {
					bw.write(key+"="+value+"\n");
				} catch (IOException e) {
					e.printStackTrace();
				}

            }
             System.out.println("存放成功！！！");
	    }
     }
