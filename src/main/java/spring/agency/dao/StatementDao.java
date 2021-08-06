package spring.agency.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.agency.model.entity.Statement;
import spring.agency.repository.StatementRepository;

import java.util.List;

@Component
public class StatementDao {


    @Autowired
    private StatementRepository statementRepository;

    public List<Statement> findAllStatements() {
        return statementRepository.findAll();
    }
}
