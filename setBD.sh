#!/bin/bash

DB_USER='jraye';
DB_PASSWD='0987654321';

DB_NAME='clientsDB';
TABLE='client';

mysql --user=$DB_USER --password=$DB_PASSWD $DB_NAME << EOF
TRUNCATE TABLE $TABLE;
INSERT INTO $TABLE (\`to_delete\`, \`id\`,\`birth_date\`, \`client_id\`, \`full_name\`, \`passport\`) VALUES (false, 0, "1111-11-11", "8529da0b-e648-43a0-8581-7d87cea5e601", "aaa", "111");
INSERT INTO $TABLE (\`to_delete\`, \`id\`,\`birth_date\`, \`client_id\`, \`full_name\`, \`passport\`) VALUES (false, 1, "1111-11-11", "8529da0b-e648-43a0-8581-7d87cea5e602", "bbb", "222");
INSERT INTO $TABLE (\`to_delete\`, \`id\`,\`birth_date\`, \`client_id\`, \`full_name\`, \`passport\`) VALUES (false, 2, "1111-11-11", "8529da0b-e648-43a0-8581-7d87cea5e603", "ccc", "333");
INSERT INTO $TABLE (\`to_delete\`, \`id\`,\`birth_date\`, \`client_id\`, \`full_name\`, \`passport\`) VALUES (false, 3, "1111-11-11", "8529da0b-e648-43a0-8581-7d87cea5e604", "ddd", "444");
INSERT INTO $TABLE (\`to_delete\`, \`id\`,\`birth_date\`, \`client_id\`, \`full_name\`, \`passport\`) VALUES (false, 4, "1111-11-11", "8529da0b-e648-43a0-8581-7d87cea5e605", "eee", "555");
INSERT INTO $TABLE (\`to_delete\`, \`id\`,\`birth_date\`, \`client_id\`, \`full_name\`, \`passport\`) VALUES (false, 5, "1111-11-11", "8529da0b-e648-43a0-8581-7d87cea5e606", "fff", "666");
INSERT INTO $TABLE (\`to_delete\`, \`id\`,\`birth_date\`, \`client_id\`, \`full_name\`, \`passport\`) VALUES (false, 6, "1111-11-11", "8529da0b-e648-43a0-8581-7d87cea5e607", "ggg", "777");
INSERT INTO $TABLE (\`to_delete\`, \`id\`,\`birth_date\`, \`client_id\`, \`full_name\`, \`passport\`) VALUES (false, 7, "1111-11-11", "8529da0b-e648-43a0-8581-7d87cea5e608", "hhh", "888");
INSERT INTO $TABLE (\`to_delete\`, \`id\`,\`birth_date\`, \`client_id\`, \`full_name\`, \`passport\`) VALUES (false, 8, "1111-11-11", "8529da0b-e648-43a0-8581-7d87cea5e609", "iii", "999");
EOF