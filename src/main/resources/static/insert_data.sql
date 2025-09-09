SET FOREIGN_KEY_CHECKS = 0;
SET SQL_SAFE_UPDATES = 0;

INSERT INTO sys_roles (role_name, descriptions) VALUES
                                                    ('Admin', 'Full access'),
                                                    ('User', 'Limited access');

INSERT INTO sys_features (feature_name, feature_code, descriptions) VALUES
                                                                        ('User Management', 'USER_MGMT', 'Manage users'),
                                                                        ('Role Management', 'ROLE_MGMT', 'Manage roles');

-- You can assign permissions like this:
INSERT INTO sys_role_feature_permission (role_id, feature_id, is_search, is_add, is_viewed, is_edit, is_deleted)
VALUES
    (1, 1, TRUE, TRUE, TRUE, TRUE, TRUE), -- Admin can do all
    (2, 1, TRUE, FALSE, TRUE, FALSE, FALSE); -- User limited



INSERT INTO sys_reports (report_code,report_name,report_type,route_path,report_icon,display_order,parent_id,b_status,created_at,updated_at,descriptions,is_export,is_viewed)
SELECT code,name,substr(code,1,3) report_type,route_path,icon,display_order,parent_id,b_status,now(),now(),description,true,true from sys_features where id in (5,6,7,8,9,10,11);
UPDATE sys_reports SET parent_id = NULL;

INSERT INTO sys_reports (report_code,report_name,report_type,route_path,report_icon,display_order,parent_id,b_status,created_at,updated_at,descriptions,is_export,is_viewed)
SELECT '00001','Loan Outstanding','OPT_LN' report_type,route_path,icon,display_order,'1' parent_id,b_status,now(),now(),description,true,true from sys_features where id = 1;


commit;
SET FOREIGN_KEY_CHECKS = 0;
SET SQL_SAFE_UPDATES = 0;