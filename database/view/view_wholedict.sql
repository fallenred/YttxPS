CREATE OR REPLACE VIEW view_wholedict AS
SELECT a.FS_DICTNO,
       a.FS_DICTNAME,
       a.FS_PARENTNO,
       a.FS_REM
  FROM tdict a
UNION ALL
SELECT a.FS_RESTYPE AS FS_DICTNO,
       a.FS_RESNAME AS FS_DICTNAME,
       'ResTypeDirc' AS FS_PARENTNO,
       '' AS FS_REM
  FROM TResTypeDirc a;

EXIT;