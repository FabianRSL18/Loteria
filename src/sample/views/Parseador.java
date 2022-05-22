package sample.views;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Parseador extends Stage implements EventHandler<KeyEvent> {
    private VBox vBox;
    private ToolBar tlbMenu;
    private TextArea txtEntrada, txtSalida;
    private FileChooser flcArchivo;
    private Scene escena;
    private Button btnAbrir, btnTraducir, btnBorrar;
    private Image imgAbrir;
    private ImageView imvAbrir;
    private HBox hBox;
    ArrayList<Character> tam = new ArrayList<Character>();
    ArrayList<String> escritura = new ArrayList<String>();

    public Parseador(){
        CrearUI();
        this.setTitle("Parseador");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        hBox = new HBox();
        vBox = new VBox();
        tlbMenu = new ToolBar();
        imgAbrir = new Image("sample/images/Carpeta.png");
        imvAbrir = new ImageView(imgAbrir);
        imvAbrir.setFitHeight(25);
        imvAbrir.setFitWidth(25);

        btnAbrir = new Button();
        btnAbrir.setGraphic(imvAbrir);
        btnAbrir.setOnAction(actionEvent -> {
            //aquì va el bloque de còdigo
            flcArchivo = new FileChooser();
            flcArchivo.setTitle("Buscar Archivo...");
            flcArchivo.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));
            File archivo =  flcArchivo.showOpenDialog(this);
            InputStream doc = null;
            try {
                doc = new FileInputStream(String.valueOf(archivo));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Scanner lee = new Scanner(doc);
            while (lee.hasNextLine()){
                txtEntrada.appendText(lee.nextLine());
                txtEntrada.appendText("\n");
            }

        });
        tlbMenu.getItems().addAll(btnAbrir);
        //...
        txtEntrada = new TextArea();
        txtEntrada.setPromptText("Introduce el texto a parsear");
        txtEntrada.setOnKeyPressed(this);

        txtSalida = new TextArea();
        txtSalida.setEditable(false);

        btnTraducir = new Button("Traducir");
        btnTraducir.setOnAction(actionEvent ->{
            TraduceEscrito();
        });

        btnBorrar = new Button("Borrar");
        btnBorrar.setOnAction(actionEvent->{
            txtEntrada.setText("");
            txtSalida.setText("");
            tam.clear();
            escritura.clear();

        });

        hBox.getChildren().addAll(btnTraducir,btnBorrar);
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(tlbMenu,txtEntrada,txtSalida,hBox);
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5));
        escena = new Scene(vBox,500,300);
        escena.getStylesheets().add("sample/css/Parseador.css");


    }

    private void TraduceEscrito() {
        String cadena = "";

        char[] tamaño = new char[txtEntrada.getLength()];
        String[] escrituraTxt = new String[tamaño.length];

        txtSalida.setText("");

        for (int i = 0; i <= txtEntrada.getLength()-1; i++) {
            tamaño[i] = txtEntrada.getText().charAt(i);
            System.out.println(tamaño[i]);

        }

        for (int i = 0; i <= tamaño.length - 1 ; i++) {
            switch (String.valueOf(tamaño[i])) {
                case "a":
                    escrituraTxt[i] = "._ ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "b":
                    escrituraTxt[i] = "_... ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "c":
                    escrituraTxt[i] = "_._. ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "d":
                    escrituraTxt[i] = "_.. ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "e":
                    escrituraTxt[i] = "." ;
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "f":
                    escrituraTxt[i] = ".._. ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "g":
                    escrituraTxt[i] = "__. ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "h":
                    escrituraTxt[i] = ".... ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "i":
                    escrituraTxt[i] = ".. ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case"j":
                    escrituraTxt[i] = ".___ ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "k":
                    escrituraTxt[i] = "_._ ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "l":
                    escrituraTxt[i] = "._.. ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "m":
                    escrituraTxt[i] = "__ ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "n":
                    escrituraTxt[i] = "_. ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "ñ":
                    escrituraTxt[i] = "__.__. ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "o":
                    escrituraTxt[i] = "___ ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "p":
                    escrituraTxt[i] = ".__. ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "q":
                    escrituraTxt[i] = "__._ ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "r":
                    escrituraTxt[i] = "._. ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "s":
                    escrituraTxt[i] = "... ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "t":
                    escrituraTxt[i] = "_ ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "u":
                    escrituraTxt[i] = ".._ ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "v":
                    escrituraTxt[i] = "..._ ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "w":
                    escrituraTxt[i] = ".__ ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "x":
                    escrituraTxt[i] = "_.._ ";
                    txtSalida.appendText( escrituraTxt[i]);
                    break;
                case "y":
                    escrituraTxt[i] = "_.__ ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "z":
                    escrituraTxt[i] = "__.. ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "\n":
                    escrituraTxt[i] = "\n";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "0":
                    escrituraTxt[i] = "_____ ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "1":
                    escrituraTxt[i] = ".____ ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "2":
                    escrituraTxt[i] = "..___ ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "3":
                    escrituraTxt[i] = "...__ ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "4":
                    escrituraTxt[i] = "...._ ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case"5":
                    escrituraTxt[i] = "..... ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case"6":
                    escrituraTxt[i] = "_.... ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case"7":
                    escrituraTxt[i] = "__... ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "8":
                    escrituraTxt[i] = "___.. ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "9":
                    escrituraTxt[i] = "____. ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case "@":
                    escrituraTxt[i] = ".__._. ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                case " ":
                    escrituraTxt[i] ="  ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
                default:
                    //Al detectarse un caracter especial, simplemente se agrega un espacio en blanco
                    escrituraTxt[i] = " ";
                    txtSalida.appendText(escrituraTxt[i]);
                    break;
            }
        }
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        Traduce(keyEvent);

    }

    private void Traduce(KeyEvent keyEvent) {
        String guarda  = "";

        String compa = keyEvent.getCode().toString().toLowerCase();
        System.out.println(keyEvent.getCode().toString().toLowerCase());
        String cadena = "";


        if (keyEvent.isControlDown()) {
            if (compa.equals("back_space")) {
                if (!escritura.isEmpty()) {
                    int tamañoReal = txtEntrada.getLength();
                    System.out.println(tamañoReal);
                    while(tamañoReal != escritura.size()){
                        System.out.println("entre");
                        escritura.remove(escritura.size()-1);
                    }
                    guarda = "";
                    Iterator<String> imprime = escritura.iterator();
                    while(imprime.hasNext()){
                        guarda = guarda + imprime.next();
                    }
                    txtSalida.setText(guarda);
                }
            }
        }

        if (keyEvent.isMetaDown()) {
            if (compa.equals("q") || compa.equals("digit2")) {
                escritura.add(".__._.");
                txtSalida.appendText(escritura.get(escritura.size() - 1));
            }
        }



        if (compa.length()<=1) {
            tam.add(compa.charAt(compa.length()-1));

            switch (String.valueOf(tam.get(tam.size()-1))){
                case "a":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("._");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "b":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("_...");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "c":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("_._.");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "d":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("_..");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "e":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "f":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".._.");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "g":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("__.");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "h":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("....");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "i":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("..");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case"j":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".___");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "k":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("_._");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "l":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("._..");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "m":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("__");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "n":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("_.");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "ñ":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("__.__.");
                        //System.out.println(escritura.get(escritura.size() - 1));
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                case "o":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("___");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "p":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".__.");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "q":

                    if (keyEvent.isAltDown()|| keyEvent.isMetaDown() || keyEvent.isControlDown()){

                    }else {
                        escritura.add("__._");
                        txtSalida.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case "r":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("._.");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "s":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("...");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "t":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("_");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "u":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".._");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "v":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("..._");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "w":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".__");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "x":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("_.._");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "y":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("_.__");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "z":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("__..");
                        txtSalida.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                /*default:
                    escritura.add("");
                    txtSalida.appendText(escritura.get(escritura.size()-1));*/
            }
        }else{
            switch (compa){
                case "back_space":
                    if (keyEvent.isControlDown()){
                        //Se ignora la clase porque se borra en la parte de arriba
                    }else{
                        if (!escritura.isEmpty()) {
                            guarda = "";
                            escritura.remove(escritura.size()-1);
                            Iterator<String> imprime = escritura.iterator();
                            while (imprime.hasNext()){
                                guarda = guarda  + imprime.next();

                            }
                            txtSalida.setText(guarda);
                        }
                    }
                    break;
                case "space":
                case "undefined":
                    escritura.add(" ");
                    txtSalida.appendText(escritura.get(escritura.size() - 1));
                    break;
                case "enter":
                    escritura.add("\n");
                    txtSalida.appendText(escritura.get(escritura.size()-1));
                    break;
                case "digit0":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("_____");
                        txtSalida.appendText(escritura.get(escritura.size()-1));
                    }

                    break;
                case "digit1":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".____");
                        txtSalida.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case "digit2":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("..___");
                        txtSalida.appendText(escritura.get(escritura.size()-1));
                    }

                    break;
                case "digit3":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("...__");
                        txtSalida.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case "digit4":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("...._");
                        txtSalida.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case"digit5":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".....");
                        txtSalida.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case"digit6":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("_....");
                        txtSalida.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case"digit7":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("__...");
                        txtSalida.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case "digit8":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("___..");
                        txtSalida.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case "digit9":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("____.");
                        txtSalida.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case "tab":
                    escritura.add("\t");
                    txtSalida.appendText(escritura.get(escritura.size()-1));
                    break;
                case "alt":
                    if(keyEvent.isAltDown()){
                        if (compa.equals("q")||compa.equals("digit2")){
                            escritura.add(".__._.");
                            txtSalida.appendText(escritura.get(escritura.size()-1));
                        }
                        //se ignora debido a que no hay morse de estos caracteres
                    }
                    break;
                case "alt_graph":
                    break;
                //se ignora debido a que no hay morse de estos caracteres
                case "shift":
                    if (keyEvent.isShiftDown()) {
                        //se ignora debidos a que no hay morse de estos caracteres
                    }
                    break;
            }
        }
    }
}
