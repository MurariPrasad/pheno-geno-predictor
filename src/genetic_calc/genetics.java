package genetic_calc;

public class genetics {

    public String[] function(String[] str, boolean[] in){
        for(int i=0;i<6;i++){
            switch(str[i]){
                case "Dark Hair":
                    if(in[i]==true) str[i]="HH";
                    else str[i]="Hh";
                    break;
                case "Light Hair":
                    str[i]="hh";
                    break;
                case "Dark Colour":
                    if(in[i]==true) str[i]="EE";
                    else str[i]="Ee";
                    break;
                case "Light Colour":
                    str[i]="ee";
                    break;
                case "Tall(5.9ft+)":
                    if(in[i]==true) str[i]="TT";
                    else str[i]="Tt";
                    break;
                case "Short":
                    str[i]="tt";
                    break;
                case "Rh +ve":
                    if(in[i]==true) str[i]="RR";
                    else str[i]="Rr";
                    break;
                case "Rh -ve":
                    str[i]="rr";
                    break;
                case "Curly":
                    if(in[i]==true) str[i]="CC";
                    else str[i]="Cc";
                    break;
                case "Straight":
                    str[i]="cc";
                    break;
            }
        }
        return str;
    }
}
