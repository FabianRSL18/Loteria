package sample.views;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import static sun.misc.PostVMInitHook.run;

public class Loteria extends Stage implements EventHandler {

    private VBox vBox;
    private HBox hBox1,hBox2, pantalla;
    private Button btnAtras, btnSiguiente, btnJugar;
    //private Button btnCarta=new Button();
    private Label lblTiempo,lblCartaFalt;
    private GridPane gdpPlantilla;
    private Image imgCarta;
    private Scene escena;
    private int n,vectoraux=0;

    private Timer tmTiempo;
    private TimerTask tmtTarea;

    int cont = 0, control = 0;
    Timeline animacion;
    private String [][]arImages2 ={
            {/*1er cartilla*/"bandolon.jpg","barril.jpeg","botella.jpeg","campana.jpg",
                    "catrin.jpeg","chalupa.jpg","chavorruco.jpeg","concha.jpeg","corazon.jpg","corona.jpg","cotorro.jpg",
                    "estrella.jpg","graduada.jpeg","luchador.jpeg","maceta.jpeg","negro.jpg"},
           {/*2da cartilla*/"venado.jpeg","valiente.jpg","tambor.jpg","tacos.jpeg","soldado.jpg","sandia.jpg","rosa.jpeg",
                   "rana.jpg","pescado.jpg","negro.jpg","maceta.jpeg","luchador.jpeg","graduada.jpeg","estrella.jpg",
                   "cotorro.jpg","corona.jpg"},
            {/*3er cartilla*/"corazon.jpg","concha.jpeg","chavorruco.jpeg","chalupa.jpg","catrin.jpeg","campana.jpg","botella.jpeg",
                    "barril.jpeg","bandolon.jpg","venado.jpeg","valiente.jpg","tambor.jpg","tacos.jpeg","soldado.jpg","sandia.jpg","rosa.jpeg"},
            {/*4ta cartilla*/"rana.jpg","pescado.jpg","negro.jpg","maceta.jpeg","luchador.jpeg","graduada.jpeg","estrella.jpg",
                    "cotorro.jpg","corona.jpg","corazon.jpg","concha.jpeg","chavorruco.jpeg","chalupa.jpg","catrin.jpeg","campana.jpg","botella.jpeg"},
            {/*5ta cartilla*/"barril.jpeg","bandolon.jpg","venado.jpeg","valiente.jpg","tambor.jpg","tacos.jpeg","soldado.jpg","sandia.jpg",
                    "rosa.jpeg","rana.jpg","pescado.jpg","negro.jpg","maceta.jpeg","luchador.jpeg","graduada.jpeg","estrella.jpg"}};

    private String[] arImages = {"bandolon.jpg","barril.jpeg","botella.jpeg","campana.jpg","catrin.jpeg","chalupa.jpg","chavorruco.jpeg",
            "concha.jpeg","corazon.jpg","corona.jpg","cotorro.jpg","estrella.jpg","graduada.jpeg","luchador.jpeg","maceta.jpeg","negro.jpg",
            "pescado.jpg","rana.jpg","rosa.jpeg","sandia.jpg","soldado.jpg","tacos.jpeg","tambor.jpg","valiente.jpg","venado.jpeg"};

    private Button[][] arBtnPlantilla = new Button[4][4];
    private Button btnCarta;
    private Image imgCartaP,imgVolteada;
    private ImageView imv,imgJ,imvVolteada;
    private int CartasFaltantes=16;
    int []cartas= new int[arImages.length];
    Boolean cerrar=false;

