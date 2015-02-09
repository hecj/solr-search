/*lz_role*/
INSERT INTO lz_role VALUES ('P4275014', '2015-01-18 19:43:15', '超级管理员', '2015-01-18 20:07:59');
INSERT INTO lz_role VALUES ('P9495696', '2015-01-18 19:51:03', '普通角色', '2015-01-18 19:51:03');
/*lz_user*/
INSERT INTO lz_user VALUES ('hecj', '2015-01-18 19:37:44', '275070023@qq.com', null, '0cbc3f8fd0b1a8b5e6777cd9403d77b7', '15811372713', '2015-01-18 19:43:40', '何超杰', 'P4275014');
/*lz_module*/
INSERT INTO lz_module VALUES ('0', null, null, '0', '根节点', 'root', 'open', '0', null);
INSERT INTO lz_module VALUES ('0001', null, 'tree-file', '0', '基本菜单', '0', 'open', null, '');
INSERT INTO lz_module VALUES ('0001001', null, 'tree-file', '1', '用户管理', '0001', 'open', null, 'admin/jsp/user/userManager.jsp');
INSERT INTO lz_module VALUES ('0001002', null, 'tree-file', '1', '模块管理', '0001', 'open', null, 'admin/jsp/module/modelemanager.jsp');
INSERT INTO lz_module VALUES ('0001003', null, 'tree-file', '1', '角色管理', '0001', 'open', null, 'admin/jsp/role/rolemanager.jsp');
INSERT INTO lz_module VALUES ('0001004', null, 'tree-file', '1', '数据搜索管理', '0001', 'open', null, 'admin/jsp/datacollect/dataCollectManager.jsp');
/*lz_role_module*/
INSERT INTO lz_role_module VALUES ('14225834253222151536', '0', 'P4275014');
INSERT INTO lz_role_module VALUES ('14225834253232560628', '0001', 'P4275014');
INSERT INTO lz_role_module VALUES ('14225834253248742860', '0001003', 'P4275014');
INSERT INTO lz_role_module VALUES ('14225834253254176999', '0001001', 'P4275014');
INSERT INTO lz_role_module VALUES ('14225834253268436107', '0001002', 'P4275014');
INSERT INTO lz_role_module VALUES ('14225834253270057951', '0001004', 'P4275014');
INSERT INTO lz_role_module VALUES ('14225834253280923844', '0', 'P4275014');
INSERT INTO lz_role_module VALUES ('14225834253282593016', '0001', 'P4275014');
INSERT INTO lz_role_module VALUES ('14225834253270057952', 'editRoleSub', 'P4275014');


INSERT INTO lz_module VALUES ('addBrotherNodeSumbit', null, '', null, '添加兄节点', null, null, '1', '');
INSERT INTO lz_module VALUES ('addChildNodeSumbit', null, '', null, '添加子节点', null, null, '1', '');
INSERT INTO lz_module VALUES ('addRadioSub', null, '', null, '添加按钮', null, null, '1', '');
INSERT INTO lz_module VALUES ('addRole', null, '', null, '添加角色', null, null, '1', '');
INSERT INTO lz_module VALUES ('addUserSub', null, '', null, '添加用户', null, null, '1', '');
INSERT INTO lz_module VALUES ('deleteNode', null, '', null, '删除节点', null, null, '1', '');
INSERT INTO lz_module VALUES ('deleteRole', null, '', null, '删除角色', null, null, '1', '');
INSERT INTO lz_module VALUES ('deleteUser', null, '', null, '删除用户', null, null, '1', '');
INSERT INTO lz_module VALUES ('delRadioSub', null, '', null, '删除按钮', null, null, '1', '');
INSERT INTO lz_module VALUES ('editNodeSumbit', null, '', null, '编辑节点', null, null, '1', '');
INSERT INTO lz_module VALUES ('editRadioSub', null, '', null, '修改按钮', null, null, '1', '');
INSERT INTO lz_module VALUES ('editRoleSub', null, '', null, '编辑角色', null, null, '1', '');
INSERT INTO lz_module VALUES ('editUserSub', null, '', null, '编辑用户', null, null, '1', '');
