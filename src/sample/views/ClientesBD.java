package sample.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.ClientesDAO;

public class ClientesBD extends Stage {
    private Scene escena;
    private TableView<ClientesDAO> tbvClientes;
    private Button btnAgregar;
    private VBox vBox;

    public ClientesBD(){
        CrearUI();
        this.setTitle("Clientes Taqueria: ");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        tbvClientes = new TableView<>();
        btnAgregar = new Button("Agregar Cliente");
        btnAgregar.setOnAction(event -> {});
        vBox.getChildren().addAll(tbvClientes,btnAgregar);
        escena = new Scene (vBox,700,250);

        CrarTabla();
    }

    private void CrarTabla() {
        TableColumn<ClientesDAO,Integer> tbcIdCliente = new TableColumn<>("ID");
        TableColumn<ClientesDAO,String> tbcNomCliente = new TableColumn<>("Nombre");
        TableColumn<ClientesDAO,String> tbcTelCliente = new TableColumn<>("Telefono");
        TableColumn<ClientesDAO,String> tbcDirCliente = new TableColumn<>("Direccion");

        tbvClientes.getColumns().addAll(tbcIdCliente,tbcNomCliente,tbcTelCliente,tbcDirCliente);
    }
}
