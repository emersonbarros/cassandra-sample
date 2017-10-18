package com.sample.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.groupon.uuid.UUID;

/**
 * Hello world!
 *
 */
public class StartApp {
  public static void main(String[] args) {
    PoolingOptions poolingOptions = new PoolingOptions();

    Cluster cluster =
        Cluster.builder().addContactPoints("172.17.0.2").withPoolingOptions(poolingOptions).build();

    Session session = cluster.connect();

    session.execute("DROP KEYSPACE ks");
    session.execute(
        "CREATE KEYSPACE IF NOT EXISTS ks WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };");
    session.execute("CREATE TYPE IF NOT EXISTS ks.address (street text);");
    session.execute(
        "CREATE TABLE IF NOT EXISTS ks.users (user_id uuid PRIMARY KEY, name text, address frozen<address>);");

    MappingManager manager = new MappingManager(session);

    Mapper<User> mapper = manager.mapper(User.class);

    UUID userId = new UUID();
    User u = new User(userId.toJavaUUID(), "John Doe", new Address("street"));
    mapper.save(u);

    ResultSet results = session.execute("SELECT * FROM ks.users;");
    Result<User> users = mapper.map(results);
    for (User us : users) {
      System.out.println("User : " + us.getName());
    }

    User uget = mapper.get(userId.toJavaUUID());

    mapper.delete(uget.getId());

    session.close();
  }
}
