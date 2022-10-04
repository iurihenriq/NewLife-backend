package br.com.itbeta.newlife.repository.specifications;

import br.com.itbeta.newlife.model.Funcionario;
import br.com.itbeta.newlife.model.Funcionario_;
import br.com.itbeta.newlife.model.Morador;
import br.com.itbeta.newlife.model.Morador_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class MoradorSpecification {

    public static Specification<Morador> nomeLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Morador_.nome), "%"+info+"%");
    }
    public static Specification<Morador> cpfLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Morador_.cpf), "%"+info+"%");
    }

    public static Specification<Morador> likeGenericQuery(String queryString) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(2);
            predicates.add(nomeLike(queryString).toPredicate(root, query, criteriaBuilder));
            predicates.add(cpfLike(queryString).toPredicate(root, query, criteriaBuilder));

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
