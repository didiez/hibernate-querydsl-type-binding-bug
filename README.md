# QueryDSL type binding bug

Sample application to reproduce a bug when using QueryDSL to query an entity with custom types used in boolean fields.

## Description

Given an `JPA` entity with two boolean fields annotated, one of them annotated with a custom type `@Type`, when querying by those two fields providing the same boolean value (`true, true` or `false, false`) the generated query ends up using `?1` for both arguments (as they are considered a constant).

The generated query is
```sql
select user
from User user
where user.active = ?1 and user.admin = ?1  -- note the two arguments as ?1
```
when the expected would be
```sql
select user
from User user
where user.active = ?1 and user.admin = ?2
```

## Steps to reproduce

Run the tests with:
```
./mvnw test
```

By default the are some loggers to `DEBUG` and `TRACE` to clarify the issue.
