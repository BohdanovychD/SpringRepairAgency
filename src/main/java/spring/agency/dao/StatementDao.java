package spring.agency.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import spring.agency.model.entity.Statement;
import spring.agency.model.entity.User;
import spring.agency.repository.StatementRepository;
import spring.agency.repository.UserRepository;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;

@Component
public class StatementDao {

    private final StatementRepository statementRepository;
    private final UserRepository userRepository;

    @Autowired
    public StatementDao(StatementRepository statementRepository, UserRepository userRepository) {
        this.statementRepository = statementRepository;
        this.userRepository = userRepository;
    }

    public Page<Statement> findAllStatementsForMaster(Integer pageNumber, String sortField, String sortDir, Principal principal) {
        User user = currentUser(principal);
        return statementRepository.findAllForMaster(sort(pageNumber, sortField, sortDir), user.getName());
    }

    public Page<Statement> findAllUncompletedStatements(Integer pageNumber, String sortField, String sortDir) {
        return statementRepository.findAllUncompleted(sort(pageNumber, sortField, sortDir));
    }

    public Page<Statement> findAllCompletedStatements(Integer pageNumber, String sortField, String sortDir) {
        return statementRepository.findAllCompleted(sort(pageNumber, sortField, sortDir));
    }

    public Statement save(Statement statement, Principal principal) {
        String login = principal.getName();
        User user = userRepository.findByLogin(login);
        Timestamp timestamp = Timestamp.from(Instant.now());
        statement.setUser(user);
        statement.setPrice(0.0);
        statement.setDate(timestamp);
        statement.setStatus("UNCHECKED");
        statementRepository.save(statement);
        return statementRepository.save(statement);
    }

    public Statement findById(Long statementId) {
        return statementRepository.findById(statementId).get();
    }

    public void setPrice(Statement statement) {
        statement.setStatus("WAITING FOR PAYMENT");
        statementRepository.setPrice(statement.getPrice(), statement.getStatus(), statement.getId());
    }

    public void leaveComment(Statement statement) {
        statement.setStatus("COMPLETED");
        statementRepository.setComment(statement.getComment(), statement.getStatus(), statement.getId());
    }

    public void cancelStatement(Statement statement) {
        statement.setStatus("CANCEL");
        statementRepository.setPrice(statement.getPrice(), statement.getStatus(), statement.getId());
    }

    public void takeStatement(Statement statement, Principal principal) {
        statement.setStatus("IN A PROCESS");
        String login = principal.getName();
        User user = userRepository.findByLogin(login);
        statementRepository.setMaster(user.getName(), statement.getStatus(), statement.getId());
    }

    public void finishStatement(Statement statement, Principal principal) {
        statement.setStatus("DONE");
        String login = principal.getName();
        User user = userRepository.findByLogin(login);
        statementRepository.setMaster(user.getName(), statement.getStatus(), statement.getId());
    }

    public Page<Statement> findByUserId(Principal principal, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10);
        String login = principal.getName();
        User user = userRepository.findByLogin(login);
        return statementRepository.findByUserId(user.getId(), pageable);
    }

    private static Pageable sort(Integer pageNumber, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        return PageRequest.of(pageNumber - 1, 10, sort);
    }

    private User currentUser(Principal principal) {
        String login = principal.getName();
        User user = userRepository.findByLogin(login);
        return user;
    }

}
