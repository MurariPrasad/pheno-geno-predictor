/*
* To include new options for users:
* 1. add option to ObsList "genes"
* 2. add to switch case for choice
* 3. make new ObsList for the option's properties
* 4. make an appropriate switch case in genetics.java
*
*
*/


package genetic_calc;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.concurrent.Task;

public class control {
    String mom1,dad1,mom2,dad2,mom3,dad3;
    boolean m1=false,d1=false,m2=false,d2=false,m3=false,d3=false;

    Main obj=new Main();
    brain brain_obj=new brain();

    ObservableList<String> genes=FXCollections.observableArrayList("Hair","Height","Eye Colour");
    ObservableList<String> hair=FXCollections.observableArrayList("Dark Hair", "Light Hair");
    ObservableList<String> eyes=FXCollections.observableArrayList("Dark Colour", "Light Colour");
    ObservableList<String> height=FXCollections.observableArrayList("Tall(5.9ft+)", "Short");

    @FXML
    Button getresults,refresh;
    @FXML
    TextField tf_geno1,tf_geno2,tf_geno3,tf_pheno1,tf_pheno2,tf_pheno3;
    @FXML
    ChoiceBox genebox_1, genebox_2, genebox_3;
    @FXML
    ChoiceBox motherbox_1, motherbox_2, motherbox_3 ,fatherbox_1, fatherbox_2, fatherbox_3;
    @FXML
    RadioButton mother_1y, mother_2y, mother_3y, mother_1n, mother_2n, mother_3n;
    @FXML
    RadioButton father_1y, father_2y, father_3y, father_1n, father_2n, father_3n;
    @FXML
    final ToggleGroup mg1=new ToggleGroup();
    @FXML
    final ToggleGroup mg2=new ToggleGroup();
    @FXML
    final ToggleGroup mg3=new ToggleGroup();

    @FXML
    final ToggleGroup dg1=new ToggleGroup();
    @FXML
    final ToggleGroup dg2=new ToggleGroup();
    @FXML
    final ToggleGroup dg3=new ToggleGroup();

    public Stage primaryStage;
    public void setPrimaryStage(Stage stage){
        this.primaryStage=stage;

    }
    public void onRefresh()throws Exception{
       Stage stage= (Stage) refresh.getScene().getWindow();
       stage.close();
        Platform.runLater( () -> {
            try {
                new Main().start(new Stage());
            } catch (Exception e) {
                System.out.println("RESET error");
            }
        });

    }

    public void getresultsAction(ActionEvent event) throws Exception {

        RadioButton ms=(RadioButton) mg1.getSelectedToggle();
        if((ms.getText()).equals("Yes")) m1=true; else m1=false;
        ms=(RadioButton) mg2.getSelectedToggle();
        if((ms.getText()).equals("Yes")) m2=true; else m2=false;
        ms=(RadioButton) mg3.getSelectedToggle();
        if((ms.getText()).equals("Yes")) m3=true; else m3=false;

        RadioButton ds=(RadioButton) dg1.getSelectedToggle();
        if((ds.getText()).equals("Yes")) d1=true; else d1=false;
        ds=(RadioButton) dg2.getSelectedToggle();
        if((ds.getText()).equals("Yes")) d2=true; else d2=false;
        ds=(RadioButton) dg3.getSelectedToggle();
        if((ds.getText()).equals("Yes")) d3=true; else d3=false;

        String[] finalgenes;
        boolean[] inputs={m1,m2,m3,d1,d2,d3};
        genetics gobj=new genetics();
        finalgenes=gobj.function(new String[]{mom1, mom2, mom3, dad1, dad2, dad3},inputs);
        mom1=finalgenes[0];mom2=finalgenes[1];mom3=finalgenes[2];
        dad1=finalgenes[3];dad2=finalgenes[4];dad3=finalgenes[5];

       Task task=new Task() {
           @Override
           protected Object call() throws Exception {
               brain_obj.driver(new String[]{mom1, mom2, mom3, dad1, dad2, dad3});
               return null;
           }
       };
       Thread taskthread=new Thread(task);
        taskthread.start();
        while(taskthread.isAlive()==true){ }
        if(taskthread.isAlive()==false) showResults();

    }

