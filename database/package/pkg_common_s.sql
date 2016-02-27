CREATE OR REPLACE PACKAGE pkg_common AS

   PRE_ERRCODE      CONSTANT VARCHAR2(12) := 'ERROR';
   PRE_SUCCESCODE   CONSTANT VARCHAR2(12) := 'SUCCESS';

   PROCEDURE prc_insertPrice(
      PRM_FT_STARTDATE       IN       DATE,        --��ʼ����
	  PRM_FT_ENDDATE         IN       DATE,        --��������
	  PRM_FS_RESTYPE         IN       CHAR,        --��Դ����
	  PRM_FS_RESNO           IN       CHAR,        --��Դ���
	  PRM_FS_CCNO            IN       CHAR,        --����ѡ����
	  PRM_FD_PRICE           IN       NUMBER,      --����
	  PRM_CODE               OUT      VARCHAR2,    --ִ��״̬��
	  PRM_ERRMSG             OUT      VARCHAR2     --������Ϣ
   );
   
END pkg_common;
/
SHOW ERROR;