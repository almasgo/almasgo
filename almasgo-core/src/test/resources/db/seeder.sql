-- Seed user data
INSERT INTO user (email, name, password) VALUES ('test1@gmail.com', 'Test 1', '$2a$10$3xMA83MbyEpD4RgwhF44feqJ3ED5gOL5N4IefhESBUqL68aQpsFiq');
SELECT @user_id:= LAST_INSERT_ID();

-- Seed engine data
INSERT INTO engine (created_at, updated_at, active, api_key, name, type, user_id) VALUES (NOW(), NOW(), 1, '9a09107b54e3040dd2c98b2a5236f27c27013d0e18889be4368cb61bd28586d9', "Test Engine", "sandbox", @user_id);
SELECT @engine_id:= LAST_INSERT_ID();