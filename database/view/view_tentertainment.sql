CREATE OR REPLACE VIEW view_tentertainment
AS
   SELECT c.fs_no,
          c.fs_name,
          c.fs_type,
          c.fs_desc,
          c.fi_stat,
          c.fs_lvl,
          c.fs_addr,
          s.fs_no   AS fs_scenicno,
          s.fs_name AS fs_scenicname
     FROM tentertainment c,
          tresourcescenic r,
          tscenic s
    WHERE c.fs_no = r.fs_resno
      AND r.fs_restype = 'yl'
      AND r.fs_scenicno = s.fs_no;
      
EXIT;