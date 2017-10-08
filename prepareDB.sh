#!/bin/bash

#Drop already existing table
echo "Dropping table teams..."
psql -d BD2 -c "DROP TABLE IF EXISTS teams;"

#Create empty table teams
echo "Recreating table teams..."
psql -d BD2 -c "CREATE TABLE IF NOT EXISTS teams (team_id INTEGER PRIMARY KEY NOT NULL, team_name TEXT NOT NULL, team_players INTEGER NOT NULL);"

#Add some sample rows to table
echo "Inserting sample values..."
psql -d BD2 -c "INSERT INTO teams VALUES(0, 'Przykład', 999);"
psql -d BD2 -c "INSERT INTO teams VALUES(1, 'Test', 666);"
psql -d BD2 -c "INSERT INTO teams VALUES(2, 'Encja', 123);"
psql -d BD2 -c "INSERT INTO teams VALUES(3, 'Kolejna encja', 321);"

#Open connection to PostgreSQL
echo "Opening connection to PostgreSQL..."
psql -d BD2

#After connection is closed
echo "Done."