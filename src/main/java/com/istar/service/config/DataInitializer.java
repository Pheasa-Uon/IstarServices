//package com.istar.service.config;
//
//import com.istar.service.entity.administrator.feature.Feature;
//import com.istar.service.entity.administrator.usersmanagement.permission.Role;
//import com.istar.service.entity.administrator.usersmanagement.permission.RoleFeaturePermission;
//import com.istar.service.entity.administrator.usersmanagement.user.User;
//import com.istar.service.repository.administrator.usersmanagement.permission.FeatureRepository;
//import com.istar.service.repository.administrator.usersmanagement.permission.RoleFeaturePermissionRepository;
//import com.istar.service.repository.administrator.usersmanagement.permission.RoleRepository;
//import com.istar.service.repository.administrator.usersmanagement.user.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class DataInitializer {
//    @Bean
//    CommandLineRunner initData(FeatureRepository featureRepo,
//                               RoleRepository roleRepo,
//                               UserRepository userRepo,
//                               RoleFeaturePermissionRepository rfpRepo,
//                               PasswordEncoder encoder) {
//        return args -> {
//            Feature customer = featureRepo.findByCode("CUSTOMER").orElseGet(() -> {
//                Feature f = new Feature();
//                f.setCode("CUSTOMER"); f.setName("Customer Management"); return featureRepo.save(f);
//            });
//
//            Role admin = roleRepo.findByRolesCode("ADMIN").orElseGet(() -> {
//                Role r = new Role(); r.setRolesCode("ADMIN"); r.setName("Administrator"); return roleRepo.save(r);
//            });
//            Role user = roleRepo.findByRolesCode("USER").orElseGet(() -> {
//                Role r = new Role(); r.setRolesCode("USER"); r.setName("Normal User"); return roleRepo.save(r);
//            });
//
//            User uAdmin = userRepo.findByUsername("admin").orElseGet(() -> {
//                User u = new User();
//                u.setUsername("admin");
//                u.setPassword(encoder.encode("P@ssw0rd"));
//                u.getRoles().add(admin);
//                return userRepo.save(u);
//            });
//            if (!uAdmin.getRoles().contains(admin)) { uAdmin.getRoles().add(admin); userRepo.save(uAdmin); }
//
//            User uUser = userRepo.findByUsername("user").orElseGet(() -> {
//                User u = new User();
//                u.setUsername("user");
//                u.setPassword(encoder.encode("user123"));
//                u.getRoles().add(user);
//                return userRepo.save(u);
//            });
//            if (!uUser.getRoles().contains(user)) { uUser.getRoles().add(user); userRepo.save(uUser); }
//
//            if (rfpRepo.findAll().stream().noneMatch(r -> r.getRole().getRolesCode().equals("ADMIN") && r.getFeature().getCode().equals("CUSTOMER"))) {
//                RoleFeaturePermission r = new RoleFeaturePermission();
//                r.setRole(admin); r.setFeature(customer);
//                r.setIsViewed(true); r.setIsAdd(true); r.setIsEdit(true); r.setIsSearch(true); r.setIsDeleted(true);
//                rfpRepo.save(r);
//            }
//
//            if (rfpRepo.findAll().stream().noneMatch(r -> r.getRole().getRolesCode().equals("USER") && r.getFeature().getCode().equals("CUSTOMER"))) {
//                RoleFeaturePermission r = new RoleFeaturePermission();
//                r.setRole(user); r.setFeature(customer);
//                r.setIsViewed(true); r.setIsAdd(false); r.setIsEdit(false); r.setIsSearch(true); r.setIsDeleted(false);
//                rfpRepo.save(r);
//            }
//        };
//    }
//}