    public void showResults(){

        tf_geno1.setText(brain_obj.geno[0]);
        tf_geno2.setText(brain_obj.geno[1]);
        tf_geno3.setText(brain_obj.geno[2]);

        tf_pheno1.setText(brain_obj.pheno[0]);
        tf_pheno2.setText(brain_obj.pheno[1]);
        tf_pheno3.setText(brain_obj.pheno[2]);
    }
    //*********************************************************************************************************************************************************************
    //
    //
    //
    //
    //
    //
    //
    //
    //*******************************************************************************************************************************************************************
    @FXML
    public void initialize(){
        //No setOnAction available in Scene Builder for ChoiceBox. Therefore Declared here.
        genebox_1.setItems(genes); genebox_1.setOnAction(ActionEvent -> {
            try { genebox_1Action((javafx.event.ActionEvent) ActionEvent);
            } catch (Exception e) { System.out.println("ERROR");
            }
        });
        genebox_2.setItems(genes); genebox_2.setOnAction(ActionEvent -> {
            try { genebox_2Action((javafx.event.ActionEvent) ActionEvent);
            } catch (Exception e) { System.out.println("ERROR");
            }
        });
        genebox_3.setItems(genes); genebox_3.setOnAction(ActionEvent -> {
            try { genebox_3Action((javafx.event.ActionEvent) ActionEvent);
            } catch (Exception e) { System.out.println("ERROR");
            }
        });

        //Grouping radio buttons for Mother
        mother_1y.setToggleGroup(mg1); mother_1y.setSelected(true);
        mother_1n.setToggleGroup(mg1);

        mother_2y.setToggleGroup(mg2); mother_2y.setSelected(true);
        mother_2n.setToggleGroup(mg2);

        mother_3y.setToggleGroup(mg3); mother_3y.setSelected(true);
        mother_3n.setToggleGroup(mg3);

        //Grouping radio buttons for Father
        father_1y.setToggleGroup(dg1); father_1y.setSelected(true);
        father_1n.setToggleGroup(dg1);

        father_2y.setToggleGroup(dg2); father_2y.setSelected(true);
        father_2n.setToggleGroup(dg2);

        father_3y.setToggleGroup(dg3); father_3y.setSelected(true);
        father_3n.setToggleGroup(dg3);
    }

    //************************************************************************************************
    public void genebox_1Action(ActionEvent event)throws Exception{
        String item=(String) genebox_1.getValue();
        switch(item){
            case "Hair": motherbox_1.setItems(hair); fatherbox_1.setItems(hair); break;
            case "Height": motherbox_1.setItems(height); fatherbox_1.setItems(height); break;
            case "Eye Colour": motherbox_1.setItems(eyes); fatherbox_1.setItems(eyes); break;
        }
        motherbox_1.setOnAction(event1 -> {
            try { motherbox_1Action((ActionEvent) event1);
            } catch (Exception e) { System.out.println("error");
            }
        });
        fatherbox_1.setOnAction(event1 -> {
            try { fatherbox_1Action((ActionEvent) event1);
            } catch (Exception e) { System.out.println("error");
            }
        });
    }
    //******************************************************************************************************
    public void genebox_2Action(ActionEvent event)throws Exception{
        String item=(String) genebox_2.getValue();
        switch(item){
            case "Hair": motherbox_2.setItems(hair); fatherbox_2.setItems(hair); break;
            case "Height": motherbox_2.setItems(height); fatherbox_2.setItems(height); break;
            case "Eye Colour": motherbox_2.setItems(eyes); fatherbox_2.setItems(eyes); break;
        }
        motherbox_2.setOnAction(event1 -> {
            try { motherbox_2Action((ActionEvent) event1);
            } catch (Exception e) { System.out.println("error");
            }
        });
        fatherbox_2.setOnAction(event1 -> {
            try { fatherbox_2Action((ActionEvent) event1);
            } catch (Exception e) { System.out.println("error");
            }
        });
    }
    //******************************************************************************************************
    public void genebox_3Action(ActionEvent event)throws Exception{
        String item=(String) genebox_3.getValue();
        switch(item){
            case "Hair": motherbox_3.setItems(hair); fatherbox_3.setItems(hair); break;
            case "Height": motherbox_3.setItems(height); fatherbox_3.setItems(height); break;
            case "Eye Colour": motherbox_3.setItems(eyes); fatherbox_3.setItems(eyes); break;
        }
        motherbox_3.setOnAction(event1 -> {
            try { motherbox_3Action((ActionEvent) event1);
            } catch (Exception e) { System.out.println("error");
            }
        });
        fatherbox_3.setOnAction(event1 -> {
            try { fatherbox_3Action((ActionEvent) event1);
            } catch (Exception e) { System.out.println("error");
            }
        });
    }
    //************************************************************************
    public void motherbox_1Action(ActionEvent event) throws Exception{
        mom1=(String) motherbox_1.getValue();
    }
    public void motherbox_2Action(ActionEvent event) throws Exception{
        mom2=(String) motherbox_2.getValue();
    }
    public void motherbox_3Action(ActionEvent event) throws Exception{
        mom3=(String) motherbox_3.getValue();
    }
    //************************************************************************
    public void fatherbox_1Action(ActionEvent event) throws Exception{
        dad1=(String) fatherbox_1.getValue();
    }
    public void fatherbox_2Action(ActionEvent event) throws Exception{
        dad2=(String) fatherbox_2.getValue();
    }
    public void fatherbox_3Action(ActionEvent event) throws Exception{
        dad3=(String) fatherbox_3.getValue();
    }
    //***************************************************************************

}
