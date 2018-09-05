package com.dtel.springrestfull.springrestfullapi.repository.lancamento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dtel.springrestfull.springrestfullapi.model.Lancamento;
import com.dtel.springrestfull.springrestfullapi.repository.filter.LancamentoFilter;
import com.dtel.springrestfull.springrestfullapi.repository.projection.ResumoLancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * LancamentoRepositoryImpl
 */
public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    private CriteriaBuilder builder;
    private CriteriaQuery<?> criteriaQuery;
    private Root<?> root;
    private TypedQuery<?> query;

    @Override
    public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {
        this.init(Lancamento.class, Lancamento.class);

        Predicate[] predicates = createCriteria(filter, this.builder, this.root);
        criteriaQuery.where(predicates);

        this.query = manager.createQuery(criteriaQuery);
        addCriteriaOfPageable(query, pageable);
        List<Lancamento> resultList = query.getResultList().stream().map(r -> (Lancamento) r).collect(Collectors.toList());
        return new PageImpl<>(resultList, pageable, total(filter));
    }

    @Override
    public Page<ResumoLancamento> resumo(LancamentoFilter filter, Pageable pageable) {
        this.init(ResumoLancamento.class, ResumoLancamento.class);

        this.builder.createQuery(ResumoLancamento.class)
            .select(this.builder.construct(ResumoLancamento.class,
                this.root.get(Lancamento.class.getDeclaredFields()[0].getName()),
                this.root.get(Lancamento.class.getDeclaredFields()[1].getName()),
                this.root.get(Lancamento.class.getDeclaredFields()[2].getName()),
                this.root.get(Lancamento.class.getDeclaredFields()[3].getName()),
                this.root.get(Lancamento.class.getDeclaredFields()[4].getName()),
                this.root.get(Lancamento.class.getDeclaredFields()[5].getName()),
                this.root.get(Lancamento.class.getDeclaredFields()[6].getName()),
                this.root.get(Lancamento.class.getDeclaredFields()[7].getName())
                    .get(Lancamento.class.getDeclaredFields()[2].getName()),
                this.root.get(Lancamento.class.getDeclaredFields()[8].getName())
                    .get(Lancamento.class.getDeclaredFields()[2].getName())));

        Predicate[] predicates = createCriteria(filter, this.builder, this.root);
        criteriaQuery.where(predicates);

        this.query = manager.createQuery(criteriaQuery);
        addCriteriaOfPageable(query, pageable);
        List<ResumoLancamento> resultList = query.getResultList().stream().map(r -> (ResumoLancamento) r).collect(Collectors.toList());
        return new PageImpl<>(resultList, pageable, total(filter));
	}

    private Predicate[] createCriteria(LancamentoFilter filter, CriteriaBuilder builder, Root<?> root) {
        
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

    private void init(Class<?> clazzQuery, Class<?> clazzRoot) {
        this.builder = manager.getCriteriaBuilder();
        this.criteriaQuery = builder.createQuery(clazzQuery);
        this.root = criteriaQuery.from(clazzRoot);
    }

    private void addCriteriaOfPageable(TypedQuery<?> query, Pageable pageable) {
        int currencyPage = pageable.getPageNumber();
        int totalRegisterForPage = pageable.getPageSize();
        int firstRegisterOfPage = currencyPage * totalRegisterForPage;

        query.setFirstResult(firstRegisterOfPage);
        query.setMaxResults(totalRegisterForPage);
    }

}