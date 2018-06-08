package water.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import water.domian.Admin;

public interface AdminRepository  extends JpaRepository<Admin,Integer>{

}
