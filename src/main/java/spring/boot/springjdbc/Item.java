package spring.boot.springjdbc;

// import org.springframework.data.annotation.Id;

/**
 * This describes (among others) which H2 DB type is mapped to which java type.
 * http://www.h2database.com/html/datatypes.html
 */

// @Entity
public class Item
{
//  @Id : won't recognize this : org.springframework.data.annotation.Id;
//  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;

  public Item( Integer iniId, String iniValue)
  {
    id = iniId;
    name = iniValue;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer iniId)
  {
    id = iniId;
  }

  public String getName()
  {
    return name;
  }

  public void setValue(String iniName)
  {
    name = iniName;
  }

  @Override
  public String toString()
  {
    return "Item{ id=" + id + ", name='" + name + "'}";
  }
}