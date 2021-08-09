package spring.agency.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.agency.model.entity.Statement;
import spring.agency.model.entity.User;
import spring.agency.repository.StatementRepository;

import java.util.List;

@Component
public class StatementDao {


    @Autowired
    private StatementRepository statementRepository;

    public List<Statement> findAllStatements() {
        return statementRepository.findAll();
    }

    public Statement save(Statement statement) {
        return statementRepository.save(statement);
    }

    public Statement findById(Long statementId) {
        return statementRepository.findById(statementId).get();
    }

    public void setPrice(Statement updatedStatement) {
        statementRepository.setPrice(updatedStatement.getPrice(), updatedStatement.getId());
    }
    public List<Statement> findByUserId(Long userId) {
        return statementRepository.findByUserId(userId);
    }
}
