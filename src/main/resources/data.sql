-- =========================================
-- GAMES
-- =========================================
INSERT INTO game (id, title, genre) VALUES (1, 'Dungeons & Dragons 5e', 'Fantasy');
INSERT INTO game (id, title, genre) VALUES (2, 'Vampire the Masquerade', 'Horror');
INSERT INTO game (id, title, genre) VALUES (3, 'Call of Cthulhu', 'Cosmic Horror');
INSERT INTO game (id, title, genre) VALUES (4, 'Cyberpunk Red', 'Sci-Fi');
INSERT INTO game (id, title, genre) VALUES (5, 'Avatar Legends', 'Fantasy');

-- =========================================
-- PLAYER_PROFILE
-- =========================================
INSERT INTO player_profile (id, nickname, email, platform) VALUES (1, 'whispering', 'flavio@example.com', 'Discord');
INSERT INTO player_profile (id, nickname, email, platform) VALUES (2, 'artha', 'arthur@example.com', 'Roll20');
INSERT INTO player_profile (id, nickname, email, platform) VALUES (3, 'dudis', 'victor@example.com', 'Discord');
INSERT INTO player_profile (id, nickname, email, platform) VALUES (4, 'casual', 'roni@example.com', 'Discord');
INSERT INTO player_profile (id, nickname, email, platform) VALUES (5, 'legbreaker', 'ronan@example.com', 'Roll20');

-- =========================================
-- player
-- =========================================
INSERT INTO player (id, name, role, profile_id) VALUES (1, 'Flávio', 'MASTER', 1);
INSERT INTO player (id, name, role, profile_id) VALUES (2, 'Arthur', 'PLAYER', 2);
INSERT INTO player (id, name, role, profile_id) VALUES (3, 'Victor', 'PLAYER', 3);
INSERT INTO player (id, name, role, profile_id) VALUES (4, 'Roni', 'MASTER', 4);
INSERT INTO player (id, name, role, profile_id) VALUES (5, 'Ronan', 'PLAYER', 5);

-- =========================================
-- TABLES
-- =========================================
INSERT INTO table_rpg (id, name) VALUES (1, 'Forgotten Realms');
INSERT INTO table_rpg (id, name) VALUES (2, 'Vampire Nights');
INSERT INTO table_rpg (id, name) VALUES (3, 'Cyberpunk City');
INSERT INTO table_rpg (id, name) VALUES (4, 'Avatar Adventures');
INSERT INTO table_rpg (id, name) VALUES (5, 'Cthulhu Horror');

-- =========================================
-- TABLE_PLAYER (ManyToMany)
-- =========================================
INSERT INTO table_player (table_id, player_id) VALUES (1, 1);
INSERT INTO table_player (table_id, player_id) VALUES (1, 2);
INSERT INTO table_player (table_id, player_id) VALUES (2, 3);
INSERT INTO table_player (table_id, player_id) VALUES (2, 4);
INSERT INTO table_player (table_id, player_id) VALUES (3, 5);
INSERT INTO table_player (table_id, player_id) VALUES (3, 1);
INSERT INTO table_player (table_id, player_id) VALUES (4, 2);
INSERT INTO table_player (table_id, player_id) VALUES (4, 3);
INSERT INTO table_player (table_id, player_id) VALUES (5, 4);
INSERT INTO table_player (table_id, player_id) VALUES (5, 5);

-- =========================================
-- GAME_SESSIONS
-- =========================================
INSERT INTO game_session (id, adventure_name, played_at, table_id, game_id) VALUES (1, 'Lost Mine of Phandelver', '2025-01-10', 1, 1);
INSERT INTO game_session (id, adventure_name, played_at, table_id, game_id) VALUES (2, 'Curse of Strahd', '2025-02-15', 2, 2);
INSERT INTO game_session (id, adventure_name, played_at, table_id, game_id) VALUES (3, 'Night City Heist', '2025-03-20', 3, 4);
INSERT INTO game_session (id, adventure_name, played_at, table_id, game_id) VALUES (4, 'Avatar Quest', '2025-04-05', 4, 5);
INSERT INTO game_session (id, adventure_name, played_at, table_id, game_id) VALUES (5, 'The Dunwich Horror', '2025-05-12', 5, 3);
