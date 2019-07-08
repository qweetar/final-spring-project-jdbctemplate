package ru.myfirstwebsite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.myfirstwebsite.domain.Role;
import ru.myfirstwebsite.domain.User;
import ru.myfirstwebsite.repository.RoleDao;
import ru.myfirstwebsite.repository.UserDao;

import java.util.List;

@Service(value = "userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userDao.findByLogin(username);
            List<Role> roles = roleDao.getRolesByUserId(user.getUserId());
            if(user.getUserId() == null) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            } else {
                return new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(roles.get(0).getRoleName())
                );
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("HibernateUser with this login not found");
        }
    }
}
