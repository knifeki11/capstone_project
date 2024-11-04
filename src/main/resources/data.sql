INSERT INTO tenant (id, name, description, admin, active, created_date, last_modified_date)
VALUES
    (1, 'TechCorp', 'Tech Company focusing on AI solutions', true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'InnovateX', 'Innovation and Product Design company', true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- INSERT INTO app_user (id, username, firstname, lastname, password, email, phone, tenant_id, active, created_date, last_modified_date)
-- VALUES
--     (1, 'user1', 'John', 'Doe', '$2a$12$AoOHtKAPAsaivOOCA0Jvxuoc68brRjCzFpyKSjGueh12R7Lky/.B6', 'john.doe@example.com', '1234567890', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--
-- INSERT INTO authority (id, username, authority, active, created_date, last_modified_date)
-- VALUES
--     (1, 'user1', 'ROLE_TENANT_ADMIN', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--
-- --
-- INSERT INTO process (id, name, description, tenant_id, active, created_date, last_modified_date)
-- VALUES
--     (1, 'Manufacturing Process A', 'Optimize assembly line efficiency', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--     (2, 'Product Design Process', 'Design and prototype new product ideas', 2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--     (3, 'Quality Control', 'Implement quality checks for products', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--
-- INSERT INTO document (id, name, type, description, tenant_id, active, created_date, last_modified_date)
-- VALUES
--     (1, 'TechCorp Training Material', 'PDF', 'Training materials for employees', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--     (2, 'Product Specifications', 'DOC', 'Specifications for new products', 2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--     (3, 'Assembly Line Guidelines', 'PDF', 'Guidelines for the assembly process', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--
-- INSERT INTO suggestion (id, title, content, user_id, process_id, status, active, created_date, last_modified_date)
-- VALUES
--     (1, 'Improve Workflow Efficiency', 'Use automation to reduce manual work', 1, 1, 'PENDING', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--     (2, 'Add New Quality Checks', 'Introduce new quality checks to improve product reliability', 1, 3, 'APPROVED', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--     (3, 'Revise Product Design', 'Revise the product design to include customer feedback', 1, 2, 'REJECTED', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--
-- INSERT INTO review (id, suggestion_id, user_id, comment, rating, is_approved, active, created_date, last_modified_date)
-- VALUES
--     (1, 1, 1, 'Great suggestion, automation will help!', 4.5, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--     (2, 2, 1, 'Quality checks are necessary, but consider cost implications.', 4.0, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--     (3, 3, 1, 'This design is too costly to implement in the short term.', 3.0, false, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
