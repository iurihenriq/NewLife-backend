package br.com.itbeta.newlife.repository.specifications;

import br.com.itbeta.newlife.model.Funcionario;
import br.com.itbeta.newlife.model.Funcionario_;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

public class FuncionarioSpecification {

    public static Specification<Funcionario> nomeLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Funcionario_.nome), "%"+info+"%");
    }

    public static Specification<Funcionario> rgLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Funcionario_.rg), "%"+info+"%");
    }

    public static Specification<Funcionario> cpfLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Funcionario_.cpf), "%"+info+"%");
    }

    public static Specification<Funcionario> telefonePrincipalLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Funcionario_.telefonePrincipal), "%"+info+"%");
    }

    public static Specification<Funcionario> telefoneSecundarioLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Funcionario_.telefoneSecundario), "%"+info+"%");
    }

    public static Specification<Funcionario> observacoesLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Funcionario_.observacoes), "%"+info+"%");
    }

    public static Specification<Funcionario> likeGenericQuery(String queryString) {
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
