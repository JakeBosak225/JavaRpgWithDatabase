DROP TABLE IF EXISTS player_inventory;
DROP TABLE IF EXISTS inventory_item;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS enemy;
DROP TABLE IF EXISTS locations;


CREATE TABLE player(
        player_id                       SERIAL PRIMARY KEY,
        player_name                     VARCHAR(50),
        current_HP                      INTEGER,
        max_HP                          INTEGER,
        player_exp                      INTEGER,
        player_level                    INTEGER,
        inventory_id                    INTEGER, --FK,
        current_location_id             INTEGER, --FK,
        currently_equipped_weapon       INTEGER --FK
);

CREATE TABLE player_inventory(
        player_id       INTEGER NOT NULL,
        item_id         INTEGER NOT NULL,
        amount_on_hand  INTEGER,
        CONSTRAINT pk_player_inventory_player_id_item_id PRIMARY KEY(player_id, item_id)
);

CREATE TABLE inventory_item(
        item_id                 SERIAL PRIMARY KEY,
        item_name               VARCHAR(200),
        item_name_plural        VARCHAR(200),
        item_type               VARCHAR(50),
        CONSTRAINT ck_item_type CHECK(item_type IN('Item', 'Healing Item', 'Weapon', 'Key Item'))
);

CREATE TABLE enemy(
        enemy_id                        SERIAL PRIMARY KEY,
        enemy_name                      VARCHAR(50),
        enemy_current_hp                INTEGER,
        enemy_max_hp                    INTEGER,
        enemy_min_attack                INTEGER,
        enemy_max_attack                INTEGER,
        enemy_min_reward_gold           INTEGER,
        enemy_max_reward_gold           INTEGER,
        enemy_min_reward_exp            INTEGER,
        enemy_max_reward_exp            INTEGER
);

CREATE TABLE locations(
        location_id                     INTEGER,
        location_name                   VARCHAR(100),
        location_description            VARCHAR(250),
        location_to_north               INTEGER,
        location_to_east                INTEGER,
        location_to_south               INTEGER,
        location_to_west                INTEGER,
        item_needed_to_enter            INTEGER,
        enemy_living_here               INTEGER
);

ALTER SEQUENCE player_player_id_seq RESTART WITH 1;
INSERT INTO player(player_name, current_HP, max_HP, player_exp, player_level, inventory_id, current_location_id, currently_equipped_weapon)
VALUES ('Player for Testing', 10, 10, 200, 1, 1,1,0);

UPDATE player SET player_level = (SELECT ((player_exp/100) + 1)) WHERE player_id = 1;

SELECT * FROM Player;


ALTER SEQUENCE enemy_enemy_id_seq RESTART WITH 1;
INSERT INTO enemy(enemy_name, enemy_current_hp, enemy_max_hp, enemy_min_attack, enemy_max_attack, 
enemy_min_reward_gold, enemy_max_reward_gold, enemy_min_reward_exp, enemy_max_reward_exp)
VALUES('Enemy For Testing', 5,5, 0, 4, 1, 10, 1,10);

SELECT * FROM enemy;

INSERT INTO locations VALUES(1,'Your House', 'Just a house for testing purposes', 2,0,3,0,0,0);
INSERT INTO locations VALUES(2,'North House', 'Just a house for testing purposes', 0,0,0,0,0,0);
INSERT INTO locations VALUES(3,'South House', 'Just a house for testing purposes', 0,0,0,0,0,0);

SELECT * FROM locations;


SELECT * FROM player;

DELETE FROM player WHERE player_id = 4;

ALTER SEQUENCE inventory_item_item_id_seq RESTART WITH 1;
INSERT INTO inventory_item(item_name, item_name_plural, item_type) VALUES('Item One', 'Item Ones', 'Item');

INSERT INTO player_inventory VALUES (1,1,4);

--How many Items 1 does the playe have?
SELECT p.player_name, ii.item_name, pi.amount_on_hand FROM player p
JOIN player_inventory pi ON pi.player_id = p.player_id
JOIN inventory_item ii ON pi.item_id = ii.item_id
WHERE ii.item_name = 'Item One';
