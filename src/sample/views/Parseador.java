package sample.views;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Parseador extends Stage implements EventHandler<KeyEvent> {

    private VBox vBox;
    private ToolBar tlbMenu;
    private TextArea txtEntrada,txtSalida;
    private FileChooser flcArchivo;
    private Button btnAbrir,btnConvertir;
    private Scene escena;
    private Image imgAbrir;
    private ImageView imvAbrir;
    private String Dato="" ,DatoRep="";

    private String[] morse={".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","--.--","---"
            ,".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."," ","\n"};
    private String[] Letras={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V"
            ,"W","X","Y","Z"," ","ENTER"};


    public Parseador(){
        CrearUI();
        this.setTitle("Traductor codigo morse");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        vBox = new VBox();
        tlbMenu = new ToolBar();
        //Imagen icono del boton para buscar archivos locales
        imgAbrir = new Image("sample/images/Carpeta.png");
        imvAbrir = new ImageView(imgAbrir);
        imvAbrir.setFitHeight(25);
        imvAbrir.setFitWidth(25);
        btnAbrir = new Button();
        btnAbrir.setGraphic(imvAbrir);
        btnAbrir.setOnAction(event -> {
            //Se crea el buscador de archivos
            flcArchivo = new FileChooser();
            //Titulo de la ventana abierta
            flcArchivo.setTitle("Buscar Archivo");
            File archivo=flcArchivo.showOpenDialog(this);
            try{
                //Se crea el lector de archivos de texto
                BufferedReader Leer = new BufferedReader(new FileReader(archivo));

                //Se crea un string para guardar los datos del lector
                String Linea=Leer.readLine();

                while(Linea !=null){
                    //Se manda el texto al txt
                    txtEntrada.appendText(Linea+"\n");
                    Linea = Leer.readLine();

                    //Se utiliza el metodo traducir y se manda al text area de salida
                    String Dato=txtEntrada.getText();
                    System.out.println(Dato);
                    String Traducido=traducir(Dato);
                    System.out.println(Traducido);
                    txtSalida.setPromptText(Traducido);
                }
            }catch (IOException e){

            }
        });

        btnConvertir = new Button("Parsear");
        btnConvertir.setPrefWidth(600);

        btnConvertir.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String Dato=txtEntrada.getText();
                String Traducido=traducir(Dato);
                System.out.println(Traducido);
                txtSalida.setPromptText(Traducido);
            }
        });

        tlbMenu.getItems().addAll(btnAbrir);

        txtEntrada = new TextArea();
        txtEntrada.setPromptText("Introduce el texto a parsear");
        txtEntrada.setOnKeyPressed(this);
        txtSalida = new TextArea();
        txtSalida.setEditable(false);

        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5));
        vBox.getChildren().addAll(tlbMenu,txtEntrada,txtSalida,btnConvertir);
        escena = new Scene(vBox,500,300);

        escena.getStylesheets().add("sample/css/Parseador.css");
    }

    @Override
    public void handle(KeyEvent event) {

        String Entrada=event.getCode().toString();
        if(Entrada=="SPACE"){
            System.out.println("Si Conoce espacio");
            DatoRep=Dato;
            Dato=txtEntrada.getText()+" ";
            String Traducido =traducir(Dato);
            txtSalida.setText(Traducido);
        }else if(Entrada=="ENTER"){
            System.out.println("Si conoce Enter");
            DatoRep=Dato;
            Dato=txtEntrada.getText()+" \n";

            String Traducido=traducir(Dato);
            txtSalida.setText(Traducido);
        }else{
            DatoRep=Dato;
            Dato=txtEntrada.getText();
            String Traducido=traducir(Dato);
            txtSalida.setText(Traducido);
        }
    }

    public String traducir(String Dato){

        String traduccion="";
        String mayuscula="";
        String letra="";

        mayuscula = Dato.toUpperCase();

        for(int i=1; i<=Dato.length();i++){

            letra = mayuscula.substring(i-1, i);

            for(int j=0;j<Letras.length;j++){
                if (letra.equals(Letras[j])){

                    traduccion = traduccion + morse[j]+ "   ";
                }
            }
        }
        return traduccion;
    }
}
