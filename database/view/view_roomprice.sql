CREATE OR REPLACE VIEW view_roomprice AS
SELECT A.FS_ACCOMNO AS FS_ACCOMNO,    --���ݴ���
       d.FS_REGIONNO AS FS_REGIONNO,  --�����������
       d.FS_NAME AS FS_TACNAME,       --�Ƶ�����
       A.FS_TYPE AS FS_ROOMTYPE,      --��������
       A.FS_NAME AS FS_ROOMNAME,      --��������
       A.FS_MEAL AS FS_ROOMMEAL,      --�������������
       A.FI_STAT AS FI_ROOMSTAT,      --״̬
       b.FT_STARTDATE,                --��ʼ����
       b.FT_ENDDATE,                  --��������
       b.FS_RESTYPE,                  --��Դ����
       b.FS_RESNO,                    --��Դ���
       b.FS_CCNO,                     --����ѡ����
       c.FS_CCNAME,                   --����ѡ������
       b.FD_PRICE                     --����
  FROM troom a,tccprice b,TCCDirc c,Taccomadation d
 WHERE a.FS_ROOMNO = b.FS_RESNO
   AND b.FS_CCNO = c.FS_CCNO
   AND a.FS_ACCOMNO = d.FS_NO
   AND b.FS_RESTYPE = 'bg';