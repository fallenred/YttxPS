CREATE OR REPLACE PACKAGE BODY pkg_common AS

   PROCEDURE prc_insertPrice(
      PRM_FT_STARTDATE       IN       DATE       ,        --��ʼ����
	  PRM_FT_ENDDATE         IN       DATE       ,        --��������
	  PRM_FS_RESTYPE         IN       CHAR       ,        --��Դ����
	  PRM_FS_RESNO           IN       CHAR       ,        --��Դ���
	  PRM_FS_CCNO            IN       CHAR       ,        --����ѡ����
	  PRM_FD_PRICE           IN       NUMBER     ,        --����
	  PRM_CODE               OUT      VARCHAR2   ,        --ִ��״̬��
	  PRM_ERRMSG             OUT      VARCHAR2   )        --������Ϣ
   IS
      --ʱ�佻�������α�
      CURSOR cur_prices IS
	  SELECT a.FT_STARTDATE,
             a.FT_ENDDATE,
			 a.FS_RESTYPE,
			 a.FS_RESNO,
			 a.FS_CCNO,
			 a.FD_PRICE
	    FROM TCCPrice a
	   WHERE a.FS_RESTYPE = PRM_FS_RESTYPE
	     AND a.FS_RESNO = PRM_FS_RESNO
		 AND a.FS_CCNO = PRM_FS_CCNO
		 AND ((PRM_FT_STARTDATE >= a.FT_STARTDATE AND PRM_FT_STARTDATE <= a.FT_ENDDATE)
		     OR (PRM_FT_ENDDATE >= a.FT_STARTDATE AND PRM_FT_ENDDATE <= a.FT_ENDDATE));
   BEGIN
      PRM_CODE := PRE_ERRCODE;
	  PRM_ERRMSG := '';
   
      --����ɾ������ʱ�����������ʱ������
	  DELETE 
	    FROM TCCPrice a
	   WHERE a.FS_RESTYPE = PRM_FS_RESTYPE
	     AND a.FS_RESNO = PRM_FS_RESNO
		 AND a.FS_CCNO = PRM_FS_CCNO
	     AND a.FT_STARTDATE >= PRM_FT_STARTDATE
	     AND a.FT_ENDDATE <= PRM_FT_ENDDATE;
		 
	  FOR rec_pri IN cur_prices LOOP
	     IF PRM_FT_STARTDATE >= rec_pri.FT_STARTDATE AND PRM_FT_ENDDATE <= rec_pri.FT_ENDDATE THEN
		    UPDATE TCCPrice
			   SET FT_ENDDATE = PRM_FT_STARTDATE - 1
			 WHERE FS_RESTYPE = PRM_FS_RESTYPE
	           AND FS_RESNO = PRM_FS_RESNO
		       AND FS_CCNO = PRM_FS_CCNO
			   AND FT_STARTDATE = rec_pri.FT_STARTDATE;
				
		    INSERT INTO TCCPrice
			   (FT_STARTDATE,
			   	FT_ENDDATE,
			    FS_RESTYPE,
			    FS_RESNO,
			    FS_CCNO,
				FD_PRICE)
			VALUES(
			    PRM_FT_ENDDATE + 1,
			    rec_pri.FT_ENDDATE,
				rec_pri.FS_RESTYPE,
				rec_pri.FS_RESNO,
				rec_pri.FS_CCNO,
				rec_pri.FD_PRICE);
		 END IF;
		 
		 IF PRM_FT_STARTDATE <= rec_pri.FT_STARTDATE AND PRM_FT_ENDDATE >= rec_pri.FT_STARTDATE THEN
		    UPDATE TCCPrice
			   SET FT_STARTDATE = PRM_FT_ENDDATE + 1
			 WHERE FS_RESTYPE = PRM_FS_RESTYPE
               AND FS_RESNO = PRM_FS_RESNO
               AND FS_CCNO = PRM_FS_CCNO
               AND FT_STARTDATE = rec_pri.FT_STARTDATE;
         END IF;
		 
		 IF PRM_FT_STARTDATE <= rec_pri.FT_ENDDATE AND PRM_FT_ENDDATE >= rec_pri.FT_ENDDATE THEN
		    UPDATE TCCPrice
			   SET FT_ENDDATE = PRM_FT_STARTDATE - 1
			 WHERE FS_RESTYPE = PRM_FS_RESTYPE
	           AND FS_RESNO = PRM_FS_RESNO
		       AND FS_CCNO = PRM_FS_CCNO
			   AND FT_STARTDATE = rec_pri.FT_STARTDATE;
		 END IF;
	  END LOOP;
	  
	  INSERT INTO TCCPrice
	     (FT_STARTDATE,
	      FT_ENDDATE,
	      FS_RESTYPE,
	      FS_RESNO,
	      FS_CCNO,
	  	  FD_PRICE)
	  VALUES(
	      PRM_FT_STARTDATE,
	      PRM_FT_ENDDATE,
	  	  PRM_FS_RESTYPE,
	  	  PRM_FS_RESNO,
	  	  PRM_FS_CCNO,
	  	  PRM_FD_PRICE);
		 
	  PRM_CODE := PRE_SUCCESCODE;
	  PRM_ERRMSG := '';
	  RETURN;
   EXCEPTION
      WHEN OTHERS THEN
         PRM_CODE  := PRE_ERRCODE;
         PRM_ERRMSG := '���ݿ����' || SQLERRM;
         RETURN;
   END prc_insertPrice;
   
END pkg_common;
/
SHOW ERROR;