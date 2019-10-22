USE `vader`;

CREATE TABLE `users` (
  `id` varchar(50) NOT NULL,
  `display_name` varchar(36) NOT NULL,
  `avatar_url` varchar(3000) NOT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `posts`(
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(50) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `text` VARCHAR(10000) NOT NULL,
  `created_at` BIGINT(20) NOT NULL,
  `updated_at` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;