package spring.boot.springjdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

// @Configuration
// @EnableJpaRepositories(basePackages = { "spring.boot.springjdbc"})
// @EnableTransactionManagement
// @RunWith(SpringRunner.class) JUnit4-et akarja a classpath-hoz adni ...
@SpringBootTest( classes = SpringjdbcApplication.class)
@Sql({"/create_db.sql", "/insert_data.sql"}) // -> this one will actually execute the scripts
public class OrderServiceTest
{
  @Autowired
  private OrdersRepository ordersRepository;

  @Bean(destroyMethod = "shutdown")
  public EmbeddedDatabase dataSource() // interface EmbeddedDatabase extends DataSource
  {
    return new EmbeddedDatabaseBuilder().setType( EmbeddedDatabaseType.H2).
                                         addScript( "db-schema.sql").
                                         addScript( "db-test-data.sql").
                                         build();
  }

  @Bean
  @Profile("test")
  public DataSource dataSource2()
  {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName( "org.h2.Driver");
    dataSource.setUrl( "jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
    dataSource.setUsername( "sa");
    dataSource.setPassword( "sa");

    return dataSource;
  }

  @Test
  public void testBasics()
  {
    List<Item> allItems = ordersRepository.getAllItemsFieldNamesObject();

    System.out.println( "allItems : " + Arrays.toString( allItems.toArray()));
  }

  @Test
  public void testUpdate()
  {
    ordersRepository.updateItem( 1, "newIem1");

    List<Item> allItems = ordersRepository.getAllItemsFieldNamesObject();

    System.out.println( "allItems : " + Arrays.toString( allItems.toArray()));
  }

  @Test
  public void testInsert()
  {
    ordersRepository.addItem( 4, "ItemName4");

    List<Item> allItems = ordersRepository.getAllItemsFieldNamesObject();

    System.out.println( "allItems : " + Arrays.toString( allItems.toArray()));
  }

  @Test
  public void testSimpleJdbcInsert()
  {
    ordersRepository.simpleJdbcInsert( new Item( 0, "ItemName4"));

    List<Item> allItems = ordersRepository.getAllItemsFieldNamesObject();

    System.out.println( "allItems : " + Arrays.toString( allItems.toArray()));
  }

  @Test
  public void testGetItemNamed()
  {
    Item item = ordersRepository.getItemNamed( 3);

    System.out.println( "testGetItemNamed() : item=" + item);

    List<Item> allItems = ordersRepository.getAllItemsFieldNamesObject();

    System.out.println( "allItems : " + Arrays.toString( allItems.toArray()));
  }
/*
  @Autowired
  private EmployeeRepository employeeRepository;

  @Test
  @Sql(scripts = {"/import_senior_employees.sql"}, config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
  public void testLoadDataForTestCase()
   {
    assertEquals(5, employeeRepository.findAll().size());
  }
*/
}
