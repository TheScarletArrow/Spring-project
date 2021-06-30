package ru.scarletarrow.bootmap.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.scarletarrow.bootmap.security.ApplicationUserRole;

import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeAppDaoService implements UserDAO {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeAppDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AppUser> selectAppUserByUserName(String username) {
        return getAppUsers().stream()
                .filter(appUser -> username.equals(appUser.getUsername()))
                .findFirst();
    }

    private List<AppUser> getAppUsers() {
        List<AppUser> appUsers = Lists.newArrayList(
                new AppUser(
                        "admin",
                        passwordEncoder.encode("admin"),
                        ApplicationUserRole.ADMIN.getGrantedAuthorities(),
                        true,
                        true, true, true
                ),
                new AppUser(
                        "trainee",
                        passwordEncoder.encode("admin"),
                        ApplicationUserRole.ADMIN_TRAINEE.getGrantedAuthorities(),
                        true,
                        true, true, true
                ),
                new AppUser(
                        "anton",
                        passwordEncoder.encode("anton"),
                        ApplicationUserRole.USER.getGrantedAuthorities(),
                        true,
                        true, true, true
                )
        );

        return appUsers;
    }
}
