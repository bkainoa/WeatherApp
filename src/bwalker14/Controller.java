package bwalker14;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {
    //Creates the variables referencing elements of the gui
    @FXML
    private TextField user_input;
    @FXML
    private Label weather;
    @FXML
    private Label temperature;
    @FXML
    private Label city_state;

    public void buttonPress( ActionEvent ae )
    {
        //Creates a new instance of the helper class weather
        Weather w = new Weather( user_input.getText() );
        w.fetch();

        //Sets the information labels
        city_state.setText( w.getCityState() );
        temperature.setText( w.getTemperature() );
        weather.setText( w.getWeather() );
    }

}
