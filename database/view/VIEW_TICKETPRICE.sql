CREATE OR REPLACE FORCE VIEW "YTDEVUSR"."VIEW_TICKETPRICE" ("FS_NO", "FS_NAME", "FTSTARTDATE", "FTENDDATE", "FS_RESNO", "FDFULLLOWQP", "FDHALFLOWQP", "FDCHILDLOWQP", "FDFREELOWQP", "FDFULLLOWTP", "FDHALFLOWTP", "FDCHILDLOWTP", "FDFREELOWTP", "FDFULLPEAKQP", "FDHALFPEAKQP", "FDCHILDPEAKQP", "FDFREEPEAKQP", "FDFULLPEAKTP", "FDHALFPEAKTP", "FDCHILDPEAKTP", "FDFREEPEAKTP") AS 
   select t1.fs_no, 
          t1.fs_name, 
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
		  t."FDFULLPEAKQP",
		  t."FDHALFPEAKQP",
		  t."FDCHILDPEAKQP",
		  t."FDFREEPEAKQP",
		  t."FDFULLPEAKTP",
		  t."FDHALFPEAKTP",
		  t."FDCHILDPEAKTP",
		  t."FDFREEPEAKTP"
     from (select c.ft_startdate as ftStartdate,   --开始日期
	              c.ft_enddate as ftEnddate,   --结束日期                                  
				  c.fs_resno ,   --资源编号
                  max(case when c.fs_ccno='000001' then c.fd_price end) fdFullLowQp,    --门票/娱乐项目 淡季挂牌价格全票
				  max(case when c.fs_ccno='000002' then c.fd_price end) fdHalfLowQp,    --门票/娱乐项目 淡季挂牌价格半票
                  max(case when c.fs_ccno='000003' then c.fd_price end) fdChildLowQp,   --门票/娱乐项目 淡季挂牌价格儿童票
				  max(case when c.fs_ccno='000004' then c.fd_price end) fdFreeLowQp,    --门票/娱乐项目 淡季挂牌价格免票
                  max(case when c.fs_ccno='000005' then c.fd_price end) fdFullLowTp,    --门票/娱乐项目 淡季团队价格全票
				  max(case when c.fs_ccno='000006' then c.fd_price end) fdHalfLowTp,    --门票/娱乐项目 淡季团队价格半票
                  max(case when c.fs_ccno='000007' then c.fd_price end) fdChildLowTp,   --门票/娱乐项目 淡季团队价格儿童票
				  max(case when c.fs_ccno='000008' then c.fd_price end) fdFreeLowTp,    --门票/娱乐项目 淡季团队价格免票
                  max(case when c.fs_ccno='000009' then c.fd_price end) fdFullPeakQp,   --门票/娱乐项目 旺季挂牌价格全票
				  max(case when c.fs_ccno='000010' then c.fd_price end) fdHalfPeakQp,   --门票/娱乐项目 旺季挂牌价格半票
                  max(case when c.fs_ccno='000011' then c.fd_price end) fdChildPeakQp,  --门票/娱乐项目 旺季挂牌价格儿童票
				  max(case when c.fs_ccno='000012' then c.fd_price end) fdFreePeakQp,   --门票/娱乐项目 旺季挂牌价格免票
                  max(case when c.fs_ccno='000013' then c.fd_price end) fdFullPeakTp,   --门票/娱乐项目 旺季团队价格全票
				  max(case when c.fs_ccno='000014' then c.fd_price end) fdHalfPeakTp,   --门票/娱乐项目 旺季团队价格半票
                  max(case when c.fs_ccno='000015' then c.fd_price end) fdChildPeakTp,  --门票/娱乐项目 旺季团队价格儿童票
				  max(case when c.fs_ccno='000016' then c.fd_price end) fdFreePeakTp    --门票/娱乐项目 旺季团队价格免票
             from tccprice c 
			where c.fs_restype = 'mp' 
			group by c.ft_startdate, c.ft_enddate, c.fs_resno) t, tticket t1 
    where t.fs_resno = t1.fs_no 
	order by t.fdFullLowQp;
 
