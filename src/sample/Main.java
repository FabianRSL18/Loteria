package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import sample.models.Conexion;
import sample.views.ClientesBD;
import sample.views.Loteria;
import sample.views.Parseador;

public class Main extends Application implements EventHandler {

    private VBox vBox;
    private MenuBar mnbPrincipal;
    private Menu menCompetencia1, menCompetencia2;
    private MenuItem mitLoteria, mitParseador,mitClientes;
    private Image ico;


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        mnbPrincipal = new MenuBar();
        menCompetencia1 = new Menu("Competencia 1");
        mitLoteria = new MenuItem("Loteria");
        mitLoteria.setOnAction(event -> Eventos(1));
        mitParseador = new MenuItem("Codigo Morse");
        mitParseador.setOnAction(event -> Eventos(2));
        mitClientes = new MenuItem("Taqueria");
        mitClientes.setOnAction(event -> Eventos(3));


        menCompetencia1.getItems().addAll(mitLoteria,mitParseador,mitClientes);

        menCompetencia2 = new Menu("Competencia 2");

        mnbPrincipal.getMenus().addAll(menCompetencia1,menCompetencia2);

        vBox = new VBox();
        vBox.getChildren().addAll(mnbPrincipal);

        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWING,this);
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST,this);
        Scene escena = new Scene(vBox,300,275);
        escena.getStylesheets().add(getClass().getResource("css/styles.css").toExternalForm());
        primaryStage.setTitle("Proyecto Topicos");
        primaryStage.setScene(escena);
        primaryStage.setMaximized(true);
        primaryStage.show();

        Conexion.crearConexion();
    }


    private void Eventos(int opc) {
        switch (opc){
            case 1: new Loteria(); break;
            case 2: new Parseador(); break;
            case 3: new ClientesBD();break;

        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(Event event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Bienvenidos :)");
        alerta.setHeaderText("Mensaje del sistema :)");
        alerta.setContentText("Manejo de eventos de la ventana usando dialogos");
        alerta.showAndWait();

    }
}
