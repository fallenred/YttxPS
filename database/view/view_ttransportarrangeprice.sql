CREATE OR REPLACE VIEW view_ttransportarrangeprice AS
SELECT a.fs_no AS fs_resno,   --车型线路(线路统称)资源ID
       a.fi_genindex,   --线路统称自动序号
       c.fs_name AS tgenname,   --线路统称名字
       a.fs_transno,   --车型代码
       d.fs_name AS transname,   --车型名称
       b.fs_restype,   --资源类型
       b.fs_ccno,   --消费选项编号
       e.fs_ccname,   --消费选项名称
       b.ft_startdate,   --开始日期
       b.ft_enddate,   --结束日期
       b.fd_price   --价格
  FROM ttransportarrange a,tccprice b,tgen c,ttransport d,TCCDirc e
 WHERE a.fi_genindex = c.fi_index
   AND a.fs_transno = d.fs_no
   AND a.fs_no = b.fs_resno
   AND b.fs_ccno = e.fs_ccno
   AND b.fs_restype = 'cx';

EXIT;