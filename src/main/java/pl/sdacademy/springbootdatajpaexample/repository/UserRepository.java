package pl.sdacademy.springbootdatajpaexample.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import pl.sdacademy.springbootdatajpaexample.entity.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    List<User> findAllByLastName(String lastName);

    List<User> findAllByLastNameLike(String lastName);

    List<User> findAllByFirstNameIsNotLike(String lastNameEndingWith);

    @Query(value = "select u.* from user u where length(u.login) <= :length ", nativeQuery = true)
    List<User> findAllWhereLoginLengthLessOrEqualThan(@Param("length") int length);

    @Query(value = "select u from User u where u.firstName = :firstName and u.lastName = :lastName")
    List<User> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(value = "select u from User u " +
            "           where " +
            "               u.firstName like %:firstName% " +
            "               and u.lastName like %:lastName%"
    )
    List<User> findByFirstNameAndLastNameLike(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Transactional
    void deleteUserByLogin(String login);
}
