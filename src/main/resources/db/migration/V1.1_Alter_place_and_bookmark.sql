-- place 테이블에서 recommend_count 컬럼의 이름을 review_count로 변경
ALTER TABLE `place`
    CHANGE COLUMN `recommend_count` `review_count` INT NOT NULL;

-- bookmark 테이블에서 status 컬럼 삭제
ALTER TABLE `bookmark`
    DROP COLUMN `status`;