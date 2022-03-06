package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.views.Loteria;
import sample.views.Parseador;

public class Main extends Application {

    private VBox vBox;
    private MenuBar mnbPrincipal;
    private Menu menCompetencia1, menCompetencia2;
    private MenuItem mitLoteria, mitParseador;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        mnbPrincipal = new MenuBar();
        menCompetencia1 = new Menu("Competencia 1");
        mitLoteria = new MenuItem("Loteria");
        mitLoteria.setOnAction(event -> Eventos(1));
        mitParseador = new MenuItem("Codigo Morse");
        mitParseador.setOnAction(event -> Eventos(2));

        menCompetencia1.getItems().addAll(mitLoteria,mitParseador);

        menCompetencia2 = new Menu("Competencia 2");
        mnbPrincipal.getMenus().addAll(menCompetencia1,menCompetencia2);

        vBox = new VBox();
        vBox.getChildren().addAll(mnbPrincipal);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(vBox, 300, 275));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void Eventos(int opc) {
        switch (opc){
            case 1: new Loteria(); break;
            case 2: new Parseador(); break;

        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
