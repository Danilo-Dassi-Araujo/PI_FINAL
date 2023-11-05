package br.com.projetointegrador.store.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class ImageController {

    @GetMapping("/getImage/{id}/{image}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable String id, @PathVariable String image) {
        try {
            // Carregue a imagem do diretório especificado
            String imagePath = "/produtosImagem" + id + "/" + image;
            File file = new File(imagePath);
            byte[] imageBytes = Files.readAllBytes(file.toPath());

            // Configure os cabeçalhos da resposta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Defina o tipo de conteúdo da imagem

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
