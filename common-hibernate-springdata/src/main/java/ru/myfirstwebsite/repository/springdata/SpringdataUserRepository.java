package ru.myfirstwebsite.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.myfirstwebsite.domain.HibernateUser;

public interface SpringdataUserRepository extends
        CrudRepository<HibernateUser, Long>,
        JpaRepository<HibernateUser, Long>,
        PagingAndSortingRepository<HibernateUser, Long> {

    HibernateUser findByLogin(String login);
    
//    List<HibernateUser> findByUserNameAnAndUserSurName(String userName, String userSurname);
//
//    List<HibernateUser> findDistinctByUserNameLikeOrderByRoleAscUserIdDesc(String userName);
//
//
//
//    @Query("select u from HibernateUser u")
//    List<HibernateUser> findAllWightQuery();
//
//    @Query(value = "select u.* from HibernateUser u", nativeQuery = true)
//    List<HibernateUser> nativeQuery();
//
//    @Query("select u from HibernateUser u where u.userId > :lowerId")
//    List<HibernateUser> findUserWithLowerId(String lowerId);
//
//    @Query("select u.userId, r.roleId from HibernateUser u join HibernateRole r on.userId = r.user.userId > :lowerId")
//    List<Object[]> findUserIdAndRoleId(@Param("lowerId") String param);
//
//    @Query("select u.userName from HibernateUser u where u.userId > :lowerId")
//    List<String> findUsersNameWithLowerId (@Param("lowerId") String param);
//
//    @Query("select u.userId, u.email from HibernateUser u where u.userId > :lowerId")
//    List<Object[]> findUserEmaliWithLowerId(@Param("lowerId") String param);
//
//    @Modifying
//    @Query("update HibernateUser u set u = :u")
//    int updateUserPhoneNumber(@Param("mobilePhone") HibernateUser user);


    HibernateUser findByUserId(Long id);
}
