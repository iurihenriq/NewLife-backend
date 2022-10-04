package br.com.itbeta.newlife.repository.specifications;

import br.com.itbeta.newlife.model.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class VisitanteSpecification {

    public static Specification<Visitante> nomeLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Visitante_.nome), "%"+info+"%");
    }

    public static Specification<Visitante> rgLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Visitante_.rg), "%"+info+"%");
    }

    public static Specification<Visitante> cpfLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Visitante_.cpf), "%"+info+"%");
    }

    public static Specification<Visitante> telefonePrincipalLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Visitante_.telefonePrincipal), "%"+info+"%");
    }

    public static Specification<Visitante> telefoneSecundarioLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Visitante_.telefoneSecundario), "%"+info+"%");
    }

    public static Specification<Visitante> observacoesLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Visitante_.observacoes), "%"+info+"%");
    }

    public static Specification<Visitante> likeGenericQuery(String queryString) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(6);
            predicates.add(nomeLike(queryString).toPredicate(root, query, criteriaBuilder));
            predicates.add(rgLike(queryString).toPredicate(root, query, criteriaBuilder));
            predicates.add(cpfLike(queryString).toPredicate(root, query, criteriaBuilder));
            predicates.add(telefonePrincipalLike(queryString).toPredicate(root, query, criteriaBuilder));
            predicates.add(telefoneSecundarioLike(queryString).toPredicate(root, query, criteriaBuilder));
            predicates.add(observacoesLike(queryString).toPredicate(root, query, criteriaBuilder));

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
