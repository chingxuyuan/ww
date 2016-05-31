var ioc = {
    dao : {
        type : "org.nutz.dao.impl.NutDao",
        args : [{refer:"dataSource"}]
    },
    dataSource : {
        type : "org.nutz.dao.impl.SimpleDataSource",
        fields : {
            jdbcUrl : "jdbc:mysql://127.0.0.1:3306/advert",
            username : "root",
            password : "root"
        }
    }
}
