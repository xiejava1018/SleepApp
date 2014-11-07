<?php
require_once("./db.class.php");
//用户业务实现类
class RpVersionBiz{
	//查询最新的版本
	public static function getNewVersion()
	{
		$db = new DB();
  	$sqlstr="SELECT * FROM `SLEEPAPP_VERSION` ORDER BY VERSION DESC LIMIT 1";
    @$data = $db->getObjListBySql($sqlstr);
    if(count($data)!=0) return $data;
    else return null;
	}
}    
?>