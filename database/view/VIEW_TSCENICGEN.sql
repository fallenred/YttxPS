CREATE OR REPLACE FORCE VIEW "YTDEVUSR"."VIEW_TSCENICGEN" ("FI_INDEX", "FS_SCENICNO", "FS_SCENICNAME", "FI_GENINDEX", "FI_DAYS") AS 
SELECT t.fi_index , 
       t.fs_scenicno , 
       s.fs_name AS fs_scenicname, 
       t.fi_genindex, 
       g.fi_days 
  FROM tscenicgen t, tscenic s, tgen g  
 WHERE t.fs_scenicno = s.fs_no 
   AND t.fi_genindex = g.fi_index
   AND s.fi_stat = 1
   AND g.fi_stat = 1;

EXIT;
 
