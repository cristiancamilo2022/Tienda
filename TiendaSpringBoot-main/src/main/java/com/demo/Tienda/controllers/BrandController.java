package com.demo.Tienda.controllers;

//Importacion de librerias necesarias
import com.demo.Tienda.entities.BrandEntity;
import com.demo.Tienda.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Gestiona las solicitudes HTTP y las devuelve en un formato JSON
@RestController
//Defino una ruta para asociarlo con las solicitudes HTTP
@RequestMapping("/tienda/1.0/marcas")
public class BrandController {
    //Se almacena la instancia brandService para utilizarla en los metodos del controlador
    private final BrandService brandService;

    //Implementa la inyeccion de dependencias en la instancia de brandService
    @Autowired
    //Constructor que recibe la instancia de BrandService y se asigana a brandService
    public BrandController(BrandService brandService) {
        //Permite que el controlador use los metodos de BrandService
        //en otros metodos del controlador para realizar operaciones
        this.brandService = brandService;
    }

    @GetMapping //Maneja las solicitudes GET
    //Metodo que devueleve en un ResponseEntity una lista de todas las marcas
    public ResponseEntity<List<BrandEntity>> getBrands() {
        List<BrandEntity> brands = brandService.getAllBrands(); //Llamado de la lista de marcas
        return ResponseEntity.ok(brands); // Retorna una respuesta HTTP 200 (OK) con la lista de marcas en el cuerpo de la respuesta
    }

    @GetMapping("/{id}") //Maneja las solicitudes GET segun su ID
    //Metodo que devueleve en un ResponseEntity la marca segun el ID
    public ResponseEntity<BrandEntity> getBrand(@PathVariable UUID id) {
        Optional<BrandEntity> brand = brandService.getBrandById(id); //Se llama la lista de marcas para que busque el ID solicitado
        return brand.map(ResponseEntity::ok) //Si el ID se encuentra devuelve una respuesta 200 con la marca solicitada
                .orElseGet(() -> ResponseEntity.notFound().build()); //Si no se encuentra, retorna un error 404(Not Found)
    }

    @PostMapping //Maneja solicitudes POST
    //Metodo que me crea Marcas
    public ResponseEntity<String> createBrand(@RequestBody BrandEntity brand) {
        UUID createBrandId = brandService.createBrand(brand); //Se llama al servicio para crear una nueva marca y devuelve un nuevo ID
        return ResponseEntity.created(URI.create("/tienda/1.0/brands/" + createBrandId)).body("Marca Creada Correctamente"); //Crea una respuesta 201 con la ubicacion de la nueva ,marca
    }

    @PutMapping("/{id}") //Maneja solicitudes PUT segun ID
    //Metodo que actualiza las Marcas segun el ID indicado
    public ResponseEntity<String> updateBrand(@PathVariable UUID id, @RequestBody BrandEntity brand) {
        Optional<BrandEntity> updateBrand = brandService.updateBrand(id, brand); //Se llama al servcio para que actualize la marca segun el ID
        return ResponseEntity.ok().body("Marca Actualizada Correctamente");//Envia una respuesta 200 con un mensaje de exito de actualizacion
    }

    @DeleteMapping("/{id}") //Maneja solicitudes DELETE segun ID
    public ResponseEntity<String> deleteBrand(@PathVariable UUID id) {
        brandService.deleteBrand(id); //Se llama al servicio para eliminar una marca segun ID
        return ResponseEntity.ok().body("Marca Eliminada Correctamente");//Envia una respuesta 200 con mensaje de exito de eliminacion
    }
}
