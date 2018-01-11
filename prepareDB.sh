#!/bin/bash

#Drop already existing tables
echo "Dropping tables..."
psql -d BD -c "DROP TABLE IF EXISTS teams;"
psql -d BD -c "DROP TABLE IF EXISTS players;"
psql -d BD -c "DROP TABLE IF EXISTS countries;"
psql -d BD -c "DROP TABLE IF EXISTS leagues;"
psql -d BD -c "DROP TABLE IF EXISTS seasons;"
psql -d BD -c "DROP TABLE IF EXISTS season_teams;"
psql -d BD -c "DROP TABLE IF EXISTS season_players;"
psql -d BD -c "DROP TABLE IF EXISTS season_matches;"
echo "All tables down!"

#Create empty table teams
echo "Creating table teams..."
psql -d BD -c "CREATE TABLE IF NOT EXISTS teams (id SERIAL, name TEXT, country TEXT, league TEXT, rank INTEGER);"
echo "Creating table players..."
psql -d BD -c "CREATE TABLE IF NOT EXISTS players (id SERIAL, name TEXT, surname TEXT, team TEXT, rank INTEGER);"
echo "Creating table countries..."
psql -d BD -c "CREATE TABLE IF NOT EXISTS countries (id SERIAL, name TEXT);"
echo "Creating table leagues..."
psql -d BD -c "CREATE TABLE IF NOT EXISTS leagues (id SERIAL, name TEXT);"
echo "Creating table seasons..."
psql -d BD -c "CREATE TABLE IF NOT EXISTS seasons (id SERIAL);"
echo "Creating table season_teams..."
psql -d BD -c "CREATE TABLE IF NOT EXISTS season_teams (id SERIAL);"
echo "Creating table season_players..."
psql -d BD -c "CREATE TABLE IF NOT EXISTS season_players (id SERIAL);"
echo "Creating table season_matches"
psql -d BD -c "CREATE TABLE IF NOT EXISTS season_matches (id SERIAL);"
echo "All tables created!"

#Open shell connection to PostgreSQL
echo "Opening connection to PostgreSQL..."
psql -d BD
