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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parseador extends Stage implements EventHandler<KeyEvent> {

    private VBox vBox;
    private ToolBar tlbMenu;
    private TextArea txtEntrada;
    private TextArea txtSalida;
    private FileChooser flcArchivo;
    private Button btnAbrir,btnConvertir;
    private Scene escena;
    private Image imgAbrir;
    private ImageView imvAbrir;
    private String Dato="" ,DatoRep="";

    private String[] morse={".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","--.--","---"
            ,".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."," "};
    private String[] Letras={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V"
            ,"W","X","Y","Z"," "};

    public Parseador(){
        CrearUI();
        this.setTitle("Traductor codigo morse");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        vBox = new VBox();
        tlbMenu = new ToolBar();
        imgAbrir = new Image("sample/images/3643772_folder_archive_open_archives_document_icon.png");
        imvAbrir = new ImageView(imgAbrir);
        imvAbrir.setFitHeight(25);
        imvAbrir.setFitWidth(25);
        btnAbrir = new Button();
        btnAbrir.setGraphic(imvAbrir);
        btnAbrir.setOnAction(event -> {
            //Aqui va el bloque de codigo
            flcArchivo = new FileChooser();
            flcArchivo.setTitle("Buscar Archivo");
            File archivo=flcArchivo.showOpenDialog(this);
            try{
                BufferedReader Leer = new BufferedReader(new FileReader(archivo));
                String Linea=Leer.readLine();
                while(Linea !=null){
                    txtEntrada.appendText(Linea);
                    Linea = Leer.readLine();
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
    }

    @Override
    public void handle(KeyEvent event) {

        String Entrada=event.getCode().toString();
        if(Entrada=="SPACE"){
             Dato=DatoRep+" ";
        }else if(Entrada=="ENTER"){
            Dato=DatoRep+" \n";
            txtSalida.setPromptText("\n");
            txtSalida.setPromptText(Dato);
        }else{
            DatoRep=Dato;
            Dato=txtEntrada.getText();
            String Traducido=traducir(Dato);
            txtSalida.setPromptText(Traducido);
        }

        /*Dato=txtEntrada.getText();
        String Traducido=traducir(Dato);

        if(Entrada=="SPACE"){
            Dato=Dato+ " ";
            Traducido=traducir(Dato);
            txtSalida.setPromptText(Traducido);
        }else if(Entrada=="Enter"){
            Dato = Dato+"\n";
            Traducido = traducir(Dato);
            txtSalida.setPromptText(Traducido);
        }

        txtSalida.setPromptText(Traducido);
        /*String Dato=traducir(Entrada);
        String DatoRep="";

        DatoRep=DatoRep+" "+Dato;
        txtSalida.setPromptText(DatoRep);
        System.out.println(event.getCode().toString());*/


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
