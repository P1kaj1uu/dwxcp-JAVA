-- ============================================================
-- dwxcp 建索引脚本
-- 优化慢查询的核心：让 SELECT / WHERE / JOIN 走 B+Tree 索引
-- 执行前请在生产环境低峰期运行；如已有同名索引会自动失败，可加 IF NOT EXISTS（MySQL 8.0.29+）
-- ============================================================

USE dwxcp;

-- 1. member（党员名册 - 最大表）
ALTER TABLE member ADD INDEX idx_name (name);
ALTER TABLE member ADD INDEX idx_groups (groups);
ALTER TABLE member ADD INDEX idx_condition (condition);
-- 复合索引：高频组合查询（先按党小组筛，再按姓名前缀匹配）
ALTER TABLE member ADD INDEX idx_groups_name (groups, name);

-- 2. evaluation（评议）
ALTER TABLE evaluation ADD INDEX idx_name (name);
ALTER TABLE evaluation ADD INDEX idx_year_quarter (year, quarter);
ALTER TABLE evaluation ADD INDEX idx_party_branch (party_branch);
ALTER TABLE evaluation ADD INDEX idx_responsibility_post (responsibility_post);
ALTER TABLE evaluation ADD INDEX idx_responsibility_area (responsibility_area);

-- 3. groups（党小组）
ALTER TABLE groups ADD INDEX idx_party_name (party, name);
ALTER TABLE groups ADD INDEX idx_name1 (name1);

-- 4. party_branch_evaluation（支部评议）
ALTER TABLE party_branch_evaluation ADD INDEX idx_year_quarter (year, quarter);
ALTER TABLE party_branch_evaluation ADD INDEX idx_party_branch (party_branch);

-- 5. basic（基本情况）
ALTER TABLE basic ADD INDEX idx_type (type);

-- 6. user（用户）
-- 已有 username 主键或唯一索引？确认后保留；如需强制唯一：
-- ALTER TABLE user ADD UNIQUE INDEX uk_username (username);

-- 7. media（媒体 - 文件列表按 category 查询）
ALTER TABLE media ADD INDEX idx_category (category);

-- 8. pdf（PDF - 文件列表按 file_type 查询）
ALTER TABLE pdf ADD INDEX idx_file_type (file_type);

-- ============================================================
-- 验证：执行完后用以下 SQL 检查索引是否被用上
-- ============================================================
-- EXPLAIN SELECT id, name, sex, nation FROM member WHERE groups = 'xxx' AND name LIKE '张%';
-- EXPLAIN SELECT * FROM evaluation WHERE year = '2024' AND quarter = '1';
-- EXPLAIN SELECT id, file_name, file_type FROM pdf WHERE file_type = 'report';
-- EXPLAIN SELECT id, file_name, file_type FROM media WHERE category = 'orgLife';

-- ============================================================
-- 监控：检查慢查询是否还出现
-- ============================================================
-- SHOW VARIABLES LIKE 'slow_query_log';
-- SHOW VARIABLES LIKE 'long_query_time';
-- SET GLOBAL slow_query_log = 'ON';
-- SET GLOBAL long_query_time = 1;
-- SHOW VARIABLES LIKE 'slow_query_log_file';
-- 启动后跑一段时间，SELECT * FROM mysql.slow_log WHERE start_time > DATE_SUB(NOW(), INTERVAL 1 HOUR);