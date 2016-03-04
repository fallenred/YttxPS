CREATE OR REPLACE VIEW view_RESSNAPSHOT AS
--门票
SELECT a.fs_routeno,
       a.fi_dayflag,
       'mp' AS restype,
       d.fs_resprop AS resprop,
       c.fs_dictno AS resno,
       c.fs_dictname AS resname
  FROM troutecc a,tticket b,tdict c,TResTypeDirc d
 WHERE a.fs_resno = b.fs_no
   AND b.fs_type = c.fs_dictno
   AND a.fs_restype = d.fs_restype
   AND a.fs_restype = 'mp'
   AND c.fs_parentno = 'mp'
UNION ALL
--娱乐项目
SELECT a.fs_routeno,
       a.fi_dayflag,
       'yl' AS restype,
       d.fs_resprop AS resprop,
       c.fs_dictno AS resno,
       c.fs_dictname AS resname
  FROM troutecc a,tentertainment b,tdict c,TResTypeDirc d
 WHERE a.fs_resno = b.fs_no
   AND b.fs_type = c.fs_dictno
   AND a.fs_restype = d.fs_restype
   AND a.fs_restype = 'yl'
   AND c.fs_parentno = 'yl'
UNION ALL
--房型
SELECT a.fs_routeno,
       a.fi_dayflag,
       'bg' AS restype,
       'prop' AS resprop,
       c.fs_dictno AS resno,
       c.fs_dictname AS resname
  FROM troutecc a,troom b,tdict c,taccomadation d,TResTypeDirc e
 WHERE a.fs_resno = b.fs_roomno
   AND d.fs_starlvl = c.fs_dictno
   AND b.fs_accomno = d.fs_no
   AND a.fs_restype = e.fs_restype
   AND a.fs_restype = 'fx'
   AND c.fs_parentno = 'bg'
UNION ALL
--导游
SELECT a.fs_routeno,
       a.fi_dayflag,
       'dy' AS restype,
       d.fs_resprop AS resprop,
       c.fs_dictno AS resno,
       c.fs_dictname AS resname
  FROM troutecc a,Tguide b,tdict c,TResTypeDirc d
 WHERE a.fs_resno = b.fs_no
   AND b.fs_lvl = c.fs_dictno
   AND a.fs_restype = d.fs_restype
   AND a.fs_restype = 'dy'
   AND c.fs_parentno = 'dy';
   
EXIT;