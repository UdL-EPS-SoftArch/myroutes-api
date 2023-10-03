package cat.udl.eps.softarch.myroutes.repository;

import cat.udl.eps.softarch.myroutes.domain.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdminRepository extends CrudRepository<Admin, String>, PagingAndSortingRepository<Admin, String> {
}
