package br.com.projetointegrador.store.repository.specification;

import br.com.projetointegrador.store.filter.specification.ControllerFilter;
import br.com.projetointegrador.store.filter.specification.handler.UserPredicateHandler;
import br.com.projetointegrador.store.model.User;
import br.com.projetointegrador.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSpecification {

    private final UserRepository userRepository;
    private final UserPredicateHandler userPredicateHandler;

    public List<User> findBy(ControllerFilter controllerFilter) {
        Specification<User> specification = (root, criteriaQuery, criteriaBuilder) ->
                criteriaQuery
                        .where(userPredicateHandler.getPredicatesByFilter(controllerFilter, root, criteriaQuery, criteriaBuilder))
                        .getRestriction();
        return userRepository.findAll(specification);
    }
}