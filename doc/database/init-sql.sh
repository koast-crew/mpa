#!/bin/bash
set -e  # 오류 발생 시 스크립트 중단

echo "Initializing MPA database with SQL scripts..."

DB_NAME="mpa"
DB_USER="postgres"

# DDL/SEQUENCE 실행
if [ -d "/database/ddl/sequence" ]; then
    echo "Executing DDL Sequence scripts..."
    for FILE in /database/ddl/sequence/*.sql; do
        if [ -f "$FILE" ]; then
            echo "Running: $FILE"
            psql -U "$DB_USER" -d "$DB_NAME" -a -f "$FILE"
        fi
    done
fi

# DDL 실행
if [ -d "/database/ddl" ]; then
    echo "Executing DDL scripts..."
    for FILE in /database/ddl/*.sql; do
        if [ -f "$FILE" ]; then
            echo "Running: $FILE"
            psql -U "$DB_USER" -d "$DB_NAME" -a -f "$FILE"
        fi
    done
fi

# DML 실행
if [ -d "/database/dml" ]; then
    echo "Executing DML scripts..."
    for FILE in /database/dml/*.sql; do
        if [ -f "$FILE" ]; then
            echo "Running: $FILE"
            psql -U "$DB_USER" -d "$DB_NAME" -a -f "$FILE"
        fi
    done
fi

echo "Database initialization complete."
