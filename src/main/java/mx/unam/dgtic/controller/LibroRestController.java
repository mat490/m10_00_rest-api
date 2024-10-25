package mx.unam.dgtic.controller;

import mx.unam.dgtic.model.Libro;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/libreria")
public class LibroRestController {
    public static final String NOMBRE = "Matías Alejandro Suárez Zúñiga";
    HashMap<Integer, Libro> libreria;

    public LibroRestController() {
        libreria = new HashMap<>();
        libreria.put(0, new Libro(0, "Modulo 10", NOMBRE));
        libreria.put(1, new Libro(1, "El perfume", "Patrick Suskind"));
        libreria.put(2, new Libro(2, "El señor de los anillos", "J.R. Tolkien"));
        libreria.put(3, new Libro(3, "Fundación", "Isaac Asimov"));
    }

    @GetMapping("/saludar")
    public String saludar(){
        return "Hola "+NOMBRE;
    }

    @GetMapping("/")
    public HashMap<Integer, Libro> getLibreria(){
        return libreria;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> getLibro(@PathVariable int id){
        //return libreria.get(id);
        Libro libro = libreria.get(id);
        if (libro != null){
            return ResponseEntity.ok(libro); // 200
        } else {
            return ResponseEntity.notFound().build(); // 404
        }

    }

    @GetMapping(path = "/", headers = {"Accept=aplication/json"},
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<Integer, Libro>> getAll(){
        return  new ResponseEntity<>(
                libreria,
                HttpStatus.OK
        );
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> addLibro(@RequestBody Libro libro){
        int id = 1;
        while(libreria.containsKey(id)){
            id++;
        }
        libro.setId(id);
        libreria.put(id,libro);
        return new  ResponseEntity<Libro>(libro, HttpStatus.CREATED); // 201
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> remplazarLibro(@PathVariable int id, @RequestBody Libro libro){
        if (libreria.containsKey(id)){
            libreria.replace(id, libro);
            return ResponseEntity.ok(libreria.get(id)); // 200

        } else {
            libreria.put(id, libro);
            return new ResponseEntity<Libro>(libreria.get(id), HttpStatus.CREATED); // 201
        }

    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> actualizacionLibro(@PathVariable int id, @RequestBody Libro libro){
        Libro libroDB = libreria.get(id);

        if (libro == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        if (libro.getTitulo() != null){
            libroDB.setTitulo(libro.getTitulo());
        }

        if (libro.getAutor() != null){
            libroDB.setAutor(libro.getAutor());
        }
        libreria.replace(id, libroDB);
        return ResponseEntity.ok(libreria.get(id));
    }
    @PatchMapping("/")
    public ResponseEntity<String> patchNoPermitido(){
        return new ResponseEntity<>("{'msg': 'Acción no permitida'}", HttpStatus.METHOD_NOT_ALLOWED);
    }


    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> deleteLibro(@PathVariable int id){
//        return libreria.remove(id);

        if (libreria.containsKey(id)){
            return ResponseEntity.ok(libreria.remove(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}

