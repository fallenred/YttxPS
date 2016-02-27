CREATE OR REPLACE FORCE VIEW "YTDEVUSR"."VROUTECCPRICE" (
     "FT_DATE", 
     "FS_ROUTENO", 
     "FI_DAYFLAG", 
     "FS_RESTYPE", 
     "FS_RESNO", 
     "FS_RESPROP", 
     "FS_RESNAME", 
     "FS_CCNO", 
     "FS_CCNAME", 
     "FD_PRICE") AS 
select distinct
       a.ft_date,
       a.fs_routeno,
       a.fi_dayflag,
       a.fs_restype,
       a.fs_resno,
       a.fs_resprop,
       a.fs_resname,
       a.fs_ccno,
       a.fs_ccname,
       b.fd_price
  from vRouteCCPrice_A a,vRouteCCPrice_A b
 where a.fs_routeno = b.fs_routeno
   and a.fs_restype = b.fs_restype
   and a.fs_resno = b.fs_resno
   and a.fs_ccno = b.fs_ccno
   and b.ft_date = a.ft_date + a.fi_dayflag;
 
