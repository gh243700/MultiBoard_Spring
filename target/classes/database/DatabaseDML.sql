INSERT INTO member(id,username,password,email) VALUES(100,'admin','admin','admin@gamil.com');
INSERT INTO member(id,username,password,email) VALUES(101,'kiesep','mamber101','member101@gamil.com');
INSERT INTO member(id,username,password,email) VALUES(102,'Nobrains','admin102','member102@gamil.com');

INSERT INTO category(id,title) VALUES (1,'The Hub');
INSERT INTO category(id,title) VALUES (2,'Computer Hardware');
INSERT INTO category(id,title) VALUES (3,'The Workbench');

INSERT INTO discussion(id,title,description,category_id,total_post_count) values (1,'General Discussion','Come hang out and discuss tech related content! Please check other subforums before posting.',1,0);
INSERT INTO discussion(id,title,description,category_id,total_post_count) values (2,'Tech News','News and reviews from all around the web!',1,0);
INSERT INTO discussion(id,title,description,category_id,total_post_count) values (3,'Off Topic','Discussion - Non-tech related content only please! New members can say Hi here.',1,0);

INSERT INTO discussion(id,title,description,category_id,total_post_count) values (4,'Graphics Cards','General Discussion, Overclocking, and Troubleshooting.',2,0);
INSERT INTO discussion(id,title,description,category_id,total_post_count) values (5,'CPUs, Motherboards, and Memory','General Discussion, Overclocking, and Troubleshooting',2,0);
INSERT INTO discussion(id,title,description,category_id,total_post_count) values (6,'Cases and Power Supplies','Discussion - Non-tech related content only please! New members can say Hi here.',2,0);

INSERT INTO discussion(id,title,description,category_id,total_post_count) values (7,'Build Logs','Show off your rig!',3,0);
INSERT INTO discussion(id,title,description,category_id,total_post_count) values (8,'New Builds and Planning','Build Planning Help, Appraisals, Tips and Tricks',3,0);
INSERT INTO discussion(id,title,description,category_id,total_post_count) values (9,'Troubleshooting','Help... I need somebody...help...not just anybody...',3,0);
INSERT INTO discussion(id,title,description,category_id,total_post_count) values (10,'Case Modding and Other Mods',null,3,0);


