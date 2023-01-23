/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopping.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.shopping.entity.User;
import com.shopping.exception.BadRequestException;
import com.shopping.exception.NotFoundException;
import com.shopping.form.LoginForm;
import com.innovaturelabs.training.contacts.form.TokenRefreshForm;
import com.shopping.form.UserForm;
import com.shopping.form.GoogleLoginForm;
import com.shopping.form.PasswordChangeForm;
import com.shopping.form.ProfileUpdatesForm;
import com.shopping.repository.UserRepository;
import static com.shopping.security.AccessTokenUserDetailsService.PURPOSE_ACCESS_TOKEN;
import com.shopping.security.config.SecurityConfig;
import com.shopping.security.util.InvalidTokenException;
import com.shopping.security.util.SecurityUtil;
import com.shopping.security.util.SignInWithGoogleUtils;
import com.shopping.security.util.TokenExpiredException;
import com.shopping.security.util.TokenGenerator;
import com.shopping.security.util.TokenGenerator.Status;
import com.shopping.security.util.TokenGenerator.Token;
import com.shopping.service.UserService;
import com.shopping.util.AppleLoginUtil;
import com.shopping.util.IdTokenPayload;
import com.shopping.util.LanguageUtil;
import com.shopping.util.Pager;
import com.shopping.view.LoginView;
import com.shopping.view.UserView;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author nirmal
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";
    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private SignInWithGoogleUtils signInWithGoogleUtils;

    @Autowired
    private LanguageUtil languageUtil;

    @Autowired
    private AppleLoginUtil appleLoginUtil;

    @PersistenceContext
    @Autowired
    private EntityManager em;

    @Override
    public UserView add(UserForm form) {

        Date date = null;
        if (form.getDob() != null) {
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(form.getDob());
            } catch (ParseException ex) {
                log.error("ParseException");
            }

        }

        if (userRepository.findByEmailAndType(form.getEmail(), User.Type.GMAIL.value).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, languageUtil.getTranslatedText("email.already.exists", null, "en"));
        }

        return new UserView(userRepository.save(new User(
                form.getName(),
                form.getEmail(),
                passwordEncoder.encode(form.getPassword()),
                date
        )));
    }

    @Override
    public UserView currentUser() {
        return new UserView(
                userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public LoginView login(LoginForm form, Errors errors) throws BadRequestException {
        if (errors.hasErrors()) {
            throw badRequestException();
        }
        User user = userRepository.findByEmailAndType(form.getEmail(), User.Type.GMAIL.value).orElseThrow(UserServiceImpl::badRequestException);
        if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            throw badRequestException();
        }

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + user.getPassword(), securityConfig.getRefreshTokenExpiry());
        return new LoginView(user, accessToken, refreshToken);
    }

    @Override
    public LoginView refresh(TokenRefreshForm form) throws BadRequestException {
        Status status;

        try {
            status = tokenGenerator.verify(PURPOSE_REFRESH_TOKEN, form.getRefreshToken());
        } catch (InvalidTokenException e) {
            throw new BadRequestException("Invalid token", e);
        } catch (TokenExpiredException e) {
            throw new BadRequestException("Token expired", e);
        }

        int userId;
        try {
            userId = Integer.parseInt(status.data.substring(0, 10));
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid token", e);
        }

        String password = status.data.substring(10);

        User user = userRepository.findByUserIdAndPassword(userId, password).orElseThrow(UserServiceImpl::badRequestException);

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        return new LoginView(
                user,
                new LoginView.TokenView(accessToken.value, accessToken.expiry),
                new LoginView.TokenView(form.getRefreshToken(), status.expiry)
        );
    }

    private static BadRequestException badRequestException() {
        return new BadRequestException("Invalid credentials");
    }

    @Override
    public Collection<User> list() {
        return userRepository.findAll();
    }

    public void delete(Integer userId) throws NotFoundException {
        userRepository.delete(userRepository.findById(userId)
                .orElseThrow(NotFoundException::new));

    }

    @Override
    @Transactional
    public UserView update(Integer userId, ProfileUpdatesForm form) throws NotFoundException {

        return userRepository.findById(userId)
                .map((user) -> {
                    Date date = null;
                    try {
                        date = (form.getDob() != null) ? new SimpleDateFormat("yyyy-MM-dd").parse(form.getDob()) : null;
                    } catch (ParseException ex) {
                        log.error("ParseException");
                    }
                    user.setName(form.getName());
                    user.setDob(date);
                    user.setUpdateDate(new Date());
                    return new UserView(userRepository.save(user));
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    public UserView get(Integer userId) throws NotFoundException {
        return userRepository.findById(userId)
                .map((user) -> {
                    return new UserView(user);
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    public LoginView signInWithGoogle(GoogleLoginForm form) {
        String email = null;
        String password = null;
        try {

            Integer type=(form.getType()==null)?0:form.getType();
            Payload payload = signInWithGoogleUtils.SigninwithGoogle(form,type);

            if (payload != null) {

                // Print user identifier
                password = payload.getSubject();
                String name = (String) payload.get("name");
                System.out.println("User ID: " + password);

                // Get profile information from payload
                String email1 = payload.getEmail();

                User user1 = userRepository.findByEmailAndType(email1, User.Type.GOOGLE.value).orElse(null);
                if (user1 != null) {
                    email = user1.getEmail();
                } else {
                    //user register
                    System.out.println("else");
                    user1 = userRepository.save(new User(
                            name,
                            email1,
                            passwordEncoder.encode(password),
                            User.Type.GOOGLE.value
                    ));
                    email = user1.getEmail();
                }

            } else {
                log.error("invalid id token");
                throw new BadRequestException(languageUtil.getTranslatedText("invalid.id.token", null, "en"));
            }
        } catch (GeneralSecurityException ex) {
            log.error("GeneralSecurityException");
        } catch (IOException ex) {
            log.error("IOException");
        }

        User user = userRepository.findByEmailAndType(email, User.Type.GOOGLE.value).orElseThrow(UserServiceImpl::badRequestException);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw badRequestException();
        }

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + user.getPassword(), securityConfig.getRefreshTokenExpiry());
        return new LoginView(user, accessToken, refreshToken);

    }

    @Override
    public void changePassword(PasswordChangeForm form) {

        User currentUser = userRepository.findById(SecurityUtil.getCurrentUserId()).orElse(null);

        if (currentUser != null) {
            if (!passwordEncoder.matches(form.getCurrentPassword(), currentUser.getPassword())) {
                throw new BadRequestException(languageUtil.getTranslatedText("old.password.incorrect", null, "en"));
            }

            if (!Objects.equals(form.getNewPassword(), form.getConfirmPassword())) {
                throw new BadRequestException(languageUtil.getTranslatedText("user.password.not.match", null, "en"));
            }

            if (passwordEncoder.matches(form.getNewPassword(), currentUser.getPassword())) {
                throw new BadRequestException(languageUtil.getTranslatedText("password.already.used", null, "en"));
            }

            userRepository.save(currentUser.changePassword(passwordEncoder.encode(form.getNewPassword())));
            log.info("User password updated: user ID - " + currentUser.getUserId());

        }

    }

    @Override
    public LoginView signInWithApple(GoogleLoginForm form) {
        String email = null;
        String password = null;
        try {

            IdTokenPayload idTokenPayload = appleLoginUtil.appleAuth(form.getIdToken(), false);
            if (idTokenPayload != null) {

                // Print user identifier
                password = idTokenPayload.getSub();
                String name = idTokenPayload.getAud();
                System.out.println("User ID: " + password);

                // Get profile information from payload
                String email1 = idTokenPayload.getEmail();

                User user1 = userRepository.findByEmailAndType(email1, User.Type.APPLE.value).orElse(null);
                if (user1 != null) {
                    email = user1.getEmail();
                } else {
                    System.out.println("else");
                    user1 = userRepository.save(new User(
                            name,
                            email1,
                            passwordEncoder.encode(password),
                            User.Type.APPLE.value
                    ));
                    email = user1.getEmail();
                }

            } else {
                log.error("invalid id token");
                throw new BadRequestException(languageUtil.getTranslatedText("invalid.id.token", null, "en"));
            }
        } catch (GeneralSecurityException ex) {
            log.error("GeneralSecurityException", ex);
        } catch (IOException ex) {
            log.error("IOException", ex);
        } catch (Exception ex) {
            log.error("Exception", ex);
        }

        User user = userRepository.findByEmailAndType(email, User.Type.APPLE.value).orElseThrow(UserServiceImpl::badRequestException);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw badRequestException();
        }

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + user.getPassword(), securityConfig.getRefreshTokenExpiry());
        return new LoginView(user, accessToken, refreshToken);
    }

    @Override
    public Pager<UserView> SearchUser(Integer peg, Integer lmt, String sort, boolean type, String lastId, String search) {

        Pager<UserView> uesrPager;
        List<UserView> userList;

        if (peg < 1) {
            throw new BadRequestException(languageUtil.getTranslatedText("page.count.invalid", null, "en"));
        }
        if (lmt < 1) {
            throw new BadRequestException(languageUtil.getTranslatedText("page.count.invalid", null, "en"));
        }

        Long count;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userroot = criteriaQuery.from(User.class);
        List<Predicate> predicate = new ArrayList<>();

        if (!StringUtils.isEmpty(search)) {
            Predicate predicateSearch = criteriaBuilder.like(userroot.get("user").get("name"), "%" + search + "%");
            predicate.add(predicateSearch);
            predicateSearch = criteriaBuilder.like(userroot.get("user").get("email"), "%" + search + "%");
            predicate.add(predicateSearch);

            DateFormat df = new SimpleDateFormat("mm-dd-yyyy");
            boolean flag = true;
            try {
                df.parse(search);
            } catch (Exception e) {
                //not a date
                flag = false;
            }
            if (flag == true) {
                predicateSearch = criteriaBuilder.equal(userroot.get("user").get("createDate"), search);
                predicate.add(predicateSearch);
            }
            if (lastId != null) {

                Predicate predicateLastid = criteriaBuilder.lessThan(userroot.get("userId"), Integer.parseInt(lastId));
                predicate.add(predicateLastid);
            }
        }
        if (lastId != null) {

            Predicate predicateLastid = criteriaBuilder.lessThan(userroot.get("userId"), Integer.parseInt(lastId));
            predicate.add(predicateLastid);
        }
        Predicate And = criteriaBuilder.or(predicate.toArray(Predicate[]::new));
        criteriaQuery.where(And);
        if (sort != null) {
            if (orderFieldExists(sort)) {
                if (type) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(userroot.get(sort)));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(userroot.get(sort)));
                }

            } else {
                throw new BadRequestException(languageUtil.getTranslatedText("item.invalid.sort", null, "en"));
            }
        }
        TypedQuery<User> list = em.createQuery(criteriaQuery);
        list.setFirstResult((peg - 1) * lmt);
        list.setMaxResults(lmt);
        CriteriaQuery<Long> cqCount = criteriaBuilder.createQuery(Long.class);
        Root<User> usercntqury = cqCount.from(criteriaQuery.getResultType());
        cqCount.where(And);
        cqCount.select(criteriaBuilder.count(usercntqury));
        count = em.createQuery(cqCount).getSingleResult();
        Page<User> result = new PageImpl<>(list.getResultList(),
                PageRequest.of(peg - 1, lmt), count);

        userList = StreamSupport
                .stream(result.spliterator(), false)
                .map(user -> new UserView(user)).collect(Collectors.toList());
        uesrPager = new Pager<>(lmt, count.intValue(), peg);
        uesrPager.setResult(userList);

        return uesrPager;
    }

    public static enum orderSortfields {
        CREATE_DATE("createDate"), UPDATE_DATE("updateDate"), USER_ID("userId"), NAME("name"), EMAIL("email");
        public String value;

        private orderSortfields(String value) {
            this.value = value;
        }
    }

    public static boolean orderFieldExists(String sort) {
        for (UserServiceImpl.orderSortfields c : UserServiceImpl.orderSortfields.values()) {
            if (c.value.equals(sort)) {
                return true;
            }
        }
        return false;
    }

}
