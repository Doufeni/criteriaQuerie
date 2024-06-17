package com.example.criteriaQuerie;

import com.example.criteriaQuerie.Entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeSearchDAO {

    private final EntityManager em;
    public List<Employee> findAllEmployee(
            String firstname,
            String lastName,
            String email
    ) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        // select * from employee
        Root<Employee> root = criteriaQuery.from(Employee.class);
        Predicate firstnamePredicate = criteriaBuilder.like(root.get("firstName"), "%"+ firstname + "%");
        Predicate lasttnamePredicate = criteriaBuilder.like(root.get("lastName"), "%"+ lastName + "%");
        Predicate emailPredicate = criteriaBuilder.like(root.get("email"), "%"+ email + "%");

        Predicate firstNameOrLastNamePredicate = criteriaBuilder.or(firstnamePredicate, lasttnamePredicate);
        var andEmailPredicate = criteriaBuilder.and(firstNameOrLastNamePredicate, emailPredicate);
        criteriaQuery.where(andEmailPredicate);
        TypedQuery<Employee> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    public List<Employee> findAllByCriteria(SearchRequest request) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        List<Predicate> predicates = new ArrayList<>();
        Root<Employee> root = criteriaQuery.from(Employee.class);

        if(request.getFirstname() != null) {
            Predicate firstnamePredicate = criteriaBuilder.like(root.get("firstName"), "%"+ request.getFirstname() + "%");
            predicates.add(firstnamePredicate);
        }
        if(request.getLastName() != null) {
            Predicate lastnamePredicate = criteriaBuilder.like(root.get("lastName"), "%"+ request.getLastName() + "%");
            predicates.add(lastnamePredicate);
        }
        if(request.getEmail() != null) {
            Predicate emailPredicate = criteriaBuilder.like(root.get("email"), "%"+ request.getEmail() + "%");
            predicates.add(emailPredicate);
        }

        criteriaQuery.where(
                criteriaBuilder.or(predicates.toArray(new Predicate[0]))
        );
        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        return query.getResultList();

    }

}
