package com.example.bank_management.repositories;

import com.example.bank_management.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("select o from Operation o where o.account.code= :code order by o.date desc ")
    public Page<Operation> listOfOperations(@Param(value = "code") String  codeAccount, Pageable pageable);
}
