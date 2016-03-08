CREATE OR REPLACE VIEW view_resource AS
SELECT fs_no,
       fs_name,
       'mp' AS restype
  FROM tticket
UNION
SELECT fs_no,
       fs_name,
       'ct' AS restype
  FROM trestaurant
UNION
SELECT fs_no,
       fs_name,
       'yl'
  FROM tentertainment
UNION
SELECT fs_roomno AS fs_no,
       fs_name,
       'bg' AS restype
  FROM troom
UNION
SELECT fs_no,
       fs_name,
       'gw' AS restype
  FROM tshop;
  
exit;