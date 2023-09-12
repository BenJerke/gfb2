-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER gfb_owner
WITH PASSWORD 'gfb2go';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO gfb_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO gfb_owner;

CREATE USER gfb_appuser
WITH PASSWORD 'gfb2usergo';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO gfb_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO gfb_appuser;
