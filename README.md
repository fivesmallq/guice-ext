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
if you don't want to bind class use ``@NoBind``.

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
3.``@GuiceModule``
````
@GuiceModule
public class ExampleModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ExampleDAO.class).to(ExampleDAOImpl.class);
		//or other things
	}
}
````
4.init 
````
install(new AutoBindModule());
install(new AutoInstallModule());
````

