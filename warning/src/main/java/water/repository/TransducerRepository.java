package water.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import water.domian.Transducer;

public interface TransducerRepository extends JpaRepository<Transducer,Integer> {
}
