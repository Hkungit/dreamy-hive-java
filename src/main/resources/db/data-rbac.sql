-- 添加默认角色
INSERT INTO `role` (`name`, `code`, `description`, `status`) VALUES
('超级管理员', 'super_admin', '系统超级管理员，拥有所有权限', 1),
('管理员', 'admin', '系统管理员，拥有大部分管理权限', 1),
('普通用户', 'user', '普通用户，拥有基本功能权限', 1);

-- 添加默认权限
-- 用户管理权限
INSERT INTO `permission` (`name`, `code`, `description`, `status`) VALUES
('用户查看', 'user:view', '查看用户列表和详情的权限', 1),
('用户创建', 'user:create', '创建用户的权限', 1),
('用户编辑', 'user:edit', '编辑用户信息的权限', 1),
('用户删除', 'user:delete', '删除用户的权限', 1);

-- 角色管理权限
INSERT INTO `permission` (`name`, `code`, `description`, `status`) VALUES
('角色查看', 'role:view', '查看角色列表和详情的权限', 1),
('角色创建', 'role:create', '创建角色的权限', 1),
('角色编辑', 'role:edit', '编辑角色信息的权限', 1),
('角色删除', 'role:delete', '删除角色的权限', 1),
('角色授权', 'role:assign', '为角色分配权限的权限', 1);

-- 权限管理权限
INSERT INTO `permission` (`name`, `code`, `description`, `status`) VALUES
('权限查看', 'permission:view', '查看权限列表和详情的权限', 1),
('权限创建', 'permission:create', '创建权限的权限', 1),
('权限编辑', 'permission:edit', '编辑权限信息的权限', 1),
('权限删除', 'permission:delete', '删除权限的权限', 1);

-- 内容管理权限
INSERT INTO `permission` (`name`, `code`, `description`, `status`) VALUES
('内容查看', 'content:view', '查看内容列表和详情的权限', 1),
('内容创建', 'content:create', '创建内容的权限', 1),
('内容编辑', 'content:edit', '编辑内容信息的权限', 1),
('内容删除', 'content:delete', '删除内容的权限', 1),
('内容审核', 'content:audit', '审核内容的权限', 1);

-- 系统管理权限
INSERT INTO `permission` (`name`, `code`, `description`, `status`) VALUES
('系统设置', 'system:setting', '管理系统设置的权限', 1),
('系统监控', 'system:monitor', '查看系统监控信息的权限', 1),
('系统日志', 'system:log', '查看系统日志的权限', 1);

-- 为超级管理员分配所有权限
INSERT INTO `role_permission` (`role_id`, `permission_id`)
SELECT 1, id FROM `permission`;

-- 为管理员分配部分权限（除了系统级权限）
INSERT INTO `role_permission` (`role_id`, `permission_id`)
SELECT 2, id FROM `permission` WHERE `code` NOT LIKE 'system:%';

-- 为普通用户分配基本权限
INSERT INTO `role_permission` (`role_id`, `permission_id`)
SELECT 3, id FROM `permission` WHERE `code` IN ('user:view', 'content:view', 'content:create', 'content:edit');

-- 设置用户ID为1的用户为超级管理员
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 1); 