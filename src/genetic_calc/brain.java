package genetic_calc;
/*
 * vertical column is mom gene
 * horizontal column is dad gene
 * */

import java.util.concurrent.*;

public class brain {
    String[] geno,pheno;

    public void driver(String[] options) throws ExecutionException, InterruptedException {
        String mom1=options[0], mom2=options[1], mom3=options[2];
        String dad1=options[3], dad2=options[4],dad3=options[5];

        ExecutorService exec= Executors.newCachedThreadPool();
        Future<String[][]> thread1=exec.submit(new task(mom1,dad1));
        Future<String[][]> thread2=exec.submit(new task(mom2,dad2));
        Future<String[][]> thread3=exec.submit(new task(mom3,dad3));

        String[][] table1=thread1.get();
        String str1=calc(table1);
        String ptr1=phenocalc(str1.substring(0,5));

        String[][] table2=thread2.get();
        String str2=calc(table2);
        String ptr2=phenocalc(str2.substring(0,5));

        String[][] table3=thread3.get();
        String str3=calc(table3);
        String ptr3=phenocalc(str3.substring(0,5));

        exec.shutdown();
        thread1.isDone();
        thread2.isDone();
        thread3.isDone();

        geno=new String[]{str1,str2,str3};
        pheno=new String[]{ptr1,ptr2,ptr3};

    }

    synchronized String calc(String[][] table){
        String s="";
        String temp;
        int a=0,b=0,c=0;
        String a1="--",b1="--",c1="--";
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                temp=table[i][j];
                if(((int)temp.charAt(1)>=65 && (int)temp.charAt(1)<=90) && ((int)temp.charAt(0)>=65 && (int)temp.charAt(0)<=90)){
                        a1=temp; a++;
                }
                else if(
                        ((int)temp.charAt(1)>=97 && (int)temp.charAt(1)<=122) && ((int)temp.charAt(0)>=65 && (int)temp.charAt(0)<=90) ||
                        ((int)temp.charAt(0)>=97 && (int)temp.charAt(0)<=122) && ((int)temp.charAt(1)>=65 && (int)temp.charAt(1)<=90)
                ){
                        b1=temp; b++;
                }
                else if(((int)temp.charAt(1)>=97 && (int)temp.charAt(1)<=122) && ((int)temp.charAt(0)>=97 && (int)temp.charAt(0)<=122)){
                        c1=temp; c++;
                }

            }
        }

        s=a+":"+b+":"+c+" ("+a1+":"+b1+":"+c1+")";
        return s;

    }
    synchronized String phenocalc(String str){
        String re=""; int a=0;int b=0;int c=0;
        a=Character.getNumericValue(str.charAt(0));
        b=Character.getNumericValue(str.charAt(2));
        c=Character.getNumericValue(str.charAt(4));
        if(a>=1.0) re=re+" Homozygous Dominant-"+(100.0*((float)a/4.0))+"%";
        if(b>=1.0) re=re+" Heterozygous Dominant-"+( 100.0*((float)b/4.0))+"%";
        if(c>=1.0) re=re+" Homozygous Recessive-"+( 100.0*((float)c/4.0))+"%";

        return re;
    }
}

