SET FOREIGN_KEY_CHECKS = 0;
SET SQL_SAFE_UPDATES = 0;

-- Roles Table
CREATE TABLE sys_roles (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           role_name VARCHAR(100) NOT NULL UNIQUE,
                           descriptions TEXT,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Features Table
CREATE TABLE sys_features (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              feature_name VARCHAR(100) NOT NULL,
                              feature_code VARCHAR(100) NOT NULL UNIQUE,
                              descriptions TEXT
);

-- Users Table
CREATE TABLE sys_users (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           username VARCHAR(100) NOT NULL UNIQUE,
                           password VARCHAR(255) NOT NULL,
                           email VARCHAR(150),
                           is_admin BOOLEAN DEFAULT FALSE,
                           login_token TEXT,
                           last_login_at DATETIME,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           role_id BIGINT,
                           FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Role-Feature-Permission Table
CREATE TABLE sys_role_feature_permission (
                                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                             role_id BIGINT,
                                             feature_id BIGINT,
                                             is_search BOOLEAN DEFAULT FALSE,
                                             is_add BOOLEAN DEFAULT FALSE,
                                             is_viewed BOOLEAN DEFAULT FALSE,
                                             is_edit BOOLEAN DEFAULT FALSE,
                                             is_deleted BOOLEAN DEFAULT FALSE,
                                             FOREIGN KEY (role_id) REFERENCES roles(id),
                                             FOREIGN KEY (feature_id) REFERENCES features(id),
                                             UNIQUE(role_id, feature_id)
);

CREATE TABLE sys_main_menu (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               main_menu_code VARCHAR(255) UNIQUE,
                               main_menu_name VARCHAR(250) NOT NULL UNIQUE,
                               route_path VARCHAR(250) NOT NULL,
                               main_menu_icon VARCHAR(250) NOT NULL,
                               orders INT NOT NULL,
                               parent_id BIGINT NOT NULL,
                               b_status BOOLEAN NOT NULL,
                               created_at VARCHAR(255) NOT NULL,
                               updated_at VARCHAR(255),
                               descriptions VARCHAR(500)
);

CREATE TABLE sys_role_menu_permissions (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           role_id BIGINT NOT NULL,
                                           main_menu_id BIGINT NOT NULL,
                                           b_status BOOLEAN,
                                           is_visible BOOLEAN,
                                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                           CONSTRAINT uq_role_menu UNIQUE (role_id, main_menu_id),
                                           CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES sys_roles(id),
                                           CONSTRAINT fk_menu FOREIGN KEY (main_menu_id) REFERENCES sys_main_menu(id)
);

CREATE TABLE sys_reports (
                             `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                             `report_code` VARCHAR(255) UNIQUE,
                             `report_name` VARCHAR(250) NOT NULL UNIQUE,
                             `report_type` VARCHAR(250),
                             `route_path` VARCHAR(250),
                             `report_icon` VARCHAR(250) NOT NULL,
                             `display_order` INT NOT NULL,
                             `parent_id` BIGINT,
                             `is_viewed` BOOLEAN NOT NULL,
                             `is_export` BOOLEAN NOT NULL,
                             `b_status` BOOLEAN NOT NULL,
                             `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
                             `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             `descriptions` VARCHAR(500)
);

CREATE TABLE sys_role_report_permissions (
                                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                             role_id BIGINT,
                                             report_id BIGINT,
                                             is_search BOOLEAN DEFAULT FALSE,
                                             is_viewed BOOLEAN DEFAULT FALSE,
                                             is_export BOOLEAN DEFAULT FALSE
);

commit;
SET FOREIGN_KEY_CHECKS = 1;
SET SQL_SAFE_UPDATES = 1;
