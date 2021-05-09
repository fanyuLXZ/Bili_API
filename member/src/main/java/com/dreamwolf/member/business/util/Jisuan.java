package com.dreamwolf.member.business.util;

public class Jisuan {
            /*2          200
            3          1500
            4          4500   
            5         10800     
            6          28800*/
    public int mincurrent(int i,int z){//i等级 z现有经验 下等级需要的经验值
        int x=0;
        if(i==1){
            x=200-z;
        }else if(i==2){
            x=1500-z;
        }else if(i==3){
            x=4500-z;
        }else if(i==4){
            x=10800-z;
        } else if(i==5){
            x=28800-z;
        }else{
            x=28800;
        }
        return x;
    }
    public int residue(int i){//升级所需最小经验
        int x=0;
        if(i==1){
            x=0;
        }else if(i==2){
            x=200;
        }else if(i==3){
            x=1500;
        }else if(i==4){
            x=4500;
        } else if(i==5){
            x=10800;
        }else{
            x=28800;
        }
        return x;
    }
}
