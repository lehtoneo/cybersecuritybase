package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sec.project.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
