CREATE OR REPLACE VIEW VIEW_ENTERTAINMENTPRICE AS
SELECT t1.FS_NO,
       t1.FS_NAME,
       t1.FS_REGIONNO,
       t1.FS_ADDR,
       t1.FS_TYPE,
       t1.FS_DESC,
       t1.FS_LVL,
       t1.FS_OPENTIME,
       t1.FI_STAT,
       t."FTSTARTDATE",
       t."FTENDDATE",
       t."FS_RESNO",
       t."FDFULLLOWQP",
       t."FDHALFLOWQP",
       t."FDCHILDLOWQP",
       t."FDFREELOWQP",
       t."FDFULLLOWTP",
       t."FDHALFLOWTP",
       t."FDCHILDLOWTP",
       t."FDFREELOWTP",
       t."FDTRANSCOSTSTP"
  FROM (SELECT c.ft_startdate AS ftStartdate,
               c.ft_enddate AS ftEnddate,
               c.fs_resno ,
               MAX(CASE WHEN c.fs_ccno='000001' THEN c.fd_price END) fdFullLowQp,
               MAX(CASE WHEN c.fs_ccno='000002' THEN c.fd_price END) fdHalfLowQp,
               MAX(CASE WHEN c.fs_ccno='000003' THEN c.fd_price END) fdChildLowQp,
               MAX(CASE WHEN c.fs_ccno='000004' THEN c.fd_price END) fdFreeLowQp,
               MAX(CASE WHEN c.fs_ccno='000005' THEN c.fd_price END) fdFullLowTp,
               MAX(CASE WHEN c.fs_ccno='000006' THEN c.fd_price END) fdHalfLowTp,
               MAX(CASE WHEN c.fs_ccno='000007' THEN c.fd_price END) fdChildLowTp,
               MAX(CASE WHEN c.fs_ccno='000008' THEN c.fd_price END) fdFreeLowTp,
               MAX(CASE WHEN c.fs_ccno='000017' THEN c.fd_price END) fdTranscostsTp
          FROM tccprice c
         WHERE c.fs_restype = 'yl'
         GROUP BY c.ft_startdate, c.ft_enddate, c.fs_resno) t, TENTERTAINMENT t1
 WHERE t.fs_resno = t1.fs_no
 ORDER BY t1.fs_no,t.fdFullLowQp;
 
EXIT;
