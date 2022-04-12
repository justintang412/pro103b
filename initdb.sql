CREATE TABLE `log_captcha` (
  `t` varchar(100) NOT NULL,
  `captcha` varchar(100),
  PRIMARY KEY (`t`)
);


INSERT INTO `log_captcha` VALUES ('1649576078954','w5dr8'),('1649576092447','dbefp'),('1649576167660','a2d24'),('1649576174387','88kba'),('1649577032034','chbww'),('1649577040469','p43hg'),('1649577048724','w37dg'),('1649590798858','hehwx'),('1649590875836','8php8'),('1649590892161','wwaeg'),('1649590997607','rykpw'),('1649591450323','5d33x'),('1649591480471','yeb7m'),('1649591943458','wfkwy'),('1649591952471','rpdr4'),('1649591953517','pmkan'),('1649591957742','k3gw2'),('1649591966624','nkg2w'),('1649592037556','cxrb2'),('1649592078018','re3wp'),('1649592201123','fmfg4'),('1649592244289','y7bfg'),('1649592590897','6apmp'),('1649592609960','kww6y'),('1649592664673','fkd7b'),('1649592679396','hb82m'),('1649593239107','4bg35'),('1649593342369','348er'),('1649593388885','p7ed6'),('1649593963714','5kycp'),('1649593975648','6xk2x'),('1649594025932','e3y8p'),('1649594026216','an3rr'),('1649594227321','ge868'),('1649594249950','y7445'),('1649594265908','wxmey'),('1649594560723','24hrm'),('1649594587468','8ahrb'),('1649594721731','wb6e3'),('1649594943995','harc6'),('1649594964525','exbhp'),('1649631719231','823nc'),('1649631734776','2hb4e'),('1649631771998','6yepw'),('1649631864938','4rae3'),('1649631880708','r2e7d'),('1649631889200','x233y'),('1649632110470','kc36r'),('1649635991887','w72wy'),('1649636153502','3ny3k'),('1649636164378','dhgxe'),('1649636243523','mgncm'),('1649636352381','pph42');



CREATE TABLE `m_right_role` (
  `role_id` varchar(100) NOT NULL,
  `right_id` varchar(100) NOT NULL,
  PRIMARY KEY (`role_id`,`right_id`)
);


CREATE TABLE `t_rights` (
  `right_id` varchar(100) NOT NULL,
  `right_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`right_id`)
);


CREATE TABLE `t_role` (
  `role_id` varchar(100) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
);



INSERT INTO `t_role` VALUES ('admin','admin'),('operator','operator'),('sysadmin','sysadmin'),('user','user');


CREATE TABLE `t_user` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `is_admin` varchar(100) DEFAULT NULL,
  `role_id` varchar(100) DEFAULT NULL,
  `is_active` varchar(100) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  `expire` bigint DEFAULT NULL,
  PRIMARY KEY (`username`)
);


INSERT INTO `t_user` VALUES ('admin','admin','true','sysadmin','true','0f395846-5612-4f89-9757-b46c5ca7f93f',1649679566139);

