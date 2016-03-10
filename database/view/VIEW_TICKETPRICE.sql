CREATE OR REPLACE FORCE VIEW VIEW_TICKETPRICE AS
SELECT t1.fs_no,
       t1.fs_name,
       t.FTSTARTDATE,
       t.FTENDDATE,
       t.FS_RESNO,
       t.FDFULLLOWQP,
       t.FDHALFLOWQP,
       t.FDCHILDLOWQP,
       t.FDFREELOWQP,
       t.FDFULLLOWTP,
       t.FDHALFLOWTP,
       t.FDCHILDLOWTP,
       t.FDFREELOWTP
  FROM (SELECT c.ft_startdate AS FTSTARTDATE,
               c.ft_enddate AS FTENDDATE,
               c.fs_resno,
               MAX(CASE WHEN c.fs_ccno = '000001' THEN c.fd_price END) FDFULLLOWQP,
               MAX(CASE WHEN c.fs_ccno = '000002' THEN c.fd_price END) FDHALFLOWQP,
               MAX(CASE WHEN c.fs_ccno = '000003' THEN c.fd_price END) FDCHILDLOWQP,
               MAX(CASE WHEN c.fs_ccno = '000004' THEN c.fd_price END) FDFREELOWQP,
               MAX(CASE WHEN c.fs_ccno = '000005' THEN c.fd_price END) FDFULLLOWTP,
               MAX(CASE WHEN c.fs_ccno = '000006' THEN c.fd_price END) FDHALFLOWTP,
               MAX(CASE WHEN c.fs_ccno = '000007' THEN c.fd_price END) FDCHILDLOWTP,
               MAX(CASE WHEN c.fs_ccno = '000008' THEN c.fd_price END) FDFREELOWTP
          FROM tccprice c
         WHERE c.fs_restype = 'mp'
         GROUP BY c.ft_startdate,c.ft_enddate,c.fs_resno) t, tticket t1
 WHERE t.fs_resno = t1.fs_no
 ORDER BY t1.fs_no,t.fdFullLowQp;
         
EXIT;