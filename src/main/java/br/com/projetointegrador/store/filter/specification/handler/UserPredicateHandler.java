package br.com.projetointegrador.store.filter.specification.handler;

import br.com.projetointegrador.store.filter.specification.ControllerFilter;
import br.com.projetointegrador.store.model.User;
import br.com.projetointegrador.store.model.User_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserPredicateHandler {

    public Predicate[] getPredicatesByFilter(ControllerFilter controllerFilter,
                                             Root<User> root,
                                             CriteriaQuery<?> cq,
                                             CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(controllerFilter.getName())) {
            String compare = "%" + controllerFilter.getName() + "%";
            predicates.add(cb.like(root.get(User_.name), compare));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
