create keyspace if not exists plecko with replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};
create table if not exists plecko.user(email text, name text, primary key (email));