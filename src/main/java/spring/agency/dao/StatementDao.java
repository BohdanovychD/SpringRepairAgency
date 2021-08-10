package spring.agency.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.agency.model.entity.Statement;
import spring.agency.model.entity.User;
import spring.agency.repository.StatementRepository;
import spring.agency.repository.UserRepository;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static java.lang.String.*;

@Component
public class StatementDao {

    private final StatementRepository statementRepository;
    private final UserRepository userRepository;

    @Autowired
    public StatementDao(StatementRepository statementRepository, UserRepository userRepository) {
        this.statementRepository = statementRepository;
        this.userRepository = userRepository;
    }

    public List<Statement> findAllStatements() {
        return statementRepository.findAll();
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

    public void setPrice(Statement updatedStatement) {
        statementRepository.setPrice(updatedStatement.getPrice(), updatedStatement.getId());
    }
    public List<Statement> findByUserId(Principal principal) {
        String login = principal.getName();
        User user = userRepository.findByLogin(login);
        return statementRepository.findByUserId(user.getId());
    }
}
