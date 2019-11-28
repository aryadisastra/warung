package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javax.swing.JOptionPane;
import koneksi.Koneksi;

/**
 *
 * @author LABKOM01-RPL07
 */
public class KasirController implements Initializable {
    
    private Label label;
    @FXML
    private TextField idorder;
    @FXML
    private CheckBox check1;
    @FXML
    private CheckBox check2;
    @FXML
    private CheckBox check3;
    @FXML
    private TextField field2;
    @FXML
    private TextField field3;
    @FXML
    private TextField total;
    @FXML
    private TextField bayar;
    @FXML
    private TextField kembali;
    @FXML
    private HBox hb1;
    @FXML
    private HBox hb2;
    @FXML
    private HBox hb3;
    @FXML
    private TextField field1;
    Connection con;
    Statement stm;
    ResultSet rs;
    @FXML
    private HBox hb11;
    @FXML
    private HBox hb12;
    @FXML
    private HBox hb13;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        field1.setText("0");
        field2.setText("0");
        field3.setText("0");
        total.setText("0");
       bayar.setText("");
       kembali.setText("0");
        Koneksi db = new Koneksi();
        db.config();
        con = db.con;
        stm = db.stm;
        
       
       bayar.setEditable(true);
       hb1.setVisible(false);
       hb2.setVisible(false);
       hb3.setVisible(false);
       String sql = "Select id from bayar";
       try{
           rs = stm.executeQuery(sql);
           rs.last();
           int id = Integer.parseInt(rs.getString("id"));
           Integer oto = id+1;
           
           idorder.setText(oto.toString());
       }catch(Exception e)
       {
           e.printStackTrace();
       }
        check1.selectedProperty().addListener(new ChangeListener<Boolean>(){
           @Override
           public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
                    hb1.setVisible(true);
                }
                else{
                    hb1.setVisible(false);
                }
           }
            
        });
        check2.selectedProperty().addListener(new ChangeListener<Boolean>(){
           @Override
           public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
                    hb2.setVisible(true);
                }
                else{
                    hb2.setVisible(false);
                }
           }
            
        });
        check3.selectedProperty().addListener(new ChangeListener<Boolean>(){
           @Override
           public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
                    hb3.setVisible(true);
                }
                else{
                    hb3.setVisible(false);
                }
           }
            
        });
    }    

    @FXML
    private void otomatis1(KeyEvent event) {
        
        int semua;
        int satu = Integer.parseInt(field1.getText());
        int dua = Integer.parseInt(field2.getText());
        int tiga = Integer.parseInt(field3.getText());
        int hasil1 = satu*1000;
        int hasil2 =  dua*1000;
        int hasil3 = tiga*1500;
        semua = hasil1+hasil2+hasil3;
        
        if(field1.getText().isEmpty())
        {
            total.setText("0");
        }
        
        if(field2.getText().isEmpty())
        {
            total.setText("0");
        }
        
        if(field3.getText().isEmpty())
        {
            total.setText("0");
        }
        else
        {
            
        total.setText(Integer.toString(semua));
        }
        
            
        
    }

    @FXML
    private void otomatis2(KeyEvent event) {
        int semua;
        int satu = Integer.parseInt(field1.getText());
        int dua = Integer.parseInt(field2.getText());
        int tiga = Integer.parseInt(field3.getText());
        int hasil1 = satu*1000;
        int hasil2 =  dua*1000;
        int hasil3 = tiga*1500;
        semua = hasil1+hasil2+hasil3;
        
        
        total.setText(Integer.toString(semua));
    }

    @FXML
    private void otomatis3(KeyEvent event) {
        int semua;
        int satu = Integer.parseInt(field1.getText());
        int dua = Integer.parseInt(field2.getText());
        int tiga = Integer.parseInt(field3.getText());
        int hasil1 = satu*1000;
        int hasil2 =  dua*1000;
        int hasil3 = tiga*1500;
        semua = hasil1+hasil2+hasil3;
        
        
        total.setText(Integer.toString(semua));
    }

    @FXML
    private void enterBayar(ActionEvent event) {
        String sql = "insert into bayar (id,ket1,ket2,ket3,total,bayar,kembali,kurang) values('"+idorder.getText()+"','Cireng = "+field1.getText()+"x1000','Bala - Bala = "+field1.getText()+"x1000','Gehu = "+field1.getText()+"x1500','"+total.getText()+"','"+bayar.getText()+"','"+kembali.getText()+"','0')";
        String sql2 = "insert into bayar (id,ket1,ket2,ket3,total,bayar,kembali,kurang) values('"+idorder.getText()+"','Cireng = "+field1.getText()+"x1000','Bala - Bala = "+field1.getText()+"x1000','Gehu = "+field1.getText()+"x1500','"+total.getText()+"','"+bayar.getText()+"','0','"+kembali.getText()+"')";
        int totalnya = Integer.parseInt(total.getText());
        int bayarnya = Integer.parseInt(bayar.getText());
        boolean a = totalnya>bayarnya;
        boolean b = bayarnya>totalnya;
        boolean c = bayarnya==totalnya;
        try{
            if(a)
                {
                    stm.execute(sql2);
                    JOptionPane.showMessageDialog(null, "Berhasil!!!!");
                    check1.setSelected(false);
                    check2.setSelected(false);
                    check3.setSelected(false);
                    String sql4 = "Select id from bayar";
       try{
           rs = stm.executeQuery(sql4);
           rs.last();
           int id = Integer.parseInt(rs.getString("id"));
           Integer oto = id+1;
           
           idorder.setText(oto.toString());
       }catch(Exception e)
       {
           e.printStackTrace();
       }
       field1.setText("0");
       field2.setText("0");
       field3.setText("0");
       total.setText("0");
       bayar.setText("");
       kembali.setText("0");
                }
            else if(b)
            {
                stm.execute(sql);
                JOptionPane.showMessageDialog(null, "Berhasil!!!!");
                check1.setSelected(false);
                    check2.setSelected(false);
                    check3.setSelected(false);
                    String sql4 = "Select id from bayar";
       try{
           rs = stm.executeQuery(sql4);
           rs.last();
           int id = Integer.parseInt(rs.getString("id"));
           Integer oto = id+1;
           
           idorder.setText(oto.toString());
       }catch(Exception e)
       {
           e.printStackTrace();
       }
       field1.setText("0");
       field2.setText("0");
       field3.setText("0");
       total.setText("0");
       bayar.setText("");
       kembali.setText("0");
            }
            else if(c)
            {
                stm.execute(sql);
                JOptionPane.showMessageDialog(null, "Berhasil!!!!");
                check1.setSelected(false);
                    check2.setSelected(false);
                    check3.setSelected(false);
                    String sql4 = "Select id from bayar";
       try{
           rs = stm.executeQuery(sql4);
           rs.last();
           int id = Integer.parseInt(rs.getString("id"));
           Integer oto = id+1;
           
           idorder.setText(oto.toString());
       }catch(Exception e)
       {
           e.printStackTrace();
       }
       field1.setText("0");
       field2.setText("0");
       field3.setText("0");
       total.setText("0");
       bayar.setText("");
       kembali.setText("0");
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Gagal!!");
            
            e.printStackTrace();
        }
        
        
    }

    @FXML
    private void inputBayar(KeyEvent event) {
        Integer totall = Integer.parseInt(total.getText());
        Integer bayarr = Integer.parseInt(bayar.getText());
        Integer biaya = bayarr-totall;
        kembali.setText(biaya.toString());
        if(bayar.getText().equals(""))
        {
            kembali.setText("0");
        }
    }

    @FXML
    private void ganti2(MouseEvent event) {
        hb11.setStyle("-fx-background-color : #fff");
        
    }

    @FXML
    private void ganti1(MouseEvent event) {
        hb11.setStyle("-fx-background-color : #a7a6a6");
    }

    @FXML
    private void ganti22(MouseEvent event) {
        hb12.setStyle("-fx-background-color : #fff");
    }

    @FXML
    private void ganti11(MouseEvent event) {
        hb12.setStyle("-fx-background-color : #a7a6a6");
    }

    @FXML
    private void ganti23(MouseEvent event) {
        hb13.setStyle("-fx-background-color : #fff");
    }

    @FXML
    private void ganti13(MouseEvent event) {
        hb13.setStyle("-fx-background-color : #a7a6a6");
    }
    
}
