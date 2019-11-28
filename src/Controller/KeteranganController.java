/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Support.Data;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import koneksi.Koneksi;

/**
 * FXML Controller class
 *
 * @author LABKOM01-RPL07
 */
public class KeteranganController implements Initializable {

    @FXML
    private TableView<Data> tabelId;
    @FXML
    private Label ket1;
    @FXML
    private Label ket2;
    @FXML
    private Label ket3;
    @FXML
    private Label total;
    @FXML
    private Label bayar;
    @FXML
    private Label kembali;
    @FXML
    private Label kurang;
    @FXML
    private TextField cariOrder;
    @FXML
    private TableColumn<Data, String> columnData;
    Connection con;
    Statement stm;
    ResultSet rs;
    ObservableList<Data> listdata;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Koneksi db = new Koneksi();
        db.config();
        con = db.con;
        stm = db.stm;
        aksi();
        tabell();
    }    
        private void tabell(){
      tabelId.getSelectionModel().clearSelection();
      columnData.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Data, String>, ObservableValue<String>>(){
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Data, String> param) {
              return param.getValue().idProperty(); //To change body of generated methods, choose Tools | Templates.
          }
          
      });
      listdata = FXCollections.observableArrayList();
        }
        
        private void aksi()
        {
            
            listdata = getDataAll();
            tabelId.setItems(listdata);
               
        }
        
        public ObservableList<Data> getDataAll()
        {
            ObservableList<Data> listdata = FXCollections.observableArrayList();
            try{
                String sql = "Select * from bayar";
                rs = stm.executeQuery(sql);
                while(rs.next())
                        {
                            Data d = new Data();
                            d.setId(rs.getString("id"));
                            listdata.add(d);
                        }
            }catch (Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Data Tidak Tampil");
            }
            return listdata;
        }
    
}
