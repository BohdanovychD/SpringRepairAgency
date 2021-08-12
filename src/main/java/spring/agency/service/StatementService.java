package spring.agency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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


    public Page<Statement> findAllUncompletedStatements(Integer pageNumber, String sortField, String sortDir) {
        return statementDao.findAllUncompletedStatements(pageNumber, sortField, sortDir);
    }

    public Page<Statement> findAllStatementsForMaster(Integer pageNumber, String sortField, String sortDir, Principal principal) {
        return statementDao.findAllStatementsForMaster(pageNumber, sortField, sortDir, principal);
    }

        public Page<Statement> findAllCompletedStatements(Integer pageNumber, String sortField, String sortDir) {
        return statementDao.findAllCompletedStatements(pageNumber, sortField, sortDir);
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

    public Page<Statement> findByUserId(Principal principal, Integer pageNumber) {
        return statementDao.findByUserId(principal, pageNumber);
    }

    public void takeStatement(Statement statement, Principal principal) {
        statementDao.takeStatement(statement, principal);
    }

    public void cancelStatement(Statement statement) {
        statementDao.cancelStatement(statement);
    }

    public void finishStatement(Statement statement, Principal principal) {
        statementDao.finishStatement(statement, principal);
    }
}

