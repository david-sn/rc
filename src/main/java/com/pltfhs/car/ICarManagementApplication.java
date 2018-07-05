package com.pltfhs.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ICarManagementApplication {

//    @Autowired
//    private UsersRepository usersRepository;
//    @Autowired
//    private UsersService usersService;
    public static void main(String[] args) {
        SpringApplication.run(ICarManagementApplication.class, args);
    }

//    @Override
//    public void run(String... strings) throws Exception {
//        Page<Users> users = usersRepository.findBySystemRolesSet_RoleIdAndIsDeletedFalse((short) 2, new PageRequest(0, 10, Sort.Direction.DESC, "createdDate"));
//        if (!users.hasContent()) {
//            usersService.addAdmin("Moustafa", "Salah", "m.salah@platformhouse.com", "01016566945", "pltP@$$w0rd3001", "1");
//        }
//    }
}
