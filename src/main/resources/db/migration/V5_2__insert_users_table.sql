-- Insère des données dans la table `users`
INSERT INTO users (login, password, firstname, lastname, email, langue, role_id) VALUES
                                                                                     ('admin', '$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW', 'John', 'Doe', 'admin@example.com', 'fr', 1),
                                                                                     ('member', '$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW', 'Jane', 'Doe', 'member@example.com', 'en', 2);
