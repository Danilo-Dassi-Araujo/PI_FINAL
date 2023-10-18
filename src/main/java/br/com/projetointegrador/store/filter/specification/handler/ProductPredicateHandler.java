package br.com.projetointegrador.store.filter.specification.handler;

import br.com.projetointegrador.store.filter.specification.FilterProducts;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.model.Product_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductPredicateHandler {

    public Predicate[] getPredicatesByFilter(FilterProducts controllerFilter,
                                             Root<Product> root,
                                             CriteriaQuery<?> cq,
                                             CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(controllerFilter.getNameProduct())) {
            String compare = "%" + controllerFilter.getNameProduct() + "%";
            predicates.add(cb.like(root.get(Product_.name), compare));
        }

        return predicates.toArray(new Predicate[0]);

    }
}
