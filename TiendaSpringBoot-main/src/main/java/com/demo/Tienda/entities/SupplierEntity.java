package com.demo.Tienda.entities;

//Importacion de librerias necesarias
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data // Me genera los metodos Getter and Setter automaticamente
@NoArgsConstructor // Me genera constructor sin argumentos
@AllArgsConstructor // Me genera constructor con todos los parametros de la clase

//Declaracion de la clase que me representa la entidad de un proveedor
public class SupplierEntity {
    private UUID id; //Identificador unico de un proveedor
    private String nombre; //Atributo que me identifica el nombre del proveedor
    private String direccion; //Atributo que identifica la direccion del proveedor
}
