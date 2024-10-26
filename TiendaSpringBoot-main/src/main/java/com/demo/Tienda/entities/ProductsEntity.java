package com.demo.Tienda.entities;

//Importacion de librerias necesarias
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data // Me genera los metodos Getter and Setter automaticamente
@NoArgsConstructor // Me genera constructor sin argumentos
@AllArgsConstructor // Me genera constructor con todos los parametros de la clase

// Declaracion de la clase que me representa la entidad de un producto
public class ProductsEntity {
    private UUID id; //Identificador unico de cada producto
    private String nombre; //Atributo que representa el nombre de un producto
    private double precio; //Atributo que representa el valor de un producto
    private UUID warrantyId; //Identificador de la garantia
}
