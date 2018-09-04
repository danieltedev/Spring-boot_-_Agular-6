package com.dtel.springrestfull.springrestfullapi.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dtel.springrestfull.springrestfullapi.model.Lancamento;
import com.dtel.springrestfull.springrestfullapi.repository.filter.LancamentoFilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * LancamentoRepositoryImpl
 */
public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteriaQuery = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);

        Predicate[] predicates = createCriteria(filter, builder, root);
        criteriaQuery.where(predicates);

        TypedQuery<Lancamento> query = manager.createQuery(criteriaQuery);
        addCriteriaOfPageable(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    private Predicate[] createCriteria(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {
        
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getDescricao() != null) {
            predicates.add(builder.like(root.get("descricao"), "%" + filter.getDescricao().toLowerCase() + "%"));
        }

        if (filter.getDataVencimentoAte() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataVencimento"), filter.getDataVencimentoDe()));
        }

        if (filter.getDataVencimentoDe() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataVencimento"), filter.getDataVencimentoAte()));
        }
        
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    public Long total(LancamentoFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = createCriteria(filter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void addCriteriaOfPageable(TypedQuery<Lancamento> query, Pageable pageable) {
        int currencyPage = pageable.getPageNumber();
        int totalRegisterForPage = pageable.getPageSize();
        int firstRegisterOfPage = currencyPage * totalRegisterForPage;

        query.setFirstResult(firstRegisterOfPage);
        query.setMaxResults(totalRegisterForPage);
    }

}