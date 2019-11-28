/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author LABKOM01-RPL07
 */
public class Data {
    private final StringProperty id = new SimpleStringProperty();
    public String getId()
    {
        return id.get();
    }
    public void setId(String isi)
    {
       id.set(isi);
    }
    public StringProperty idProperty()
    {
        return id;
    }
}
