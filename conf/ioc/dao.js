var ioc = {
    dao : {
        type : "org.nutz.dao.impl.NutDao",
        args : [{refer:"dataSource"}]
    },
    dataSource : {
        type : "org.nutz.dao.impl.SimpleDataSource",
        fields : {
            jdbcUrl : "jdbc:mysql://localhost:3306/woyaofa?useUnicode=true&characterEncoding=UTF-8",
//            username : "root",
//            password : "zzton7978"	
            	 username : "adsdk",
                 password : "wwadsdk"
        }
    }
}
