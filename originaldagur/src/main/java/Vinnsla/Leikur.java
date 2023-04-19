package Vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

public class Leikur {
    private IntegerProperty stig = new SimpleIntegerProperty();
    public IntegerProperty StringProperty(){
        return stig;
    }
    public Leikur(Label fxStig){
        fxStig.textProperty().bind(StringProperty().asString());
    }
    public void haekkastig(int i){
        stig.set(stig.get()+i);
    }
}
