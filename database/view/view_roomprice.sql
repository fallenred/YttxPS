CREATE OR REPLACE VIEW view_roomprice AS
SELECT A.FS_ACCOMNO AS FS_ACCOMNO,    --宾馆代码
       d.FS_REGIONNO AS FS_REGIONNO,  --所属地区编号
       d.FS_NAME AS FS_TACNAME,       --酒店名称
       A.FS_TYPE AS FS_ROOMTYPE,      --房间类型
       A.FS_NAME AS FS_ROOMNAME,      --房型名称
       A.FS_MEAL AS FS_ROOMMEAL,      --早中晚三餐情况
       A.FI_STAT AS FI_ROOMSTAT,      --状态
       b.FT_STARTDATE,                --开始日期
       b.FT_ENDDATE,                  --结束日期
       b.FS_RESTYPE,                  --资源类型
       b.FS_RESNO,                    --资源编号
       b.FS_CCNO,                     --消费选项编号
       c.FS_CCNAME,                   --消费选项名称
       b.FD_PRICE                     --定价
  FROM troom a,tccprice b,TCCDirc c,Taccomadation d
 WHERE a.FS_ROOMNO = b.FS_RESNO
   AND b.FS_CCNO = c.FS_CCNO
   AND a.FS_ACCOMNO = d.FS_NO
   AND b.FS_RESTYPE = 'bg';