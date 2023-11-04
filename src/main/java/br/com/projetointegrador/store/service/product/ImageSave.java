package br.com.projetointegrador.store.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ImageSave {

    public String saveImage(String base64String, String id){
        // Use regex para remover a parte inicial "data:image/jpeg;base64,"
        String base64Data = base64String;
//        base64Data = base64Data.replaceAll("[^a-zA-Z0-9/+=]", "");
        Pattern pattern = Pattern.compile("data:image/([a-zA-Z]+);base64,");
        Matcher matcher = pattern.matcher(base64Data);

        String imageExtension = "";
        if (matcher.find()) {
            imageExtension = matcher.group(1);
        }
        base64Data = base64Data.replaceAll("data:image/([a-zA-Z]+);base64,", "");
        // Decodifique a string base64 para bytes
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

        // Converta os bytes em um arquivo com um nome gerado pela Timestamp e um número randomizado
        String timestamp = String.valueOf(System.currentTimeMillis());
        String random = String.valueOf(new Random().nextInt(1000));
        String fileName = timestamp + "_" + random + "." + imageExtension; // Use a extensão apropriada

        // Defina o caminho completo para a pasta produtosImagem
        String pathToProdutosImagem = "produtosImagem/"+ id +"/";

        // Verifique se o diretório existe e crie-o, se necessário
        File produtosImagemDirectory = new File(pathToProdutosImagem);
        if (!produtosImagemDirectory.exists()) {
            produtosImagemDirectory.mkdirs();
        }

        // Construa o caminho completo para o arquivo
        String fullPath = pathToProdutosImagem + fileName;
        File file = new File(fullPath);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(decodedBytes);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao processar o arquivo.";
        }
        return fileName;
    }
}
