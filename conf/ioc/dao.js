var ioc = {
    dao : {
        type : "org.nutz.dao.impl.NutDao",
        args : [{refer:"dataSource"}]
    },
    dataSource : {
        type : "org.nutz.dao.impl.SimpleDataSource",
        fields : {
            jdbcUrl : "jdbc:mysql://127.0.0.1:3306/woyaofa?useUnicode=true&characterEncoding=UTF-8",
            username : "ww",
            password : "123"
        }
    }
}
