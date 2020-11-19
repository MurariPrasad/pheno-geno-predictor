package genetic_calc;
import java.util.concurrent.Callable;

class task implements Callable {
    String[][] table;
    String mom,dad;

    public task(String str1,String str2){
        mom=str1;
        dad=str2;
    }

    @Override
    public String[][] call() throws Exception{
        return matrix(mom,dad);

    }

    public String[][] matrix(String mom,String dad){
        table=new String[2][2];
        table[0][0]= Character.toString(mom.charAt(0))+ Character.toString(dad.charAt(0));
        table[0][1]= Character.toString(mom.charAt(0))+ Character.toString(dad.charAt(1));
        table[1][0]= Character.toString(mom.charAt(1))+ Character.toString(dad.charAt(0));
        table[1][1]= Character.toString(mom.charAt(1))+ Character.toString(dad.charAt(1));

        return table;
    }
}
