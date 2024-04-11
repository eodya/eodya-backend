
-- user 테이블에서 oauth_id의 타입을 Long으로 변경
ALTER TABLE users MODIFY COLUMN oauth_id LONG;