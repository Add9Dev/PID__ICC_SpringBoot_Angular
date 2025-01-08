CREATE TABLE `locations` (
                             `id` BIGINT NOT NULL AUTO_INCREMENT,
                             `slug` VARCHAR(60) COLLATE utf8mb4_unicode_ci NOT NULL UNIQUE,
                             `designation` VARCHAR(60) COLLATE utf8mb4_unicode_ci NOT NULL,
                             `address` VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                             `locality_id` BIGINT NOT NULL,
                             `website` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `phone` VARCHAR(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `locations_locality_id_fk` (`locality_id`),
                             CONSTRAINT `locations_locality_id_fk` FOREIGN KEY (`locality_id`) REFERENCES `localities` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
