CREATE OR REPLACE VIEW view_ttransportarrangeprice AS
SELECT a.fs_no AS fs_resno,   --������·(��·ͳ��)��ԴID
       a.fi_genindex,   --��·ͳ���Զ����
       c.fs_name AS tgenname,   --��·ͳ������
       a.fs_transno,   --���ʹ���
       d.fs_name AS transname,   --��������
       b.fs_restype,   --��Դ����
       b.fs_ccno,   --����ѡ����
       e.fs_ccname,   --����ѡ������
       b.ft_startdate,   --��ʼ����
       b.ft_enddate,   --��������
       b.fd_price   --�۸�
  FROM ttransportarrange a,tccprice b,tgen c,ttransport d,TCCDirc e
 WHERE a.fi_genindex = c.fi_index
   AND a.fs_transno = d.fs_no
   AND a.fs_no = b.fs_resno
   AND b.fs_ccno = e.fs_ccno
   AND b.fs_restype = 'cx';

EXIT;