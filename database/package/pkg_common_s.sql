CREATE OR REPLACE PACKAGE pkg_common AS

   PRE_ERRCODE      CONSTANT VARCHAR2(12) := 'ERROR';
   PRE_SUCCESCODE   CONSTANT VARCHAR2(12) := 'SUCCESS';

   PROCEDURE prc_insertPrice(
      PRM_FT_STARTDATE       IN       DATE,        --开始日期
	  PRM_FT_ENDDATE         IN       DATE,        --结束日期
	  PRM_FS_RESTYPE         IN       CHAR,        --资源类型
	  PRM_FS_RESNO           IN       CHAR,        --资源编号
	  PRM_FS_CCNO            IN       CHAR,        --消费选项编号
	  PRM_FD_PRICE           IN       NUMBER,      --定价
	  PRM_CODE               OUT      VARCHAR2,    --执行状态码
	  PRM_ERRMSG             OUT      VARCHAR2     --错误信息
   );
   
END pkg_common;
/
SHOW ERROR;