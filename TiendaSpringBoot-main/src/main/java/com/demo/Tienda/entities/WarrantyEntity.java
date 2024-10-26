package com.demo.Tienda.entities;

//Importacion de librerias necesarias
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data // Me genera los metodos Getter and Setter automaticamente
@NoArgsConstructor // Me genera constructor sin argumentos
@AllArgsConstructor // Me genera constructor con todos los parametros de la clase

//Declaracion de la clase que me representa la entidad de la garantia
public class WarrantyEntity {
    private UUID id; //Identificador unico de las garantias
    private LocalDate fechaInicio; //Atributo que representa la fecha de inicio de la garantia
    private LocalDate fechaFin; //Atributo que representa la fecha de fin de la garantia
}
