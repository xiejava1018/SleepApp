CREATE TABLE IF NOT EXISTS `SLEEPAPP_VERSION` (
  `VERSION` tinyint(5) NOT NULL,
  `VERSION_NAME` varchar(100) NOT NULL,
  `VERSION_URL` varchar(2000) NOT NULL,
  `VERSION_DESC` varchar(500) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=gbk;