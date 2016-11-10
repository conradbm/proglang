package main;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Param {
    String type; //[] for multiple args
    String varName; //[] for multiple args
    ArrayList<String> typeList = new ArrayList<String>();
    ArrayList<String> varNameList = new ArrayList<String>();
    StringTokenizer st;
    int value;
    int x = 0;
    public Param(String allParam){
        String newParam = allParam.replace(",", " ");
        st = new StringTokenizer(newParam, " ");
        
        while(st.hasMoreTokens()){
            String tmp = st.nextToken();
            if(tmp.equals(" ")){
                //we dont need space tokens
            }
            else{
            typeList.add(tmp);
            varNameList.add(st.nextToken());
            System.out.println("type:" + typeList.get(x) + "\nvarName: "+ varNameList.get(x));
            x++;
            }
        }
    }
    public Param(){
        //nothing
    }
    public void inlineParams(String allToks){
        String newParam = allToks.replace(",", " ");
        st = new StringTokenizer(newParam, " ");
        
        while(st.hasMoreTokens()){
            String tmp = st.nextToken();
            if(tmp.equals(" ")){
                //do nothing
            }
            else{
                varNameList.add(tmp);
                System.out.println("inlineParams: " + varNameList.get(x));
                x++;
            }
            
        }
    }
}
