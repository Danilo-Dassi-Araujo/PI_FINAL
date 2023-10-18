package br.com.projetointegrador.store.utils;

import br.com.projetointegrador.store.dto.request.EmailDTORequest;
import br.com.projetointegrador.store.dto.request.ProductRequestDTO;
import br.com.projetointegrador.store.dto.request.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class ValidatorUtils {

    public static void validateRequest(UserRequestDTO requestDTO) throws Exception {
        if(ObjectUtils.isEmpty(requestDTO)){
            throw new Exception("A request está vazia!");
        }

        if(ObjectUtils.isEmpty(requestDTO.getEmail())){
            throw new Exception("O email está vazio!");
        }

        if(ObjectUtils.isEmpty(requestDTO.getName())){
            throw new Exception("O nome está vazio!");
        }

        if(ObjectUtils.isEmpty(requestDTO.getCpf())){
            throw new Exception("O cpf está vazio!");
        }

        if(ObjectUtils.isEmpty(requestDTO.getPassword())){
            throw new Exception("O senha está vazia!");
        }

        if(ObjectUtils.isEmpty(requestDTO.getPasswordConfirmation())){
            throw new Exception("O confirmação de senha está vazia!");
        }

        if(!requestDTO.getPassword().equals(requestDTO.getPasswordConfirmation())){
            throw new Exception("As senhas não estão iguais!");
        }
    }

    public static void validateProduct(ProductRequestDTO productRequestDTO) throws Exception {
        if (ObjectUtils.isEmpty(productRequestDTO.getNameProduct())) {
            throw new Exception("Nome do produto está vazio!");
        }

        if (ObjectUtils.isEmpty(productRequestDTO.getNameProduct().length() > 200)) {
            throw new Exception("O nome do produto não pode conter mais de 200 caracteres!");
        }

        if (ObjectUtils.isEmpty(productRequestDTO.getDescription())) {
            throw new Exception("Descrição do produto está vazia!");
        }

        if (productRequestDTO.getDescription().length() > 2000) {
            throw new Exception("A descrição não pode conter mais de 2000 caracteres!");
        }

        if(ObjectUtils.isEmpty(productRequestDTO.getPriceProduct())){
            throw new Exception("Preço do produto está vazio!");
        }

        if(productRequestDTO.getPriceProduct() < 0){
            throw new Exception("Preço não pode ser menor que 0.");
        }

        if (0.5 > productRequestDTO.getAssessmentProduct() || productRequestDTO.getAssessmentProduct() > 5) {
            throw new Exception("Avaliação: " + productRequestDTO.getAssessmentProduct() + " está fora do range de 0.5 - 5");
        }

        if(ObjectUtils.isEmpty(productRequestDTO.getStockQuantity())){
            throw new Exception("Quantidade não pode ser vazio!");
        }

        if (productRequestDTO.getStockQuantity() < 0) {
            throw new Exception("Quantidade não pode ser menor que 0.");
        }
    }

    public static void validateEmailRequest(EmailDTORequest emailDTORequest) throws Exception {
        if(ObjectUtils.isEmpty(emailDTORequest)){
            throw new Exception("Request Vazia!");
        }
    }
}
