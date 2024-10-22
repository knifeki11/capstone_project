INSERT INTO "user" (id, username, firstname, lastname, password, email, phone, tenant_id, active, created_date, last_modified_date) VALUES
(1, 'user1', 'John', 'Doe', 'passwordHash1', 'john.doe@example.com', '1234567890', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'user2', 'Jane', 'Smith', 'passwordHash2', 'jane.smith@example.com', '2345678901', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
3, 'user3', 'Robert', 'Brown', 'passwordHash3', 'robert.brown@example.com', '3456789012', 2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'user4', 'Emily', 'Davis', 'passwordHash4', 'emily.davis@example.com', '4567890123', 2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tenant (id, name, description, admin, active, created_date, last_modified_date) VALUES
                                                                                                (1, 'TechCorp', 'Tech Company focusing on AI solutions', true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                (2, 'InnovateX', 'Innovation and Product Design company', true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO authority (id, username, authority, active, created_date, last_modified_date) VALUES
                                                                                              (1, 'user1', 'ROLE_USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                              (2, 'user2', 'ROLE_REVIEWER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                              (3, 'user3', 'ROLE_USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                              (4, 'user4', 'ROLE_UPPER_MANAGER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO process (id, name, description, tenant_id, active, created_date, last_modified_date) VALUES
                                                                                                     (1, 'Manufacturing Process A', 'Optimize assembly line efficiency', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                     (2, 'Product Design Process', 'Design and prototype new product ideas', 2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                     (3, 'Quality Control', 'Implement quality checks for products', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO document (id, name, type, description, tenant_id, active, created_date, last_modified_date) VALUES
                                                                                                            (1, 'TechCorp Training Material', 'PDF', 'Training materials for employees', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                            (2, 'Product Specifications', 'DOC', 'Specifications for new products', 2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                            (3, 'Assembly Line Guidelines', 'PDF', 'Guidelines for the assembly process', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO suggestion (id, title, content, user_id, process_id, status, active, created_date, last_modified_date) VALUES
                                                                                                                       (1, 'Improve Workflow Efficiency', 'Use automation to reduce manual work', 1, 1, 'PENDING', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                       (2, 'Add New Quality Checks', 'Introduce new quality checks to improve product reliability', 2, 3, 'APPROVED', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                       (3, 'Revise Product Design', 'Revise the product design to include customer feedback', 3, 2, 'REJECTED', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO review (id, suggestion_id, user_id, comment, rating, is_approved, active, created_date, last_modified_date) VALUES
                                                                                                                            (1, 1, 2, 'Great suggestion, automation will help!', 4.5, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                            (2, 2, 4, 'Quality checks are necessary, but consider cost implications.', 4.0, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                            (3, 3, 2, 'This design is too costly to implement in the short term.', 3.0, false, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
