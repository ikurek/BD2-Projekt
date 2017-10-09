#!/bin/bash

#Drop already existing tables
echo "Dropping tables."
psql -d BD2 -c "DROP TABLE IF EXISTS teams;"
psql -d BD2 -c "DROP TABLE IF EXISTS players;"
psql -d BD2 -c "DROP TABLE IF EXISTS countries;"
psql -d BD2 -c "DROP TABLE IF EXISTS leagues;"


#Create empty table teams
echo "Recreating table teams..."
psql -d BD2 -c "CREATE TABLE IF NOT EXISTS teams (id SERIAL, name TEXT, country TEXT, league TEXT, rank INTEGER);"
echo "Recreating table players..."
psql -d BD2 -c "CREATE TABLE IF NOT EXISTS players (id SERIAL, name TEXT, surname TEXT, team TEXT, rank INTEGER);"
echo "Recreating table countries..."
psql -d BD2 -c "CREATE TABLE IF NOT EXISTS countries (id SERIAL, name TEXT);"
echo "Recreating table leagues..."
psql -d BD2 -c "CREATE TABLE IF NOT EXISTS leagues (id SERIAL, name TEXT);"



#Open connection to PostgreSQL
echo "Opening connection to PostgreSQL..."
psql -d BD2

#After connection is closed
echo "Done."