package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private Button btnCalculate;
    @FXML
    private TextField euroTextField;
    @FXML
    private ComboBox currencyComboBox;
    @FXML
    private Label resultLabel;

    private List<Currencies> listOfCurrencies;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listOfCurrencies = new ArrayList<Currencies>();
        int temp = 0;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("currency.xml");
            NodeList cubeList = document.getElementsByTagName("Cube");
            for(int i=0;i<cubeList.getLength();i++){
                Node c = cubeList.item(i);
                if(c.getNodeType() == Node.ELEMENT_NODE){
                    Element curr = (Element) c;
                    if(!curr.getAttribute("currency").equals("")) {
                        currencyComboBox.getItems().add(curr.getAttribute("currency"));
                        listOfCurrencies.add(new Currencies(curr.getAttribute("currency"), Float.parseFloat(curr.getAttribute("rate"))));
                        System.out.println(curr.getAttribute("currency") + " " + curr.getAttribute("rate"));
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (SAXException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        currencyComboBox.getSelectionModel().selectFirst();


        System.out.println(listOfCurrencies.toString());

        btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String convertToCurrency = (String)currencyComboBox.getValue();
                int rateIndex = 0;
                for(int i=0; i<listOfCurrencies.size();i++){
                    if(listOfCurrencies.get(i).getCurrencyName().equals(convertToCurrency)){
                        rateIndex = i;
                        break;
                    }
                }
                float euroAmount = Float.parseFloat(euroTextField.getText());
                float rateValue = listOfCurrencies.get(rateIndex).getRate();
                float convertedValue = euroAmount * rateValue;
                resultLabel.setText(convertedValue + " " + convertToCurrency);
            }
        });
    }


}