    public Loteria(){
        CrearUI();
        this.setTitle("Loteria");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        //Area de seleccion de plantilla
        btnAtras = new Button("Atras");
        btnAtras.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(cont>0) {
                    cont--;
                    CrearPlantilla(cont);
                }else{
                    cont=4;
                    CrearPlantilla(cont);
                }
                System.out.println("Plantilla: " + cont);
            }
        });
        btnAtras.setPrefWidth(100);
        btnSiguiente = new Button("Siguiente");
        btnSiguiente.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(cont<4){
                    cont++;
                    CrearPlantilla(cont);
                }else {
                    cont=0;
                    CrearPlantilla(cont);
                }
                System.out.println("Plantila : "+cont);
            }
        });
        btnSiguiente.setPrefWidth(100);
        lblTiempo = new Label("Tiempo:");
        lblCartaFalt=new Label();
        String cartaF = "";
        cartaF=cartaF+CartasFaltantes;
        lblCartaFalt.setText("Cartas Faltantes :"+cartaF);
        hBox1 = new HBox();
        hBox1.setSpacing(5);
        hBox1.getChildren().addAll(btnAtras,lblTiempo,btnSiguiente,lblCartaFalt);

        gdpPlantilla = new GridPane();
        PlatillaInicio();
        //imgCarta = new Image("");
        hBox2 = new HBox();

        //hBox2.getChildren().addAll(gdpPlantilla);

        pantalla=new HBox();
        btnCarta=new Button();
        imgVolteada = new Image("sample/images/Borrar.jpg");
        imvVolteada = new ImageView(imgVolteada);
        imvVolteada.setFitHeight(240);
        imvVolteada.setFitWidth(135);
        btnCarta.setGraphic(imvVolteada);
        btnCarta.setPrefWidth(135);
        btnCarta.setPrefHeight(240);


        pantalla.getChildren().add(btnCarta);
        pantalla.setAlignment(Pos.CENTER);

        hBox2.setSpacing(8);
        hBox2.getChildren().addAll(gdpPlantilla,pantalla);

        btnJugar = new Button("Jugar");
        btnJugar.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
        btnJugar.setPrefWidth(368);


        arBtnPlantilla[0][0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,0,0);
            }
        });
        arBtnPlantilla[0][1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,0,1);
            }
        });
        arBtnPlantilla[0][2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,0,2);
            }
        });
        arBtnPlantilla[0][3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,0,3);
            }
        });
        arBtnPlantilla[1][0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,1,0);
            }
        });
        arBtnPlantilla[1][1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,1,1);
            }
        });
        arBtnPlantilla[1][2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,1,2);
            }
        });
        arBtnPlantilla[1][3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,1,3);
            }
        });
        arBtnPlantilla[2][0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,2,0);
            }
        });
        arBtnPlantilla[2][1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,2,1);
            }
        });
        arBtnPlantilla[2][2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,2,2);
            }
        });
        arBtnPlantilla[2][3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,2,3);
            }
        });
        arBtnPlantilla[3][0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,3,0);
            }
        });
        arBtnPlantilla[3][1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,3,1);
            }
        });
        arBtnPlantilla[3][2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,3,2);
            }
        });
        arBtnPlantilla[3][3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,3,3);
            }
        });



        vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5));
        vBox.getChildren().addAll(hBox1,hBox2,btnJugar);

    escena = new Scene(vBox, 543,528);
    escena.getStylesheets().add("sample/css/Loteria.css");
    }

    public void PlatillaInicio(){
        int carta = 0;
        for (int i = 0;i < 4;i++){
            for (int j = 0; j<4 ; j++){
                arBtnPlantilla[i][j] = new Button();
                imgCartaP = new Image("sample/images/"+arImages2[0][carta]);
                imv = new ImageView(imgCartaP);
                imv.setFitWidth(70);
                imv.setFitHeight(100);
                arBtnPlantilla [i][j].setGraphic(imv);
                gdpPlantilla.add(arBtnPlantilla[i][j],j,i);
                carta++;
            }
        }
    }

    private void control(int n, int reg, int col) {
        int aux=0;
        System.out.println("Plantilla : "+cont);
        for (int i=0;i<arBtnPlantilla.length;i++){
            for(int j=0;j<arBtnPlantilla[0].length;j++){
                System.out.println(arImages[n]+arImages2[cont][aux]);
                if(i==reg && j==col){
                    if(arImages[n].equalsIgnoreCase(arImages2[cont][aux])){
                        CartasFaltantes--;
                        String cartaF="";
                        cartaF=cartaF+CartasFaltantes;
                        lblCartaFalt.setText(cartaF);
                        BorraCarta(i,j);
                         if(vectoraux==25){
                            Resultado(CartasFaltantes);
                            cerrar=true;
                            animacion.stop();
                            this.close();
                         }
                        if(CartasFaltantes!=0){
                            NuevaTarjeta();
                            cerrar=false;
                            animacion.playFromStart();
                        }else{
                            Resultado(CartasFaltantes);
                            animacion.stop();
                            cerrar=true;
                            this.close();
                        }
                        }
                }
                aux++;
                System.out.println("Aux : "+aux);
            }
        }
    }

    private void BorraCarta(int cont, int aux) {
        Image imgBorradoTemp;
        ImageView imgBorrado;
        imgBorradoTemp=new Image("sample/images/Borrar.jpg");
        imgBorrado=new ImageView(imgBorradoTemp);
        imgBorrado.setFitWidth(70);
        imgBorrado.setFitHeight(100);
        arBtnPlantilla [cont][aux].setGraphic(imgBorrado);
        arBtnPlantilla [cont][aux].getBackground();
        arBtnPlantilla[cont][aux].setDisable(true);

    }

    //Crea plantillas de
    private void CrearPlantilla(int pos) {
        int carta = 0;
        for (int i = 0;i < 4;i++){
            for (int j = 0; j<4 ; j++){
                //arBtnPlantilla[i][j] = new Button();
                imgCartaP = new Image("sample/images/"+arImages2[pos][carta]);
                imv = new ImageView(imgCartaP);
                imv.setFitWidth(70);
                imv.setFitHeight(100);
                arBtnPlantilla [i][j].setGraphic(imv);
                //gdpPlantilla.add(arBtnPlantilla[i][j],j,i);
                carta++;
            }

        }
    }

    @Override
    public void handle(Event event) {
        btnSiguiente.setDisable(true);
        btnAtras.setDisable(true);
        btnJugar.setDisable(true);

        int[] v_tiempo = {1};
        tmTiempo = new Timer();
        tmtTarea = new TimerTask() {
            @Override
            public void run() {
                if (v_tiempo[0]>0){
                    int conserva = v_tiempo[0];
                    Platform.runLater(() -> lblTiempo.setText("00:"+conserva));
                    v_tiempo[0]++;
                    if(conserva==250){
                        tmtTarea.cancel();
                    }
                    if(vectoraux==25){
                        tmtTarea.cancel();
                    }
                    if(CartasFaltantes==0){
                        Resultado(CartasFaltantes);
                        tmtTarea.cancel();
                    }
                }else{
                    v_tiempo[0] = 0;
                }
            }
        };
        tmTiempo.scheduleAtFixedRate(tmtTarea,1000,1000);
        NuevaTarjeta();
         animacion= new Timeline(new KeyFrame(Duration.seconds(4), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 NuevaTarjeta();
                 if(vectoraux==25){
                     if(cerrar!=true){
                         Cerrar();
                     }
                    // System.exit(0);
                }
            }
        }));
        animacion.setCycleCount(25);
         animacion.play();
        Timeline timeline= new Timeline(new KeyFrame(Duration.seconds(1),new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Num Cartas : "+vectoraux);
                if(CartasFaltantes==0){
                    Resultado(CartasFaltantes);
                   // System.exit(0);
                }
                if(vectoraux==25){
                    Resultado(CartasFaltantes);
                   // System.exit(0);
                }
            }
        })) ;
        //timeline.play();
        timeline.stop();
        timeline.setCycleCount(timeline.INDEFINITE);
    }

    private void Resultado(int cartasFaltantes) {
        if(CartasFaltantes == 0){
            Alert resul = new Alert(Alert.AlertType.INFORMATION);
            resul.setTitle("Felicidades");
            resul.setHeaderText("Usted ha ganado la loteria");
            resul.setContentText("");
            resul.showAndWait();

        }else {
            Alert resul = new Alert(Alert.AlertType.INFORMATION);
            resul.setTitle("Sigue intentandolo");
            resul.setHeaderText("Usted ha Perdido!!!");
            resul.setContentText("");
            resul.showAndWait();
        }
    }
    private void Cerrar(){
        Resultado(CartasFaltantes);
        this.close();
    }

    private void cerrar(ActionEvent event){
        Node sourse = (Node) event.getSource();
        Stage stage = (Stage) sourse.getScene().getWindow();
        stage.close();
    }

    //Crea la tarjeta aleatoria y la manda al imgeView
    private void NuevaTarjeta() {
        boolean opc;
        do{
            n=(int)(Math.random()*25+0);
            opc = verificar(cartas,n,control);
            if(n==0){
                control++;
            }
            if (opc==true){
                cartas[vectoraux]=n;
                vectoraux++;
                System.out.println("Vector aux :"+vectoraux);
                imgCarta = new Image("sample/images/"+arImages[n]);
                imgJ = new ImageView(imgCarta);
                imgJ.setFitWidth(135);
                imgJ.setFitHeight(240);
                btnCarta.setGraphic(imgJ);
            }
        }while(opc!=true);
        //imprimirVec(cartas);
    }

    //Manda mensaje del valor aleatorio (no repetir numeros en el array)
    private void imprimirVec(int[] cartas) {
        for(int i=0;i<cartas.length;i++){
            System.out.println(cartas[i]);
        }
    }

    //Verifica array de 2da dimension y el de primera en la carta aleatoria
    private boolean verificar(int[] cartas, int n, int control) {
        for(int i=0;i<25;i++){
            //System.out.println(cartas[i]+" n "+ n);
            if(n==0 && control==0){
                control++;
                return true;
            }
            if(cartas[i]==n){
                return false;
            }
        }
        return true;
    }
}