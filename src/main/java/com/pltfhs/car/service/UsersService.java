package com.pltfhs.car.service;

import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.entity.SystemRoles;
import com.pltfhs.car.entity.Users;
import com.pltfhs.car.repository.SystemRolesRepository;
import com.pltfhs.car.repository.UsersRepository;
import com.pltfhs.car.util.MultimediaUtil;
import com.pltfhs.car.view.SocialReponse;
import io.jsonwebtoken.Jwts;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private SystemRolesRepository systemRolesRepository;
    private static final String fbURL = "https://graph.facebook.com/me?fields=id,first_name,last_name,email&access_token=";
    private static final String googleURL = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=";

//    @Autowired
//    private AuthorizationServerEndpointsConfiguration configuration;
    public Map<String, Object> registerUser(String firstName, String lastname, String email, String mobileToken, String mobile, String password, String language) {
        GeneralResponse< Map<String, Object>> response;

        Map<String, Object> result = new HashMap<>();
        Users userDB = usersRepository.findByEmail(email);
        if (userDB == null) {
            List<SystemRoles> systemRoles = new ArrayList<>();
            systemRoles.add(systemRolesRepository.findOne((short) 1));
            ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
            password = passwordEncoder.encodePassword(password, null);

            Users users = new Users(null, firstName, lastname, email,
                    password, new Date(), language, systemRoles
            );
            if (mobileToken != null) {
                users.setMobileToken(mobileToken);
            }
            if (mobile != null) {
                users.setMobile(mobile);
            }
            users.setUserType("Customer");
            users.setIsDeleted(false);
            usersRepository.save(users);

            String token = Jwts.builder().setSubject(email).claim("roles", users.getSystemRolesSet()).setIssuedAt(new Date())
                    .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "secretkey").compact();
            result.put("token", token);
            result.put("type", "Bearer");
            users.setPassword("PROTECTED");
            result.put("user", users);
            result.put("statusCode", StatusCode.OK);
            // response = new GeneralResponse<>(StatusCode.OK, result);
        } else {
            // response = new GeneralResponse<>(StatusCode.EMAIL_EXIST);
            result.put("statusCode", StatusCode.EMAIL_EXIST);
        }
        return result;
    }

    public GeneralResponse<Users> getUserInfo(String language, String emailAddress) {
        GeneralResponse<Users> response;
        Users userDB = usersRepository.findByEmail(emailAddress);
        if (userDB != null) {
            response = new GeneralResponse<>(StatusCode.OK, userDB);
        } else {
            response = new GeneralResponse<>(StatusCode.USER_NOT_FOUND);
        }
        return response;
    }

    // add admin function
    public GeneralResponse<Map<String, Object>> addAdmin(String firstName, String lastname, String email, String mobile, String password, String language) {
        GeneralResponse< Map<String, Object>> response;
        Users userDB = usersRepository.findByEmail(email);
        if (userDB == null) {
            List<SystemRoles> systemRoles = new ArrayList<>();
//            systemRoles.add(systemRolesRepository.findOne((short) 1));
            systemRoles.add(systemRolesRepository.findOne((short) 2));//admin

            ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
            password = passwordEncoder.encodePassword(password, null);

            Users users = new Users(null, firstName, lastname, email,
                    password, new Date(), language, systemRoles
            );
            if (mobile != null) {
                users.setMobile(mobile);
            }
            usersRepository.save(users);

            Map<String, Object> result = new HashMap<>();
            String token = Jwts.builder().setSubject(email).claim("roles", users.getSystemRolesSet()).setIssuedAt(new Date())
                    .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "secretkey").compact();
            result.put("token", token);
            result.put("type", "bearer");
            result.put("user", users);

            response = new GeneralResponse<>(StatusCode.OK, result);
        } else {
            response = new GeneralResponse<>(StatusCode.EMAIL_EXIST);
        }
        return response;
    }

    public GeneralResponse<List<Users>> findAllAdmins(int page, int noOfRows) {
        GeneralResponse<List<Users>> response;
        Page<Users> users = usersRepository.findBySystemRolesSet_RoleIdAndIsDeletedFalse((short) 2, new PageRequest(page - 1, noOfRows, Sort.Direction.DESC, "createdDate"));
        if (!users.hasContent()) {
            response = new GeneralResponse<>(StatusCode.OK, users.getContent());
            response.setTotalCount(users.getTotalElements());
            return response;
        } else {
            response = new GeneralResponse<>(StatusCode.ZERO_RESULTS);
            return response;
        }
    }

    public GeneralResponse<List<Users>> findAllUsers(int page, int noOfRows) {
        GeneralResponse<List<Users>> response;
        Page<Users> users = usersRepository.findBySystemRolesSet_RoleIdAndIsDeletedFalse((short) 1, new PageRequest(page - 1, noOfRows, Sort.Direction.DESC, "createdDate"));
        if (!users.hasContent()) {
            response = new GeneralResponse<>(StatusCode.OK, users.getContent());
            response.setTotalCount(users.getTotalElements());
            return response;
        } else {
            response = new GeneralResponse<>(StatusCode.ZERO_RESULTS);
            return response;
        }
    }

    public GeneralResponse updateUser(String emailAddress, Users user) {
        Users userDB = usersRepository.findByEmail(emailAddress);
        if (userDB != null) {
            if (user.getFirstName() != null) {
                userDB.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                userDB.setLastName(user.getLastName());
            }
            if (user.getUserPhone() != null) {
                userDB.setUserPhone(user.getUserPhone());
            }

            if (user.getMobile() != null) {
                userDB.setMobile(user.getMobile());
            }

            userDB.setUserLanguage(user.getUserLanguage());
            usersRepository.saveAndFlush(userDB);
            return new GeneralResponse<>(StatusCode.OK);
        } else {
            return new GeneralResponse<>(StatusCode.USER_NOT_FOUND);
        }
    }

    public GeneralResponse<Users> findByUserId(long userId) {

        Users user = usersRepository.findOne(userId);
        if (user != null) {
            return new GeneralResponse<>(StatusCode.OK, user);
        } else {
            return new GeneralResponse<>(StatusCode.USER_NOT_FOUND);
        }
    }

    public GeneralResponse changePassword(String emailAddress, String oldPassword, String newPassword) {
        GeneralResponse response;
        Users userDB = usersRepository.findByEmail(emailAddress);
        ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
        oldPassword = passwordEncoder.encodePassword(oldPassword, null);
        if (oldPassword.equals(userDB.getPassword())) {
            userDB.setPassword(passwordEncoder.encodePassword(newPassword, null));
            usersRepository.saveAndFlush(userDB);
            response = new GeneralResponse(StatusCode.OK);
        } else {
            response = new GeneralResponse(StatusCode.PASSWORD_NOT_MATCH);
        }
        return response;
    }

    public GeneralResponse changeProfilePicture(String emailAddress, MultipartFile profilePic) {
        Users userDB = usersRepository.findByEmail(emailAddress);
        userDB.setProfilePic(MultimediaUtil.uploadPhoto(profilePic, "profile"));
        usersRepository.saveAndFlush(userDB);
        userDB.setPassword("PROTECTED");
        return new GeneralResponse(StatusCode.OK, userDB);
    }

    public GeneralResponse deleteUser(Long id) {
        Users userDB = usersRepository.findOne(id);
        if (userDB != null) {
            userDB.setIsDeleted(true);
            usersRepository.saveAndFlush(userDB);
            return new GeneralResponse<>(StatusCode.OK);
        } else {
            return new GeneralResponse<>(StatusCode.USER_NOT_FOUND);
        }

    }

    public Map<String, Object> loginFaceBook(String socialToken) {
        RestTemplate rest = new RestTemplate();
        SocialReponse facebookUser = rest.getForObject(fbURL + socialToken, SocialReponse.class);
        System.out.println(facebookUser);

        Map<String, Object> result = new HashMap<>();
        if (facebookUser.getId() != null) {
            Users user = usersRepository.findBySocialId(facebookUser.getId());
            if (user == null) {
                user = new Users();
                user.setSocialId(facebookUser.getId());
                user.setFirstName(facebookUser.getFirst_name());
                user.setLastName(facebookUser.getLast_name());
                //generate random password;
                ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
                String userPassword = passwordEncoder.encodePassword(facebookUser.getId(), null);
                user.setPassword(userPassword);
                user.setCreatedDate(new Date());
                user.setIsSocial(true);
                if (facebookUser.getEmail() != null) {
                    user.setEmail(facebookUser.getEmail());
                } else {
                    user.setEmail(facebookUser.getId());
                }
                if (facebookUser.getPhone() != null) {
                    user.setMobile(facebookUser.getPhone());
                }
                List<SystemRoles> roles = new ArrayList<>();
                roles.add(systemRolesRepository.findOne((short) 1));
                user.setSystemRolesSet(roles);

                usersRepository.saveAndFlush(user);
            }

            String token = Jwts.builder().setSubject(user.getEmail()).claim("roles", user.getSystemRolesSet()).setIssuedAt(new Date())
                    .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "secretkey").compact();
            result.put("token", token);
            result.put("type", "Bearer");
            user.setPassword("PROTECTED");
            result.put("user", user);
            result.put("statusCode", StatusCode.OK);
        } else {
            result.put("statusCode", StatusCode.EMAIL_EXIST);
        }
        return result;
    }

    public Map<String, Object> loginGoogle(String socialToken) {
        RestTemplate rest = new RestTemplate();
        SocialReponse googleUser = rest.getForObject(googleURL + socialToken, SocialReponse.class);
        System.out.println(googleUser);

        Map<String, Object> result = new HashMap<>();
        if (googleUser.getId() != null) {
            Users user = usersRepository.findBySocialId(googleUser.getId());
            if (user == null) {
                user = new Users();
                user.setSocialId(googleUser.getId());
                user.setFirstName(googleUser.getGiven_name());
                user.setLastName(googleUser.getFamily_name());
                user.setProfilePic(googleUser.getPicture());

                //generate random password;
                ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
                String userPassword = passwordEncoder.encodePassword(googleUser.getId(), null);
                user.setPassword(userPassword);
                user.setCreatedDate(new Date());
                user.setIsSocial(true);
                if (googleUser.getEmail() != null) {
                    user.setEmail(googleUser.getEmail());
                } else {
                    user.setEmail(googleUser.getId());
                }
                if (googleUser.getPhone() != null) {
                    user.setMobile(googleUser.getPhone());
                }
                List<SystemRoles> roles = new ArrayList<>();
                roles.add(systemRolesRepository.findOne((short) 1));
                user.setSystemRolesSet(roles);

                usersRepository.saveAndFlush(user);
            }

            String token = Jwts.builder().setSubject(user.getEmail()).claim("roles", user.getSystemRolesSet()).setIssuedAt(new Date())
                    .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "secretkey").compact();
            result.put("token", token);
            result.put("type", "Bearer");
            user.setPassword("PROTECTED");
            result.put("user", user);
            result.put("statusCode", StatusCode.OK);
        } else {
            result.put("statusCode", StatusCode.EMAIL_EXIST);
        }
        return result;
    }
}
