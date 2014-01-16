guice-ext
=========
a guice ext for auto bind and install module

=========
1.``@AutoBind`` , ``@Named``
````
@AutoBind
@Named("mysql")
public class MysqlBaseDAOImpl implements BaseDAO{
	....
}


@AutoBind
@Named("oracle")
public class OracleBaseDAOImpl implements BaseDAO{
	....
}


````

2.inject use
````
public class BaseServiceImpl implements BaseService{
	@Inject
	@Named("mysql")
	private BaseDAO mysqlDAO;
	
	@Inject
	@Named("oracle")
	private BaseDAO oracleDAO;
	
	public void test(){
		mysqlDAO.test();
		oracleDAO.test();
	}
}
````
3.init 
````
install(new AutoBindModule());
install(new AutoInstallModule());
````

