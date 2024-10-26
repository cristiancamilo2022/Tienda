package com.demo.Tienda.entities;

//Importacion de librerias necesarias
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data // Me genera los metodos Getter and Setter automaticamente
@NoArgsConstructor // Me genera constructor sin argumentos
@AllArgsConstructor // Me genera constructor con todos los parametros de la clase

//Declaracion de la clase que me representa la entidad de una marca
public class BrandEntity {
    private UUID id; //Identificador unico de una marca
    private String nombre; //Atributo que representa el nombre de una marca
}
