create or replace view view_tticket as
select c.fs_no, 
       c.fs_name, 
       c.fs_type, 
       c.fs_desc, 
       c.fi_stat, 
       trim(s.fs_no) as fs_scenicno, 
       s.fs_name as fs_scenicname 
  from tticket c, tresourcescenic r, tscenic s 
 where c.fs_no = r.fs_resno 
   and r.fs_restype = 'mp' 
   and r.fs_scenicno = s.fs_no;
