package br.com.projetointegrador.store.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ImageRemove {

    public void removeImage(String folderPath, String fileName) throws IOException {
        Path filePath = Paths.get(folderPath, fileName);

        Files.delete(filePath);
    }
}
