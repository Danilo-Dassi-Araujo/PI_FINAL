package br.com.projetointegrador.store.utils;

import br.com.projetointegrador.store.dto.request.IdDTORequest;
import br.com.projetointegrador.store.dto.request.ProductRequestDTO;
import br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ValidatorUtils {

    public static void validateProduct(ProductRequestDTO productRequestDTO) throws Exception {
        if (ObjectUtils.isEmpty(productRequestDTO.getName())) {
            throw new Exception("Nome do produto está vazio!");
        }

        if (ObjectUtils.isEmpty(productRequestDTO.getName().length() > 200)) {
            throw new Exception("O nome do produto não pode conter mais de 200 caracteres!");
        }

        if (ObjectUtils.isEmpty(productRequestDTO.getDescription())) {
            throw new Exception("Descrição do produto está vazia!");
        }

        if (productRequestDTO.getDescription().length() > 2000) {
            throw new Exception("A descrição não pode conter mais de 2000 caracteres!");
        }

        if(ObjectUtils.isEmpty(productRequestDTO.getPrice())){
            throw new Exception("Preço do produto está vazio!");
        }

        if (0.5 > productRequestDTO.getRating() || productRequestDTO.getRating() > 5) {
            throw new Exception("Avaliação: " + productRequestDTO.getRating() + " está fora do range de 0.5 - 5");
        }

        if(ObjectUtils.isEmpty(productRequestDTO.getStock())){
            throw new Exception("Quantidade não pode ser vazio!");
        }

        if (productRequestDTO.getStock() < 0) {
            throw new Exception("Quantidade não pode ser menor que 0.");
        }
    }


    public static void validateUpdateProduct(UpdateProductRequestDTO updateProductRequestDTO) throws Exception {
        if (ObjectUtils.isEmpty(updateProductRequestDTO.getName())) {
            throw new Exception("Nome do produto está vazio!");
        }

        if (ObjectUtils.isEmpty(updateProductRequestDTO.getName().length() > 200)) {
            throw new Exception("O nome do produto não pode conter mais de 200 caracteres!");
        }

        if (ObjectUtils.isEmpty(updateProductRequestDTO.getDescription())) {
            throw new Exception("Descrição do produto está vazia!");
        }

        if (updateProductRequestDTO.getDescription().length() > 2000) {
            throw new Exception("A descrição não pode conter mais de 2000 caracteres!");
        }

        if(ObjectUtils.isEmpty(updateProductRequestDTO.getPrice())){
            throw new Exception("Preço do produto está vazio!");
        }

        if(updateProductRequestDTO.getPrice().compareTo(BigDecimal.ZERO) < 0){
            throw new Exception("Preço não pode ser menor que 0.");
        }

        if (0.5 > updateProductRequestDTO.getRating() || updateProductRequestDTO.getRating() > 5) {
            throw new Exception("Avaliação: " + updateProductRequestDTO.getRating() + " está fora do range de 0.5 - 5");
        }

        if(ObjectUtils.isEmpty(updateProductRequestDTO.getStock())){
            throw new Exception("Quantidade não pode ser vazio!");
        }

        if (updateProductRequestDTO.getStock() < 0) {
            throw new Exception("Quantidade não pode ser menor que 0.");
        }
    }



    public static void validateEmailRequest(IdDTORequest idDTORequest) throws Exception {
        if(ObjectUtils.isEmpty(idDTORequest)){
            throw new Exception("Request Vazia!");
        }
    }
}
