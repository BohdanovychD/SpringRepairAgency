package spring.agency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.agency.dao.StatementDao;
import spring.agency.dao.UserDao;
import spring.agency.model.entity.Statement;

import java.security.Principal;
import java.util.List;

@Service
public class StatementService {

    @Autowired
    private StatementDao statementDao;


    public List<Statement> findAllStatements() {
        return statementDao.findAllStatements();
    }

    public Statement save(Statement statement, Principal principal) {
        return statementDao.save(statement, principal);
    }

    public Statement findById(Long statementId) {
        return statementDao.findById(statementId);
    }

    public void setPrice(Statement statement) {
        statementDao.setPrice(statement);
    }

    public void leaveComment(Statement statement) {
        statementDao.leaveComment(statement);
    }

    public List<Statement> findByUserId(Principal principal) {
        return statementDao.findByUserId(principal);
    }

    public void takeStatement(Statement statement) {
        statementDao.takeStatement(statement);
    }

    public void cancelStatement(Statement statement) {
        statementDao.cancelStatement(statement);
    }

    public void finishStatement(Statement statement) {
        statementDao.finishStatement(statement);
    }
}

