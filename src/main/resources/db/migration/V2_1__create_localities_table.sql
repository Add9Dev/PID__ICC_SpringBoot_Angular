CREATE TABLE localities (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            postal_code VARCHAR(10) NOT NULL,
                            locality VARCHAR(255) NOT NULL
);

ALTER TABLE `localities` MODIFY COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT;
