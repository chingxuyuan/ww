
package com.woyaofa;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/", "*anno", "com.woyaofa", "*tx"})
@SetupBy(MainSetup.class)
@Modules(scanPackage = true)
public class MainModule {

}
