package ahmed.foudi.itlens.dao;

import ahmed.foudi.itlens.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDAO extends JpaRepository<Owner, Long>{
    
}
