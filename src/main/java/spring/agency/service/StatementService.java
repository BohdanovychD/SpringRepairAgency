package spring.agency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.agency.dao.StatementDao;
import spring.agency.model.entity.Statement;

import java.util.List;

@Service
public class StatementService {

    @Autowired
    private StatementDao statementDao;

    public List<Statement> findAllStatements() {
        return statementDao.findAllStatements();
    }

    public Statement save(Statement statement) {
        return statementDao.save(statement);
    }

    public Statement findById(Long statementId) {
        return statementDao.findById(statementId);
    }

    public void setPrice(Statement updatedStatement) {
        statementDao.setPrice(updatedStatement);
    }
    public List<Statement> findByUserId(Long userId) {
        return statementDao.findByUserId(userId);
    }
}

