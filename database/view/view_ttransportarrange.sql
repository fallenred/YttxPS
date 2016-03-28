create or replace view view_ttransportarrange as
select t1."FS_NO",
       t1."FI_GENINDEX",
       t1."FS_TRANSNO",
       t2.fs_name as fs_transName,
       t4.fs_name as fi_genName
  from TtransportArrange t1
  left join Ttransport t2 on t1.fs_transno = t2.fs_no
  left join tgen t4 on t1.fi_genindex = t4.fi_index;
 
exit;
